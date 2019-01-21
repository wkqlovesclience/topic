package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.model.block.Block;

@Namespace("/admin/block")
public class BlockUpdateRequestAction extends BlockBaseAction {

	private static final long serialVersionUID = -1987545791295988067L;
	
	private Integer templateId;
	private Integer blockId;
	
	private Block editedBlock;
	
	@Action(value="updateRequest", results={
			@Result(name=SUCCESS, location="/jsp/admin/block/admin_block_update.jsp", type="dispatcher"),
			@Result(name=INPUT, location="/admin/template/list.action", type="redirect")
	})
	public String updateRequest() {
		if(isUnavailableTemplateId())
			return INPUT;
		
		blockList = blockManager.queryBlocks(templateId);
		editedBlock = queryEditedBlock();
		
		if(editedBlock == null)
			return INPUT;
		
		if(editedBlock.isAutoBlock())
			resourceList = resourceManager.queryAllResource();
		
		return SUCCESS;
	}
	
	private boolean isUnavailableTemplateId() {
		return templateId == null || templateId < 1;
	}
	
	private Block queryEditedBlock() {
		if (isUnavailableBlockId())
			return queryFirstBlockFromBlockList();
		
		Block temp = blockManager.getBlockById(blockId);
		
		if (temp == null)
			temp = queryFirstBlockFromBlockList();
		
		return temp;
	}
	
	private boolean isUnavailableBlockId() {
		return blockId == null || blockId < 1;
	}
	
	private Block queryFirstBlockFromBlockList() {
		if(blockList.isEmpty())
			return null;
		
		return blockList.get(0);
	}
	
	@Action(value="commonBlockUpdateRequest", results={
			@Result(name=INPUT, location="/admin/block/list.action", type="redirect"),
			@Result(name=SUCCESS, location="/jsp/admin/block/admin_common_block_update.jsp", type="dispatcher")
	})
	public String commonBlockUpdateRequest() {
		if(isUnavailableBlockId())
			return INPUT;
		
		block = blockManager.getBlockById(blockId);
		
		return SUCCESS;
	}
	
	@Action(value="autoBlockUpdateRequest", results={
			@Result(name=INPUT, location="/admin/block/list.action", type="redirect"),
			@Result(name=SUCCESS, location="/jsp/admin/block/admin_auto_block_update.jsp", type="dispatcher")
	})
	public String autoBlockUpdateRequest() {
		if(isUnavailableBlockId())
			return INPUT;
		
		block = blockManager.getBlockById(blockId);
		if(block == null || ! block.isAutoBlock())
			return INPUT;
		
		resourceList = resourceManager.queryAllResource();
		
		return SUCCESS;
	}
	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Block getEditedBlock() {
		return editedBlock;
	}

	
}