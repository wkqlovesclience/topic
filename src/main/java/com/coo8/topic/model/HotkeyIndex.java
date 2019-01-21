package com.coo8.topic.model;

import java.io.Serializable;
import java.util.Date;

public class HotkeyIndex implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer hotkeyId;
	private String hotkeyTitle;
	private String pinyin;
	private String hotkeyIndex;
	private Integer source;
	private Integer priority;
	private Integer status;
	private String operator;
	private Date updatetime;
	private String site;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHotkeyId() {
		return hotkeyId;
	}
	public void setHotkeyId(Integer hotkeyId) {
		this.hotkeyId = hotkeyId;
	}
	public String getHotkeyTitle() {
		return hotkeyTitle;
	}
	public void setHotkeyTitle(String hotkeyTitle) {
		this.hotkeyTitle = hotkeyTitle;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getHotkeyIndex() {
		return hotkeyIndex;
	}
	public void setHotkeyIndex(String hotkeyIndex) {
		this.hotkeyIndex = hotkeyIndex;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "HotkeyIndex [id=" + id + ", hotkeyId=" + hotkeyId
				+ ", hotkeyTitle=" + hotkeyTitle + ", pinyin=" + pinyin
				+ ", hotkeyIndex=" + hotkeyIndex + ", source=" + source
				+ ", priority=" + priority + ", status=" + status
				+ ", operator=" + operator + ", updatetime=" + updatetime
				+ ", site=" + site + "]";
	}
	
}
