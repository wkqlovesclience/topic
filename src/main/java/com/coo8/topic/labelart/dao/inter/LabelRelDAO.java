/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.labelart.dao.inter;

import java.util.List;
import java.util.Map;

import com.coo8.topic.labelart.modal.LabelRel; 

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

 


public interface LabelRelDAO{

	public LabelRel getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
	
	 
	public String save(LabelRel entity);
	 
	public int update(LabelRel entity);

 	
	public List<LabelRel> findByMap(Map<String, Object> search);

	
	

}
