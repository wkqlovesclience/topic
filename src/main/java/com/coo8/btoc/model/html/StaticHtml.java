package com.coo8.btoc.model.html;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

public class StaticHtml extends BaseModel {

	private static final long serialVersionUID = 3414876973670298232L;
	
	public static final int COMMON_TYPE = 1;
	public static final int CATALOG_TYPE = 2;
	
	private Integer id;
	private Integer templateId;
	private Integer type;
	private Integer refId;
	
	private String name;
	private String path;
	private String description;
	private String creator;
	private String updator;
	
	private Date createdTime;
	private Date updatedTime;
	
	public boolean isCatalogType() {
		return type != null && CATALOG_TYPE == type; 
	}
	
	public boolean isCommonType() {
		return type != null && COMMON_TYPE == type; 
	}
	
	//for search
	private Integer pageIndex;
	private Integer pageSize;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
}