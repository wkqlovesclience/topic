package com.coo8.btoc.business.interfaces.block;

import java.util.List;

import com.coo8.btoc.model.block.Block;

public interface BlockManager {
	
	void createAutoBlock(Block block);
	void createTemplateManualBlock(Block block);
	void createCommonManualBlock(Block block);
	
	void updateBlock(Block block);
	void updateBlockName(Block block, String oldName);
	void updateBlockLocaotion(Integer id, String location);
	
	Block getBlockById(Integer id);
	List<Block> queryBlocks(Integer templateId);
	List<Block> queryBlocks(Block block);
	List<Block> queryBlocksWithResourceType(Block block);
	List<Block> queryBlockWithHtmlid(Integer htmlId,Integer resourceId);
	/**
	 * 分页发布商品块
	 * @param startnum商品起始位置
	 * @param pagesize商品页数
	 * @param blockid块ID
	 * @param templateid模板ID
	 */
 	void insertBlockQueue(int startnum, int pagesize, Integer blockid, Integer templateid);
 }
