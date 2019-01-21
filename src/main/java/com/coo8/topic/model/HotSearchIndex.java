package com.coo8.topic.model;

import java.io.Serializable;
import java.util.Date;

public class HotSearchIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3595775059684531881L;
	private Integer id;
	private Integer hotSearchId;
	private String hotSearchTitle;
	private String pinyin;
	private String hotSearchIndex;
	private Integer source;
	private Integer priority;
	private Integer status;
	private String operator;
	private Date updatetime;
	private String createtime;
	private String site;

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String getHotSearchTitle() {
		return hotSearchTitle;
	}

	public void setHotSearchTitle(String hotSearchTitle) {
		this.hotSearchTitle = hotSearchTitle;
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
