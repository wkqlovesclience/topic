package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IAnchorKeywordsManager;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.ErrorAnchorKeyword;
import com.coo8.topic.model.ErrorHotKeyword;
import com.coo8.topic.model.ImportLog;
import com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO;

public class AnchorKeywordsManagerImpl implements IAnchorKeywordsManager {

	
	private IAnchorKeywordsDAO anchorKeywordsDAO;
	
	/**
	 * @return the anchorKeywordsDAO
	 */
	public IAnchorKeywordsDAO getAnchorKeywordsDAO() {
		return anchorKeywordsDAO;
	}

	/**
	 * @param anchorKeywordsDAO the anchorKeywordsDAO to set
	 */
	public void setAnchorKeywordsDAO(IAnchorKeywordsDAO anchorKeywordsDAO) {
		this.anchorKeywordsDAO = anchorKeywordsDAO;
	}

	@Transactional(readOnly = true)
	@Override
	public AnchorKeywords getById(Integer id) {
		return anchorKeywordsDAO.getById(id);
	}

	@Transactional
	@Override
	public String save(AnchorKeywords entity) {
		if (entity.getId() != null && !Integer.toString(entity.getId()).equals(""))
		{
			anchorKeywordsDAO.update(entity);
			return entity.getId().toString();
		}
		else
		{
			return anchorKeywordsDAO.save(entity);
		}
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		anchorKeywordsDAO.delete(id);
	}
	
	@Transactional
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		anchorKeywordsDAO.deleteAll();
	}
	

	@Override
	public void update(AnchorKeywords entity) {
		// TODO Auto-generated method stub
		anchorKeywordsDAO.update(entity);
	}

	@Override
	public List<AnchorKeywords> findAllAnchorKeywordsList() {
		// TODO Auto-generated method stub
		return anchorKeywordsDAO.findAnchorKeywords();
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#findLikeByMap(java.util.Map)
	 */
	@Override
	public PaginatedList<AnchorKeywords> findLikeByMap(
			Map<String, Object> search) {
		return anchorKeywordsDAO.findByMap(search);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#findListLikeByMap(java.util.Map)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<AnchorKeywords> findListLikeByMap(Map<String, Object> search) {
		return anchorKeywordsDAO.findListLikeByMap(search);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#listLog(java.util.Map)
	 */
	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap) {
		return anchorKeywordsDAO.listLog(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#addErrorWords(java.util.List)
	 */
	@Override
	public void addErrorWords(List<ErrorAnchorKeyword> errorAnchorKeyWords) {
		// TODO Auto-generated method stub
		anchorKeywordsDAO.addErrorWords(errorAnchorKeyWords);
	}

	
	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#listDownLog(java.util.Map)
	 */
	@Override
	public List<ErrorAnchorKeyword> listDownLog(Map<String, Object> paramMap) {
		return anchorKeywordsDAO.listDownLog(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#addLog(com.coo8.topic.model.ImportLog)
	 */
	@Override
	public void addLog(ImportLog importLog) {
		// TODO Auto-generated method stub
		anchorKeywordsDAO.addLog(importLog);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.IAnchorKeywordsManager#getByNamePcUrl(com.coo8.topic.model.AnchorKeywords)
	 */
	@Override
	public AnchorKeywords getByNamePcUrl(AnchorKeywords entity) {
		return anchorKeywordsDAO.getByNamePcUrl(entity);
	}

	
	
}
