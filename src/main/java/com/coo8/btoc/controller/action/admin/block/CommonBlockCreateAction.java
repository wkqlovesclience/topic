package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/block")
public class CommonBlockCreateAction extends BlockBaseAction {

	private static final long serialVersionUID = 4148802844878865121L;
	
	@Action(value="createCommonManualBlock", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		
		blockManager.createCommonManualBlock(block);
		
		String location = writeHtmlFile();
		blockManager.updateBlockLocaotion(block.getId(), location);
		
		return SUCCESS;
	}
	
}
