/*
 * 文件名： UserInfo.java
 * 
 * 创建日期： 2010-4-21
 *
 * Copyright(C) 2010, by zhangbo.
 *
 * 原始作者: <a href="mailto:zhangbo@staff.chinabyte.com">zhangbo</a>
 *
 */
package com.coo8.btoc.model.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coo8.btoc.model.BaseModel;
/**
 * 
 *
 * @author <a href="mailto:zhangbo@staff.chinabyte.com">zhangbo</a>
 *
 * @version $$Revision$$
 *
 * @since 2010-4-21
 */
public class UserInfo extends BaseModel {
	private static final long serialVersionUID = 5587432362170994360L;
	public static final int STATUS_USE=1;
	public static final int STATUS_NOUSE=0;
	/**
	 * ID
	 */
	private int id;
	/**
	 * 用户真是姓名
	 */
	private String pin;
	/**
	 * 登陆名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 状态 0为停用 1为启用
	 */
	private Integer status;
	/**
	 * 录入时间
	 */
	private Date inputdate;
	/**
	 * 录入人
	 */
	private String inputer;
	/**
	 * 更新时间
	 */
	private Date updatedtime;
	/**
	 * 更新人
	 */
	private String updater;
	/**
	 * 用户对应的权限集合
	 */
	private List rolemaps = new ArrayList();
	
	
	public List getRolemaps() {
		return rolemaps;
	}
	public void setRolemaps(List rolemaps) {
		this.rolemaps = rolemaps;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
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
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
}
