/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.model; 

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class Toptitle  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Toptitle";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAMES = "names";
	public static final String ALIAS_URLS = "urls";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_PRIORS = "priors";
	public static final String ALIAS_STATUS = "status";
	public static final String ALIAS_SITE = "site";
	
	 
	 
	//columns START
	private java.lang.Integer id;
	private java.lang.String names;
	private java.lang.String urls;
	private java.lang.String creator;
	private java.util.Date createtime;
	private java.lang.Integer priors;
	private java.lang.String status;
	private java.lang.String site;
	//columns END

	public java.lang.String getSite() {
		return site;
	}

	public void setSite(java.lang.String site) {
		this.site = site;
	}

	public Toptitle(){
	}

	public Toptitle(
		java.lang.Integer id
	){
		this.id = id;
	} 
		 
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
		 
	public void setNames(java.lang.String value) {
		this.names = value;
	}
	
	public java.lang.String getNames() {
		return this.names;
	}
		 
	public void setUrls(java.lang.String value) {
		this.urls = value;
	}
	
	public java.lang.String getUrls() {
		return this.urls;
	}
		 
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
		 
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
		 
	public void setPriors(java.lang.Integer value) {
		this.priors = value;
	}
	
	public java.lang.Integer getPriors() {
		return this.priors;
	}
		 
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Names",getNames())
			.append("Urls",getUrls())
			.append("Creator",getCreator())
			.append("Createtime",getCreatetime())
			.append("Priors",getPriors())
			.append("Status",getStatus())
			.append("site",getSite())
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Toptitle == false) return false;
		if(this == obj) return true;
		Toptitle other = (Toptitle)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

