package com.coo8.topic.controller.action;

import java.util.Date;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.HotkeyIndex;

public class HotkeyIndexBaseAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	protected Integer page_index = 1;
	protected Integer page_size  =10;
	
	protected HotkeyIndex hotkeyIndexEntity;
	protected PaginatedList<HotkeyIndex> hotkeyIndexList;
	
	protected Integer id;
	protected Integer hotkeyId;
	protected String pinyin;
	protected String hotkeyIndex;
	protected Integer source;
	protected Integer priority;
	protected Integer status;
	protected String operator;
	protected Date updatetime;
	protected String site;
	
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
	
	public HotkeyIndex getHotkeyIndexEntity() {
		return hotkeyIndexEntity;
	}
	public void setHotkeyIndexEntity(HotkeyIndex hotkeyIndexEntity) {
		this.hotkeyIndexEntity = hotkeyIndexEntity;
	}
	public PaginatedList<HotkeyIndex> getHotkeyIndexList() {
		return hotkeyIndexList;
	}
	public void setHotkeyIndexList(PaginatedList<HotkeyIndex> hotkeyIndexList) {
		this.hotkeyIndexList = hotkeyIndexList;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHotkeyId() {
		return hotkeyId;
	}
	public void setHotkeyId(Integer hotkeyId) {
		this.hotkeyId = hotkeyId;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getHotkeyIndex() {
		return hotkeyIndex;
	}
	public void setHotkeyIndex(String hotkeyIndex) {
		this.hotkeyIndex = hotkeyIndex;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

}
