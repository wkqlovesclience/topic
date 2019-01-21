package com.coo8.topic.model.gomeStores;

import java.io.Serializable;

public class GomeMstDivision implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 销售区域代码
	private String daId;
	// 排序
	private String orderNo;
	// 行政区域级别（1=省，2=市，3=区县）
	private int divLevel;
	// 父行政区域名称
	private String parentDivisionName;
	// 父行政区域Code
	private String parentDivisionCode;
	// 行政区域名称
	private String divisionName;
	// 行政区域Code
	private String divisionCode;

	public GomeMstDivision() {

	}

	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getDivLevel() {
		return divLevel;
	}

	public void setDivLevel(int divLevel) {
		this.divLevel = divLevel;
	}

	public String getParentDivisionName() {
		return parentDivisionName;
	}

	public void setParentDivisionName(String parentDivisionName) {
		this.parentDivisionName = parentDivisionName;
	}

	public String getParentDivisionCode() {
		return parentDivisionCode;
	}

	public void setParentDivisionCode(String parentDivisionCode) {
		this.parentDivisionCode = parentDivisionCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
}
