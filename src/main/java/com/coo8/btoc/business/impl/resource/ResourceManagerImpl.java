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
	protected String KEYHEAD = ReloadableConfig.getProperty("redis_key", "/redis/json/");// 缓存Key的头

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
	 * 分公司删除产品位中的商品，并重新写缓存
	 */
	@Override
	public void deleteAndUpdateCache(ResourceCatalog resourceCatalog) {
		List<ResourceCatalog> querylist = queryResourceCatalog(resourceCatalog);
		if (querylist.isEmpty()) {
			return;
		}
		String citys = querylist.get(0).getCityIds();// 要删除的缓存的城市ID
		deleteResourceCatalog(resourceCatalog);// 删除该商品
		resourceCatalog.setCityIds(citys);// 删除商品时，写缓存需要传入cityid
		resetCacheByComp(resourceCatalog); // 重新写缓存
	}

	/**
	 * 总公司添加产品位,num:产品位个数
	 */
	@Override
	public void insertPositions(ResourceCatalog resourceCatalog, int num) {
		for (int i = 1; i <= num; i++) {
			resourceCatalog.setPosition(i);
			resourceDao.insertResourceCatalog(resourceCatalog);
		}
	}

	/**
	 * 通过静态页ID删除该静态页下的商品位，并清楚缓存
	 */
	@Override
	public void deleteResourceCatalogByHtmlId(Integer htmlId) {
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setHtmlId(htmlId);
		resourceDao.deleteResourceCatalog(resourceCatalog);
		clearRedisByKey(KEYHEAD + htmlId.toString() + "_*");
	}

	/**
	 * 分公司添加商品，并写入缓存
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
		resetCacheByComp(resourceCatalog);// 将分公司在本静态页中的本块的缓存重新写入
	}

	/**
	 * 关闭/打开产品位
	 */
	@Override
	public void deleteBranchItems(ResourceCatalog resourceCatalog) {
		// 更新默认产品位的状态
		updateResourceCatalog(resourceCatalog);
		// 如果关闭产品位，则需要将该产品位的所有分公司商品全部删除，并且将分公司的缓存重新整理
		if (resourceCatalog.getOpen() != null && resourceCatalog.getOpen().equals(ResourceCatalog.CLOSE)) {
			resourceCatalog.setCompId(null);
			resourceCatalog.setOpen(null);
			resourceCatalog.setType(ResourceCatalog.BRANCH_OFFICE_TYPE);
			// 将该产品位的分公司的resourcecatalog查出，清理缓存时需要
			List<ResourceCatalog> deleo = this.queryResourceCatalog(resourceCatalog);
			// 删除该产品位的分公司的resourcecatalog
			deleteResourceCatalog(resourceCatalog);
			for (int i = 0; i < deleo.size(); i++) {
				/**
				 * 重新加载该产品位的每个分公司的缓存
				 */
				ResourceCatalog o = deleo.get(i);
				resetCacheByComp(o);
			}
		}
	}

	/**
	 * 更改某个商品的缓存信息，更改商品的gift等属性时使用
	 */
	@Override
	public void updateProductRedis(Integer productId) {
		ResourceCatalog rc = new ResourceCatalog();
		rc.setProductId(productId);
		rc.setType(ResourceCatalog.BRANCH_OFFICE_TYPE);
		rc.setOpen(ResourceCatalog.OPEN);
		rc.setStatus(ResourceCatalog.ENABLED);
		// 查询产品位的产品为制定ID的分公司的resourceCatalog
		List<ResourceCatalog> results = queryResourceCatalog(rc);
		for (int i = 0; i < results.size(); i++) {
			// 清理每个公司的缓存
			ResourceCatalog o = results.get(i);
			resetCacheByComp(o);
		}
	}

	/**
	 * 写某个分公司在某个静态页和某个数据源下的缓存 当没有商品时，清除缓存（这时需要传入cityids）
	 * 缓存格式，/redis/jeson/静态页ID_数据源ID_城市1.hmtl ：
	 * [{产品1属性1:value,产品1属性2:value},{产品2属性1:value..}]
	 */
	public void resetCacheByComp(ResourceCatalog resourceCatalog) {
		ResourceCatalog search = initSearch(resourceCatalog);
		if (search == null) {
			throw new RuntimeException("查询缓存的对象为空");
		}
		// 查询该公司在本静态页下的某个块中的所有商品位的个数	
		int count = queryResourceCatalogCount(search);
		if (count > 0) {
			List<ResourceCatalog> resourceCatalogList = queryResourceCatalog(search);   
			if (resourceCatalogList.isEmpty()) {
				throw new RuntimeException("莫名其妙");
			}
			String value = getRedisValue(resourceCatalogList);
			String[] delekeys = getRedisKeys(resourceCatalogList.get(0));
			writeRedis(delekeys, value);
		} else {
			// 该分公司在本块中没有其他商品时，清除静态页下本数据源本公司城市下所有缓存
			if (StringUtil.isNullorBlank(resourceCatalog.getCityIds())) {
				throw new RuntimeException("没有城市ID，无法清除缓存");
			}
			String[] delekeys = getRedisKeys(resourceCatalog);
			clearRedisByKeys(delekeys);
		}
	}

	/**
	 * 初始化缓存所需查询对象
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
	 * 通过静态页ID，数据源ID，城市ID（用逗号分隔），获取到缓存key的数组
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
	 * 通过产品位list，得到写入缓存的value
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
	 * 获得缓存value里需要的属性值的map
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
	 * 写缓存
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
	 * 通过缓存Key清除缓存（支持*）
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
	 * 通过key的数组清楚缓存
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

		// 先更新其前一条记录
		resourceCatalog.setId(null);
		resourceCatalog.setPosition(curPos - 1);
		resourceCatalog.setPositionChange(curPos);
		updateResourceCatalog(resourceCatalog);

		// 再更新本条记录
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

		// 先更新其后一条记录
		resourceCatalog.setId(null);
		resourceCatalog.setPosition(curPos + 1);
		resourceCatalog.setPositionChange(curPos);
		updateResourceCatalog(resourceCatalog);

		// 再更新本条记录
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
