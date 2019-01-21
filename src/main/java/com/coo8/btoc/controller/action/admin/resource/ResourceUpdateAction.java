package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/resource")
public class ResourceUpdateAction extends ResourceBaseAction {

	private static final long serialVersionUID = -2748264664361084860L;
	
	@Action(value="update", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		if(isUnCorrectResource())
			return SUCCESS;
		
		resource.setUpdator(getAdminSessionOperator());
		resourceManager.updateResource(resource);
		
		return SUCCESS;
	}
	
	private boolean isUnCorrectResource() {
		return resource == null || 
			resource.getId() == null || resource.getId() < 1;
			
	}
	
	@Action(value="delete", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	public String delete() {
		resourceManager.deleteResource(resourceId);		
		return SUCCESS;
	}
}
