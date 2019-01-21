package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.model.resource.Resource;

@Namespace("/admin/resource")
public class ResourceListAction extends ResourceBaseAction {

	private static final long serialVersionUID = 7275297136185089742L;
	
	@Action(value="list", results={
			@Result(name=SUCCESS, location="/jsp/admin/resource/admin_resource_list.jsp", type="dispatcher"),
			@Result(name="QXBZ",location="/jsp/admin/popedom/error.jsp"),
			@Result(name="userInexistence",location="/jsp/admin/index.jsp")
	})
	@Override
	public String execute() {
		
		if(resource == null)
			resource = new Resource();
		
		if(resource.getPageIndex() == null || resource.getPageIndex() < 1)
			resource.setPageIndex(1);
		if(resource.getPageSize() == null || resource.getPageSize() < 1)
			resource.setPageSize(30);
		
		resourceList = resourceManager.queryResource(resource);
		
		return SUCCESS;
	}
}
