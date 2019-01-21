package com.coo8.topic.controller.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IAladdinKeywordsManager;
import com.coo8.topic.model.AladdinKeywords;

public class AladdinBaseAction extends BaseAction {

	protected IAladdinKeywordsManager aladdinKeywordsManager;

	protected PaginatedList<AladdinKeywords> keywordsList;

	protected Integer page_index = 1;

	protected Integer page_size = 10;

	protected Integer id;
	protected String names;
	protected String descr;
	protected String status;
	protected Integer catalogId;
	protected String catalogName;
	protected String related = "";
	protected AladdinKeywords akeywords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public AladdinKeywords getAladdinKeywords() {
		return akeywords;
	}

	public void setAladdinKeywords(AladdinKeywords aladdinKeywords) {
		this.akeywords = aladdinKeywords;
	}

	public IAladdinKeywordsManager getAladdinKeywordsManager() {
		return aladdinKeywordsManager;
	}

	public void setAladdinKeywordsManager(IAladdinKeywordsManager aladdinKeywordsManager) {
		this.aladdinKeywordsManager = aladdinKeywordsManager;
	}

	public PaginatedList<AladdinKeywords> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(PaginatedList<AladdinKeywords> keywordsList) {
		this.keywordsList = keywordsList;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public AladdinKeywords getAkeywords() {
		return akeywords;
	}

	public void setAkeywords(AladdinKeywords akeywords) {
		this.akeywords = akeywords;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

}
