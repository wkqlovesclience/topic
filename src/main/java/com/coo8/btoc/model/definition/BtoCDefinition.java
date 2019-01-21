/*
 * �ļ����� BtoCDefinition.java
 * 
 * �������ڣ� 2010-5-3
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
 * ��Ʒ���Զ���
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
	private String name;//������д(��̬������ǰ׺)
	private String displayname;//��ʾ����
	private int status;//״̬ 0ͣ�� 1����
	private Date inputdate;//¼��ʱ��
	private String inputer;//¼����
	private String updater;//�޸���
	private Date updatetime;//����ʱ��
	/**
	 * @return ���� id��
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id Ҫ���õ� id��
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return ���� name��
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name Ҫ���õ� name��
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return ���� displayname��
	 */
	public String getDisplayname() {
		return displayname;
	}
	/**
	 * @param displayname Ҫ���õ� displayname��
	 */
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	/**
	 * @return ���� status��
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status Ҫ���õ� status��
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return ���� inputdate��
	 */
	public Date getInputdate() {
		return inputdate;
	}
	/**
	 * @param inputdate Ҫ���õ� inputdate��
	 */
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	/**
	 * @return ���� inputer��
	 */
	public String getInputer() {
		return inputer;
	}
	/**
	 * @param inputer Ҫ���õ� inputer��
	 */
	public void setInputer(String inputer) {
		this.inputer = inputer;
	}
	/**
	 * @return ���� updater��
	 */
	public String getUpdater() {
		return updater;
	}
	/**
	 * @param updater Ҫ���õ� updater��
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	/**
	 * @return ���� updatetime��
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime Ҫ���õ� updatetime��
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	

}
