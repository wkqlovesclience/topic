package com.coo8.topic.model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;

import com.coo8.common.utils.HttpClientUtil;
/**
 * 
 * @author zhanghan-ds3
 * 锚文本添加系统关键词表
 *
 */

import ch.qos.logback.core.net.SyslogOutputStream;
public class AnchorKeywords implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3681059483240785811L;

	public static final String ALIAS_ID = "id";
	
	//编号
	private Integer id ;
	//关键词名称
	private String keyName;
	//PC端url
	private String pcUrl;
	//WAP端url
	private String wapUrl;
	//url校验
	private String testUrl ;
	//频率
	private Integer rate;
	//创建时间
	private Date createDate;
	//创建者
	private String createUser;
	//修改者
	private String updateUser;
	//修改时间
	private Date updateDate;
	//优先级
	private Integer youxianji; 
	 
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AnchorKeywords == false) return false;
		if(this == obj) return true;
		AnchorKeywords other = (AnchorKeywords)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
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
	public String getTestUrl() {
		return testUrl;
	}
	public void setTestUrl(String pcUrl, String wapUrl) {
		//pcUrl和wapUrl都正常
		if(HttpClientUtil.testUrl(pcUrl) && HttpClientUtil.testUrl(wapUrl)){
			this.testUrl = "p_w";
		}else if(HttpClientUtil.testUrl(pcUrl)){   //pcUrl正常
			this.testUrl = "p";
		}else if (HttpClientUtil.testUrl(wapUrl)) { //wapUrl正常
			this.testUrl = "w";
		}else {
			this.testUrl = "no";
		}		
		System.out.println("pcUrl:"+HttpClientUtil.testUrl(pcUrl)+"  wapUrl:"+HttpClientUtil.testUrl(wapUrl));
	}
	
}
