package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AllHotKeyword;
import com.coo8.topic.model.ErrorAllHotkeyword;
import com.coo8.topic.model.ImportAllKeywordLog;

public interface IAllHotKeywordDAO {
	/**
	 * 保存热词
	 * 
	 * @param allHotKeyword
	 */
	public void addAllHotKey(AllHotKeyword allHotKeyword);

	/**
	 * 记录导入热词日志
	 * 
	 * @param importAllHotKey
	 */
	public void addAllHotKeyLog(ImportAllKeywordLog importAllHotKey);

	/**
	 * 
	 * @param errorAllHotkeyword
	 */
	public void addAllErrorHotKey(ErrorAllHotkeyword errorAllHotkeyword);

	public List<AllHotKeyword> selectAllHotKeyword(Map<String, Object> params);

	public PaginatedList<AllHotKeyword> listAllHotKeywordPage(Map<String, Object> paramMap);

}
