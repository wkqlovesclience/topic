package com.coo8.btoc.persistence.interfaces.queue;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.queue.BlockQueue;
import com.coo8.btoc.model.queue.ProductQueue;

public interface QueueDao {
	public void insertBlockQueue(BlockQueue blockQueue);
	public void insertProductQueue(ProductQueue productQueue);
	public List<Integer> getRelatedBlockIdList(Map<String,Object> map);
}
