/*
 * 文件名： Color.java
 * 
 * 创建日期： 2010-5-8
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.model.catalog;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

/**
 * 颜色
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-5-8
 */
public class Color extends BaseModel {

	private static final long serialVersionUID = 5525847522576459706L;
	private int id ;
	private int catalogid ;//所属分类
	private String colorname ;//颜色名称
	private int status ;//状态
	private int priority ;//排序
	private Date inputdate ;//录入时间
	private String inputer ;//录入者
	private Date updatetime ;//修改者
	private String updater ;//更新时间
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
	 * @return 返回 colorname。
	 */
	public String getColorname() {
		return colorname;
	}
	/**
	 * @param colorname 要设置的 colorname。
	 */
	public void setColorname(String colorname) {
		this.colorname = colorname;
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
	

}
