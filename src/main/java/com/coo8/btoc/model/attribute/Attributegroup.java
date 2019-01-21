/*
 * �ļ����� Attributegroup.java
 * 
 * �������ڣ� 2010-4-28
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
 * ���Է���
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
    private String name;//��������
    private int definitionid;//��Ʒ����id
    private String displayname;//��ʾ����
    private int priority;//����
    private Date updatedtime;
    private String updater;
    private int type ;//�������ͣ�1��Ʒ��0����Ʒ
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
	 * @return ���� definitionid��
	 */
	public int getDefinitionid() {
		return definitionid;
	}
	/**
	 * @param definitionid Ҫ���õ� definitionid��
	 */
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
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
	 * @return ���� updatedtime��
	 */
	public Date getUpdatedtime() {
		return updatedtime;
	}
	/**
	 * @param updatedtime Ҫ���õ� updatedtime��
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
	 * @param updater Ҫ���õ� updater��
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
	/**
	 * @return ���� type��
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type Ҫ���õ� type��
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
