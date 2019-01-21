package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.model.resource.ResourceCatalog;

@Namespace("/admin/resource")
public class ResourceUpdateRequestAction extends ResourceBaseAction {

	private static final long serialVersionUID = -5471562689799671273L;
	
	@Action(value="updateRequest", results={
			@Result(name=INPUT, location="list.action", type="redirect"),
			@Result(name="Procedure", location="/jsp/admin/resource/admin_resource_update.jsp",type="dispatcher"),
			@Result(name="Product", location="/jsp/admin/resource/admin_product_resource_update.jsp", type="dispatcher"),
			@Result(name="Outside", location="/jsp/admin/resource/admin_outside_resource_update.jsp", type="dispatcher"),
			@Result(name="Atg", location="/jsp/admin/resource/admin_atg_resource_create.jsp", type="dispatcher")
	})
	public String updateRequest(){
		if(isUnavailableId())
			return INPUT;
		
		resource = resourceManager.getResourceById(resourceId);
		if(resource.isProcedure()){
			return "Procedure";
		}
		else if(resource.isProduct()){
			return "Product";
		}
		else if(resource.isOutside()){
			return "Outside";
		}
		else{
			return "Atg";
		}
	}
	
	
	@Action(value="updateOpenState", results={
            @Result(name=SUCCESS, location="/jsp/admin/common/blank.jsp")
    })
	public String updateOpenState() {
		
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setHtmlId(staticHtmlId);
		resourceCatalog.setResourceId(resourceId);
		resourceCatalog.setPosition(position);
		resourceCatalog.setCompId(compId);
		resourceCatalog.setOpen(open);
		resourceCatalog.setUpdator(this.getAdminSessionOperator());
		
		resourceManager.deleteBranchItems(resourceCatalog);
		
		return SUCCESS;
	}
	
	
	@Action(value="moveUpPosition", results={
            @Result(name=SUCCESS, location="/jsp/admin/common/blank.jsp")
    })
	public String moveUpPosition() throws Exception{
		
		if(!isHeadCompany()){
			return null;
		}
		
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setId(resourceHtmlId);
		resourceCatalog.setHtmlId(staticHtmlId);
		resourceCatalog.setResourceId(resourceId);
		resourceCatalog.setPosition(position);
		resourceCatalog.setType(ResourceCatalog.HEAD_OFFICE_TYPE);
		resourceManager.updateMoveUpPosition(resourceCatalog);
		
		return SUCCESS;
	}
	
	
	@Action(value="moveDownPosition", results={
            @Result(name=SUCCESS, location="/jsp/admin/common/blank.jsp")
    })
	public String moveDownPosition() throws Exception {
		
		if(!isHeadCompany()){
			return null;
		}
		
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setId(resourceHtmlId);
		resourceCatalog.setHtmlId(staticHtmlId);
		resourceCatalog.setResourceId(resourceId);
		resourceCatalog.setPosition(position);
		resourceCatalog.setType(ResourceCatalog.HEAD_OFFICE_TYPE);
		resourceManager.updateMoveDownPosition(resourceCatalog, dateNum);
		
		return SUCCESS;
	}
	
	private boolean isHeadCompany(){
		return this.getAdminSessionCOMPID().equals(this.HEADOFFICEID);
	}
	
	private boolean isUnavailableId() {
		return resourceId == null || resourceId < 1;
	}

}