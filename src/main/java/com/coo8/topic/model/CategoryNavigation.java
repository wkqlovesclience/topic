package com.coo8.topic.model;

public class CategoryNavigation implements java.io.Serializable{
	
	private static final long serialVersionUID = -2697504392191388376L;
	
	private Integer id;
	private Integer sorts;
	private String names;
	private Integer status;
	private String createdName;
	private String createdTime;
	private String updatedTime;
	private String defaultImg;
	private String hoverImg;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSorts() {
		return sorts;
	}
	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
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
	public String getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	public String getHoverImg() {
		return hoverImg;
	}
	public void setHoverImg(String hoverImg) {
		this.hoverImg = hoverImg;
	}
	
}
