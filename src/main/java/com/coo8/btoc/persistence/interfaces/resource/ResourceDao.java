package com.coo8.btoc.persistence.interfaces.resource;

import java.util.List;

import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;

/**
 * ��̬����Դ�־û��ӿ�
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
	 * ¼�뾲̬ҳ������Ʒ��û������ӣ�������¡�
	 * @param resourceCatalog
	 */
	void replaceInsertResourceCatalog(ResourceCatalog resourceCatalog);
	int queryResourceCatalogCount(ResourceCatalog resourceCatalog);
}
