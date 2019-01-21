package com.coo8.btoc.business.impl.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coo8.btoc.business.interfaces.resource.ResourceManager;
import com.coo8.btoc.config.ReloadableConfig;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;
import com.coo8.btoc.persistence.interfaces.items.IItemsDAO;
import com.coo8.btoc.persistence.interfaces.resource.ResourceDao;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.HotWordsUtil;
import com.coo8.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gcache.clients.gedis.RoundRobinJedisPool;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class ResourceManagerImpl implements ResourceManager {

	private ResourceDao resourceDao;
	private IItemsDAO itemsDao;
	private  static Logger logger = LoggerFactory.getLogger(ResourceManagerImpl.class);
	protected String KEYHEAD = ReloadableConfig.getProperty("redis_key", "/redis/json/");// ����Key��ͷ

	@Override
	public void createResource(Resource resource) {
		resourceDao.insertResource(resource);
	}

	@Override
	public void updateResource(Resource resource) {
		resourceDao.updateResource(resource);
	}

	@Override
	public void deleteResource(Integer id) {
		resourceDao.deleteResource(id);
	}

	@Override
	public Resource getResourceById(Integer id) {
		return resourceDao.getResourceById(id);
	}

	@Override
	public List<Resource> queryResource(Resource resource) {
		return resourceDao.queryResources(resource);
	}

	@Override
	public List<Resource> queryAllResource() {
		return queryResource(null);
	}

	@Override
	public void createResourceCatalog(ResourceCatalog resourceCatalog) {
		resourceDao.insertResourceCatalog(resourceCatalog);
	}

	@Override
	public List<ResourceCatalog> queryResourceCatalog(ResourceCatalog resourceCatalog) {
		resourceCatalog.setStatus(ResourceCatalog.ENABLED);
		return resourceDao.queryResourceCatalog(resourceCatalog);
	}

	@Override
	public void deleteResourceCatalog(ResourceCatalog resourceCatalog) {
		resourceCatalog.setStatus(ResourceCatalog.ENABLED);
		resourceDao.deleteResourceCatalog(resourceCatalog);
	}

	/**
	 * �ֹ�˾ɾ����Ʒλ�е���Ʒ��������д����
	 */
	@Override
	public void deleteAndUpdateCache(ResourceCatalog resourceCatalog) {
		List<ResourceCatalog> querylist = queryResourceCatalog(resourceCatalog);
		if (querylist.isEmpty()) {
			return;
		}
		String citys = querylist.get(0).getCityIds();// Ҫɾ���Ļ���ĳ���ID
		deleteResourceCatalog(resourceCatalog);// ɾ������Ʒ
		resourceCatalog.setCityIds(citys);// ɾ����Ʒʱ��д������Ҫ����cityid
		resetCacheByComp(resourceCatalog); // ����д����
	}

	/**
	 * �ܹ�˾��Ӳ�Ʒλ,num:��Ʒλ����
	 */
	@Override
	public void insertPositions(ResourceCatalog resourceCatalog, int num) {
		for (int i = 1; i <= num; i++) {
			resourceCatalog.setPosition(i);
			resourceDao.insertResourceCatalog(resourceCatalog);
		}
	}

	/**
	 * ͨ����̬ҳIDɾ���þ�̬ҳ�µ���Ʒλ�����������
	 */
	@Override
	public void deleteResourceCatalogByHtmlId(Integer htmlId) {
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setHtmlId(htmlId);
		resourceDao.deleteResourceCatalog(resourceCatalog);
		clearRedisByKey(KEYHEAD + htmlId.toString() + "_*");
	}

	/**
	 * �ֹ�˾�����Ʒ����д�뻺��
	 */
	@Override
	public void saveOrUpdateBranchComp(ResourceCatalog resourceCatalog) {
		Integer productid = resourceCatalog.getProductId();
		resourceCatalog.setProductId(null);
		int count = queryResourceCatalogCount(resourceCatalog);
		resourceCatalog.setProductId(productid);
		if (count <= 0) {
			createResourceCatalog(resourceCatalog);
		} else {
			updateResourceCatalog(resourceCatalog);
		}
		resetCacheByComp(resourceCatalog);// ���ֹ�˾�ڱ���̬ҳ�еı���Ļ�������д��
	}

	/**
	 * �ر�/�򿪲�Ʒλ
	 */
	@Override
	public void deleteBranchItems(ResourceCatalog resourceCatalog) {
		// ����Ĭ�ϲ�Ʒλ��״̬
		updateResourceCatalog(resourceCatalog);
		// ����رղ�Ʒλ������Ҫ���ò�Ʒλ�����зֹ�˾��Ʒȫ��ɾ�������ҽ��ֹ�˾�Ļ�����������
		if (resourceCatalog.getOpen() != null && resourceCatalog.getOpen().equals(ResourceCatalog.CLOSE)) {
			resourceCatalog.setCompId(null);
			resourceCatalog.setOpen(null);
			resourceCatalog.setType(ResourceCatalog.BRANCH_OFFICE_TYPE);
			// ���ò�Ʒλ�ķֹ�˾��resourcecatalog�����������ʱ��Ҫ
			List<ResourceCatalog> deleo = this.queryResourceCatalog(resourceCatalog);
			// ɾ���ò�Ʒλ�ķֹ�˾��resourcecatalog
			deleteResourceCatalog(resourceCatalog);
			for (int i = 0; i < deleo.size(); i++) {
				/**
				 * ���¼��ظò�Ʒλ��ÿ���ֹ�˾�Ļ���
				 */
				ResourceCatalog o = deleo.get(i);
				resetCacheByComp(o);
			}
		}
	}

	/**
	 * ����ĳ����Ʒ�Ļ�����Ϣ��������Ʒ��gift������ʱʹ��
	 */
	@Override
	public void updateProductRedis(Integer productId) {
		ResourceCatalog rc = new ResourceCatalog();
		rc.setProductId(productId);
		rc.setType(ResourceCatalog.BRANCH_OFFICE_TYPE);
		rc.setOpen(ResourceCatalog.OPEN);
		rc.setStatus(ResourceCatalog.ENABLED);
		// ��ѯ��Ʒλ�Ĳ�ƷΪ�ƶ�ID�ķֹ�˾��resourceCatalog
		List<ResourceCatalog> results = queryResourceCatalog(rc);
		for (int i = 0; i < results.size(); i++) {
			// ����ÿ����˾�Ļ���
			ResourceCatalog o = results.get(i);
			resetCacheByComp(o);
		}
	}

	/**
	 * дĳ���ֹ�˾��ĳ����̬ҳ��ĳ������Դ�µĻ��� ��û����Ʒʱ��������棨��ʱ��Ҫ����cityids��
	 * �����ʽ��/redis/jeson/��̬ҳID_����ԴID_����1.hmtl ��
	 * [{��Ʒ1����1:value,��Ʒ1����2:value},{��Ʒ2����1:value..}]
	 */
	public void resetCacheByComp(ResourceCatalog resourceCatalog) {
		ResourceCatalog search = initSearch(resourceCatalog);
		if (search == null) {
			throw new RuntimeException("��ѯ����Ķ���Ϊ��");
		}
		// ��ѯ�ù�˾�ڱ���̬ҳ�µ�ĳ�����е�������Ʒλ�ĸ���	
		int count = queryResourceCatalogCount(search);
		if (count > 0) {
			List<ResourceCatalog> resourceCatalogList = queryResourceCatalog(search);   
			if (resourceCatalogList.isEmpty()) {
				throw new RuntimeException("Ī������");
			}
			String value = getRedisValue(resourceCatalogList);
			String[] delekeys = getRedisKeys(resourceCatalogList.get(0));
			writeRedis(delekeys, value);
		} else {
			// �÷ֹ�˾�ڱ�����û��������Ʒʱ�������̬ҳ�±�����Դ����˾���������л���
			if (StringUtil.isNullorBlank(resourceCatalog.getCityIds())) {
				throw new RuntimeException("û�г���ID���޷��������");
			}
			String[] delekeys = getRedisKeys(resourceCatalog);
			clearRedisByKeys(delekeys);
		}
	}

	/**
	 * ��ʼ�����������ѯ����
	 * 
	 * @param resourceCatalog
	 * @return
	 */
	private ResourceCatalog initSearch(ResourceCatalog resourceCatalog) {
		if (resourceCatalog.getHtmlId() == null || resourceCatalog.getResourceId() == null
				|| StringUtil.isNullorBlank(resourceCatalog.getCompId())) {
			return null;
		}
		ResourceCatalog search = new ResourceCatalog();
		search.setHtmlId(resourceCatalog.getHtmlId());
		search.setResourceId(resourceCatalog.getResourceId());
		search.setCompId(resourceCatalog.getCompId());
		search.setType(ResourceCatalog.BRANCH_OFFICE_TYPE);
		search.setStatus(ResourceCatalog.ENABLED);
		search.setOpen(ResourceCatalog.OPEN);
		return search;
	}

	/**
	 * ͨ����̬ҳID������ԴID������ID���ö��ŷָ�������ȡ������key������
	 * 
	 * @param htmlId
	 * @param resourceId
	 * @param cityids
	 * @return /redis/json/htmlId_resourceId_cityid.html
	 */
	private String[] getRedisKeys(ResourceCatalog resourceCatalog) {
		Integer htmlId = resourceCatalog.getHtmlId();
		Integer resourceId = resourceCatalog.getResourceId();
		String[] citystr = resourceCatalog.getCityIds().split(",");
		String key = KEYHEAD + htmlId.toString() + "_" + resourceId.toString() + "_";
		String[] delekeys = new String[citystr.length];
		for (int i = 0; i < citystr.length; i++) {
			if (!StringUtil.isNullorBlank(citystr[i])) {
				delekeys[i] = key + citystr[i] + ".html";
			}
		}
		return delekeys;
	}

	/**
	 * ͨ����Ʒλlist���õ�д�뻺���value
	 * 
	 * @param list
	 * @return
	 */
	private String getRedisValue(List<ResourceCatalog> list) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (ResourceCatalog rcInBlock : list) {
			BtoCItems item = itemsDao.findAllItemsById(rcInBlock.getProductId());
			Map<String, Object> map = createMap(rcInBlock.getPosition(), item);
			mapList.add(map);
		}
		JSONArray jsonArray = JSONArray.fromObject(mapList);
		String value = String.valueOf(jsonArray);
		return value;
	}

	/**
	 * ��û���value����Ҫ������ֵ��map
	 * 
	 * @param position
	 * @param item
	 * @return
	 */
	private Map<String, Object> createMap(Integer position, BtoCItems item) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("position", position);
		map.put("id", item.getId());
		map.put("itemid", item.getItemid());
		map.put("productname", item.getProductname());
		map.put("gift", item.getGift());
		map.put("status", item.getStatus());
		map.put("orininaleprice", item.getOriginalprice());
		map.put("mainimg3", item.getMainimg3());
		map.put("showpic", item.getShowpic());

		return map;
	}

	/**
	 * д����
	 * 
	 * @param keys
	 * @param value
	 * @throws RuntimeException
	 */
	private void writeRedis(String[] keys, String value) throws RuntimeException {
		Jedis jedis = null;
		try {
			jedis = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_RESOURCE_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
			for (int i = 0; i < keys.length; i++) {
				jedis.set(keys[i], value);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally{
			 if (jedis != null)  
	                jedis.close();  
		}
		
	}

	/**
	 * ͨ������Key������棨֧��*��
	 * 
	 * @param key
	 * @throws RuntimeException
	 */
	private void clearRedisByKey(String key) throws RuntimeException {
		Jedis jedis = null;
		try {
			jedis = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_RESOURCE_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
			Set<String> set = jedis.keys(key);
			if (set.isEmpty()) {
				return;
			}
			String[] keys = new String[set.size()];
			int i = 0;
			for (String str : set) {
				keys[i] = str;
				i++;
			}
			jedis.del(keys);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			 if (jedis != null)  
	                jedis.close();  
		}
	}

	/**
	 * ͨ��key�������������
	 * 
	 * @param keys
	 * @throws RuntimeException
	 */
	private void clearRedisByKeys(String[] keys) throws RuntimeException {
		Jedis jedis = null;
		try {
			jedis = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_RESOURCE_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
			jedis.del(keys);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			 if (jedis != null)  
	                jedis.close();  
		}
	}

	private int queryResourceCatalogCount(ResourceCatalog resourceCatalog) {

		return resourceDao.queryResourceCatalogCount(resourceCatalog);
	}

	@Override
	public void updateResourceCatalog(ResourceCatalog resourceCatalog) {
		resourceDao.updateResourceCatalog(resourceCatalog);
	}

	@Override
	public void updateMoveUpPosition(ResourceCatalog resourceCatalog) throws Exception {
		int id = resourceCatalog.getId();
		int curPos = resourceCatalog.getPosition();
		if (curPos <= 1) {
			return;
		}

		// �ȸ�����ǰһ����¼
		resourceCatalog.setId(null);
		resourceCatalog.setPosition(curPos - 1);
		resourceCatalog.setPositionChange(curPos);
		updateResourceCatalog(resourceCatalog);

		// �ٸ��±�����¼
		resourceCatalog.setId(id);
		resourceCatalog.setPosition(null);
		resourceCatalog.setPositionChange(curPos - 1);
		updateResourceCatalog(resourceCatalog);

	}

	@Override
	public void updateMoveDownPosition(ResourceCatalog resourceCatalog, Integer dataNum) throws Exception {

		int id = resourceCatalog.getId();
		int curPos = resourceCatalog.getPosition();
		if (curPos >= dataNum) {
			return;
		}

		// �ȸ������һ����¼
		resourceCatalog.setId(null);
		resourceCatalog.setPosition(curPos + 1);
		resourceCatalog.setPositionChange(curPos);
		updateResourceCatalog(resourceCatalog);

		// �ٸ��±�����¼
		resourceCatalog.setId(id);
		resourceCatalog.setPosition(null);
		resourceCatalog.setPositionChange(curPos + 1);
		updateResourceCatalog(resourceCatalog);

	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setItemsDao(IItemsDAO itemsDao) {
		this.itemsDao = itemsDao;
	}

}
