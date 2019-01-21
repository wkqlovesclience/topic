/*
 * 文件名： Attributeenumvalue.java
 * 
 * 创建日期： 2010-4-27
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.model.attribute;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

/**
 * 
 * 
 * @author wangyan
 * 
 * @version $Revision$
 * 
 * @since 2010-4-27
 */
public class Attributeenumvalue extends BaseModel {

	private static final long serialVersionUID = -3269291295627206567L;
	private int attributeid;//自增长ID
	private String attributename;// 属性名称
	private String value;// 属性值
	private int priority;// 排序字段
	private String tags;// 标签/关键词
	private Date updatedtime;// 更新时间
	private String updater;// 更新人
	private String attributeenumpic;// 图片存储地址
	private String attrienumpicdesc;// 图片描述

	/**
	 * @return 返回 attributename。
	 */
	public String getAttributename() {
		return attributename;
	}

	/**
	 * @param attributename
	 *            要设置的 attributename。
	 */
	public void setAttributename(String attributename) {
		this.attributename = attributename;
	}

	/**
	 * @return 返回 value。
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            要设置的 value。
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return 返回 priority。
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            要设置的 priority。
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return 返回 tags。
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            要设置的 tags。
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return 返回 updatedtime。
	 */
	public Date getUpdatedtime() {
		return updatedtime;
	}

	/**
	 * @param updatedtime
	 *            要设置的 updatedtime。
	 */
	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	/**
	 * @return 返回 updater。
	 */
	public String getUpdater() {
		return updater;
	}

	/**
	 * @param updater
	 *            要设置的 updater。
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	/**
	 * @return 返回 attributeenumpic。
	 */
	public String getAttributeenumpic() {
		return attributeenumpic;
	}

	/**
	 * @param attributeenumpic
	 *            要设置的 attributeenumpic。
	 */
	public void setAttributeenumpic(String attributeenumpic) {
		this.attributeenumpic = attributeenumpic;
	}

	/**
	 * @return 返回 attrienumpicdesc。
	 */
	public String getAttrienumpicdesc() {
		return attrienumpicdesc;
	}

	/**
	 * @param attrienumpicdesc
	 *            要设置的 attrienumpicdesc。
	 */
	public void setAttrienumpicdesc(String attrienumpicdesc) {
		this.attrienumpicdesc = attrienumpicdesc;
	}

	public int getAttributeid() {
		return attributeid;
	}

	public void setAttributeid(int attributeid) {
		this.attributeid = attributeid;
	}

}
