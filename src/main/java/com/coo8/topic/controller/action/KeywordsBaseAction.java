package com.coo8.topic.controller.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IKeywordsManager;
import com.coo8.topic.model.Keywords;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class KeywordsBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/web/Keywords/query.jsp";
	protected static final String LIST_JSP= "/web/Keywords/list.jsp";
	protected static final String CREATE_JSP = "/web/Keywords/create.jsp";
	protected static final String EDIT_JSP = "/web/Keywords/edit.jsp";
	protected static final String SHOW_JSP = "/web/Keywords/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/Keywords/list.do";
	
	protected IKeywordsManager keywordsManager;
	protected PaginatedList<Keywords> listtags ;
	
	protected Integer pageNumber = 1;
	protected Integer page_size = 10;

	protected Keywords keywords;

	protected java.lang.Integer id;
	protected java.lang.String types;
	protected java.lang.String names;
	protected java.lang.String url;
	protected java.util.Date createTime;
	protected java.util.Date updateTime;
	protected java.lang.String creator;

		 
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
		 
	public void setNames(java.lang.String value) {
		this.names = value;
	}
	
	public java.lang.String getNames() {
		return this.names;
	}
		 
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}


	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Keywords getKeywords() {
		return keywords;
	}

	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public java.lang.String getTypes() {
		return types;
	}

	public void setTypes(java.lang.String types) {
		this.types = types;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.String getCreator() {
		return creator;
	}

	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}

	public void setKeywordsManager(IKeywordsManager keywordsManager) {
		this.keywordsManager = keywordsManager;
	}

	public PaginatedList<Keywords> getListtags() {
		return listtags;
	}

	public void setListtags(PaginatedList<Keywords> listtags) {
		this.listtags = listtags;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


}
