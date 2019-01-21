/*
 * 文件名： BtoCSeries.java
 * 
 * 创建日期： 2010-4-29
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.model.catalog;

import com.coo8.btoc.model.BaseModel;

/**
 * 系列
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-29
 */
public class BtoCSeries extends BaseModel {
	
	private static final long serialVersionUID = 5544568128191231463L;
	private int id;
	private int catalogid;
	private String seriesname;
	private int priority;
	private String description;
	private String picurl;
    /**
	 * @return 返回 id。
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id。
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return 返回 catalogid。
	 */
	public int getCatalogid() {
		return catalogid;
	}
	/**
	 * @param catalogid 要设置的 catalogid。
	 */
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	/**
	 * @return 返回 seriesname。
	 */
	public String getSeriesname() {
		return seriesname;
	}
	/**
	 * @param seriesname 要设置的 seriesname。
	 */
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	/**
	 * @return 返回 priority。
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority 要设置的 priority。
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return 返回 description。
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description 要设置的 description。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return 返回 picurl。
	 */
	public String getPicurl() {
		return picurl;
	}
	/**
	 * @param picurl 要设置的 picurl。
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	/**
	 * @return 返回 status。
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status 要设置的 status。
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	private int status;

}
