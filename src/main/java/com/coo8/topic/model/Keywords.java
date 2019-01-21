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


public class Keywords  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Keywords";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPES = "types";
	public static final String ALIAS_NAMES = "names";
	public static final String ALIAS_URL = "url";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_MODIFIER = "modifier";
	public static final String ALIAS_SITE = "site";
	
	 
	 
	//columns START
	private java.lang.Integer id;
	private java.lang.String types;
	private java.lang.String names;
	private java.lang.String url;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.String creator;
	private java.lang.String modifier;
	private java.lang.String site;
	private java.lang.Integer titleId;
	private java.lang.Integer articleId;
	//columns END

	public java.lang.Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(java.lang.Integer articleId) {
		this.articleId = articleId;
	}

	public java.lang.String getSite() {
		return site;
	}

	public void setSite(java.lang.String site) {
		this.site = site;
	}

	public Keywords(){
	}

	public Keywords(
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
		 
	public void setTypes(java.lang.String value) {
		this.types = value;
	}
	
	public java.lang.String getTypes() {
		return this.types;
	}
		 
	public void setNames(java.lang.String value) {
		this.names = value;
	}
	
	public java.lang.String getNames() {
		return this.names;
	}
		 
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
		 
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		 
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
		 
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Types",getTypes())
			.append("Names",getNames())
			.append("Url",getUrl())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Creator",getCreator())
			.append("Site",getSite())
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
		if(obj instanceof Keywords == false) return false;
		if(this == obj) return true;
		Keywords other = (Keywords)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.String getModifier() {
		return modifier;
	}

	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
	}

	public java.lang.Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(java.lang.Integer titleId) {
		this.titleId = titleId;
	}
}

