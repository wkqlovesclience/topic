/*
 * �ļ����� Attributeenumvalue.java
 * 
 * �������ڣ� 2010-4-27
 *
 * Copyright(C) 2010, by wangyan.
 *
 * ԭʼ����: wangyan
 *
 */
package com.coo8.btoc.model.attribute;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

/**
 * 
 * 
 * @author wangyan
 * 
 * @version $Revision$
 * 
 * @since 2010-4-27
 */
public class Attributeenumvalue extends BaseModel {

	private static final long serialVersionUID = -3269291295627206567L;
	private int attributeid;//������ID
	private String attributename;// ��������
	private String value;// ����ֵ
	private int priority;// �����ֶ�
	private String tags;// ��ǩ/�ؼ���
	private Date updatedtime;// ����ʱ��
	private String updater;// ������
	private String attributeenumpic;// ͼƬ�洢��ַ
	private String attrienumpicdesc;// ͼƬ����

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
	 * @return ���� value��
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            Ҫ���õ� value��
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return ���� tags��
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            Ҫ���õ� tags��
	 */
	public void setTags(String tags) {
		this.tags = tags;
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
	 * @return ���� attributeenumpic��
	 */
	public String getAttributeenumpic() {
		return attributeenumpic;
	}

	/**
	 * @param attributeenumpic
	 *            Ҫ���õ� attributeenumpic��
	 */
	public void setAttributeenumpic(String attributeenumpic) {
		this.attributeenumpic = attributeenumpic;
	}

	/**
	 * @return ���� attrienumpicdesc��
	 */
	public String getAttrienumpicdesc() {
		return attrienumpicdesc;
	}

	/**
	 * @param attrienumpicdesc
	 *            Ҫ���õ� attrienumpicdesc��
	 */
	public void setAttrienumpicdesc(String attrienumpicdesc) {
		this.attrienumpicdesc = attrienumpicdesc;
	}

	public int getAttributeid() {
		return attributeid;
	}

	public void setAttributeid(int attributeid) {
		this.attributeid = attributeid;
	}

}
