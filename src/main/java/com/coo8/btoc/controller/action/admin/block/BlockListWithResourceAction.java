package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/block")
public class BlockListWithResourceAction extends BlockBaseAction {

	private static final long serialVersionUID = -6890940888031915881L;
	
	private Integer resourceType;
	private Integer staticHtmlId;
	
	@Action(value="listWithResource", results={
			@Result(name=HTML, location="/jsp/admin/block/admin_block_list_with_resource_html.jsp")
	})
	
	@Override
	public String execute() {
		
		block.setType(resourceType);
		
		blockList = blockManager.queryBlocksWithResourceType(block);
		
		return HTML;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getStaticHtmlId() {
		return staticHtmlId;
	}

	public void setStaticHtmlId(Integer staticHtmlId) {
		this.staticHtmlId = staticHtmlId;
	}
	
}