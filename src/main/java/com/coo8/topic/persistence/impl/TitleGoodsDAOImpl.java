/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.impl;
import com.coo8.topic.persistence.interfaces.ITitleGoodsDAO;
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




@Repository("titleGoodsDAO")
public class TitleGoodsDAOImpl extends SqlMapClientDaoSupport  implements ITitleGoodsDAO{
	
	 
	@Override
	public TitleGoods getById(java.lang.Integer id){
		return (TitleGoods) getSqlMapClientTemplate().queryForObject(
				"TitleGoods.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("TitleGoods.delete", id);
	}
	
    @Override
	public String save(TitleGoods entity){
		Object obj = getSqlMapClientTemplate().insert(
				"TitleGoods.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(TitleGoods entity){
		return getSqlMapClientTemplate().update("TitleGoods.update",
				entity);
	}

	 
	@Override
	public List<TitleGoods> findByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"TitleGoods.findPage", search);
	 
	}
	
	 
	
}
