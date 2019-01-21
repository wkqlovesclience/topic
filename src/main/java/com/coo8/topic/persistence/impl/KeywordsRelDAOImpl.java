/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.impl;
import com.coo8.topic.persistence.interfaces.IKeywordsRelDAO;
import org.springframework.stereotype.Repository;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
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




@Repository("keywordsRelDAO")
public class KeywordsRelDAOImpl extends SqlMapClientDaoSupport  implements IKeywordsRelDAO{
	
	 
	@Override
	public KeywordsRel getById(java.lang.Integer id){
		return (KeywordsRel) getSqlMapClientTemplate().queryForObject(
				"KeywordsRel.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("KeywordsRel.delete", id);
	}
	
    @Override
	public String save(KeywordsRel entity){
		Object obj = getSqlMapClientTemplate().insert(
				"KeywordsRel.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(KeywordsRel entity){
		return getSqlMapClientTemplate().update("KeywordsRel.update",
				entity);
	}

	 
	@Override
	public List<KeywordsRel> findByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"KeywordsRel.findPage", search);
	 
	}
	
	 
	
}
