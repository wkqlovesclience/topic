package com.coo8.topic.controller.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IAnchorKeywordsManager;
import com.coo8.topic.model.AnchorKeywords;

public class AnchorBaseAction extends BaseAction{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4171235652315182282L;

	protected IAnchorKeywordsManager  anchorKeywordsManager;
	
	protected PaginatedList<AnchorKeywords> anchorKeywordsList ;
	
	protected Integer page_index=1;
	
	protected Integer page_size = 10;
	
	protected static final int COMMAND_CREATE = 0;
	protected static final int COMMAND_EDIT = 1;
	
	protected Integer id;
	protected String keyName;
	protected String pcUrl;
	protected String wapUrl;
	protected Integer rate;
	protected AnchorKeywords akeywords;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * @return the anchorKeywordsManager
	 */
	public IAnchorKeywordsManager getAnchorKeywordsManager() {
		return anchorKeywordsManager;
	}
	/**
	 * @param anchorKeywordsManager the anchorKeywordsManager to set
	 */
	public void setAnchorKeywordsManager(
			IAnchorKeywordsManager anchorKeywordsManager) {
		this.anchorKeywordsManager = anchorKeywordsManager;
	}
	/**
	 * @return the anchorKeywordsList
	 */
	public PaginatedList<AnchorKeywords> getAnchorKeywordsList() {
		return anchorKeywordsList;
	}
	/**
	 * @param anchorKeywordsList the anchorKeywordsList to set
	 */
	public void setAnchorKeywordsList(
			PaginatedList<AnchorKeywords> anchorKeywordsList) {
		this.anchorKeywordsList = anchorKeywordsList;
	}
	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}
	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	/**
	 * @return the pcUrl
	 */
	public String getPcUrl() {
		return pcUrl;
	}
	/**
	 * @param pcUrl the pcUrl to set
	 */
	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}
	/**
	 * @return the wapUrl
	 */
	public String getWapUrl() {
		return wapUrl;
	}
	/**
	 * @param wapUrl the wapUrl to set
	 */
	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}
	/**
	 * @return the rate
	 */
	public Integer getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	/**
	 * @return the akeywords
	 */
	public AnchorKeywords getAkeywords() {
		return akeywords;
	}
	/**
	 * @param akeywords the akeywords to set
	 */
	public void setAkeywords(AnchorKeywords akeywords) {
		this.akeywords = akeywords;
	}
}
