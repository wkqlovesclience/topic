package com.coo8.btoc.persistence.impl.resource;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;
import com.coo8.btoc.persistence.interfaces.resource.ResourceDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

public class ResourceDaoImpl extends SqlMapClientDaoSupport 
	implements ResourceDao {

	@Override
	public void insertResource(Resource resource) {
		getSqlMapClientTemplate().insert("Resource.insertResource", resource);
	}

	@Override
	public void updateResource(Resource resource) {
		getSqlMapClientTemplate().update("Resource.updateResource", resource);
	}
	
	@Override
	public void deleteResource(Integer id) {
		getSqlMapClientTemplate().delete("Resource.deleteResource", id);
	}

	@Override
	public Resource getResourceById(Integer id) {
		return (Resource)getSqlMapClientTemplate()
			.queryForObject("Resource.getResourceById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> queryResources(Resource resource) {
		if (isNotNeedSplitPage(resource))
			return getSqlMapClientTemplate().queryForList(
					"Resource.queryResources", resource);
		
		int count = queryResourceCount(resource);
		if (count <= 0)
			return PaginatedArrayList.emptyList();
		
		PaginatedList<Resource> resourceList = new PaginatedArrayList<Resource>(
				count, resource.getPageIndex(), resource.getPageSize());
		List<Resource> temp = getSqlMapClientTemplate().queryForList(
				"Resource.queryResources", resource, resourceList.getStartPos(), 
				resource.getPageSize());
		resourceList.addAll(temp);
	
		return resourceList;
	}
	
	private boolean isNotNeedSplitPage(Resource resource) {
		return resource == null || resource.getPageSize() == null 
			|| resource.getPageSize() < 0;
	}
	
	private int queryResourceCount(Resource resource) {
		Integer count = (Integer) getSqlMapClientTemplate().
			queryForObject("Resource.queryResourceCount", resource);
		
		return count == null ? 0 : count.intValue();
	}

	@Override
	public void insertResourceCatalog(ResourceCatalog resourceCatalog) {
		getSqlMapClientTemplate()
			.insert("Resource.insertResourceCatalog", resourceCatalog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceCatalog> queryResourceCatalog(
		ResourceCatalog resourceCatalog) {
		
		return getSqlMapClientTemplate()
			.queryForList("Resource.queryResourceCatalog", resourceCatalog);
	}

	@Override
	public boolean addProductIds(ResourceCatalog resourceCatalog) {
		int resultCount = getSqlMapClientTemplate()
			.update("Resource.addProductIds", resourceCatalog);
		
		return resultCount > 0;
	}
	
	@Override
	public boolean directAddProductIds(ResourceCatalog resourceCatalog) {
		int resultCount = getSqlMapClientTemplate()
			.update("Resource.directAddProductIds", resourceCatalog);
		
		return resultCount > 0;
	}
	
	@Override
	public void deleteResourceCatalog(ResourceCatalog resourceCatalog) {
		getSqlMapClientTemplate()
			.delete("Resource.deleteResourceCatalog", resourceCatalog);
	}

	@Override
	public void updateResourceCatalog(ResourceCatalog resourceCatalog) {
		getSqlMapClientTemplate().update("Resource.updateResourceCatalog",resourceCatalog);
	}

	@Override
	public void replaceInsertResourceCatalog(ResourceCatalog resourceCatalog) {
		getSqlMapClientTemplate().update("Resource.replaceInsertResourceCatalog",resourceCatalog);
	}
	
	@Override
	public int queryResourceCatalogCount(ResourceCatalog resourceCatalog){
	    Integer count = (Integer) getSqlMapClientTemplate().
            queryForObject("Resource.queryResourceCatalogCount", resourceCatalog);
	    return count == null ? 0 : count.intValue();
	}

}