/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.interfaces;

import java.util.*;

import org.apache.commons.lang.StringUtils;
 

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.*;
import com.coo8.topic.persistence.interfaces.*;
import com.coo8.topic.business.interfaces.*;  

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

 


public interface IToptitleDAO{

	public Toptitle getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
	
	 
	public String save(Toptitle entity);
	 
	public int update(Toptitle entity);

 	
	public List<Toptitle> findByMap(Map<String, Object> search);

	public PaginatedList<Toptitle> findPageByMapLike(Map<String, Object> search);

	
	

}
