package com.coo8.btoc.model.items;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemQueryParam {
	private  static Logger logger = LoggerFactory.getLogger(ItemQueryParam.class);
	private Integer brandId;
	private Integer pageIndex;
	private Integer pageSize;
	private Integer fatherCatalogId;
	private Integer catalogId;
	private Integer status;
	private Integer mainPush;
	private Integer defectstatus;
	
	private String id;
	private String catalogIds = "";
	private String primaryKey = "";
	private String itemId = "";
	private String productName;
	private String companyId;
	
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void param(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getFatherCatalogId() {
		return fatherCatalogId;
	}
	public void setFatherCatalogId(Integer fatherCatalogId) {
		this.fatherCatalogId = fatherCatalogId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCatalogIds() {
		return catalogIds;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setCatalogIds(String catalogIds) {
		this.catalogIds = catalogIds;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		try {
			this.productName = URLDecoder.decode(productName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Integer getMainPush() {
		return mainPush;
	}
	public void setMainPush(Integer mainPush) {
		this.mainPush = mainPush;
	}
	public Integer getDefectstatus() {
		return defectstatus;
	}
	public void setDefectstatus(Integer defectstatus) {
		this.defectstatus = defectstatus;
	}
	
}
