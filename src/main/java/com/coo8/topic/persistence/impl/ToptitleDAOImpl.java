/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.impl;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.persistence.interfaces.IToptitleDAO;
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




@Repository("toptitleDAO")
public class ToptitleDAOImpl extends SqlMapClientDaoSupport  implements IToptitleDAO{
	
	 
	@Override
	public Toptitle getById(java.lang.Integer id){
		return (Toptitle) getSqlMapClientTemplate().queryForObject(
				"Toptitle.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("Toptitle.delete", id);
	}
	
    @Override
	public String save(Toptitle entity){
		Object obj = getSqlMapClientTemplate().insert(
				"Toptitle.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(Toptitle entity){
		return getSqlMapClientTemplate().update("Toptitle.update",
				entity);
	}

	 
	@Override
	public List<Toptitle> findByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Toptitle.findPage", search);
	 
	}

	@Override 
	public PaginatedList<Toptitle> findPageByMapLike(Map<String, Object> Keywords) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Toptitle.findPageLike.count", Keywords);
		if(o==null)
			return null;
		PaginatedList<Toptitle> paginatedArrayList = new PaginatedArrayList<Toptitle>(Integer.parseInt(o.toString()), (Integer)Keywords.get("pageNumber"),(Integer)Keywords.get("pageSize"));
		List<Toptitle> list = this.getSqlMapClientTemplate().queryForList("Toptitle.findPageLike", Keywords, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	 
	
}
