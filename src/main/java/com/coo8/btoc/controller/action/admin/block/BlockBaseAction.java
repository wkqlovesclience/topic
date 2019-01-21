package com.coo8.btoc.controller.action.admin.block;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.block.BlockManager;
import com.coo8.btoc.business.interfaces.resource.ResourceManager;
import com.coo8.btoc.config.ReloadableConfig;
import com.coo8.btoc.controller.action.CommonAction;
import com.coo8.btoc.model.block.Block;
import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.util.file.FileUtils;
import com.coo8.btoc.util.file.PathUtil;

public class BlockBaseAction extends CommonAction {

	private static final long serialVersionUID = -3865135359180194666L;
	private  static Logger logger = LoggerFactory.getLogger(BlockBaseAction.class);
	protected BlockManager blockManager;
	
	protected ResourceManager resourceManager;
	
	protected Block block;
	protected List<Resource> resourceList;
	protected List<Block> blockList;
	
	public Block getBlock() {
		return block;
	}
	
	protected String writeHtmlFile() {
		if(! block.isManualBlock() || block.isDisabled() )
			return null;
		
		String path = PathUtil.getManualBlockPath(block); 
		
		try {
			FileUtils.fileWrite(getRealPath() + ReloadableConfig.getProperty("base_path", "") + path, block.getContent(), false);
		} catch(IOException e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		
		return PathUtil.getIncludeOrder(path);
	}

	
	public void setBlock(Block block) {
		this.block = block;
	}

	public void setBlockManager(BlockManager blockManager) {
		this.blockManager = blockManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public List<Block> getBlockList() {
		return blockList;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}
	
}