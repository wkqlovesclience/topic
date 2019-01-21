/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.labelart.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.coo8.topic.labelart.dao.inter.LabelRelDAO;
import com.coo8.topic.labelart.modal.LabelRel;  

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */




@Repository("labelRelDao")
public class LabelRelDAOImpl extends SqlMapClientDaoSupport  implements LabelRelDAO{
	
	 
	@Override
	public LabelRel getById(java.lang.Integer id){
		return (LabelRel) getSqlMapClientTemplate().queryForObject(
				"LabelRel.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("LabelRel.delete", id);
	}
	
    @Override
	public String save(LabelRel entity){
		Object obj = getSqlMapClientTemplate().insert(
				"LabelRel.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(LabelRel entity){
		return getSqlMapClientTemplate().update("LabelRel.update",
				entity);
	}

	 
	@Override
	public List<LabelRel> findByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"LabelRel.findPage", search);
	 
	}
	
	 
	
}
