package com.coo8.btoc.model.items;

import com.coo8.btoc.model.BaseModel;

public class BtocItemDesc extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7835519758502723758L;
	private Integer id;
	private String itemid;
	private String description;
	private String maininfo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMaininfo() {
		return maininfo;
	}
	public void setMaininfo(String maininfo) {
		this.maininfo = maininfo;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	
	

}
