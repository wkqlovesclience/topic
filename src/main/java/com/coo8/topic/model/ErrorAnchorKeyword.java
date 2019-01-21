package com.coo8.topic.model;

import java.util.Date;

public class ErrorAnchorKeyword {
	private Integer id;
	private String keyName;
	private String pcUrl;
	private String wapUrl;
	private Integer rate;
	private Integer youxianji;
	//创建时间
	private Date createDate;
		//创建者
	private String createUser;
	
	private Integer type;
	/**
	 * @return the rate
	 */
	public Integer getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}
	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	/**
	 * @return the pcUrl
	 */
	public String getPcUrl() {
		return pcUrl;
	}
	/**
	 * @param pcUrl the pcUrl to set
	 */
	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}
	/**
	 * @return the wapUrl
	 */
	public String getWapUrl() {
		return wapUrl;
	}
	/**
	 * @param wapUrl the wapUrl to set
	 */
	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * @return the youxianji
	 */
	public Integer getYouxianji() {
		return youxianji;
	}
	/**
	 * @param youxianji the youxianji to set
	 */
	public void setYouxianji(Integer youxianji) {
		this.youxianji = youxianji;
	}
	
}
