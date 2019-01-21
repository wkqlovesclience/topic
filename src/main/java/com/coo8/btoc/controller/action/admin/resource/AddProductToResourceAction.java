package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.model.resource.ResourceCatalog;
import com.yesky.common.utils.StringUtil;

@Namespace("/admin/resource")
public class AddProductToResourceAction extends ResourceBaseAction {

	private static final long serialVersionUID = -4950430729313721727L;
	private  static Logger logger = LoggerFactory.getLogger(AddProductToResourceAction.class);
	@Action(value="addProductIds", results={
			@Result(name=SUCCESS, location="/jsp/admin/common/common_success_text.jsp"),
			@Result(name=INPUT, location="/jsp/admin/common/common_error_text.jsp")
	})
	public String addProductIds() {
		try {
			if (isUnCorrectResource())
				return INPUT;
			
			String comp_id = this.getAdminSessionCOMPID();
			if(StringUtil.isNullorBlank(comp_id)){
				return ERROR;
			}
			
			resourceCatalog = new ResourceCatalog();
			resourceCatalog.setId(resourceHtmlId);
			resourceCatalog.setProductId(productId);
			
			if(comp_id.equals(HEADOFFICEID)){
				resourceCatalog.setUpdator(getAdminSessionOperator());
				resourceManager.updateResourceCatalog(resourceCatalog);
			}else{
				
				buildResourceCatalog(resourceCatalog);
				resourceManager.saveOrUpdateBranchComp(resourceCatalog);
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return SUCCESS;
	}
	
	
	private void buildResourceCatalog(ResourceCatalog resourceCata){
		if(resourceCata == null)
			resourceCata = new ResourceCatalog();		
		resourceCata.setResourceId(resourceId);
		resourceCata.setHtmlId(staticHtmlId);
		resourceCata.setCompId(this.getAdminSessionCOMPID());
		resourceCata.setCityIds(this.getAdminSessionCITYIDS());
		resourceCata.setPosition(position);
		resourceCata.setOpen(ResourceCatalog.OPEN);
		resourceCata.setType(ResourceCatalog.BRANCH_OFFICE_TYPE);
		resourceCata.setStatus(ResourceCatalog.ENABLED);
		resourceCata.setCreator(getAdminSessionOperator());
		resourceCata.setUpdator(getAdminSessionOperator());
		
	}
	
	
	private boolean isUnCorrectResource() {
		return resourceId == null || resourceId < 1;
	}
	
}