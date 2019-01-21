package com.coo8.btoc.model.template;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;


public class Template extends BaseModel {

	private static final long serialVersionUID = -7475962811909023579L;
	
	public static final int ENABLED = 0;//∆Ù”√
	public static final int DISABLED = 1;//Õ£”√
	
	private Integer id;
	private Integer status;
	
	private String name;
	private String description;
	private String content;
	private String creator;
	private String updator;
	private java.lang.String path;
	private Date createdTime;
	private Date updatedTime;
	private String site;
	
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	//for search
	private Integer pageIndex;
	private Integer pageSize;
	
	public boolean isDisabled() {
		return status != null && status == DISABLED;
	}
	
	public boolean isEnabled() {
		return status != null && status == ENABLED;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public java.lang.String getPath() {
		return path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}
	
}
