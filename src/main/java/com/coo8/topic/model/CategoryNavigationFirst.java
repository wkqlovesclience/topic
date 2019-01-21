package com.coo8.topic.model;

public class CategoryNavigationFirst implements java.io.Serializable{
	
	private static final long serialVersionUID = -2697504392191388376L;
	
	private Integer id;
	private Integer groupId;
	private Integer sorts;
	private String catId;
	private Integer status;
	private String createdName;
	private String createdTime;
	private String updatedTime;
	private String categoryName;
	private String subCategoryNames;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getSorts() {
		return sorts;
	}
	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryNames() {
		return subCategoryNames;
	}
	public void setSubCategoryNames(String subCategoryNames) {
		this.subCategoryNames = subCategoryNames;
	}
	
}
