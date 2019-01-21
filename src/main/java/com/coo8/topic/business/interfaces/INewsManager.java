package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.LabelArtRel;
import com.coo8.topic.model.News;
import com.coo8.topic.model.NewsAssess;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface INewsManager{
	 

	public News getById(java.lang.Integer id);
	
	   
	public String save(News entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(News entity);
	   
	public PaginatedList<News> findAll(Map<String, Object> search) ; 
	
	public List<News> findListByMap(Map<String, Object> search);

	public int publishNews(int id,String site);
	
	public int publishNewsTest(int id,String site);
	
	public NewsAssess newsAssess(int id,int operation);
	
	public NewsAssess getAssessById(int id);
	
	public String getAssessValues(NewsAssess obj);
	
	public void deleteAssessById(java.lang.Integer id);
 
	/**
	 * 检查文章ID所在的评论文件是否存在，不存在则创建
	 * @param 文章编号
	 * @reutn 文件名
	 */
	public String checkCreateNewsAssessXml(java.lang.Integer newsId);
	
	/**
	 * 根据文章ID，xml文件所在文章评论信息
	 * @param newsId
	 * @param xmlFileURL
	 * @return NewsAssess
	 */
	public NewsAssess parseXml2NewsAssessById(java.lang.Integer newsId , String xmlFileURL);
	
	/**
	 * 改变评论信息并保存到xml文件中
	 * @param newsId    
	 * @param xmlFileURL
	 * @param type 1:有用 2:无用
	 * @return
	 */
	public NewsAssess changeNewsAssess2XmlById(java.lang.Integer newsId , String xmlFileURL , int type);
	
	public List<AnchorKeywords> getContainAnchor(String content);	
	
	public AnchorKeywords  getAnchorById(Integer anchorId);	
	
	
	/**
	 * 通过新闻ID获取标签id
	* @Title: getNewsTagsById  
	* @Description: TODO
	* @param @param newsId
	* @param @return
	* @return List<LabelArtRel>
	* @throws
	 */
	public List<LabelArtRel> getNewsTagsById(Integer newsId);
}

