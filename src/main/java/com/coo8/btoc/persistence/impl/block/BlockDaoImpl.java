package com.coo8.btoc.persistence.impl.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.block.Block;
import com.coo8.btoc.persistence.interfaces.block.BlockDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

public class BlockDaoImpl extends SqlMapClientDaoSupport 
	implements BlockDao {

	@Override
	public void insert(Block block) {
		getSqlMapClientTemplate().insert("Block.insertBlock", block);
	}

	@Override
	public Block getBlockById(Integer id) {
		return (Block)getSqlMapClientTemplate()
			.queryForObject("Block.getBlockById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> queryBlocks(Block block) {
		if (isNotNeedSplitPage(block))
			return getSqlMapClientTemplate().queryForList(
					"Block.queryBlocks", block);
		
		int count = getBlockCount(block);
		if (count <= 0)
			return PaginatedArrayList.emptyList();
		
		PaginatedList<Block> blockPageList = new PaginatedArrayList<Block>(
				count, block.getPageIndex(), block.getPageSize());
		
		List<Block> temp = getSqlMapClientTemplate().queryForList(
				"Block.queryBlocks", block, blockPageList.getStartPos(), 
				block.getPageSize());
		blockPageList.addAll(temp);
		
		return blockPageList;
	}
	
	private boolean isNotNeedSplitPage(Block block) {
		return block == null || block.getPageSize() == null 
			|| block.getPageSize() < 1;
	}
	
	private int getBlockCount(Block block) {
		Integer count = (Integer) getSqlMapClientTemplate()
			.queryForObject("Block.queryBlockCount", block);
		
		return count == null ? 0 : count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> queryBlock(Integer templateId, String name) {
		Block block = new Block();
		block.setTemplateId(templateId);
		block.setName(name);
		
		return getSqlMapClientTemplate().queryForList(
				"Block.queryBlocks", block);
	}

	@Override
	public void updateBlock(Block block) {
		getSqlMapClientTemplate().update("Block.updateBlock", block);
	}
	
	@Override
	public void updateBlockLocation(Block block) {
		getSqlMapClientTemplate().update("Block.updateBlockLocation", block);
	}

	@Override
	public void insertBlockHistory(Block history) {
		getSqlMapClientTemplate().
			insert("Block.insertBlockContetnHistory", history);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> queryBlocksWithResourceType(Block block) {
		return getSqlMapClientTemplate().queryForList(
				"Block.queryBlockWithResource", block);
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Block> queryBlockWithHtmlid(Integer htmlId, Integer resourceId) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("htmlId",htmlId);
        map.put("resourceId", resourceId);
        return getSqlMapClientTemplate().queryForList(
                "Block.queryBlockWithHtmlid", map);
    }
    /**
     * ²åÈë¿éµ½q±í
     */
    @Override
    public void insertBlockQueue(Integer blockid, Integer templateid,
            Integer productid) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("templateid", templateid);
        map.put("blockid", blockid);
        map.put("productid", productid);
        getSqlMapClientTemplate().insert("Block.insertBlockQueue", map);
    }
	
}
