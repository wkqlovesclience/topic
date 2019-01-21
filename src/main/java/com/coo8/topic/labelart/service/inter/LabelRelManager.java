package com.coo8.topic.labelart.service.inter; 

import java.util.List;
import java.util.Map;

import com.coo8.topic.labelart.modal.LabelRel;
import com.coo8.topic.model.KeywordsRel;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface LabelRelManager{
	 

	public LabelRel getById(java.lang.Integer id);
	
	   
	public void save(LabelRel entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(LabelRel entity);
	   
	
	public List<LabelRel> findAll(Map<String, Object> search);

 
}
