/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IToptitleManager;
import com.coo8.topic.model.Toptitle;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class ToptitleBaseAction extends BaseAction {
	
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	protected IToptitleManager toptitleManager;
	protected PaginatedList<Toptitle> listtop ;
	protected Integer pageNumber = 1;
	
	protected Integer page_size = 60;

	protected Toptitle toptitle;

	protected java.lang.Integer id;
	protected java.lang.String names;
	protected java.lang.String urls;
	protected java.lang.String creator;
	protected java.util.Date createtime;
	protected java.lang.Integer priors;
	protected java.lang.String status;

		 
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
		 
	public void setUrls(java.lang.String value) {
		this.urls = value;
	}
	
	public java.lang.String getUrls() {
		return this.urls;
	}
		 
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
		 
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
		 
	public void setPriors(java.lang.Integer value) {
		this.priors = value;
	}
	
	public java.lang.Integer getPriors() {
		return this.priors;
	}
		 
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}

	public void setToptitleManager(IToptitleManager toptitleManager) {
		this.toptitleManager = toptitleManager;
	}

	public PaginatedList<Toptitle> getListtop() {
		return listtop;
	}

	public void setListtop(PaginatedList<Toptitle> listtop) {
		this.listtop = listtop;
	}

 

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Toptitle getToptitle() {
		return toptitle;
	}

	public void setToptitle(Toptitle toptitle) {
		this.toptitle = toptitle;
	}


}
