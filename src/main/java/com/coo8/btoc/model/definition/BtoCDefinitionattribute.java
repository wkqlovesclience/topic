/*
 * �ļ����� Definitionattribute.java
 * 
 * �������ڣ� 2010-4-29
 *
 * Copyright(C) 2010, by wangyan.
 *
 * ԭʼ����: wangyan
 *
 */
package com.coo8.btoc.model.definition;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

/**
 * ��Ʒ����Ͳ�Ʒ���Թ�����
 * 
 * @author wangyan
 * 
 * @version $Revision$
 * 
 * @since 2010-4-29
 */
public class BtoCDefinitionattribute extends BaseModel {

	private static final long serialVersionUID = -1381181967451043256L;

	private int attributeid; // ����ID
	private int definitionid;// ��Ʒ����id
	private int id; // ������ID
	private String attributename;// ��Ʒ����
	private String attributedisplayname;// ������ʾ����
	private int attributegroupid;// ���Է���id
	private int priority;// ����
	/**
	 * ״̬,λ�����ʾ x x x x x x ���� ���� ���� ���� ���� ��ʾ
	 */
	private int status;// ״̬
	private int selecttype;// ѡ������(��ѡ��ѡ)
	private Date updatedtime;// ����ʱ��
	private String updater;// ������

	/**
	 * @return ���� definitionid��
	 */
	public int getDefinitionid() {
		return definitionid;
	}

	/**
	 * @param definitionid
	 *            Ҫ���õ� definitionid��
	 */
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
	}

	/**
	 * @return ���� attributename��
	 */
	public String getAttributename() {
		return attributename;
	}

	/**
	 * @param attributename
	 *            Ҫ���õ� attributename��
	 */
	public void setAttributename(String attributename) {
		this.attributename = attributename;
	}

	/**
	 * @return ���� attributedisplayname��
	 */
	public String getAttributedisplayname() {
		return attributedisplayname;
	}

	/**
	 * @param attributedisplayname
	 *            Ҫ���õ� attributedisplayname��
	 */
	public void setAttributedisplayname(String attributedisplayname) {
		this.attributedisplayname = attributedisplayname;
	}

	/**
	 * @return ���� attributegroupid��
	 */
	public int getAttributegroupid() {
		return attributegroupid;
	}

	/**
	 * @param attributegroupid
	 *            Ҫ���õ� attributegroupid��
	 */
	public void setAttributegroupid(int attributegroupid) {
		this.attributegroupid = attributegroupid;
	}

	/**
	 * @return ���� priority��
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            Ҫ���õ� priority��
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return ���� status��
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            Ҫ���õ� status��
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return ���� selecttype��
	 */
	public int getSelecttype() {
		return selecttype;
	}

	/**
	 * @param selecttype
	 *            Ҫ���õ� selecttype��
	 */
	public void setSelecttype(int selecttype) {
		this.selecttype = selecttype;
	}

	/**
	 * @return ���� updatedtime��
	 */
	public Date getUpdatedtime() {
		return updatedtime;
	}

	/**
	 * @param updatedtime
	 *            Ҫ���õ� updatedtime��
	 */
	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	/**
	 * @return ���� updater��
	 */
	public String getUpdater() {
		return updater;
	}

	/**
	 * @param updater
	 *            Ҫ���õ� updater��
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	/**
	 * @return ���� st_bt_��
	 */
	public int getSt_bt_() {
		return st_bt_;
	}

	/**
	 * @param stBt
	 *            Ҫ���õ� st_bt_��
	 */
	public void setSt_bt_(int stBt) {
		st_bt_ = stBt;
	}

	/**
	 * @return ���� st_zsx_��
	 */
	public int getSt_zsx_() {
		return st_zsx_;
	}

	/**
	 * @param stZsx
	 *            Ҫ���õ� st_zsx_��
	 */
	public void setSt_zsx_(int stZsx) {
		st_zsx_ = stZsx;
	}

	/**
	 * @return ���� st_xs_��
	 */
	public int getSt_xs_() {
		return st_xs_;
	}

	/**
	 * @param stXs
	 *            Ҫ���õ� st_xs_��
	 */
	public void setSt_xs_(int stXs) {
		st_xs_ = stXs;
	}

	/**
	 * @return ���� pageSize��
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            Ҫ���õ� pageSize��
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return ���� pageIndex��
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            Ҫ���õ� pageIndex��
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return ���� startPos��
	 */
	public int getStartPos() {
		return startPos;
	}

	/**
	 * @param startPos
	 *            Ҫ���õ� startPos��
	 */
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	/**
	 * @return ���� endPos��
	 */
	public int getEndPos() {
		return endPos;
	}

	/**
	 * @param endPos
	 *            Ҫ���õ� endPos��
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
	 * ����Ϊ��չ���ԣ����ڱ�������status ״̬ (����OperationUtils.AndMath() �������صĽ�� ����Ӧ����jsp
	 * ҳ����ֱ������������û���ҵ� status 2 �� bean ����ʹ�÷�ʽ
	 * 
	 * @return
	 */
	private int st_bt_; // ����
	private int st_zsx_; // ������
	private int st_xs_; // ��ʾ

	private int pageSize;
	private int pageIndex;
	private int startPos;
	private int endPos;
}
