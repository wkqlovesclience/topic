package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/block")
public class AutoBlockCreateAction extends BlockBaseAction {

	private static final long serialVersionUID = 1465051378940292992L;
	
	@Action(value="autoBlockCreate", results={
			@Result(name=SUCCESS, location="list.action", type="redirect"),
			@Result(name=INPUT, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		
		blockManager.createAutoBlock(block);
		
		return SUCCESS;
	}
}
