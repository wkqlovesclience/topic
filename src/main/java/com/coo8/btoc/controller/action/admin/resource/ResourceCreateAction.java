package com.coo8.btoc.controller.action.admin.resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.model.resource.Resource;

@Namespace("/admin/resource")
public class ResourceCreateAction extends ResourceBaseAction {

	private static final long serialVersionUID = -6117168888820056353L;
	 private  static Logger logger = LoggerFactory.getLogger(ResourceCreateAction.class);
	@Action(value="create", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	public String createProcedureResource() {
		resource.setCreator(getAdminSessionOperator());
		resource.setType(Resource.PROCEDURE_RESOURCE);
	
		resourceManager.createResource(resource);
		
		return SUCCESS;
	}
	
	@Action(value="createProductResource",results={
		@Result(name=SUCCESS, location="updateProductResource.action?resourceId=${resource.id}", type="redirect")
	})
	public String createProductResource() {
		resource.setCreator(getAdminSessionOperator());
		resource.setType(Resource.PRODUCT_RESOURCE);
		
		resourceManager.createResource(resource);
		
		return SUCCESS;	
	}
	@Action(value="createOutsideResource",results={
	     @Result(name=SUCCESS, location="list.action", type="redirect")
	 })
    public String createOutsideResource() {
        resource.setCreator(getAdminSessionOperator());
        resource.setType(Resource.OUTSIDE_RESOURCE);
        
        resourceManager.createResource(resource);
        
        return SUCCESS; 
    }
	@Action(value="createAtgResource",results={
		     @Result(name=SUCCESS, location="list.action", type="redirect")
		 })
	    public String createAtgResource() {
	        resource.setCreator(getAdminSessionOperator());
	        logger.info("creator : "+getAdminSessionOperator());
	        resource.setType(Resource.ATG_RESOURCE);
	        
	        resourceManager.createResource(resource);
	        
	        return SUCCESS; 
	    }
}
