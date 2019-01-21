package com.gome.productwords.manager.impl;


import com.alibaba.fastjson.JSONObject;
import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.productwords.dao.infer.ProductWordsCardDAO;
import com.gome.productwords.manager.infer.ProductWordsManager;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.productwords.model.ProductWordsCardImportLog;
import com.gome.productwords.model.ProductWordsErrorCard;
import org.gome.search.dubbo.idl.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wangkeqiang
 * @version 1.0
 * @since 1.0
 */

public class ProductWordsManagerImpl implements ProductWordsManager {

	private  static Logger logger = LoggerFactory.getLogger(ProductWordsManagerImpl.class);
	private ProductWordsCardDAO productWordsCardDAO;
	//查询商品词的服务接口
	protected DubboService dubboService;

	@Transactional
	public String save(ProductWordsCard productWords) {
		JSONObject jsonObject = null;
		try{
			JSONObject productSearch = getDefaultSearchJSONObject(productWords.getProductWordsName());
			jsonObject = dubboService.productSearch(productSearch);
			JSONObject header = jsonObject.getJSONObject("header");
			String rawquestion = header.getString("rawquestion");
			JSONObject content = jsonObject.getJSONObject("content");
			JSONObject pageBar = content.getJSONObject("pageBar");
			Integer totalCount = pageBar.getInteger("totalCount");
			productWords.setSearch_amount(totalCount);
			logger.info("--------------------------------------"+totalCount+"-------------------------------------------------------");
			if(totalCount<=2){
				productWords.setIsEnable(0);
			}else {
				productWords.setIsEnable(1);
			}

			if(rawquestion==null){
				productWords.setSearch_status(0);
			}else if(productWords.getProductWordsName().equals(rawquestion)){
				productWords.setSearch_status(1);
			}else {
				productWords.setSearch_status(0);
				if(totalCount<30) {
					productWords.setIsEnable(0);
				}
			}


		}catch (Exception e){
			if(null != jsonObject){
				logger.error("调用接口参数jsonObject: "+"    "+jsonObject+"    "+e.getMessage());

			}else {
				logger.error("调用接口参数jsonObject: "+"    "+jsonObject);
			}
		}

		if (productWords.getId() != null) {
			logger.info("进行update操作");
			productWordsCardDAO.update(productWords);
			return productWords.getId().toString();
		} else {
			return productWordsCardDAO.save(productWords);
		}
	}

	@Override
	@Transactional
	public void update(ProductWordsCard entity) {
		productWordsCardDAO.update(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public PaginatedList<ProductWordsCard> findByMap(Map<String, Object> search) {
		return productWordsCardDAO.findByMap(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductWordsCard> findListByMap(Map<String, Object> search) {
		return productWordsCardDAO.findListByMap(search);
	}



	@Override
	public PaginatedList<ProductWordsCardImportLog> listLog(Map<String, Object> paramMap) {
		return productWordsCardDAO.listLog(paramMap);
	}

	@Override
	public List<ProductWordsErrorCard> listDownLog(Map<String, Object> paramMap) {
		return productWordsCardDAO.listDownLog(paramMap);
	}

	@Override
	public void addError(List<ProductWordsErrorCard> productWordsErrorCards,Integer logID) {
		productWordsCardDAO.addError(productWordsErrorCards,logID);
	}

	@Override
	public Integer addLog(ProductWordsCardImportLog importLog) {
		return  productWordsCardDAO.addLog(importLog);
	}

	@Override
	public ProductWordsCard getByProductWordsName(String productWordsName) {
		return productWordsCardDAO.getByProductWordsName(productWordsName);
	}



	@Override
	public PaginatedList<ProductWordsCard> findLikeByMap(Map<String, Object> search) {
		PaginatedList<ProductWordsCard> productWordsCards = productWordsCardDAO.findLikeByMap(search);
//	    //调dubbo搜索服务，按照格式传入对应的参数，解析返回值，完成判断存值  totalCount>2?有效:无效
//		JSONObject jsonObject = null;
//		for (ProductWordsCard productWords : productWordsCards) {
//			productWords.setStatus(1);
//			try{
//				JSONObject productSearch = new JSONObject();
//				productSearch.put("from","robot");
//				productSearch.put("question",productWords.getProductWordsName());
//				productSearch.put("catId","0");
//				productSearch.put("regionId","11010200");
//				jsonObject = dubboService.productSearch(productSearch);
//				JSONObject content = jsonObject.getJSONObject("content");
//				JSONObject pageBar = content.getJSONObject("pageBar");
//				Integer totalCount = pageBar.getInteger("totalCount");
//				if(totalCount>2){
//					productWords.setStatus(0);
//				}
//			}catch (Exception e){
//				if(null != jsonObject){
//					logger.error("调用接口参数jsonObject: "+"    "+jsonObject+"    "+e.getMessage());
//
//				}else {
//					logger.error("调用接口参数jsonObject: "+"    "+jsonObject);
//				}
//			}
//		}
		return productWordsCards;
	}



	@Override
	public ProductWordsCard getById(Integer id) {
		return  productWordsCardDAO.getById(id);
	}


	@Override
	public List<ProductWordsCard> getByRangeId(Map<String, Object> paramMap) {
		return productWordsCardDAO.getByRangeId(paramMap);
	}

	@Override
	public Integer getCountIsEnable() {
		return productWordsCardDAO.getCountIsEnable();
	}

	@Override
	public Integer getCountNotEnable() {
		return productWordsCardDAO.getCountNotEnable();
	}


	public JSONObject getDefaultSearchJSONObject(String productWordName) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("from","robot");
		jsonObject.put("question",productWordName);
		jsonObject.put("catId","0");
		jsonObject.put("regionId","11010200");
		jsonObject.put("market","10");
		jsonObject.put("facets","0");
		jsonObject.put("productTag","0");
		jsonObject.put("sort","0");
		jsonObject.put("priceTag","0");
		jsonObject.put("pageSize","48");
		jsonObject.put("pageNumber","1");
		jsonObject.put("instock","0");
		jsonObject.put("sale","0");
		return jsonObject;
	}

	/**
	 *  getters  and  setters
	 */
	public ProductWordsCardDAO getProductWordsCardDAO() {
		return productWordsCardDAO;
	}

	public void setProductWordsCardDAO(ProductWordsCardDAO productWordsCardDAO) {
		this.productWordsCardDAO = productWordsCardDAO;
	}

	public DubboService getDubboService() {
		return dubboService;
	}

	public void setDubboService(DubboService dubboService) {
		this.dubboService = dubboService;
	}
}
