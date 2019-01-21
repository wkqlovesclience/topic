package com.coo8.btoc.controller.action.admin.block;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import com.coo8.btoc.model.block.Block;

@Namespace("/admin/block")
public class BlockListAction extends BlockBaseAction {

	private static final long serialVersionUID = -5583811505260132220L;
	
	@Action(value="list", results={
			@Result(name=SUCCESS, location="/jsp/admin/block/admin_block_list.jsp", type="dispatcher"),
			@Result(name="QXBZ", location="/jsp/admin/popedom/error.jsp"),
			@Result(name="userInexistence", location="/jsp/admin/index.jsp")
	})
	
	@Override
	public String execute() {
		initPage();
		
		blockList = blockManager.queryBlocks(block);
		
		if(isSpecifyDataType() && HTML.equals(dataType))
			return HTML;
		
		return SUCCESS;
	}
	
	private void initPage() {
		if(block == null)
			block = new Block();
		
		if(block.getPageIndex() == null || block.getPageIndex() < 1)
			block.setPageIndex(1);
		
		if(block.getPageSize() == null || block.getPageSize() < 1)
			block.setPageSize(30);
	}
}
