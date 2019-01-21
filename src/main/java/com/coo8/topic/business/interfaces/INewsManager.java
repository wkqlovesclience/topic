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
	 * �������ID���ڵ������ļ��Ƿ���ڣ��������򴴽�
	 * @param ���±��
	 * @reutn �ļ���
	 */
	public String checkCreateNewsAssessXml(java.lang.Integer newsId);
	
	/**
	 * ��������ID��xml�ļ���������������Ϣ
	 * @param newsId
	 * @param xmlFileURL
	 * @return NewsAssess
	 */
	public NewsAssess parseXml2NewsAssessById(java.lang.Integer newsId , String xmlFileURL);
	
	/**
	 * �ı�������Ϣ�����浽xml�ļ���
	 * @param newsId    
	 * @param xmlFileURL
	 * @param type 1:���� 2:����
	 * @return
	 */
	public NewsAssess changeNewsAssess2XmlById(java.lang.Integer newsId , String xmlFileURL , int type);
	
	public List<AnchorKeywords> getContainAnchor(String content);	
	
	public AnchorKeywords  getAnchorById(Integer anchorId);	
	
	
	/**
	 * ͨ������ID��ȡ��ǩid
	* @Title: getNewsTagsById  
	* @Description: TODO
	* @param @param newsId
	* @param @return
	* @return List<LabelArtRel>
	* @throws
	 */
	public List<LabelArtRel> getNewsTagsById(Integer newsId);
}

