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

 


public interface INewsDAO{

	public News getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
	
	 
	public String save(News entity);
	 
	public int update(News entity);

 	
	public PaginatedList<News> findByMap(Map<String, Object> search);

	public List<News> findListByMap(Map<String, Object> search);

	public int publishCoo8GomeNews(Map<String,Object> map);

	public int newsAssessCreate(int id);
	
	public int newsAssessGood(int id);
	
	public int newsAssessBad(int id);

	public NewsAssess getAssessById(int id);

	public int deleteAssessById(Integer id);
	
	/**
	 * 通过新闻id获取新闻信息
	* @Title: getNewsTagsById  
	* @Description: TODO
	* @param @param map
	* @param @return
	* @return List<LabelArtRel>
	* @throws
	 */
	public List<LabelArtRel> getNewsTagsById(Map<String,Object> map);
	

}
