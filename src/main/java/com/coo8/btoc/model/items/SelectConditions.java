package com.coo8.btoc.model.items;

import java.util.Date;
import java.util.List;

public class SelectConditions {
	
	protected int status;
	protected String operator;
	protected String itemid;
	protected String name;
	protected String productname;
	protected int catalogid;
	protected int brandid;
	protected Date inbegintime;
	protected Date inendtime;
	protected Date upbegintime;
	protected Date upendtime;
	protected List<Integer> catalogIdList;
	protected int defectstatus;
	
	
	public int getDefectstatus() {
		return defectstatus;
	}
	public void setDefectstatus(int defectstatus) {
		this.defectstatus = defectstatus;
	}
	public List<Integer> getCatalogIdList() {
		return catalogIdList;
	}
	public void setCatalogIdList(List<Integer> catalogIdList) {
		this.catalogIdList = catalogIdList;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public Date getInbegintime() {
		return inbegintime;
	}
	public void setInbegintime(Date inbegintime) {
		this.inbegintime = inbegintime;
	}
	public Date getInendtime() {
		return inendtime;
	}
	public void setInendtime(Date inendtime) {
		this.inendtime = inendtime;
	}
	public Date getUpbegintime() {
		return upbegintime;
	}
	public void setUpbegintime(Date upbegintime) {
		this.upbegintime = upbegintime;
	}
	
	public Date getUpendtime() {
		return upendtime;
	}
	public void setUpendtime(Date upendtime) {
		this.upendtime = upendtime;
	}
	
	

}
