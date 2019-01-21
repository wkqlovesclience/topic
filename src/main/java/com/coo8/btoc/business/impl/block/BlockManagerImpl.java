package com.coo8.btoc.business.impl.block;

import java.util.List;

import com.coo8.btoc.business.interfaces.block.BlockManager;
import com.coo8.btoc.model.block.Block;
import com.coo8.btoc.model.template.TemplateHistory;
import com.coo8.btoc.persistence.interfaces.block.BlockDao; 
import com.coo8.btoc.persistence.interfaces.items.IItemsDAO;
import com.coo8.btoc.persistence.interfaces.template.TemplateDao;
import com.coo8.btoc.util.PatternUtil;
import com.coo8.common.utils.StringUtil;
import com.mysql.jdbc.StringUtils;

public class BlockManagerImpl implements BlockManager {
	
	private BlockDao blockDao;
	private TemplateDao templateDao;
 	private IItemsDAO itemsDao; 
	@Override
	public void createAutoBlock(Block block) {
		block.setType(Block.AUTO_BLOCK);
		
		createBlock(block);
	}
	
	@Override
	public void createTemplateManualBlock(Block block) {
		block.setType(Block.TEMPLATE_MANUAL_BLOCK);
		block.setStatus(Block.ENABLED);
		
		blockDao.insert(block);
	}

	@Override
	public void createCommonManualBlock(Block block) {
		block.setType(Block.COMMON_MANUAL_BLOCK);
		block.setStatus(Block.ENABLED);
		
		blockDao.insert(block);		
	}

	private void createBlock(Block block) {
		if(isNoneResource(block) || StringUtil.isNullorBlank(block.getContent()))
			block.setStatus(Block.DISABLED);
		
		blockDao.insert(block);
	}
	
	private boolean isNoneResource(Block block) {
		return block.getResourceId() == null || block.getResourceId() < 1; 
	}
	
	@Override
	public Block getBlockById(Integer id) {
		
		return blockDao.getBlockById(id);
	}

	@Override
	public List<Block> queryBlocks(Block block) {
		
		return blockDao.queryBlocks(block);
	}
	
	@Override
	public List<Block> queryBlocks(Integer templateId) {
		Block searcher = new Block();
		searcher.setTemplateId(templateId);
		
		return queryBlocks(searcher);
	}
	
	
	@Override
	public void updateBlockName(Block block, String oldName) {
		updateTemplateBlockTag(block, oldName);
		updateBlock(block);
	}

	@Override
	public void updateBlock(Block block) {
		storyHistory(block);
		checkBlockStatus(block);
		
		block.setContent(StringUtil.trim(block.getContent()));
		
		blockDao.updateBlock(block);
	}
	
	private void updateTemplateBlockTag(Block block, String oldName) {
		if(! isNeedUpdateTemplateBlockTag(block, oldName))
			return;
		
		TemplateHistory history = templateDao.
			queryLastTemplateHistory(block.getTemplateId());
		if (isUnavailableValue(block, oldName, history))
			return;
		
		history.setContent(PatternUtil.replaceBlockTagName(
				oldName, block.getName(), block.getType(), history.getContent())
			);
		
		templateDao.updateTemplateHistory(history);
	}

	private boolean isUnavailableValue(
		Block block, String oldName,TemplateHistory history) {
		
		return history == null || StringUtil.isNullorBlank(history.getContent()) 
				|| StringUtil.isNullorBlank(block.getName()) 
				|| StringUtil.isNullorBlank(oldName);
	}
	
	private boolean isNeedUpdateTemplateBlockTag(Block block, String oldName) {
		return (!block.getName().equals(oldName)) && 
			(block.getTemplateId() != null && block.getTemplateId() > 0); 
	}

	private void storyHistory(Block block) {
		blockDao.insertBlockHistory(block);
		/*
		 * 暂且放宽这个限制，全部的块都有备份机制
		if(isStoreHistory(block)){
			blockDao.insertBlockHistory(block);			
		}
		*/
	}
	
	@SuppressWarnings("unused")
	private boolean isStoreHistory(Block block) {
		return ! block.isAutoBlock();
	}

	private void checkBlockStatus(Block block) {
		if(! isCorrectBlock(block))
			block.setStatus(Block.DISABLED);
	}
	
	private boolean isCorrectBlock(Block block) {
		return	(block.isAutoBlock() && block.getResourceId() != null && block.getResourceId() > 0)
		|| block.isManualBlock();
	}
	
	@Override
	public void updateBlockLocaotion(Integer id, String location) {
		if(id == null || id < 1 || StringUtils.isNullOrEmpty(location))	
			return;
		
		Block block = new Block();
		block.setLocation(location);
		block.setId(id);
		
		blockDao.updateBlockLocation(block);
	}
	
	@Override
	public List<Block> queryBlockWithHtmlid(Integer htmlId,Integer resourceId){
	    return blockDao.queryBlockWithHtmlid(htmlId, resourceId);
	}
	@Override
	public List<Block> queryBlocksWithResourceType(Block block) {
		return blockDao.queryBlocksWithResourceType(block);
	}

	public void setBlockDao(BlockDao blockDao) {
		this.blockDao = blockDao;
	}

	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}
	/**
	 * 分页发布商品块
	 */
    @Override 
    public void insertBlockQueue(int startnum, int pagesize, Integer blockid,
            Integer templateid) {
        List<Integer> itemid = itemsDao.queryOnlineItemId(startnum, pagesize);
        for (int i = 0; i < itemid.size(); i++) {
            Integer productid = itemid.get(i);
            blockDao.insertBlockQueue(blockid, templateid, productid);
        }
    } 

   public IItemsDAO getItemsDao() {
        return itemsDao;
    }

    public void setItemsDao(IItemsDAO itemsDao) {
        this.itemsDao = itemsDao;
    }
	 
	
}
