package com.coo8.btoc.persistence.interfaces.resource;

import java.util.List;

import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;

/**
 * 动态数据源持久化接口
 * @author Arron
 *
 */
public interface ResourceDao {
	
	void insertResource(Resource resource);
	void updateResource(Resource resource);
	void deleteResource(Integer id);
	
	Resource getResourceById(Integer id);
	
	List<Resource> queryResources(Resource resource);
	
	void insertResourceCatalog(ResourceCatalog resourceCatalog);
	List<ResourceCatalog> queryResourceCatalog(ResourceCatalog resourceCatalog);
	boolean addProductIds(ResourceCatalog resourceCatalog);
	boolean directAddProductIds(ResourceCatalog resourceCatalog);
	
	void updateResourceCatalog(ResourceCatalog resourceCatalog);
	void deleteResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
	 * 录入静态页块中商品，没有则添加，有则更新。
	 * @param resourceCatalog
	 */
	void replaceInsertResourceCatalog(ResourceCatalog resourceCatalog);
	int queryResourceCatalogCount(ResourceCatalog resourceCatalog);
}
