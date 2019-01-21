package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IKeywordsManager;
import com.coo8.topic.model.Keywords;
import com.coo8.topic.persistence.interfaces.IKeywordsDAO;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


 
public class KeywordsManagerImpl   implements IKeywordsManager{

	private IKeywordsDAO keywordsDAO;
	 
	public void setKeywordsDAO(IKeywordsDAO dao) {
		this.keywordsDAO = dao;
	} 

	 
	@Transactional(readOnly=true)
    @Override
	public Keywords getById(java.lang.Integer id){
		return keywordsDAO.getById( id);
	}
	@Transactional
    @Override
	public void deleteById(java.lang.Integer id){
		keywordsDAO.deleteById(id);
	}
	 
	@Transactional
    @Override
	public int save(Keywords entity){
		return keywordsDAO.save(entity);
	}
	 
	@Transactional
	@Override
	public void update(Keywords entity){
		keywordsDAO.update(entity);
	}
 
	@Transactional(readOnly=true)
	@Override
	public List<Keywords> findAll(Map<String, Object> search){
		return keywordsDAO.findByMap(search);
	}
	@Transactional(readOnly=true)
	@Override
	   public List<Keywords> findByLike(Map<String, Object> search){
			return keywordsDAO.findByLike(search);
		} 
 
	
	@Override
	public PaginatedList<Keywords> findPageByMap(Map<String, Object> Keywords) {
		return keywordsDAO.findPageByMap(Keywords);
	}
	
	@Override
	public PaginatedList<Keywords> findPageByMapLike(Map<String, Object> Keywords) {
		return keywordsDAO.findPageByMapLike(Keywords);
	}
	@Override
	public List<Keywords> findAllTab(Map<String, Object> search){
		return keywordsDAO.findAllTab(search);
	}
}
