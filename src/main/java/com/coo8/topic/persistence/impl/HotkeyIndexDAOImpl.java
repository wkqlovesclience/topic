package com.coo8.topic.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.HotkeyIndex;
import com.coo8.topic.persistence.interfaces.IHotkeyIndexDAO;

public class HotkeyIndexDAOImpl extends SqlMapClientDaoSupport implements IHotkeyIndexDAO {

	@Override
	public PaginatedList<HotkeyIndex> findHotkeyIndexByMap(
			Map<String, Object> search) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("HotKeyIndexs.queryListCount", search);
		PaginatedList<HotkeyIndex> paginatedArrayList = new PaginatedArrayList<HotkeyIndex>(count, (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		@SuppressWarnings("unchecked")
		List<HotkeyIndex> list = getSqlMapClientTemplate().queryForList("HotKeyIndexs.queryList",search,paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if(list != null){
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList;
	}

	@Override
	public HotkeyIndex getById(int id) {
		return  (HotkeyIndex) getSqlMapClientTemplate().queryForObject("HotKeyIndexs.getById", id);
	}

	@Override
	public int insert(HotkeyIndex entity) {
		Object result = null;
		result = getSqlMapClientTemplate().insert("HotKeyIndexs.insertHotkeyIndex", entity);
		if(result == null){
			return 1;
		}
		else{			
			return 0;
		}
	}

	@Override
	public int update(HotkeyIndex entity) {
		return getSqlMapClientTemplate().update("HotKeyIndexs.updateHotkeyIndex", entity);
	}

	@Override
	public int delete(int id) {
		return getSqlMapClientTemplate().delete("HotKeyIndexs.deleteHotkeyIndex", id);
	}

	@Override
	public Integer getSpecificHotkeyCheck(Map<String, Object> search) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HotKeyIndexs.getSpecificHotkeyCheck", search);
	}

	@Override
	public int getSpecificHotkeyIndexCount(Map<String, Object> search) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HotKeyIndexs.getSpecificHotkeyIndexCount", search);
	}

	@Override
	public String getHotkeyTitleName(int hotkeyId) {
		return (String) getSqlMapClientTemplate().queryForObject("HotKeyIndexs.getHotkeyTitleName", hotkeyId);
	}

	@Override
	public int publishHotkeyHomePage() {
		return getSqlMapClientTemplate().update("HotKeyIndexs.publishHotkeyHomePage");
	}
	
	@Override
	public int publishMobileHotkeyHomePage() {
		return getSqlMapClientTemplate().update("HotKeyIndexs.publishMobileHotkeyHomePage");
	}

	@Override
	public int publishAllHotkeyListPage() {
		return getSqlMapClientTemplate().update("HotKeyIndexs.publishAllHotkeyListPage");
	}

}
