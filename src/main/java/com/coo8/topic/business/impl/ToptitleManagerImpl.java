package com.coo8.topic.business.impl;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IToptitleManager;
import com.coo8.topic.model.Toptitle;
import com.coo8.topic.persistence.interfaces.IToptitleDAO;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


 
public class ToptitleManagerImpl   implements IToptitleManager{

	private IToptitleDAO toptitleDAO;
 
	public void setToptitleDAO(IToptitleDAO dao) {
		this.toptitleDAO = dao;
	} 

	 
	@Transactional(readOnly=true)
	public Toptitle getById(java.lang.Integer id){
		return toptitleDAO.getById( id);
	}
	@Transactional
	public void deleteById(java.lang.Integer id){
		toptitleDAO.deleteById(id);
	}
	 
	@Transactional
	public void save(Toptitle entity){
		toptitleDAO.save(entity);
	}
	 
	@Transactional
	public void update(Toptitle entity){
		toptitleDAO.update(entity);
	}
 
	@Transactional(readOnly=true)
   public List<Toptitle> findAll(Map<String, Object> search){
		return toptitleDAO.findByMap(search);
	}
	
	@Transactional(readOnly=true)
	public PaginatedList<Toptitle> findByMapLike(Map<String, Object> search){
			return toptitleDAO.findPageByMapLike(search);
	}

}
