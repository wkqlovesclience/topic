package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.catalog.Category;
import com.coo8.btoc.model.catalog.CategoryBrandBase;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AladdinKeywords;
import com.coo8.topic.model.AladdinKeywordsRef;

public interface IAladdinKeywordsManager {
	
	public AladdinKeywords getById(java.lang.Integer id);

	public int save(AladdinKeywords entity);
	
	public int deleteById(java.lang.Integer id);
	
	public int update(AladdinKeywords entity);
	
	public PaginatedList<AladdinKeywords> findLikeByMap(Map<String, Object> search);
	
	public List<AladdinKeywords> findPage(Map<String, Object> search);
	
	/**
	 * 获得产品分类
	 * @param catalogId
	 * @return
	 */
	public List<CategoryBrandBase> getCatalogListById(Integer catalogId);
	/**
	 * 根据分类ID获得商品---只取1个
	 * @param catalogId
	 * @return
	 */
	public List<BtoCItems> getItemListByCid(Integer catalogId);
	
	/**
	 *  根据条件查找泛需求词对应的商品品牌信息（泛需求词与品牌关联） 
	 * @param search
	 * @return
	 */
	public List<AladdinKeywordsRef> findAllKeywordsRef(
			Map<String, Object> search);
	
	/**
	 *  根据ID删除 泛需求对应的商品品牌信息  （泛需求词与品牌关联） 
	 * @param id
	 */
	public int deleteKeywordsRefById(Integer id);
	
	/**
	 *  根据商品品牌ID数组获得品牌
	 * @param catalogArr
	 * @return
	 */
	public List<CategoryBrandBase> getCatalogListByIdMap(Integer[] catalogArr);
	
	/**
	 * 通过ATG接口获得商品分类
	 * @param ids 一级分类
	 * @return
	 */
	public List<Category> getATGCatalogListByIds(String ids);
}
