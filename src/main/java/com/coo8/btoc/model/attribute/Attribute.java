/*
 * �ļ����� Attribute.java
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
 * ��Ʒ����
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-27
 */
public class Attribute extends BaseModel {
	private static final long serialVersionUID = 9053606981063787907L;
	private int id;//������ID
	private String name;//������д����̬���ֶΣ�
    private String displayname;//��ʾ����
    private String defaultvalue;//Ĭ��ֵ
    private String glossaryurl;//������͵�ַ
    private int width;//������
    private String fromwhere;//��Դ
    private Date inputdate;//¼��ʱ��
    private String inputer;//¼����
    private String updater;//������
    private Date updatetime;//����ʱ��
    private String criterion;//��׼
    private String abilityName;//��������
    private String help ;//����˵��(ǰ̨)
    private String fatherName;//����������
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
	 * @return ���� defaultvalue��
	 */
	public String getDefaultvalue() {
		return defaultvalue;
	}
	/**
	 * @param defaultvalue Ҫ���õ� defaultvalue��
	 */
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	/**
	 * @return ���� glossaryurl��
	 */
	public String getGlossaryurl() {
		return glossaryurl;
	}
	/**
	 * @param glossaryurl Ҫ���õ� glossaryurl��
	 */
	public void setGlossaryurl(String glossaryurl) {
		this.glossaryurl = glossaryurl;
	}
	/**
	 * @return ���� width��
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width Ҫ���õ� width��
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return ���� fromwhere��
	 */
	public String getFromwhere() {
		return fromwhere;
	}
	/**
	 * @param fromwhere Ҫ���õ� fromwhere��
	 */
	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
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
	 * @return ���� updatedtime��
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
	 * @return ���� criterion��
	 */
	public String getCriterion() {
		return criterion;
	}
	/**
	 * @param criterion Ҫ���õ� criterion��
	 */
	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}
	/**
	 * @return ���� abilityName��
	 */
	public String getAbilityName() {
		return abilityName;
	}
	/**
	 * @param abilityName Ҫ���õ� abilityName��
	 */
	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}
	/**
	 * @return ���� help��
	 */
	public String getHelp() {
		return help;
	}
	/**
	 * @param help Ҫ���õ� help��
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
