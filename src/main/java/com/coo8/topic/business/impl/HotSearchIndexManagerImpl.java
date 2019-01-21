package com.coo8.topic.business.impl;

import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IHotSearchIndexManager;
import com.coo8.topic.model.HotSearchIndex;
import com.coo8.topic.persistence.interfaces.IHotSearchIndexDAO;

public class HotSearchIndexManagerImpl implements IHotSearchIndexManager {

	private IHotSearchIndexDAO hotSearchIndexDao;

	public void setHotSearchIndexDao(IHotSearchIndexDAO hotSearchIndexDao) {
		this.hotSearchIndexDao = hotSearchIndexDao;
	}

	public IHotSearchIndexDAO getHotSearchIndexDao() {
		return hotSearchIndexDao;
	}

	@Override
	public PaginatedList<HotSearchIndex> findHotSearchIndexByMap(Map<String, Object> search) {
		return hotSearchIndexDao.findHotSearchIndexByMap(search);
	}

	@Override
	public HotSearchIndex getById(int id) {
		return hotSearchIndexDao.getById(id);
	}

	@Override
	public int insert(HotSearchIndex entity) {
		return hotSearchIndexDao.insert(entity);
	}

	@Override
	public int update(HotSearchIndex entity) {
		return hotSearchIndexDao.update(entity);
	}

	@Override
	public int delete(int id) {
		return hotSearchIndexDao.delete(id);
	}

	@Override
	public Integer getSpecificHotSearchCheck(Map<String, Object> search) {
		return hotSearchIndexDao.getSpecificHotSearchCheck(search);
	}

	@Override
	public int getSpecificHotSearchIndexCount(Map<String, Object> search) {
		return hotSearchIndexDao.getSpecificHotSearchIndexCount(search);
	}

	@Override
	public String getHotSearchTitleName(int hotSearchId) {
		return hotSearchIndexDao.getHotSearchTitleName(hotSearchId);
	}

	@Override
	public int publishHotSearchHomePage() {
		return hotSearchIndexDao.publishHotSearchHomePage();
	}

	@Override
	public int publishMobileHotSearchHomePage() {
		return hotSearchIndexDao.publishMobileHotSearchHomePage();
	}

	@Override
	public int publishAllHotSearchListPage() {
		return hotSearchIndexDao.publishAllHotSearchListPage();
	}

}
