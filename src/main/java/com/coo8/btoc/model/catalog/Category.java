package com.coo8.btoc.model.catalog;

import com.coo8.btoc.model.BaseModel;

public class Category extends BaseModel{
	
	
	
	private String cataid;  //����id
	private String cataname;//��������
	private String pcataid; //������id
	
	public Category() {
		// ������	
	}
	
	public Category(String cataid, String cataname, String pcataid) {
		super();
		this.cataid = cataid;
		this.cataname = cataname;
		this.pcataid = pcataid;
	}
	
	public String getCataid() {
		return cataid;
	}
	public void setCataid(String cataid) {
		this.cataid = cataid;
	}
	public String getCataname() {
		return cataname;
	}
	public void setCataname(String cataname) {
		this.cataname = cataname;
	}
	public String getPcataid() {
		return pcataid;
	}
	public void setPcataid(String pcataid) {
		this.pcataid = pcataid;
	}

}
