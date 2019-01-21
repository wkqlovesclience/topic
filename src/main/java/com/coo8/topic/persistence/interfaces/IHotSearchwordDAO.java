package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotSearchword;
import com.coo8.topic.model.HotSearchword;
import com.coo8.topic.model.ImportSearchLog;

public interface IHotSearchwordDAO {

	/**
	 * @desc ����ȴ�
	 * @param hotSearchword
	 */
	public void add(List<HotSearchword> hotSearchword);

	/**
	 * @desc ��Ӵ����ȴ�
	 * @param errorHotSearchWords
	 */
	public void addErrorWords(List<ErrorHotSearchword> errorHotSearchWords);

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
	 * @desc ��ȡĳ���ȴ�
	 * @param id
	 */
	public HotSearchword getById(Integer id);

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
	 * @desc ��ȡ�ȴ���־�б�
	 * @param paramMap
	 */
	public PaginatedList<ImportSearchLog> listLog(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴʷ�Χ
	 * @param paramMap
	 */
	public List<HotSearchword> getByRangeId(Map<String, Object> paramMap);

	/**
	 * @desc �������ʧ���ȴ��б�
	 * @param paramMap
	 * @return
	 */
	public List<ErrorHotSearchword> listDownLog(Map<String, Object> paramMap);

	/**
	 * @desc ��ȡ�ȴ��б�����
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);

	/**
	 * @desc �޸��ȴ�
	 * @param hotKeyWord
	 */
	public void update(HotSearchword hotKeyWord);

	/**
	 * @desc �����ȴ�
	 * @param hotKeyWord
	 */
	public void publish(HotSearchword hotKeyWord);

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
	public List<HotSearchword> getHotkeyRelatedKeywords(Map<String, Object> paramMap);
	
	/**
	 * ��ѯָ��������ָ��״̬�ȴ�
	 * @param paramMap
	 * @return
	 */
	public List<Integer> listLimit(Map<String, Object> paramMap);
	

}
