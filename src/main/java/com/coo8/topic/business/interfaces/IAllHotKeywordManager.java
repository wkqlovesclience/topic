package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AllHotKeyword;
import com.coo8.topic.model.ErrorAllHotkeyword;
import com.coo8.topic.model.ImportAllKeywordLog;

public interface IAllHotKeywordManager {

	public void addAllHotKey(AllHotKeyword allHotKeyword);

	public void addAllHotKeyLog(ImportAllKeywordLog importAllHotKey);

	public void addAllErrorHotKey(ErrorAllHotkeyword errorAllHotkeyword);

	public void addBatchAllHotKey(List<AllHotKeyword> allHotKeyword);

	public List<AllHotKeyword> selectAllHotKeyword(Map<String, Object> params);

	public PaginatedList<AllHotKeyword> listAllHotKeywordPage(Map<String, Object> paramMap);

}
