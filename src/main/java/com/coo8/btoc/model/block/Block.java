package com.coo8.btoc.model.block;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;



public class Block extends BaseModel {
	
	private static final long serialVersionUID = -5063099209762632179L;
	
	public static final int ENABLED = 0;//启用
	public static final int DISABLED = 1;//停用
	
	public static final int AUTO_BLOCK = 0;//自动块
	
	public static final int COMMON_MANUAL_BLOCK = 2;//通用手动块
	public static final int TEMPLATE_MANUAL_BLOCK = 1;//模板相关手动块
	
	private Integer id;
	private Integer templateId;
	private Integer resourceId;
	private Integer type;
	private Integer status;
	private Integer dataNum;
	private Integer page;
	
	private String name;
	private String displayName;
	private String content;
	private String description;
	private String updator;
	private String creator;
	private String location;
	
	private Date updatedTime;
	private Date createdTime;
	
	//查询使用
	private Integer pageIndex;
	private Integer pageSize;
	
	//站点
	private String site;
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDataNum() {
		return dataNum;
	}
	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public boolean isAutoBlock(){
		return type != null && type == Block.AUTO_BLOCK;
	}
	
	public boolean isManualBlock() {
		return type != null && (type == Block.COMMON_MANUAL_BLOCK || type == Block.TEMPLATE_MANUAL_BLOCK);
	}
	
	public boolean isCommonBlock() {
		return type != null && type == Block.COMMON_MANUAL_BLOCK;
	}
	
	public boolean isTemplateRelatedBlock() {
		return type != null && type == Block.TEMPLATE_MANUAL_BLOCK;
	}
	
	public boolean isEnabled() {
		return status != null && status == Block.ENABLED;
	}
	
	public boolean isDisabled() {
		return status != null && status == Block.DISABLED;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}

