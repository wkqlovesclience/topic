package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IAllHotKeywordManager;
import com.coo8.topic.model.AllHotKeyword;
import com.coo8.topic.model.ErrorAllHotkeyword;
import com.coo8.topic.model.ImportAllKeywordLog;
import com.coo8.topic.persistence.interfaces.IAllHotKeywordDAO;

public class AllHotKeywordManagerImpl implements IAllHotKeywordManager {

	private IAllHotKeywordDAO allHotKeywordDAO;

	@Transactional
	@Override
	public void addAllHotKey(AllHotKeyword allHotKeyword) {
		this.allHotKeywordDAO.addAllHotKey(allHotKeyword);

	}

	@Override
	public void addAllHotKeyLog(ImportAllKeywordLog importAllHotKey) {
		this.allHotKeywordDAO.addAllHotKeyLog(importAllHotKey);
	}

	@Override
	public void addAllErrorHotKey(ErrorAllHotkeyword errorAllHotkeyword) {
		this.allHotKeywordDAO.addAllErrorHotKey(errorAllHotkeyword);

	}

	@Transactional
	@Override
	public void addBatchAllHotKey(List<AllHotKeyword> allHotKeywordList) {

		if (allHotKeywordList != null && !allHotKeywordList.isEmpty()) {
			for (AllHotKeyword allHotKeyword : allHotKeywordList) {
				this.allHotKeywordDAO.addAllHotKey(allHotKeyword);
			}
		}

	}

	@Override
	public List<AllHotKeyword> selectAllHotKeyword(Map<String, Object> params) {

		return this.allHotKeywordDAO.selectAllHotKeyword(params);
	}

	@Override
	public PaginatedList<AllHotKeyword> listAllHotKeywordPage(Map<String, Object> paramMap) {

		PaginatedList<AllHotKeyword> paginatedArrayList = this.allHotKeywordDAO.listAllHotKeywordPage(paramMap);

		return paginatedArrayList;
	}

	public IAllHotKeywordDAO getAllHotKeywordDAO() {
		return allHotKeywordDAO;
	}

	public void setAllHotKeywordDAO(IAllHotKeywordDAO allHotKeywordDAO) {
		this.allHotKeywordDAO = allHotKeywordDAO;
	}

}
