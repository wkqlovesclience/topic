/*
 * 文件名： BtoCDefinition.java
 * 
 * 创建日期： 2010-5-3
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.model.definition;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

/**
 * 产品属性定义
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-5-3
 */
public class BtoCDefinition extends BaseModel {

	private static final long serialVersionUID = 1385180425026427192L;
	
	private int id;
	private String name;//中文缩写(动态表名称前缀)
	private String displayname;//显示名称
	private int status;//状态 0停用 1启用
	private Date inputdate;//录入时间
	private String inputer;//录入者
	private String updater;//修改者
	private Date updatetime;//更新时间
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
	 * @return 返回 name。
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 返回 displayname。
	 */
	public String getDisplayname() {
		return displayname;
	}
	/**
	 * @param displayname 要设置的 displayname。
	 */
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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
	/**
	 * @return 返回 inputdate。
	 */
	public Date getInputdate() {
		return inputdate;
	}
	/**
	 * @param inputdate 要设置的 inputdate。
	 */
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	/**
	 * @return 返回 inputer。
	 */
	public String getInputer() {
		return inputer;
	}
	/**
	 * @param inputer 要设置的 inputer。
	 */
	public void setInputer(String inputer) {
		this.inputer = inputer;
	}
	/**
	 * @return 返回 updater。
	 */
	public String getUpdater() {
		return updater;
	}
	/**
	 * @param updater 要设置的 updater。
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	/**
	 * @return 返回 updatetime。
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime 要设置的 updatetime。
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	

}
