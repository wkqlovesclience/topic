package com.coo8.topic.persistence.interfaces;

import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.HotSearchIndex;

public interface IHotSearchIndexDAO {
	public PaginatedList<HotSearchIndex> findHotSearchIndexByMap(Map<String, Object> search);

	public HotSearchIndex getById(int id);

	public int insert(HotSearchIndex entity);

	public int update(HotSearchIndex entity);

	public int delete(int id);

	public Integer getSpecificHotSearchCheck(Map<String, Object> search);

	public int getSpecificHotSearchIndexCount(Map<String, Object> search);

	public String getHotSearchTitleName(int hotSearchId);

	/**
	 * ����������ҳ
	 */
	public int publishHotSearchHomePage();

	/**
	 * ����������ҳ
	 */
	public int publishMobileHotSearchHomePage();

	/**
	 * ���������б�ҳ
	 */
	public int publishAllHotSearchListPage();
}
