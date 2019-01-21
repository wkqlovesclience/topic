package com.coo8.topic.model;

import java.io.Serializable;
import java.util.Date;

public class ErrorAllHotkeyword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3117079768952856891L;

	private Integer id;
	// type 为1：热词重复 为2：热词为空 3：没有搜索结果
	private Integer type;
	private String title;

	private String creator;

	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
