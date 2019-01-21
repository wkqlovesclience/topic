package com.coo8.topic.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.HotSearchIndex;
import com.coo8.topic.persistence.interfaces.IHotSearchIndexDAO;

public class HotSearchIndexDAOImpl extends SqlMapClientDaoSupport implements IHotSearchIndexDAO {

	@Override
	public PaginatedList<HotSearchIndex> findHotSearchIndexByMap(Map<String, Object> search) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("HotSearchIndexs.queryListCount", search);
		PaginatedList<HotSearchIndex> paginatedArrayList = new PaginatedArrayList<HotSearchIndex>(count,
				(Integer) search.get("pageNumber"), (Integer) search.get("pageSize"));
		@SuppressWarnings("unchecked")
		List<HotSearchIndex> list = getSqlMapClientTemplate().queryForList("HotSearchIndexs.queryList", search,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) {
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList;
	}

	@Override
	public HotSearchIndex getById(int id) {
		return (HotSearchIndex) getSqlMapClientTemplate().queryForObject("HotSearchIndexs.getById", id);
	}

	@Override
	public int insert(HotSearchIndex entity) {
		Object result = null;
		result = getSqlMapClientTemplate().insert("HotSearchIndexs.insertHotSearchIndex", entity);
		if (result == null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int update(HotSearchIndex entity) {
		return getSqlMapClientTemplate().update("HotSearchIndexs.updateHotSearchIndex", entity);
	}

	@Override
	public int delete(int id) {
		return getSqlMapClientTemplate().delete("HotSearchIndexs.deleteHotSearchIndex", id);
	}

	@Override
	public Integer getSpecificHotSearchCheck(Map<String, Object> search) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HotSearchIndexs.getSpecificHotSearchCheck", search);
	}

	@Override
	public int getSpecificHotSearchIndexCount(Map<String, Object> search) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HotSearchIndexs.getSpecificHotSearchIndexCount",
				search);
	}

	@Override
	public String getHotSearchTitleName(int hotSearchId) {
		return (String) getSqlMapClientTemplate().queryForObject("HotSearchIndexs.getHotSearchTitleName", hotSearchId);
	}

	@Override
	public int publishHotSearchHomePage() {
		return getSqlMapClientTemplate().update("HotSearchIndexs.publishAllHotSearchListPage");
	}

	@Override
	public int publishMobileHotSearchHomePage() {
		return getSqlMapClientTemplate().update("HotSearchIndexs.publishMobileHotSearchHomePage");
	}

	@Override
	public int publishAllHotSearchListPage() {
		return getSqlMapClientTemplate().update("HotSearchIndexs.publishAllHotSearchListPage");
	}

}
