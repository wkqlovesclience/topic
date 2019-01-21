package com.coo8.btoc.persistence.interfaces.block;

import java.util.List;

import com.coo8.btoc.model.block.Block;


public interface BlockDao {
	
	void insert(Block block);
	void updateBlock(Block block);
	void updateBlockLocation(Block block);
	
	Block getBlockById(Integer id);
	List<Block> queryBlocks(Block block);
	List<Block> queryBlocksWithResourceType(Block block);
	List<Block> queryBlock(Integer templateId, String name);

	
	void insertBlockHistory(Block history);
	List<Block> queryBlockWithHtmlid(Integer htmlId,Integer resourceId);
	void insertBlockQueue(Integer blockid,Integer templateid,Integer productid);
}
