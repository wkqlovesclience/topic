package com.coo8.topic.controller.action;

import com.coo8.topic.business.interfaces.IKeywordsRelManager;
import com.coo8.topic.model.KeywordsRel;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class KeywordsRelBaseAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	//example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/web/KeywordsRel/query.jsp";
	protected static final String LIST_JSP= "/web/KeywordsRel/list.jsp";
	protected static final String CREATE_JSP = "/web/KeywordsRel/create.jsp";
	protected static final String EDIT_JSP = "/web/KeywordsRel/edit.jsp";
	protected static final String SHOW_JSP = "/web/KeywordsRel/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/KeywordsRel/list.do";
	
	protected IKeywordsRelManager keywordsRelManager;
	
	protected Integer page_index;
	
	protected Integer page_size;

	protected KeywordsRel keywordsRel;

	private java.lang.Integer id;
	private java.lang.Integer titleId;
	private java.lang.Integer keywords;

		 
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
		 
	public void setKeywords(java.lang.Integer value) {
		this.keywords = value;
	}
	
	public java.lang.Integer getKeywords() {
		return this.keywords;
	}


}
