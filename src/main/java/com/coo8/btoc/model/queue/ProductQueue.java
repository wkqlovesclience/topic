package com.coo8.btoc.model.queue;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

public class ProductQueue extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rfid;
	private Integer priority;
	private Integer type;//0:ƒ¨»œ,7:‘§¿¿,4:…æ≥˝
	private Date inputDate;
	private Integer status;
	private Integer rtype;//Task¿‡–Õ±‡∫≈
	private String suffix;
	private Integer templateId;
	private String site;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRfid() {
		return rfid;
	}
	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRtype() {
		return rtype;
	}
	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
}
