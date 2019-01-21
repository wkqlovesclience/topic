package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.common.utils.StringUtil;

@Namespace("/admin/block")
public class BlockUpdateAction extends BlockBaseAction {

	private static final long serialVersionUID = -1634343759867716601L;
	
	private String oldName;
	
	@Action(value="update", results={
			@Result(name=INPUT, location="/admin/template/list.action", type="redirect"),
			@Result(name=SUCCESS, location="/admin/template/list.action", type="redirect")
	})
	public String templateBlockUpdate() {
		return update();
	}
	
	@Action(value="saveAndContinue", results={
			@Result(name=INPUT, location="/admin/block/updateRequest.action?templateId=${block.templateId}&blockId=${block.id}", type="redirect"),
			@Result(name=SUCCESS, location="/admin/block/updateRequest.action?templateId=${block.templateId}&blockId=${block.id}", type="redirect")
	})
	public String saveAndContinueUpdateBlockUpdate() {
		return update();
	}
	
	@Action(value="manualUpdate", results={
			@Result(name=INPUT, location="/admin/block/list.action", type="redirect"),
			@Result(name=SUCCESS, location="/admin/block/list.action", type="redirect")
	})
	public String manualBlockUpdate() {
		return update();
	}
	
	private String update(){
		if(isNotCorrectBlock())
			return INPUT;
		String location = writeHtmlFile();
		
		block.setLocation(location);
		block.setUpdator(getAdminSessionOperator());
		
		if(isUpdatedBlockName())
			blockManager.updateBlockName(block, oldName);
		else
			blockManager.updateBlock(block);
		
		return SUCCESS;
	}
	
	@Action(value="autoBlockUpdate", results={
			@Result(name=INPUT, location="/admin/block/list.action", type="redirect"),
			@Result(name=SUCCESS, location="/admin/block/list.action", type="redirect")
	})
	public String updateAutoBlock() {
		if(isNotCorrectBlock())
			return INPUT;
		
		block.setUpdator(getAdminSessionOperator());
		blockManager.updateBlock(block);
		
		return SUCCESS;
	}
	
	private boolean isNotCorrectBlock() {
		return block == null || block.getId() == null || block.getId() < 1;
	}
	
	private boolean isUpdatedBlockName() {
		return ! StringUtil.isNullorBlank(oldName) 
			&& !StringUtil.isNullorBlank(block.getName()) 
				&& ! oldName.equals(block.getName());
	}
	
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
}