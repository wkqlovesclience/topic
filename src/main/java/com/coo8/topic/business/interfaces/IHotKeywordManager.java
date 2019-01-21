/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company �����������޹�˾
 * @desc �ȴʵ�ҵ���ӿ� 
 */
package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotKeyword;
import com.coo8.topic.model.HotKeyword;
import com.coo8.topic.model.ImportLog;

public interface IHotKeywordManager {
	/**
	 * @desc ����ȴ�
	 * @param hotKeyWord
	 */
	public void add(List<HotKeyword> hotKeyWords);

	/**
	 * @desc ��Ӵ����ȴ�
	 * @param errorHotKeyWords
	 */
	public void addErrorWords(List<ErrorHotKeyword> errorHotKeyWords);

	/**
	 * @desc �����־
	 * @param importLog
	 */
	public void addLog(ImportLog importLog);

	/**
	 * @desc ɾ���ȴ�
	 * @param id
	 */
	public void delete(List<Integer> ids);

	/**
	 * @desc ��ȡĳ���ȴ�
	 * @param id
	 */
	public HotKeyword getById(Integer id);

	/**
	 * @desc ��ȡ�ȴʷ�Χ
	 * @param paramMap
	 */
	public List<HotKeyword> getByRangeId(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ��б�
	 * @param paramMap
	 */
	public PaginatedList<HotKeyword> list(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ����ҳ�ȴ��б�
	 * @param paramMap
	 */
	public List<HotKeyword> listAll(Map<String, Object> paramMap);

	/**
	 * @desc �������ʧ���ȴ��б�
	 * @param paramMap
	 * @return
	 */
	public List<ErrorHotKeyword> listDownLog(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ���־�б�
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ��б�����
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);

	/**
	 * @desc �޸��ȴ�
	 * @param hotKeyWord
	 */
	public void update(HotKeyword hotKeyWord);

	/**
	 * @desc �����ȴ�
	 * @param hotKeyWord
	 */
	public void publish(HotKeyword hotKeyWord);

	/**
	 * ��������Ԥ��
	 */
	public int publishGomeHotWordsTest(int id);

	/**
	 * �������򣺷����洢���̵ĵ���
	 */
	public int publishGomeHotWords(int id);

	/**
	 * �������򣺷����洢���̵ĵ���
	 */
	public int publishWapGomeHotWords(int id);

	/**
	 * ��ȡ���²�����ȴ�ID
	 */
	public int getLastedInsertId();

	public List<HotKeyword> selectHotKeywordlist(Map<String, Object> params);
}
