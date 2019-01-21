/*
 * 文件名： Definitionattribute.java
 * 
 * 创建日期： 2010-4-29
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
 * 产品定义和产品属性关联表
 * 
 * @author wangyan
 * 
 * @version $Revision$
 * 
 * @since 2010-4-29
 */
public class BtoCDefinitionattribute extends BaseModel {

	private static final long serialVersionUID = -1381181967451043256L;

	private int attributeid; // 属性ID
	private int definitionid;// 产品定义id
	private int id; // 自增长ID
	private String attributename;// 产品名称
	private String attributedisplayname;// 属性显示名称
	private int attributegroupid;// 属性分组id
	private int priority;// 排序
	/**
	 * 状态,位编码表示 x x x x x x 保留 保留 保留 主参 必填 显示
	 */
	private int status;// 状态
	private int selecttype;// 选择条件(单选多选)
	private Date updatedtime;// 更新时间
	private String updater;// 更新人

	/**
	 * @return 返回 definitionid。
	 */
	public int getDefinitionid() {
		return definitionid;
	}

	/**
	 * @param definitionid
	 *            要设置的 definitionid。
	 */
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
	}

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
	 * @return 返回 attributedisplayname。
	 */
	public String getAttributedisplayname() {
		return attributedisplayname;
	}

	/**
	 * @param attributedisplayname
	 *            要设置的 attributedisplayname。
	 */
	public void setAttributedisplayname(String attributedisplayname) {
		this.attributedisplayname = attributedisplayname;
	}

	/**
	 * @return 返回 attributegroupid。
	 */
	public int getAttributegroupid() {
		return attributegroupid;
	}

	/**
	 * @param attributegroupid
	 *            要设置的 attributegroupid。
	 */
	public void setAttributegroupid(int attributegroupid) {
		this.attributegroupid = attributegroupid;
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
	 * @return 返回 status。
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            要设置的 status。
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return 返回 selecttype。
	 */
	public int getSelecttype() {
		return selecttype;
	}

	/**
	 * @param selecttype
	 *            要设置的 selecttype。
	 */
	public void setSelecttype(int selecttype) {
		this.selecttype = selecttype;
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
	 * @return 返回 st_bt_。
	 */
	public int getSt_bt_() {
		return st_bt_;
	}

	/**
	 * @param stBt
	 *            要设置的 st_bt_。
	 */
	public void setSt_bt_(int stBt) {
		st_bt_ = stBt;
	}

	/**
	 * @return 返回 st_zsx_。
	 */
	public int getSt_zsx_() {
		return st_zsx_;
	}

	/**
	 * @param stZsx
	 *            要设置的 st_zsx_。
	 */
	public void setSt_zsx_(int stZsx) {
		st_zsx_ = stZsx;
	}

	/**
	 * @return 返回 st_xs_。
	 */
	public int getSt_xs_() {
		return st_xs_;
	}

	/**
	 * @param stXs
	 *            要设置的 st_xs_。
	 */
	public void setSt_xs_(int stXs) {
		st_xs_ = stXs;
	}

	/**
	 * @return 返回 pageSize。
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            要设置的 pageSize。
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return 返回 pageIndex。
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            要设置的 pageIndex。
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return 返回 startPos。
	 */
	public int getStartPos() {
		return startPos;
	}

	/**
	 * @param startPos
	 *            要设置的 startPos。
	 */
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	/**
	 * @return 返回 endPos。
	 */
	public int getEndPos() {
		return endPos;
	}

	/**
	 * @param endPos
	 *            要设置的 endPos。
	 */
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getAttributeid() {
		return attributeid;
	}
	public void setAttributeid(int attributeid) {
		this.attributeid = attributeid;
	}

	/**
	 * 以下为扩展属性，用于表现上面status 状态 (运行OperationUtils.AndMath() 方法返回的结果 本来应该在jsp
	 * 页面上直接描述，但是没有找到 status 2 的 bean 方法使用方式
	 * 
	 * @return
	 */
	private int st_bt_; // 必填
	private int st_zsx_; // 主属性
	private int st_xs_; // 显示

	private int pageSize;
	private int pageIndex;
	private int startPos;
	private int endPos;
}
