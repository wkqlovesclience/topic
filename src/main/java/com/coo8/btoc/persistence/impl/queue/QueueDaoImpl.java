package com.coo8.btoc.persistence.impl.queue;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.queue.BlockQueue;
import com.coo8.btoc.model.queue.ProductQueue;
import com.coo8.btoc.persistence.interfaces.queue.QueueDao;

public class QueueDaoImpl extends SqlMapClientDaoSupport implements QueueDao {

	private String getNameSpace(){
		return "Queue";
	}
	
	@Override
	public void insertBlockQueue(BlockQueue blockQueue) {
		getSqlMapClientTemplate().insert(getNameSpace()+".insertBlockQueue", blockQueue);
	}

	@Override
	public void insertProductQueue(ProductQueue productQueue) {
		getSqlMapClientTemplate().insert(getNameSpace()+".insertProductQueue", productQueue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getRelatedBlockIdList(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(getNameSpace()+".getRelatedBlockIdList", map);
	}

}
