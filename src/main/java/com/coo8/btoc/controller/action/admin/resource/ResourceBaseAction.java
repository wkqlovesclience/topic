package com.coo8.btoc.controller.action.admin.resource;

import java.util.List;

import com.coo8.btoc.business.interfaces.resource.ResourceManager;
import com.coo8.btoc.config.ReloadableConfig;
import com.coo8.btoc.controller.action.CommonAction;
import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;

public class ResourceBaseAction extends CommonAction {

	private static final long serialVersionUID = -8190547978065634340L;
	
	protected ResourceManager resourceManager;
	
	protected Resource resource;
	
	protected ResourceCatalog resourceCatalog;
	
	protected List<Resource> resourceList;
	
	protected List<ResourceCatalog> resourceCatalogList;
	
	protected Integer resourceHtmlId;
	
	protected Integer staticHtmlId;
	
	protected Integer resourceId;
	
	protected Integer productId;
	
	protected String compId;
	
	protected Integer open;
	
	protected Integer position;
	
	protected Integer dateNum;//块对应的产品位个数
	
	protected String HEADOFFICEID = ReloadableConfig.getProperty("header_company_id", "c1000");//总公司ID
	
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}
	
	public Integer getStaticHtmlId() {
		return staticHtmlId;
	}

	public void setStaticHtmlId(Integer staticHtmlId) {
		this.staticHtmlId = staticHtmlId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public ResourceCatalog getResourceCatalog() {
		return resourceCatalog;
	}

	public void setResourceCatalog(ResourceCatalog resourceCatalog) {
		this.resourceCatalog = resourceCatalog;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public Integer getResourceHtmlId() {
		return resourceHtmlId;
	}

	public void setResourceHtmlId(Integer resourceHtmlId) {
		this.resourceHtmlId = resourceHtmlId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

    public Integer getDateNum() {
        return dateNum;
    }

    public void setDateNum(Integer dateNum) {
        this.dateNum = dateNum;
    }

	public List<ResourceCatalog> getResourceCatalogList() {
		return resourceCatalogList;
	}

	public void setResourceCatalogList(List<ResourceCatalog> resourceCatalogList) {
		this.resourceCatalogList = resourceCatalogList;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}
	
}