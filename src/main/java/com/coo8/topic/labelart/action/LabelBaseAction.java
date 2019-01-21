package com.coo8.topic.labelart.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.controller.action.BaseAction;
import com.coo8.topic.labelart.modal.Label;
import com.coo8.topic.labelart.modal.LabelIndex;
import com.coo8.topic.labelart.service.inter.LabelManager;
import com.coo8.topic.model.TitleIndex;

public class LabelBaseAction extends BaseAction{
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/web/Keywords/query.jsp";
	protected static final String LIST_JSP= "/web/Keywords/list.jsp";
	protected static final String CREATE_JSP = "/web/Keywords/create.jsp";
	protected static final String EDIT_JSP = "/web/Keywords/edit.jsp";
	protected static final String SHOW_JSP = "/web/Keywords/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/Keywords/list.do";
	
	protected LabelManager labelManager;
	protected PaginatedList<Label> listtags ;
	
	protected Integer pageNumber = 1;
	protected Integer page_size = 10;

	protected Label label;
	
	protected PaginatedList<LabelIndex> labelIndexList;		//Ë÷ÒýÁÐ±í
	protected LabelIndex labelIndex;
	
	protected Integer page_index=1;

	protected java.lang.Integer id;
	protected java.lang.String type;
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

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
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

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public PaginatedList<Label> getListtags() {
		return listtags;
	}

	public void setListtags(PaginatedList<Label> listtags) {
		this.listtags = listtags;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public LabelManager getLabelManager() {
		return labelManager;
	}

	public void setLabelManager(LabelManager labelManager) {
		this.labelManager = labelManager;
	}

	public LabelIndex getLabelIndex() {
		return labelIndex;
	}

	public void setLabelIndex(LabelIndex labelIndex) {
		this.labelIndex = labelIndex;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	public PaginatedList<LabelIndex> getLabelIndexList() {
		return labelIndexList;
	}

	public void setLabelIndexList(PaginatedList<LabelIndex> labelIndexList) {
		this.labelIndexList = labelIndexList;
	}

}
