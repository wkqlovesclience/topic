package com.coo8.hotlink.controller.action;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.hotlink.business.interfaces.IHotLinkManager;
import com.coo8.hotlink.model.HotLink;
import com.coo8.topic.controller.action.BaseAction;

public class HotLinkBaseAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3692132346969577931L;

	protected IHotLinkManager  hotLinkManager;
	
	protected PaginatedList<HotLink> hotLinkList ;
	
	protected Integer page_index=1;
	
	protected Integer page_size = 10;
	
	protected static final int COMMAND_CREATE = 0;
	protected static final int COMMAND_EDIT = 1;
	
	protected Integer id;
	protected String hotName;
	protected String pcUrl;
	protected String wapUrl;
	protected Integer sort;
	
	protected HotLink hotLink;

	/**
	 * @return the hotLinkManager
	 */
	public IHotLinkManager getHotLinkManager() {
		return hotLinkManager;
	}

	/**
	 * @param hotLinkManager the hotLinkManager to set
	 */
	public void setHotLinkManager(IHotLinkManager hotLinkManager) {
		this.hotLinkManager = hotLinkManager;
	}

	/**
	 * @return the hotLinkList
	 */
	public PaginatedList<HotLink> getHotLinkList() {
		return hotLinkList;
	}

	/**
	 * @param hotLinkList the hotLinkList to set
	 */
	public void setHotLinkList(PaginatedList<HotLink> hotLinkList) {
		this.hotLinkList = hotLinkList;
	}

	/**
	 * @return the page_index
	 */
	public Integer getPage_index() {
		return page_index;
	}

	/**
	 * @param page_index the page_index to set
	 */
	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	/**
	 * @return the page_size
	 */
	public Integer getPage_size() {
		return page_size;
	}

	/**
	 * @param page_size the page_size to set
	 */
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the hotName
	 */
	public String getHotName() {
		return hotName;
	}

	/**
	 * @param hotName the hotName to set
	 */
	public void setHotName(String hotName) {
		this.hotName = hotName;
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
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the hotLink
	 */
	public HotLink getHotLink() {
		return hotLink;
	}

	/**
	 * @param hotLink the hotLink to set
	 */
	public void setHotLink(HotLink hotLink) {
		this.hotLink = hotLink;
	}
	
	
}
