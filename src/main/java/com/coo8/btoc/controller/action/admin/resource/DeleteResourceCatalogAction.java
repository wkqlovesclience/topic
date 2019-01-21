package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.model.resource.ResourceCatalog;

@Namespace("/admin/resource/catalog")
public class DeleteResourceCatalogAction extends ResourceBaseAction {

	private static final long serialVersionUID = 5827520425450082233L;
	
	private Integer resourceId;
	private Integer catalogId;
	private Integer type;
	
	@Action(value="delete", results={
			@Result(name=SUCCESS, location="/admin/resource/catalog/addProductRequest.action?resourceId=${resourceId}&type=${type}", type="redirect")
	})
	
	@Override
	public String execute() {
		ResourceCatalog rc = new ResourceCatalog();
		rc.setHtmlId(catalogId);
		rc.setResourceId(resourceId);
		rc.setType(type);
		
		resourceManager.deleteResourceCatalog(rc);
		
		return SUCCESS;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}