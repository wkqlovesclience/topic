package com.coo8.btoc.persistence.impl.rank;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.rank.SingleTopRanking;
import com.coo8.btoc.persistence.interfaces.rank.ISingleTopRankingDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

public class SingleTopRankingDaoImpl extends SqlMapClientDaoSupport implements ISingleTopRankingDao{

	private String getNameSpaces(){
		return "SingleTopRankings";
	}
	@Override
	public SingleTopRanking getById(int id) {
		return (SingleTopRanking) getSqlMapClientTemplate().queryForObject(getNameSpaces()+".getById", id);
	}

	@Override
	public int insert(SingleTopRanking ranking) {
		return (Integer) getSqlMapClientTemplate().insert(getNameSpaces()+".insert", ranking);
	}

	@Override
	public int update(SingleTopRanking ranking) {
		return getSqlMapClientTemplate().update(getNameSpaces()+".update",ranking);
	}

	@Override
	public int delete(int id) {
		return getSqlMapClientTemplate().delete(getNameSpaces()+".delete", id);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNameSpaces()+".getTotalCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<SingleTopRanking> getListByWheres(Map<String, Object> map) {
		int totalCount = getTotalCount(map);
		if(totalCount == 0){
			return null;
		}
		PaginatedList<SingleTopRanking> paginatedArrayList = new PaginatedArrayList<SingleTopRanking>(totalCount,(Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
		List<SingleTopRanking> list = getSqlMapClientTemplate().queryForList(getNameSpaces()+".getListByWheres",map, paginatedArrayList.getStartPos(),paginatedArrayList.getEndPos());
		if(list!=null){
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList;
	}

	@Override
	public int isHasChildren(int id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNameSpaces()+".hasChildrenOrNot", id);
	}
	
	@Override
	public int changeState(Map<String, Object> map) {
		return getSqlMapClientTemplate().update(getNameSpaces()+".changeState", map);
	}
	@Override
	public int batchDeleteChildren(int parent) {
		return getSqlMapClientTemplate().delete(getNameSpaces()+".batchDelete", parent);
	}

}
