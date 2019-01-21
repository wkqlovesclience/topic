package com.coo8.btoc.business.impl.rank;

import java.util.Map;

import com.coo8.btoc.business.interfaces.rank.ISingleTopRankingManager;
import com.coo8.btoc.model.rank.SingleTopRanking;
import com.coo8.btoc.persistence.interfaces.rank.ISingleTopRankingDao;
import com.coo8.btoc.util.pages.PaginatedList;

public class SingleTopRasnkingManagerImpl implements ISingleTopRankingManager {

	private ISingleTopRankingDao singleTopRankingDao;
	
	public ISingleTopRankingDao getSingleTopRankingDao() {
		return singleTopRankingDao;
	}

	public void setSingleTopRankingDao(ISingleTopRankingDao singleTopRankingDao) {
		this.singleTopRankingDao = singleTopRankingDao;
	}

	@Override
	public SingleTopRanking getById(int id) {
		return singleTopRankingDao.getById(id);
	}

	@Override
	public int insert(SingleTopRanking ranking) {
		return singleTopRankingDao.insert(ranking);
	}

	@Override
	public int update(SingleTopRanking ranking) {
		return singleTopRankingDao.update(ranking);
	}

	@Override
	public int delete(int id) {
		return singleTopRankingDao.delete(id);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return singleTopRankingDao.getTotalCount(map);
	}

	@Override
	public PaginatedList<SingleTopRanking> getListByWheres(Map<String, Object> map) {
		return singleTopRankingDao.getListByWheres(map);
	}

	@Override
	public int isHasChildren(int id) {
		return singleTopRankingDao.isHasChildren(id);
	}

	@Override
	public int changeState(Map<String, Object> map) {
		return singleTopRankingDao.changeState(map);
	}

	@Override
	public int batchDeleteChildren(int parent) {
		return singleTopRankingDao.batchDeleteChildren(parent);
	}

}
