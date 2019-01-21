/*
 * 文件名： Catalog.java
 * 
 * 创建日期： 2010-4-22
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.model.catalog;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.coo8.topic.model.AladdinKeywords;

/**
 * 分类
 * 
 * @author wangyan
 * 
 * @version $Revision$
 * 
 * @since 2010-4-22
 */
public class BtoCCatalog extends CategoryBrandBase implements Comparable {
	private static final long serialVersionUID = 6964040978942102058L;
	private Date inputdate;// 录入时间
	private String inputer;// 录入人
	private Date updatedtime;// 更新时间
	private int pricelife;// 报价有效期
	private int type;
	private int definitionid;
	private int specialstatus; // 1位：是否全部显示关联商品，2位：是否显示规格

	public static final int DEFAULT_ROOTID = -1;
	public static final int TYPE_BIGCAT = 1;
	public static final int TYPE_SMALLCAT = 2;
	public static final int TYPE_BRANDCAT = 3;
	// 状态启用
	public static final int STATUS_ENABLE = 1;
	// 状态停用
	public static final int STATUS_DISABLE = 0;

	public Date getInputdate() {
		return inputdate;
	}

	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}

	public String getInputer() {
		return inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public Date getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	public int getPricelife() {
		return pricelife;
	}

	public void setPricelife(int pricelife) {
		this.pricelife = pricelife;
	}

	@Override
	public int compareTo(Object o) {
		if (this.type > ((BtoCCatalog) o).type)
			return 1;
		return -1;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDefinitionid() {
		return definitionid;
	}

	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
	}

	public int getSpecialstatus() {
		return specialstatus;
	}

	public void setSpecialstatus(int specialstatus) {
		this.specialstatus = specialstatus;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BtoCCatalog == false) return false;
		if(this == obj) return true;
		BtoCCatalog other = (BtoCCatalog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
}
