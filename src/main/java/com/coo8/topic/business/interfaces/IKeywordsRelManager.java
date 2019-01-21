package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.topic.model.KeywordsRel;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface IKeywordsRelManager{
	 

	public KeywordsRel getById(java.lang.Integer id);
	
	   
	public void save(KeywordsRel entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(KeywordsRel entity);
	   
	
	public List<KeywordsRel> findAll(Map<String, Object> search);

 
}
