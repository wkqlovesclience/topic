package com.coo8.topic.business.impl;

import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IHotkeyIndexManager;
import com.coo8.topic.model.HotkeyIndex;
import com.coo8.topic.persistence.interfaces.IHotkeyIndexDAO;

public class HotkeyIndexManagerImpl implements IHotkeyIndexManager {
	
	private IHotkeyIndexDAO hotkeyIndexDao;

	public void setHotkeyIndexDao(IHotkeyIndexDAO hotkeyIndexDao) {
		this.hotkeyIndexDao = hotkeyIndexDao;
	}

	public IHotkeyIndexDAO getHotkeyIndexDao() {
		return hotkeyIndexDao;
	}

	@Override
	public PaginatedList<HotkeyIndex> findHotkeyIndexByMap(
			Map<String, Object> search) {
		return hotkeyIndexDao.findHotkeyIndexByMap(search);
	}

	@Override
	public HotkeyIndex getById(int id) {
		return hotkeyIndexDao.getById(id);
	}

	@Override
	public int insert(HotkeyIndex entity) {
		return hotkeyIndexDao.insert(entity);
	}

	@Override
	public int update(HotkeyIndex entity) {
		return hotkeyIndexDao.update(entity);
	}

	@Override
	public int delete(int id) {
		return hotkeyIndexDao.delete(id);
	}

	@Override
	public Integer getSpecificHotkeyCheck(Map<String, Object> search) {
		return hotkeyIndexDao.getSpecificHotkeyCheck(search);
	}

	@Override
	public int getSpecificHotkeyIndexCount(Map<String, Object> search) {
		return hotkeyIndexDao.getSpecificHotkeyIndexCount(search);
	}

	@Override
	public String getHotkeyTitleName(int hotkeyId) {
		return hotkeyIndexDao.getHotkeyTitleName(hotkeyId);
	}

	@Override
	public int publishHotkeyHomePage() {
		return hotkeyIndexDao.publishHotkeyHomePage();
	}

	
	@Override
	public int publishMobileHotkeyHomePage() {
		return hotkeyIndexDao.publishMobileHotkeyHomePage();
	}

	
	@Override
	public int publishAllHotkeyListPage() {
		return hotkeyIndexDao.publishAllHotkeyListPage();
	}
	
}
