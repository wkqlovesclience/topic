/*
 * �ļ����� BtoCSeries.java
 * 
 * �������ڣ� 2010-4-29
 *
 * Copyright(C) 2010, by wangyan.
 *
 * ԭʼ����: wangyan
 *
 */
package com.coo8.btoc.model.catalog;

import com.coo8.btoc.model.BaseModel;

/**
 * ϵ��
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
	 * @return ���� id��
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id Ҫ���õ� id��
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return ���� catalogid��
	 */
	public int getCatalogid() {
		return catalogid;
	}
	/**
	 * @param catalogid Ҫ���õ� catalogid��
	 */
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	/**
	 * @return ���� seriesname��
	 */
	public String getSeriesname() {
		return seriesname;
	}
	/**
	 * @param seriesname Ҫ���õ� seriesname��
	 */
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	/**
	 * @return ���� priority��
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority Ҫ���õ� priority��
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return ���� description��
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description Ҫ���õ� description��
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return ���� picurl��
	 */
	public String getPicurl() {
		return picurl;
	}
	/**
	 * @param picurl Ҫ���õ� picurl��
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	/**
	 * @return ���� status��
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status Ҫ���õ� status��
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	private int status;

}
