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
	 * @desc ��ȡ�ؼ�����־�б�
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);

		/**
	 * @desc ��Ӵ���ؼ���
	 * @param errorAnchorKeyWords
	 */
	public void addErrorWords(List<ErrorAnchorKeyword> errorAnchorKeyWords);
	
	/**
	 * @desc �������ʧ�ܹؼ����б�
	 * @param paramMap
	 * @return
	 */
	public List<ErrorAnchorKeyword> listDownLog(Map<String, Object> paramMap);
	
	
	/**
	 * @desc �����־
	 * @param importLog
	 */
	public void addLog(ImportLog importLog);
}
