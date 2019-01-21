package com.coo8.btoc.business.interfaces.queue;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.queue.BlockQueue;
import com.coo8.btoc.model.queue.ProductQueue;

public interface QueueManager {
	public void insertBlockQueue(BlockQueue blockQueue);
	public void insertProductQueue(ProductQueue productQueue);
	public List<Integer> getRelatedBlockIdList(Map<String,Object> map);
	
	public void publish(int rfid,int templateId,int product_type,int product_rtype,int block_rtype);
}
