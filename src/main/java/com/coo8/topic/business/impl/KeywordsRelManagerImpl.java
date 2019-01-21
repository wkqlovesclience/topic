package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.topic.business.interfaces.IKeywordsRelManager;
import com.coo8.topic.model.KeywordsRel;
import com.coo8.topic.persistence.interfaces.IKeywordsRelDAO;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


 
public class KeywordsRelManagerImpl   implements IKeywordsRelManager{

	private IKeywordsRelDAO keywordsRelDAO;
	
	public void setKeywordsRelDAO(IKeywordsRelDAO dao) {
		this.keywordsRelDAO = dao;
	} 

	 
	@Transactional(readOnly=true)
	@Override
	public KeywordsRel getById(java.lang.Integer id){
		return keywordsRelDAO.getById( id);
	}
	@Transactional
	@Override
	public void deleteById(java.lang.Integer id){
		keywordsRelDAO.deleteById(id);
	}
	 
	@Transactional
	@Override
	public void save(KeywordsRel entity){
		keywordsRelDAO.save(entity);
	}
	 
	@Transactional
	@Override
	public void update(KeywordsRel entity){
		keywordsRelDAO.update(entity);
	}
 
	@Transactional(readOnly=true)
	@Override
   public List<KeywordsRel> findAll(Map<String, Object> search){
		return keywordsRelDAO.findByMap(search);
	}

}
