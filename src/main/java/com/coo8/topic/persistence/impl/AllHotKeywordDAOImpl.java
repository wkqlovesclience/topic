package com.coo8.topic.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AllHotKeyword;
import com.coo8.topic.model.ErrorAllHotkeyword;
import com.coo8.topic.model.ImportAllKeywordLog;
import com.coo8.topic.persistence.interfaces.IAllHotKeywordDAO;

public class AllHotKeywordDAOImpl extends SqlMapClientDaoSupport implements IAllHotKeywordDAO {

	@Override
	public void addAllHotKey(AllHotKeyword allHotKeyword) {

		if (allHotKeyword != null) {
			this.getSqlMapClientTemplate().insert("AllHotKeyword.addAllHotKey", allHotKeyword);
		}
	}

	@Override
	public void addAllHotKeyLog(ImportAllKeywordLog importAllHotKey) {

		if (importAllHotKey != null) {
			this.getSqlMapClientTemplate().insert("AllHotKeyword.addAllHotKeyLog", importAllHotKey);
		}

	}

	@Override
	public void addAllErrorHotKey(ErrorAllHotkeyword errorAllHotkeyword) {
		if (errorAllHotkeyword != null) {
			this.getSqlMapClientTemplate().insert("AllHotKeyword.addAllErrorHotKey", errorAllHotkeyword);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllHotKeyword> selectAllHotKeyword(Map<String, Object> params) {
		return this.getSqlMapClientTemplate().queryForList("AllHotKeyword.selectAllHotKeyword", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<AllHotKeyword> listAllHotKeywordPage(Map<String, Object> paramMap) {
		Object o = this.getSqlMapClientTemplate().queryForObject("AllHotKeyword.count", paramMap);
		if (o == null)
			return null;

		PaginatedList<AllHotKeyword> paginatedArrayList = new PaginatedArrayList<AllHotKeyword>(
				Integer.parseInt(o.toString()), (Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<AllHotKeyword> list = this.getSqlMapClientTemplate().queryForList("AllHotKeyword.list", paramMap,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());

		if (list != null)
			paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

}
