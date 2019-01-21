package com.coo8.btoc.model.resource;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;


public class Resource extends BaseModel {

	private static final long serialVersionUID = -5348363520142324501L;
	
	public static final int ENABLED = 0;//启用
	public static final int DISABLED = 1;//停用
	
	public static final int PRODUCT_RESOURCE = 1;
	public static final int PROCEDURE_RESOURCE = 2;
	public static final int OUTSIDE_RESOURCE = 3;
	public static final int ATG_RESOURCE = 4;
	
	private Integer id;
	private Integer code;
	private Integer priority;
	private Integer status;
	private Integer type;//数据源类型
	
	private String creator;
	private String updator;
	private String name;
	private String resource;
	private String testCode;
	private String description;
	private String doc;//返回字段描述
	
	private Date createdTime;
	private Date updatedTime;
	
	private Integer pageIndex;
	private Integer pageSize;
	
	public boolean isEnabled() {
		return status != null && status == ENABLED;
	}
	
	public boolean isDisabled() {
		return status != null && status == DISABLED;
	}
	
	public boolean isProduct() {
		return type != null && type == PRODUCT_RESOURCE;
	}
	
	public boolean isProcedure() {
		return type != null && type == PROCEDURE_RESOURCE;
	}
	
	public boolean isOutside(){
	    return type != null && type == OUTSIDE_RESOURCE;
	}
	public boolean isAtg(){
		return type != null && type == ATG_RESOURCE;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
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
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
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

}
