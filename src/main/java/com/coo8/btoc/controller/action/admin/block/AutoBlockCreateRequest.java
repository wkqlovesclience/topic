package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/block")
public class AutoBlockCreateRequest extends BlockBaseAction {

	private static final long serialVersionUID = 6124646663133914950L;
	
	@Action(value="autoCreateRequest", results={
			@Result(name=SUCCESS, location="/jsp/admin/block/admin_auto_block_create.jsp", type="dispatcher")
	})
	
	@Override
	public String execute() {
		
		resourceList = resourceManager.queryAllResource();
		
		return SUCCESS;
	}
	
}
