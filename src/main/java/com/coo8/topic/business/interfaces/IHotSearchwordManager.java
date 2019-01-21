package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotSearchword;
import com.coo8.topic.model.HotSearchword;
import com.coo8.topic.model.ImportSearchLog;

/**
 * �����ȴʹ���
 * 
 * @author fanqingxia
 *
 */
public interface IHotSearchwordManager {
	/**
	 * @desc ����ȴ�
	 * @param hotSearchword
	 */
	public void add(List<HotSearchword> hotSearchword);

	/**
	 * @desc �����־
	 * @param importLog
	 */
	public void addLog(ImportSearchLog importSearchLog);

	/**
	 * @desc ɾ���ȴ�
	 * @param id
	 */
	public void delete(List<Integer> ids);

	/**
	 * @desc ��Ӵ����ȴ�
	 * @param errorHotKeyWords
	 */
	public void addErrorWords(List<ErrorHotSearchword> errorHotSearchWords);

	/**
	 * @desc ��ȡĳ���ȴ�
	 * @param id
	 */
	public HotSearchword getById(Integer id);

	/**
	 * @desc ��ȡ�ȴʷ�Χ
	 * @param paramMap
	 */
	public List<HotSearchword> getByRangeId(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ��б�
	 * @param paramMap
	 */
	public PaginatedList<HotSearchword> list(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ����ҳ�ȴ��б�
	 * @param paramMap
	 */
	public List<HotSearchword> listAll(Map<String, Object> paramMap);

	/**
	 * @desc �������ʧ���ȴ��б�
	 * @param paramMap
	 * @return
	 */
	public List<ErrorHotSearchword> listDownLog(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ���־�б�
	 * @param paramMap
	 */
	public PaginatedList<ImportSearchLog> listLog(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ��б�����
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);

	/**
	 * @desc �޸��ȴ�
	 * @param HotSearchword
	 */
	public void update(HotSearchword HotSearchword);

	/**
	 * @desc �����ȴ�
	 * @param HotSearchword
	 */
	public void publish(HotSearchword HotSearchword);

	/**
	 * ��ȡ���²�����ȴ�ID
	 */
	public int getLastedInsertId();

	/**
	 * ��ģ����ѯ�ȴ�
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<HotSearchword> listHotSearch(Map<String, Object> paramMap);

	/**
	 * ��ѯ������Ѵ�
	 * 
	 * @param map
	 * @return
	 */
	public List<HotSearchword> getHotkeyRelatedKeywords(Map<String, Object> map);

	/**
	 * ��ʼ������ȴ�
	 */
	public void initReatedHotWords() throws Exception;
	
	/**
	 * ��������ѯָ��������ָ��״̬ �ȴ�
	 * @param paramMap
	 * @return
	 */
	List<Integer> listLimit(Map<String, Object> paramMap);
}
