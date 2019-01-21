/*
 * 文件名： Attribute.java
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
 * 产品属性
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-27
 */
public class Attribute extends BaseModel {
	private static final long serialVersionUID = 9053606981063787907L;
	private int id;//自增长ID
	private String name;//中文缩写（动态表字段）
    private String displayname;//显示名称
    private String defaultvalue;//默认值
    private String glossaryurl;//术语解释地址
    private int width;//定义宽度
    private String fromwhere;//来源
    private Date inputdate;//录入时间
    private String inputer;//录入者
    private String updater;//更新者
    private Date updatetime;//更新时间
    private String criterion;//标准
    private String abilityName;//性能名称
    private String help ;//帮助说明(前台)
    private String fatherName;//父分类名称
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
	 * @return 返回 defaultvalue。
	 */
	public String getDefaultvalue() {
		return defaultvalue;
	}
	/**
	 * @param defaultvalue 要设置的 defaultvalue。
	 */
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	/**
	 * @return 返回 glossaryurl。
	 */
	public String getGlossaryurl() {
		return glossaryurl;
	}
	/**
	 * @param glossaryurl 要设置的 glossaryurl。
	 */
	public void setGlossaryurl(String glossaryurl) {
		this.glossaryurl = glossaryurl;
	}
	/**
	 * @return 返回 width。
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width 要设置的 width。
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return 返回 fromwhere。
	 */
	public String getFromwhere() {
		return fromwhere;
	}
	/**
	 * @param fromwhere 要设置的 fromwhere。
	 */
	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
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
	 * @return 返回 updatedtime。
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
	 * @return 返回 criterion。
	 */
	public String getCriterion() {
		return criterion;
	}
	/**
	 * @param criterion 要设置的 criterion。
	 */
	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}
	/**
	 * @return 返回 abilityName。
	 */
	public String getAbilityName() {
		return abilityName;
	}
	/**
	 * @param abilityName 要设置的 abilityName。
	 */
	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}
	/**
	 * @return 返回 help。
	 */
	public String getHelp() {
		return help;
	}
	/**
	 * @param help 要设置的 help。
	 */
	public void setHelp(String help) {
		this.help = help;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
}
