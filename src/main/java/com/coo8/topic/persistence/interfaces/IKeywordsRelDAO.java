/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.interfaces;

import java.util.*;
import org.apache.commons.lang.StringUtils;
 

import com.coo8.topic.model.*;
import com.coo8.topic.persistence.interfaces.*;
import com.coo8.topic.business.interfaces.*;  

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

 


public interface IKeywordsRelDAO{

	public KeywordsRel getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
	
	 
	public String save(KeywordsRel entity);
	 
	public int update(KeywordsRel entity);

 	
	public List<KeywordsRel> findByMap(Map<String, Object> search);

	
	

}
