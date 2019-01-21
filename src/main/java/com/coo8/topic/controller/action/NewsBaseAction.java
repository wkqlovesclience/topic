/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.INewsManager;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.model.News;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class NewsBaseAction extends BaseAction {
 
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	protected static final String TYPES_KEYWORDS = "1"; //类型-关键字
	protected static final String TYPES_TAGS     = "2"; //类型-标签
	protected static final String TYPES_LABEL    = "3"; //类型-文字链
	 
	
	protected INewsManager newsManager;
	protected ITitlesManager titlesManager;
	
	public ITitlesManager getTitlesManager() {
		return titlesManager;
	}

	public void setTitlesManager(ITitlesManager titlesManager) {
		this.titlesManager = titlesManager;
	}

	protected Integer pageNumber=1;
	
	protected Integer page_size=10;
	
	protected PaginatedList<News> listNews;

	protected News news;

	protected java.lang.Integer id;
	protected java.lang.Integer titleId;
	protected java.lang.String topic;
	protected java.lang.String isTop;
	protected java.lang.String keywords;
	protected java.lang.String remark;
	protected java.lang.String contents;
	protected java.util.Date publicTime;
	protected java.lang.String creator;
	protected java.util.Date createTime;
	protected java.util.Date updateTime;
	protected String states;
	protected String sourceurl;
	protected String pubTime;
		 
	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public java.util.Date getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(java.util.Date publicTime) {
		this.publicTime = publicTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
		 
	public void setTitleId(java.lang.Integer value) {
		this.titleId = value;
	}
	
	public java.lang.Integer getTitleId() {
		return this.titleId;
	}
		 
	public void setTopic(java.lang.String value) {
		this.topic = value;
	}
	
	public java.lang.String getTopic() {
		return this.topic;
	}
		 
	public void setIsTop(java.lang.String value) {
		this.isTop = value;
	}
	
	public java.lang.String getIsTop() {
		return this.isTop;
	}
		 
	public void setKeywords(java.lang.String value) {
		this.keywords = value;
	}
	
	public java.lang.String getKeywords() {
		return this.keywords;
	}
		 
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
		 
	public void setContents(java.lang.String value) {
		this.contents = value;
	}
	
	public java.lang.String getContents() {
		return this.contents;
	}
		 
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		 
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}


	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public PaginatedList<News> getListNews() {
		return listNews;
	}

	public void setListNews(PaginatedList<News> listNews) {
		this.listNews = listNews;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public void setNewsManager(INewsManager newsManager) {
		this.newsManager = newsManager;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSourceurl() {
		return sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}


}
