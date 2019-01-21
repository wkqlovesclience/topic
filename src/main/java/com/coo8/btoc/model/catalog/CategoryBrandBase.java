package com.coo8.btoc.model.catalog;

import com.coo8.btoc.model.BaseModel;

/**
 * @author:duanzuocai
 * @email:duanzuocai@yahoo.com.cn
 * @DateTime:2011-3-18
 * @Description: �����Ʒ�ƻ���
 */
public class CategoryBrandBase extends BaseModel{
	/**	UID */
	private static final long serialVersionUID = 4424955458695804703L;
	protected int id;			// ���
	protected String name;		// ����/Ʒ������
	protected String enname;	// Ӣ������
	protected int fatherid;		// ������ ID
	protected String picurl;	// ͼƬURL
	protected int priority;		// ����ֵ
	protected int status;		// ״̬
	protected int productcount;	// ��Ʒ����
	protected int type;			// ���ͣ�Ʒ��û��type,Ĭ��Ϊ0������ҳ��ֻ�ܽ��ճ�0��1����� 2С���� 3Ʒ��
	protected String updater;	// ������
	protected int definitionid;	// ����ID
	protected String urlcode;	// ҳ��س�
	protected String catname;	// ������������
	protected int level;		// Ȩ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public int getFatherid() {
		return fatherid;
	}
	public void setFatherid(int fatherid) {
		this.fatherid = fatherid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getProductcount() {
		return productcount;
	}
	public void setProductcount(int productcount) {
		this.productcount = productcount;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public int getDefinitionid() {
		return definitionid;
	}
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
	}
	public String getUrlcode() {
		return urlcode;
	}
	public void setUrlcode(String urlcode) {
		this.urlcode = urlcode;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
