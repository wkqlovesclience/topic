package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.Toptitle;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface IToptitleManager{
	 

	public Toptitle getById(java.lang.Integer id);
	
	   
	public void save(Toptitle entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(Toptitle entity);
	   
	
	public List<Toptitle> findAll(Map<String, Object> search);
	
	public PaginatedList<Toptitle> findByMapLike(Map<String, Object> search);
		

 
}
