/*
 * 文件名： Attributegroup.java
 * 
 * 创建日期： 2010-4-28
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
 * 属性分组
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-28
 */
public class Attributegroup extends BaseModel {

	private static final long serialVersionUID = 7638694476222302868L;
	
    private int id;
    private String name;//分组名称
    private int definitionid;//产品定义id
    private String displayname;//显示名称
    private int priority;//排序
    private Date updatedtime;
    private String updater;
    private int type ;//分组类型，1礼品，0非礼品
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
	 * @return 返回 definitionid。
	 */
	public int getDefinitionid() {
		return definitionid;
	}
	/**
	 * @param definitionid 要设置的 definitionid。
	 */
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
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
	 * @return 返回 updatedtime。
	 */
	public Date getUpdatedtime() {
		return updatedtime;
	}
	/**
	 * @param updatedtime 要设置的 updatedtime。
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
	 * @param updater 要设置的 updater。
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
	/**
	 * @return 返回 type。
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type 要设置的 type。
	 */
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Attributegroup [definitionid=" + definitionid
				+ ", displayname=" + displayname + ", id=" + id + ", name="
				+ name + ", priority=" + priority + ", type=" + type
				+ ", updatedtime=" + updatedtime + ", updater=" + updater + "]";
	}

}
