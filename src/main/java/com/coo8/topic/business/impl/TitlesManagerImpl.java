package com.coo8.topic.business.impl;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.topic.model.*;
import org.gome.search.dubbo.idl.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.model.queue.ProductQueue;
import com.coo8.btoc.persistence.interfaces.queue.QueueDao;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.StringUtil;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.ConstantUtil;
import com.coo8.common.utils.HttpClientUtil;
import com.coo8.topic.business.interfaces.INewsManager;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.persistence.interfaces.INewsDAO;
import com.coo8.topic.persistence.interfaces.ITitlesDAO;
import com.gome.frontSe.comm.FSGoods;
import com.gome.stage.interfaces.item.IProductInfoService;

import antlr.StringUtils;
import net.sf.json.JSONObject;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public class TitlesManagerImpl implements ITitlesManager {

	private  static Logger logger = LoggerFactory.getLogger(ReloadableConfig.class);
	private ITitlesDAO titlesDAO;
	private DubboService dubboService;
	private IProductInfoService productInfoServiceItem;
	private String SITE = "gome";
	
	/**
	 * 新闻管理
	 */
	private INewsManager newsManager;
	
	/**
	 * 队列操作
	 */
	private QueueDao queueDao;
	
	/**
	 * 获取新闻列表
	 */
	private INewsDAO newsDao;

	public void setTitlesDAO(ITitlesDAO dao) {
		this.titlesDAO = dao;
	}

	public IProductInfoService getProductInfoServiceItem() {
		return productInfoServiceItem;
	}

	public void setProductInfoServiceItem(IProductInfoService productInfoServiceItem) {
		this.productInfoServiceItem = productInfoServiceItem;
	}

	public INewsManager getNewsManager() {
		return newsManager;
	}

	public void setNewsManager(INewsManager newsManager) {
		this.newsManager = newsManager;
	}

	public QueueDao getQueueDao() {
		return queueDao;
	}

	public void setQueueDao(QueueDao queueDao) {
		this.queueDao = queueDao;
	}

	@Transactional(readOnly = true)
	public Titles getById(java.lang.Integer id) {
		return titlesDAO.getById(id);
	}

	@Transactional
	public void deleteById(java.lang.Integer id) {
		titlesDAO.deleteById(id);
	}
	

	public INewsDAO getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDAO newsDao) {
		this.newsDao = newsDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coo8.topic.business.interfaces.ITitlesManager#deletePublish()
	 */
	@Override
	public void deletePublish() {
		titlesDAO.deleteBlockQueue();
		titlesDAO.deleteProductQueue();
	}

	@Override
	public void changePath() {
		titlesDAO.changePath();
	}

	@Transactional
	public String save(Titles entity) {
		if (entity.getId() != null && !"".equals(String.valueOf(entity.getId()))) {
			titlesDAO.update(entity);
			return entity.getId().toString();
		} else {
			return titlesDAO.save(entity);
		}
	}

	@Transactional
	public void update(Titles entity) {
		titlesDAO.update(entity);
	}

	@Transactional(readOnly = true)
	public PaginatedList<Titles> findByMap(Map<String, Object> search) {
		return titlesDAO.findByMap(search);
	}

	@Transactional(readOnly = true)
	public PaginatedList<Titles> findLikeByMap(Map<String, Object> search) {
		return titlesDAO.findLikeByMap(search);
	}

	@Transactional(readOnly = true)
	public List<Titles> findListByMap(Map<String, Object> search) {
		return titlesDAO.findListByMap(search);
	}

	@Transactional(readOnly = true)
	public List<Titles> findListLikeByMap(Map<String, Object> search) {
		return titlesDAO.findListLikeByMap(search);
	}

	@Override
	public String checkItemByATG(String id) {
		if (null == id || "".equals(id)) {
			return "";
		} else {
			return getATGProdDescById(id);
		}
	}

	/**
	 * 閫氳繃ATG 鏍规嵁鍟嗗搧ID鏌ヨ鍟嗗搧鎻忚堪淇℃伅
	 * 
	 * @param id
	 * @return
	 */
	private String getATGProdDescById(String id) {
		String retStr = "";
		if (null != id && !"".equals(id)) {
			try {
				String atgJspPageUrl = ReloadableConfig.getProperty("atgJspPageUrl",
						"http://www.gome.com.cn/coo8/data/productInterface.jsp?productId=");
				atgJspPageUrl += id;
				String jsonStr = HttpClientUtil.readHttpPage(atgJspPageUrl);
				JSONObject demoJson = JSONObject.fromObject(jsonStr);

				String url = "" + demoJson.get("descritpionUrl");

				String desc = "";

				if (null != url && !"".equals(url)) {
					desc = HttpClientUtil.readHttpPage(url.trim() + "&callback=jianjie&_=1362972404279");
				}

				if (!"".equals(desc)) {
					retStr = desc;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				retStr = "";
			}
		}
		return retStr;
	}

	@Override
	public String checkItemName(String id) {
		String retStr = "";
		if (org.apache.commons.lang.StringUtils.isNotBlank(id)) {
			try {
				/**
				 * String atgJspPageUrl = ReloadableConfig
				 * .getProperty("atgJspPageUrl",
				 * "http://www.gome.com.cn/coo8/data/productInterface.jsp?productId="
				 * ); atgJspPageUrl += id; String jsonStr =
				 * HttpClientUtil.readHttpPage(atgJspPageUrl); JSONObject
				 * demoJson = JSONObject.fromObject(jsonStr); String productname
				 * = String.valueOf(demoJson.get("productname"));
				 */

				logger.info("TitlesManagerImpl_checkItemName_start");

				FSGoods goods = productInfoServiceItem.getFSGoods(id, null);
				String productname = (goods != null) ? goods.getName() : "";

				logger.info("TitlesManagerImpl_checkItemName: id=" + id + ", productname=" + productname);

				if (null != productname && !"".equals(productname.trim()) && !"null".equalsIgnoreCase(productname.trim())) { 					
					   retStr = productname;
					   if(productname.indexOf(" ") != -1){
						   retStr = productname.replace(" ", "");}
					   }
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				retStr = "";
			}
		}
		return retStr;
	}

	@Override
	public List<Integer> findGoodsListByInt(int search) {
		return null;
	}

	public ITitlesDAO getTitlesDAO() {
		return titlesDAO;
	}

	@Override
	public String saveDrops(List<GoodsDrops> drops) {
		return titlesDAO.saveDrops(drops);
	}

	@Override
	public PaginatedList<Titles> findDropsListByMap(Map<String, Object> search) {
		return titlesDAO.findDropsListByMap(search);
	}

	@Override
	public int deleteAllDrops() {
		return titlesDAO.deleteAllDrops();
	}

	@Override
	public List<Titles> findAllTitlesList() {
		return titlesDAO.findAllTitlesList();
	}

	@Override
	public int deleteDropsByObj(GoodsDrops drops) {
		return titlesDAO.deleteDropsByObj(drops);
	}

	/**
	 * 鏍规嵁鍟嗗搧ID鑾峰緱鍟嗗搧鎻忚堪淇℃伅 url
	 */
	@Override
	public String getATGItemDescByGoodId(String goodsId) {
		String retStr = "";
		if (null != goodsId && !"".equals(goodsId)) {
			try {
				/**
				 * String atgJspPageUrl = ReloadableConfig
				 * .getProperty("atgJspPageUrl",
				 * "http://www.gome.com.cn/coo8/data/productInterface.jsp?productId="
				 * ); atgJspPageUrl += goodsId; String jsonStr =
				 * HttpClientUtil.readHttpPage(atgJspPageUrl); JSONObject
				 * demoJson = JSONObject.fromObject(jsonStr);
				 * 
				 * retStr = String.valueOf(demoJson.get("descritpionUrl"));
				 */

				logger.info("TitlesManagerImpl_getATGItemDescByGoodId_start");

				FSGoods goods = productInfoServiceItem.getFSGoods(goodsId, null);
				retStr = (goods != null) ? goods.getDescription() : "";

				logger.info("TitlesManagerImpl_getATGItemDescByGoodId: goodsId=" + goodsId + ", desc=" + retStr);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				retStr = "";
			}
		}
		return retStr;
	}

	/**
	 * 鏍规嵁鍟嗗搧ID鏌ユ壘鍟嗗搧涓撻
	 */
	@Override
	public List<Titles> findListByGoodId(String goodsId) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("goodsId", goodsId);
		search.put("sortColumns", "create_Time desc");
		return titlesDAO.findListByMap(search);
	}

	@Override
	public String insertTitleIndex(TitleIndex entity) {
		return titlesDAO.insertTitleIndex(entity);
	}

	@Override
	public PaginatedList<TitleIndex> findTitleIndexByMap(Map<String, Object> search) {
		return titlesDAO.findTitleIndexByMap(search);
	}

	@Override
	public TitleIndex getTitleIndexById(int id) {
		return titlesDAO.getTitleIndexById(id);
	}

	@Override
	public TitleIndex getTitleIndexByTitleId(int titleId){
		return titlesDAO.getTitleIndexByTitleId(titleId);
	}

	@Override
	public int deleteTitleIndex(int id) {
		return titlesDAO.deleteTitleIndex(id);
	}

	@Override
	public int updateTitleIndex(TitleIndex entity) {
		return titlesDAO.updateTitleIndex(entity);
	}

	@Override
	public int isAddRepeat(Map<String, Object> search) {
		return titlesDAO.isAddRepeat(search);
	}

	@Override
	public int publishTitle(int id, String site) {
		int result = 1;
		if (null != site) {
			if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
				result = titlesDAO.publishCoo8Title(id);
			} else if (site.equals(ConstantUtil.SITE_FLAG_GOME)) {
				result = titlesDAO.publishGomeTitle(id);
			}
		}
		return result;
	}

	@Override
	public int publishTitleTest(int id, String site) {
		int result = 1;
		if (null != site) {
			Titles t = titlesDAO.getById(id);
			if (null != t) {
				if (null == t.getPaths() || "".equals(t.getPaths().trim())) {
					t.setPaths(t.getId().toString());
				}
				titlesDAO.update(t);
			}
			if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
				result = titlesDAO.publishCoo8TitleTest(id);
			} else if (site.equals(ConstantUtil.SITE_FLAG_GOME)) {
				result = titlesDAO.publishGomeTitleTest(id);
			}
		}
		return result;
	}

	@Override
	public int publicTitleHomePage(String site) {
		return titlesDAO.publicTitleHomePage(site);
	}

	@Override
	public int publishAllTitleListPage(String site) {
		return titlesDAO.publishAllTitleListPage(site);
	}

	@Override
	public int publishAllNewsListPage(String site) {
		return titlesDAO.publishAllNewsListPage(site);
	}

	@Override
	public int getMaxId(String site) {
		return titlesDAO.getMaxId(site);
	}

	@Override
	public int publicWapTitleHomePage() {
		return titlesDAO.publicWapTitleHomePage();
	}

	@Override
	public int publishWapTitle(int id) {
		/**
		 * 执行发布文章
		 */
		int result = titlesDAO.publishWapGomeTitle(id);
		if(result>-1){
			//获取该专题所有文章---然后处理每个文章的标签titleId=id
			Map<String,Object> search = new HashMap<String,Object>();
			search.put("titleId", id);
			List<News> news = this.newsDao.findListByMap(search);
			if(news!=null && !news.isEmpty()){
				for(News n:news){
					//发布文章标签
					List<LabelArtRel> tags = newsManager.getNewsTagsById(n.getId());
					if(tags!=null && !tags.isEmpty()){
						for(LabelArtRel item: tags){
							ProductQueue entity = new ProductQueue();
							entity.setInputDate(new Date());
							entity.setPriority(0);
							entity.setRfid(item.getKeywords());
							entity.setRtype(38);
							entity.setTemplateId(100);
							entity.setStatus(0);
							entity.setSite("gome");
							entity.setType(0);
							//增加生成队列
							queueDao.insertProductQueue(entity);
						}
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap) {
		return titlesDAO.listLog(paramMap);
	}

	@Override
	public List<ErrorTitles> listDownLog(Map<String, Object> paramMap) {
		return titlesDAO.listDownLog(paramMap);
	}

	@Override
	public void addErrorWords(List<ErrorTitles> errorTitles) {		
		titlesDAO.addErrorWords(errorTitles);
	}

	@Override
	public void addLog(ImportLog importLog) {
		titlesDAO.addLog(importLog);
	}

	@Override
	public List<Titles> getByTitleName(String titleName) {
		return titlesDAO.getByTitleName(titleName);
	}

	public DubboService getDubboService() {
		return dubboService;
	}

	public void setDubboService(DubboService dubboService) {
		this.dubboService = dubboService;
	}

	@Override
	public String insertTitleIndex(Titles title) {
		String pinyin = Chinese2PinyinUtil.parseChinese(title.getTitle());
		String cindex = String.valueOf(pinyin.charAt(0)).toUpperCase();
		TitleIndex index = new TitleIndex();
		index.setTitleId(title.getId());
		index.setPinyin(pinyin);
		index.setCindex(cindex);
		index.setOperator("system");
		index.setSource(1);// 后台处理
		index.setPriority(0);// 优先级最低
		index.setSite(SITE);// 设置站点
		index.setStatus(1);// 有效
		return titlesDAO.insertTitleIndex(index);
	}


	/**
	 * 判断专题名是否有效，专题名下商品少于 2 视作无效，不予添加。
	 * @param titleName
	 * @return
	 */
	@Override
	public List<Map<String,Object>> checkIsInvalid(String titleName) {
		List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			com.alibaba.fastjson.JSONObject jsonObject = null;
			com.alibaba.fastjson.JSONObject productSearch = this.getDefaultSearchJSONObject(titleName);
			jsonObject = dubboService.productSearch(productSearch);
			com.alibaba.fastjson.JSONObject header = jsonObject.getJSONObject("header");
			String rawquestion = header.getString("rawquestion");
			com.alibaba.fastjson.JSONObject content = jsonObject.getJSONObject("content");
			com.alibaba.fastjson.JSONObject pageBar = content.getJSONObject("pageBar");
			Integer totalCount = pageBar.getInteger("totalCount");
			logger.info(" 专题下商品总数为 ："+totalCount);
			if (totalCount>6){
				com.alibaba.fastjson.JSONObject prodInfo = content.getJSONObject("prodInfo");
				JSONArray productArray = prodInfo.getJSONArray("products");
				List<Object> subList = productArray.subList(0, 6);
				for (Object object : subList) {
					Map<String,Object> dataMap = new HashMap<String, Object>();
					com.alibaba.fastjson.JSONObject js = (com.alibaba.fastjson.JSONObject) object;
					String skuId = js.getString("skuId");
					String productId = js.getString("pId");
					String productName = js.getString("alt");

					dataMap.put("skuId",skuId);
					dataMap.put("productId",productId);
					dataMap.put("productName",productName);
					dataList.add(dataMap);
				}
				return dataList;
			}
			if (totalCount>0 && totalCount<=6){
				com.alibaba.fastjson.JSONObject prodInfo = content.getJSONObject("prodInfo");
				JSONArray productArray = prodInfo.getJSONArray("products");
				for (int i = 0; i <productArray.size() ; i++) {
					Map<String,Object> dataMap = new HashMap<String, Object>();
					com.alibaba.fastjson.JSONObject js = (com.alibaba.fastjson.JSONObject) productArray.get(i);
					String skuId = js.getString("skuId");
					String productId = js.getString("pId");
					String productName = js.getString("alt");
					dataMap.put("skuId",skuId);
					dataMap.put("productId",productId);
					dataMap.put("productName",productName);
					dataList.add(dataMap);
				}
				return dataList;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public com.alibaba.fastjson.JSONObject getDefaultSearchJSONObject(String titleName) {
		com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
		jsonObject.put("from","robot");
		jsonObject.put("question",titleName);
		jsonObject.put("catId","0");
		jsonObject.put("regionId","11010200");
		jsonObject.put("market","10");
		jsonObject.put("facets","0");
		jsonObject.put("productTag","0");
		jsonObject.put("sort","10");
		jsonObject.put("priceTag","0");
		jsonObject.put("pageSize","48");
		jsonObject.put("pageNumber","1");
		jsonObject.put("instock","1");
		jsonObject.put("sale","0");
		return jsonObject;
	}

	@Override
	public int getTitleInvalidInDateCount(String createDate){
		return titlesDAO.getTitleInvalidInDateCount(createDate);
	}
	@Override
	public List<TitleInvalid> getTitleInvalidInDate(String createDate){
		return titlesDAO.getTitleInvalidInDate(createDate);
	}

	@Override
	public List<String> getTitleInvalidDate(){
		return titlesDAO.getTitleInvalidDate();
	}

}
