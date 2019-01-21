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

 


public interface IKeywordsDAO{

	public Keywords getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
	
	 
	public int save(Keywords entity);
	 
	public int update(Keywords entity);

	public PaginatedList<Keywords> findPageByMap(Map<String, Object> Keywords);
	
	public List<Keywords> findByMap(Map<String, Object> search);

	public List<Keywords> findByLike(Map<String, Object> search);

	public PaginatedList<Keywords> findPageByMapLike(Map<String, Object> Keywords);

	public List<Keywords> findAllTab(Map<String, Object> search);

	
	

}
