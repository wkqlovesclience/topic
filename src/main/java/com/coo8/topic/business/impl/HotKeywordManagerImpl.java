/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 热词的业务实现类
 */
package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IHotKeywordManager;
import com.coo8.topic.model.ErrorHotKeyword;
import com.coo8.topic.model.HotKeyword;
import com.coo8.topic.model.ImportLog;
import com.coo8.topic.persistence.interfaces.IHotKeywordDAO;

public class HotKeywordManagerImpl implements IHotKeywordManager {
	private IHotKeywordDAO hotKeywordDAO;

	@Transactional
	@Override
	public void add(List<HotKeyword> hotKeyWords) {
		hotKeywordDAO.add(hotKeyWords);
	}

	@Transactional
	@Override
	public void addErrorWords(List<ErrorHotKeyword> errorHotKeyWords) {
		hotKeywordDAO.addErrorWords(errorHotKeyWords);
	}

	@Transactional
	@Override
	public void addLog(ImportLog importLog) {
		hotKeywordDAO.addLog(importLog);
	}

	@Transactional
	@Override
	public void delete(List<Integer> ids) {
		hotKeywordDAO.delete(ids);
	}

	@Override
	public HotKeyword getById(Integer id) {
		return hotKeywordDAO.getById(id);
	}

	@Override
	public List<HotKeyword> getByRangeId(Map<String, Object> paramMap) {
		return hotKeywordDAO.getByRangeId(paramMap);
	}

	@Override
	public List<ErrorHotKeyword> listDownLog(Map<String, Object> paramMap) {
		return hotKeywordDAO.listDownLog(paramMap);
	}

	@Override
	public PaginatedList<HotKeyword> list(Map<String, Object> paramMap) {
		return hotKeywordDAO.list(paramMap);
	}

	@Override
	public List<HotKeyword> listAll(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return hotKeywordDAO.listAll(paramMap);
	}

	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap) {
		return hotKeywordDAO.listLog(paramMap);
	}

	@Override
	public Integer count(Map<String, Object> paramMap) {
		return hotKeywordDAO.count(paramMap);
	}

	@Transactional
	@Override
	public void update(HotKeyword hotKeyWord) {
		hotKeywordDAO.update(hotKeyWord);
	}

	@Transactional
	@Override
	public void publish(HotKeyword hotKeyWord) {
		hotKeywordDAO.publish(hotKeyWord);
	}

	public IHotKeywordDAO getHotKeywordDAO() {
		return hotKeywordDAO;
	}

	public void setHotKeywordDAO(IHotKeywordDAO hotKeywordDAO) {
		this.hotKeywordDAO = hotKeywordDAO;
	}

	@Override
	public int publishGomeHotWordsTest(int id) {
		return hotKeywordDAO.publishGomeHotWordsTest(id);
	}

	@Override
	public int publishGomeHotWords(int id) {
		return hotKeywordDAO.publishGomeHotWords(id);
	}

	@Override
	public int publishWapGomeHotWords(int id) {
		return hotKeywordDAO.publishWapGomeHotWords(id);
	}

	@Override
	public int getLastedInsertId() {
		return hotKeywordDAO.getLastedInsertId();
	}

	@Override
	public List<HotKeyword> selectHotKeywordlist(Map<String, Object> params) {

		return this.hotKeywordDAO.selectHotKeywordlist(params);
	}

}
