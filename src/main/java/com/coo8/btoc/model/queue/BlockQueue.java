package com.coo8.btoc.model.queue;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;

public class BlockQueue extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer templateId;
	private Integer blockId;
	private Integer rfid;//引用Id，对应专题Id，产品Id，或者热搜词Id
	private Integer rtype;//类型
	private Integer priority;
	private Date inputDate;
	private Integer status;
	private Integer parade1;//扩展字段
	private Integer parade2;//扩展字段
	private String site;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getBlockId() {
		return blockId;
	}
	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	public Integer getRfid() {
		return rfid;
	}
	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}
	public Integer getRtype() {
		return rtype;
	}
	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	public Integer getParade1() {
		return parade1;
	}
	public void setParade1(Integer parade1) {
		this.parade1 = parade1;
	}
	public Integer getParade2() {
		return parade2;
	}
	public void setParade2(Integer parade2) {
		this.parade2 = parade2;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
}
