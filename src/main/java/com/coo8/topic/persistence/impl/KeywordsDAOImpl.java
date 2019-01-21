/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.impl;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.persistence.interfaces.IKeywordsDAO;
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




@Repository("keywordsDAO")
public class KeywordsDAOImpl extends SqlMapClientDaoSupport  implements IKeywordsDAO{
	
	 
	@Override
	public Keywords getById(java.lang.Integer id){
		return (Keywords) getSqlMapClientTemplate().queryForObject(
				"Keywords.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("Keywords.delete", id);
	}
	
    @Override
	public int save(Keywords entity){
		Object obj = getSqlMapClientTemplate().insert(
				"Keywords.insert", entity);
		if (obj != null) {
			return   ((Integer)obj).intValue();
		}
		return 0; 
		 
	} 


	@Override
	public int update(Keywords entity){
		return getSqlMapClientTemplate().update("Keywords.update",
				entity);
	}

	 
	@Override
	public List<Keywords> findByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Keywords.findPage", search);
	 
	}
	@Override
	public List<Keywords> findByLike(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Keywords.findPageLike", search);
	 
	}
	@Override
	public PaginatedList<Keywords> findPageByMap(Map<String, Object> Keywords) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Keywords.findPage.count", Keywords);
		if(o==null)
			return null;
		PaginatedList<Keywords> paginatedArrayList = new PaginatedArrayList<Keywords>(Integer.parseInt(o.toString()), (Integer)Keywords.get("pageNumber"),(Integer)Keywords.get("pageSize"));
		List<Keywords> list = this.getSqlMapClientTemplate().queryForList("Keywords.findPage", Keywords, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	@Override
	public PaginatedList<Keywords> findPageByMapLike(Map<String, Object> Keywords) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Keywords.findPageLike.count", Keywords);
		if(o==null)
			return null;
		PaginatedList<Keywords> paginatedArrayList = new PaginatedArrayList<Keywords>(Integer.parseInt(o.toString()), (Integer)Keywords.get("pageNumber"),(Integer)Keywords.get("pageSize"));
		List<Keywords> list = this.getSqlMapClientTemplate().queryForList("Keywords.findPageLike", Keywords, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	@Override
	public List<Keywords> findAllTab(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Keywords.findAllTab", search);
	 
	}
	 
	
}
