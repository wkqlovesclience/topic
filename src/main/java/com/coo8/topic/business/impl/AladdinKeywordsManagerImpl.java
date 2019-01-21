package com.coo8.topic.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.catalog.Category;
import com.coo8.btoc.model.catalog.CategoryBrandBase;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.persistence.interfaces.items.IItemsDAO;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.HttpClientUtil;
import com.coo8.topic.business.interfaces.IAladdinKeywordsManager;
import com.coo8.topic.model.AladdinKeywords;
import com.coo8.topic.model.AladdinKeywordsRef;
import com.coo8.topic.persistence.interfaces.IAladdinKeywordsDAO;

public class AladdinKeywordsManagerImpl implements IAladdinKeywordsManager{

	@Override
	public AladdinKeywords getById(Integer id) {
		return aladdinKeywordsDAO.getById(id);
	}

	@Override
	public int save(AladdinKeywords entity) {
		int ret = -1;
		if(null != entity){
			int id = aladdinKeywordsDAO.save(entity);
			AladdinKeywordsRef keywordsRef = new AladdinKeywordsRef();
			if(-1 != id){
				keywordsRef.setAkeywords(id);
				keywordsRef.setCatalogid(entity.getCatalogId());
				ret = aladdinKeywordsDAO.saveKeywordsRef(keywordsRef);
			}
		}
		return  ret;
	}

	@Override
	public int deleteById(Integer id) {
		return aladdinKeywordsDAO.deleteById(id);
		
	}

	@Override
	public int update(AladdinKeywords entity) {
		if(null != entity){
			AladdinKeywordsRef keywordsRef = new AladdinKeywordsRef();
			keywordsRef.setAkeywords(entity.getId());
			keywordsRef.setCatalogid(entity.getCatalogId());
			return aladdinKeywordsDAO.update(entity);
		}
		return -1;
	}

	@Override
	public PaginatedList<AladdinKeywords> findLikeByMap(
			Map<String, Object> search) {
		return aladdinKeywordsDAO.findLikeByMap(search);
	}
	
	private IAladdinKeywordsDAO aladdinKeywordsDAO;
	private IItemsDAO itemsDAO;

	public IAladdinKeywordsDAO getAladdinKeywordsDAO() {
		return aladdinKeywordsDAO;
	}

	public void setAladdinKeywordsDAO(IAladdinKeywordsDAO aladdinKeywordsDAO) {
		this.aladdinKeywordsDAO = aladdinKeywordsDAO;
	}

	public IItemsDAO getItemsDAO() {
		return itemsDAO;
	}

	public void setItemsDAO(IItemsDAO itemsDAO) {
		this.itemsDAO = itemsDAO;
	}

	@Override
	public List<CategoryBrandBase> getCatalogListById(Integer catalogId) {
		return itemsDAO.getCatalogListById(catalogId);
	}

	@Override
	public List<BtoCItems> getItemListByCid(Integer catalogId) {
		return itemsDAO.getItemListByCid(catalogId);
	}

	@Override
	public List<AladdinKeywords> findPage(Map<String, Object> search) {
		return aladdinKeywordsDAO.findPage(search);
	}

	@Override
	public List<AladdinKeywordsRef> findAllKeywordsRef(
			Map<String, Object> search) {
		return aladdinKeywordsDAO.findAllKeywordsRef(search);
	}

	@Override
	public int deleteKeywordsRefById(Integer id) {
		return aladdinKeywordsDAO.deleteKeywordsRefById(id);
	}

	@Override
	public List<CategoryBrandBase> getCatalogListByIdMap(Integer[] catalogArr) {
		return itemsDAO.getCatalogListByIdMap(catalogArr);
	}

	/**
	 * 通过ATG接口获得商品分类
	 * @param ids 一级分类
	 * @return
	 */
	@Override
	public List<Category> getATGCatalogListByIds(String ids) {
		List<Category> categoryList = new ArrayList<Category>();
		if(null == ids || "".equals(ids.trim())){
			return categoryList;
		}
		String atgCategoryUrl = ReloadableConfig.getProperty("atgCategoryUrl","http://10.58.24.100/coo8/ec/data/coo8ThirdCategoryInterferce.jsp?cataIds=");
		atgCategoryUrl += ids.trim();
		List<Map<String,Object>> dataList = HttpClientUtil.getJsonMapListByATGUrl(atgCategoryUrl);
		
		if( null != dataList && !dataList.isEmpty()){
			String cataid = "";
			String cataname = "";
			String pcataid = "";
			Category cataObj = null;
			for(Map<String,Object> jsonMap:dataList){
				cataid = ""+jsonMap.get("cataid");
				cataname = ""+jsonMap.get("cataname");
				pcataid = ""+jsonMap.get("pcataid");
				
				cataObj = new Category(cataid,cataname,pcataid);
				categoryList.add(cataObj);
			}
		}
		
		return categoryList;
	}
	
}
