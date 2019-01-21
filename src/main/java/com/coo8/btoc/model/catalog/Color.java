/*
 * �ļ����� Color.java
 * 
 * �������ڣ� 2010-5-8
 *
 * Copyright(C) 2010, by wangyan.
 *
 * ԭʼ����: wangyan
 *
 */
package com.coo8.btoc.model.catalog;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

/**
 * ��ɫ
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
	private int catalogid ;//��������
	private String colorname ;//��ɫ����
	private int status ;//״̬
	private int priority ;//����
	private Date inputdate ;//¼��ʱ��
	private String inputer ;//¼����
	private Date updatetime ;//�޸���
	private String updater ;//����ʱ��
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
	 * @return ���� catalogid��
	 */
	public int getCatalogid() {
		return catalogid;
	}
	/**
	 * @param catalogid Ҫ���õ� catalogid��
	 */
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	/**
	 * @return ���� colorname��
	 */
	public String getColorname() {
		return colorname;
	}
	/**
	 * @param colorname Ҫ���õ� colorname��
	 */
	public void setColorname(String colorname) {
		this.colorname = colorname;
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
	 * @return ���� priority��
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority Ҫ���õ� priority��
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	

}
