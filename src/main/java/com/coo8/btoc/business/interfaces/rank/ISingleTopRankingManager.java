package com.coo8.btoc.business.interfaces.rank;

import java.util.Map;

import com.coo8.btoc.model.rank.SingleTopRanking;
import com.coo8.btoc.util.pages.PaginatedList;

public interface ISingleTopRankingManager {
	public SingleTopRanking getById(int id);
	public int insert(SingleTopRanking ranking);
	public int update(SingleTopRanking ranking);
	public int delete(int id);
	
	public int getTotalCount(Map<String,Object> map);
	public PaginatedList<SingleTopRanking> getListByWheres(Map<String,Object> map);
	
	public int isHasChildren(int id);
	
	public int changeState(Map<String,Object> map);
	public int batchDeleteChildren(int parent);
}
