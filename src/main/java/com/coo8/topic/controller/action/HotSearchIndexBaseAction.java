package com.coo8.topic.controller.action;

import java.util.Date;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.HotSearchIndex;

public class HotSearchIndexBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	protected Integer page_index = 1;
	protected Integer page_size = 10;

	protected HotSearchIndex hotSearchIndexEntity;
	protected PaginatedList<HotSearchIndex> hotSearchIndexList;

	protected Integer id;
	protected Integer hotSearchId;
	protected String pinyin;
	protected String hotSearchIndex;
	protected Integer source;
	protected Integer priority;
	protected Integer status;
	protected String operator;
	protected Date updatetime;
	protected String createtime;
	protected String site;

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public HotSearchIndex getHotSearchIndexEntity() {
		return hotSearchIndexEntity;
	}

	public void setHotSearchIndexEntity(HotSearchIndex hotSearchIndexEntity) {
		this.hotSearchIndexEntity = hotSearchIndexEntity;
	}

	public PaginatedList<HotSearchIndex> getHotSearchIndexList() {
		return hotSearchIndexList;
	}

	public void setHotSearchIndexList(PaginatedList<HotSearchIndex> hotSearchIndexList) {
		this.hotSearchIndexList = hotSearchIndexList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHotSearchId() {
		return hotSearchId;
	}

	public void setHotSearchId(Integer hotSearchId) {
		this.hotSearchId = hotSearchId;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getHotSearchIndex() {
		return hotSearchIndex;
	}

	public void setHotSearchIndex(String hotSearchIndex) {
		this.hotSearchIndex = hotSearchIndex;
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
