/** 
 * Project Name:topic_1.0.25_BR 
 * File Name:LabelArtRel.java 
 * Package Name:com.coo8.topic.model 
 * Date:2017��9��19������12:24:54 
 * Copyright (c) 2017, suchao@gomeplus.com All Rights Reserved. 
 * 
*/  

package com.coo8.topic.model;

import java.io.Serializable;

/** 
 * ClassName:LabelArtRel <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017��9��19�� ����12:24:54 <br/> 
 * @author   suchao 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public class LabelArtRel implements Serializable {

	/**  
	* @Fields serialVersionUID : TODO
	*/  
	
	private static final long serialVersionUID = 396371917910845154L;
	
	/**
	 * id
	 */
	private int id;
	
	/**
	 * Ŀǰ�� --- ��ǩID
	 */
	private int keywords;
	
	/**
	 * ����ID Ŀǰ�洢���� newsId
	 */
	private int articleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKeywords() {
		return keywords;
	}

	public void setKeywords(int keywords) {
		this.keywords = keywords;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
}
