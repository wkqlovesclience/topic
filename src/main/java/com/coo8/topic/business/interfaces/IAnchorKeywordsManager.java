package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.ErrorAnchorKeyword;
import com.coo8.topic.model.ImportLog;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface IAnchorKeywordsManager{
	 

	public AnchorKeywords getById(java.lang.Integer id);
	

	public AnchorKeywords getByNamePcUrl(AnchorKeywords entity);
	
	
	public String save(AnchorKeywords entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(AnchorKeywords entity);
	
	public void deleteAll();

	public List<AnchorKeywords> findAllAnchorKeywordsList();
	
	public PaginatedList<AnchorKeywords> findLikeByMap(Map<String, Object> search);
	
	public List<AnchorKeywords> findListLikeByMap(Map<String, Object> search);
	
	/**
	 * @desc 获取关键词日志列表
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);

		/**
	 * @desc 添加错误关键词
	 * @param errorAnchorKeyWords
	 */
	public void addErrorWords(List<ErrorAnchorKeyword> errorAnchorKeyWords);
	
	/**
	 * @desc 下载添加失败关键词列表
	 * @param paramMap
	 * @return
	 */
	public List<ErrorAnchorKeyword> listDownLog(Map<String, Object> paramMap);
	
	
	/**
	 * @desc 添加日志
	 * @param importLog
	 */
	public void addLog(ImportLog importLog);
}
