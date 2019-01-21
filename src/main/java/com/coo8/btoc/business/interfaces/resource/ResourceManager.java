package com.coo8.btoc.business.interfaces.resource;

import java.util.List;

import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;

public interface ResourceManager {
	
	void createResource(Resource resource);
	void updateResource(Resource resource);
	void deleteResource(Integer id);
	
	Resource getResourceById(Integer id);
	List<Resource> queryResource(Resource resource);
	List<Resource> queryAllResource();
	
	/**
	 * 创建新商品位
	 * @param resourceCatalog
	 */
	void createResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
	 * 查询商品位集合
	 * @param resourceCatalog
	 * @return
	 */
	List<ResourceCatalog> queryResourceCatalog(ResourceCatalog resourceCatalog);
	/**
	 * 更新商品位
	 * @param resourceCatalog
	 */
	void updateResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
	 * 删除商品位
	 * @param resourceCatalog
	 */
	void deleteResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
     * 添加几个默认商品位
     */
	void insertPositions(ResourceCatalog resourceCatalog,int num);
	/**
	 * 删除/重新挂载静态页时，删除静态页下的产品位，并清理缓存
	 * @param htmlId
	 */
	void deleteResourceCatalogByHtmlId(Integer htmlId);
	/**
	 * 更改某个商品的产品位缓存信息
	 * @param productid
	 */
	void updateProductRedis(Integer productId);
	
	/**
	 * 分公司为产品位添加商品，并写缓存
	 * @param rs
	 */
	void saveOrUpdateBranchComp(ResourceCatalog resourceCatalog) throws Exception;
	/**
	 * 关闭/打开某个产品位
	 * @param resourceCatalog
	 */
	void deleteBranchItems(ResourceCatalog resourceCatalog);
	/**
     * 分公司删除产品位中的商品，并重新写缓存
     */
	void deleteAndUpdateCache(ResourceCatalog resourceCatalog) throws Exception;
	
	
	/**
	 * 上移产品位
	 * @param resourceCatalog
	 * @throws Exception
	 */
	void updateMoveUpPosition(ResourceCatalog resourceCatalog) throws Exception;
	
	/**
	 * 下移产品位
	 * @param resourceCatalog
	 * @param dataNum
	 * @throws Exception
	 */
	void updateMoveDownPosition(ResourceCatalog resourceCatalog, Integer dataNum) throws Exception;
}