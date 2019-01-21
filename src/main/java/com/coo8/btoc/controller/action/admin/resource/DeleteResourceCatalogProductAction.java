package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.model.resource.ResourceCatalog;
import com.coo8.common.utils.StringUtil;

@Namespace("/admin/resource/product")
public class DeleteResourceCatalogProductAction extends ResourceBaseAction {

	private static final long serialVersionUID = 1478054755444658232L;
	
	private Integer type;
	
	
	
	@Action(value="delete", results={
			@Result(name=SUCCESS, location="/jsp/admin/common/blank.jsp")
	})
	
	@Override
	public String execute() throws Exception{
		String comp_id = this.getAdminSessionCOMPID();
		if(StringUtil.isNullorBlank(comp_id)){
			return ERROR;
		}
		
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setHtmlId(staticHtmlId);
		resourceCatalog.setResourceId(resourceId);
		resourceCatalog.setPosition(position);
		resourceCatalog.setCompId(comp_id);
		
		if(comp_id.equals(HEADOFFICEID)){
			resourceCatalog.setProductId(0);
			resourceCatalog.setUpdator(this.getAdminSessionOperator());
			resourceManager.updateResourceCatalog(resourceCatalog);
		}else{
			resourceManager.deleteAndUpdateCache(resourceCatalog);
		}
		
		return SUCCESS;
	}
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
