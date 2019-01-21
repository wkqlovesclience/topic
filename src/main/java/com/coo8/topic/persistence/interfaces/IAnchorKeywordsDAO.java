/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company �����������޹�˾
 * @desc �ȴ����ݳ־ò�ӿ�
 */
package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.ErrorAnchorKeyword;
import com.coo8.topic.model.ImportLog;


public interface IAnchorKeywordsDAO{
   
	public List<AnchorKeywords>  findAnchorKeywords();
	
	public int deleteAll();
	
	public String save(AnchorKeywords anchorKeywords);
	
	public int update(AnchorKeywords anchorKeywords);
	
	public int delete(java.lang.Integer id); 
	
	public AnchorKeywords getById(java.lang.Integer id);
	
	public PaginatedList<AnchorKeywords> findByMap(Map<String, Object> search);
	
	public List<AnchorKeywords> findListLikeByMap(Map<String, Object> search);
	
	/**
	 * @desc ��ȡ�ؼ�����־�б�
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);
	
	/**
	 * @desc ��Ӵ���ؼ���
	 * @param errorHotKeyWords
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
	

	public AnchorKeywords getByNamePcUrl(AnchorKeywords entity);

}
