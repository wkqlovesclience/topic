package com.coo8.topic.business.interfaces;

import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.HotkeyIndex;

public interface IHotkeyIndexManager {
	public PaginatedList<HotkeyIndex> findHotkeyIndexByMap(Map<String,Object> search);
	public HotkeyIndex getById(int id);
	public int insert(HotkeyIndex entity);
	public int update(HotkeyIndex entity);
	public int delete(int id);
	public Integer getSpecificHotkeyCheck(Map<String,Object> search);
	public int getSpecificHotkeyIndexCount(Map<String,Object> search);
	public String getHotkeyTitleName(int hotkeyId);
	
	/**
	 * ����������ҳ
	 */
	public int publishHotkeyHomePage();
	
	
	/**
	 * �ֻ��˷���������ҳ
	 */
	public int publishMobileHotkeyHomePage();
	
	
	/**
	 * ���������б�ҳ
	 */
	public int publishAllHotkeyListPage();
}
