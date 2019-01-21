package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.Keywords;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface IKeywordsManager{
	 

	public Keywords getById(java.lang.Integer id);
	   
	public int save(Keywords entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(Keywords entity);
	   
	public PaginatedList<Keywords> findPageByMap(Map<String, Object> Keywords);
	
	public List<Keywords> findAll(Map<String, Object> search);
	
	public List<Keywords> findByLike(Map<String, Object> search);
	  
	public PaginatedList<Keywords> findPageByMapLike(Map<String, Object> Keywords);

	public List<Keywords> findAllTab(Map<String, Object> search);
	
	
 
}
