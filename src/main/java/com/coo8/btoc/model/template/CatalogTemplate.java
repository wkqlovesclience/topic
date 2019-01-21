package com.coo8.btoc.model.template;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

public class CatalogTemplate extends BaseModel {

	private static final long serialVersionUID = 3894972534588235414L;
	
	public static final int SMALL_CATALOG_TYPE = 1;//小类模板
	public static final int BRAND_TYPE = 2;//品牌模板
	public static final int PRODUCT_TYPE = 3;//产品模板
	public static final int BIG_CATALOG_TYPE = 4;//大类模板
	
	public static final int DISABLED = 1;
	public static final int ENABLED = 0;
	
	private Integer id;
	private Integer templateId;
	private Integer catalogId;
	private Integer type;
	private Integer status;
	
	private String creator;
	private String updator;
	
	private Date createdDate;
	private Date updatedDate;
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
	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
