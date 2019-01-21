/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.impl; 
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.persistence.interfaces.INewsDAO;
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




@Repository("newsDAO")
public class NewsDAOImpl extends SqlMapClientDaoSupport  implements INewsDAO{
	
	 
	@Override
	public News getById(java.lang.Integer id){
		return (News) getSqlMapClientTemplate().queryForObject(
				"News.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("News.delete", id);
	}
	
    @Override
	public String save(News entity){
		Object obj = getSqlMapClientTemplate().insert(
				"News.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(News entity){
		return getSqlMapClientTemplate().update("News.update",
				entity);
	}

	@Override
	public PaginatedList<News> findByMap(Map<String, Object> search) {
		 
		Object o  =  this.getSqlMapClientTemplate().queryForObject("News.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<News> paginatedArrayList = new PaginatedArrayList<News>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<News> list = this.getSqlMapClientTemplate().queryForList("News.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
		 
	}
	@Override
	public List<News> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"News.findPage", search);
	 
	}
	@Override
	public int publishCoo8GomeNews(Map<String,Object> map){
		return this.getSqlMapClientTemplate().update("NewsMap.publishCoo8GomeNews",map);
	}

	@Override
	public int newsAssessCreate(int id) {
		Object o = this.getSqlMapClientTemplate().insert("NewsMap.newsAssessCreate",id);
		if(null != o){
			return 1;
		}
		return 0;
	}

	@Override
	public int newsAssessGood(int id) {
		return this.getSqlMapClientTemplate().update("NewsMap.newsAssessGood",id);
	}

	@Override
	public int newsAssessBad(int id) {
		return this.getSqlMapClientTemplate().update("NewsMap.newsAssessBad",id);
	}

	@Override
	public NewsAssess getAssessById(int id) {
		NewsAssess ret = null;
		Object o = getSqlMapClientTemplate().queryForObject("NewsMap.listNewsAssess", id);
		if(null != o ){
			ret = (NewsAssess)o;
		}
		return ret;
	}

	@Override
	public int deleteAssessById(Integer id) {
		return	getSqlMapClientTemplate().delete("NewsMap.deleteAssessById", id);
	} 
	
	
	//Titles.getTagIds
	
	/**
	 * 
	* @Title: getNewsTagsById  
	* @Description: TODO
	* @param @param map  id=123
	* @param @return
	* @return List<LabelArtRel>
	* @throws
	 */
	@Override
	public List<LabelArtRel> getNewsTagsById(Map<String,Object> map){
		return this.getSqlMapClientTemplate().queryForList("Titles.getTagIds",map);
	}
}
