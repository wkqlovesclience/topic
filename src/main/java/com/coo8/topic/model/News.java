/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.model; 

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class News  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "News";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE_ID = "titleId";
	public static final String ALIAS_TOPIC = "topic";
	public static final String ALIAS_IS_TOP = "isTop";
	public static final String ALIAS_KEYWORDS = "keywords";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_CONTENTS = "contents";
	public static final String ALIAS_PUBLIC_TIME = "publicTime";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_MODIFIER = "modifier";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_SOURCEURL = "sourceUrl";
	public static final String ALIAS_SITE = "site";
	 
	 
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer titleId;
	private java.lang.String topic;
	private java.lang.String isTop;
	private java.lang.String keywords;
	private java.lang.String remark;
	private java.lang.String contents;
	private java.lang.String picUrl;	
	private java.util.Date publicTime;
	private java.lang.String creator;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private String states;
	private String sourceUrl;
	private String modifier;
	private String paths;  //ר������·��
	private String site;
	private java.lang.String tags;
	
	private String newsUrl;
	private String newsTestUrl;
	//columns END

	public java.lang.String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(java.lang.String picUrl) {
		this.picUrl = picUrl;
	}
	
	public java.lang.String getTags() {
		return tags;
	}

	public void setTags(java.lang.String tags) {
		this.tags = tags;
	}
	
	
	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}

	public News(){
	}

	public News(
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
		 
	public void setTitleId(java.lang.Integer value) {
		this.titleId = value;
	}
	
	public java.lang.Integer getTitleId() {
		return this.titleId;
	}
		 
	public void setTopic(java.lang.String value) {
		this.topic = value;
	}
	
	public java.lang.String getTopic() {
		return this.topic;
	}
		 
	public void setIsTop(java.lang.String value) {
		this.isTop = value;
	}
	
	public java.lang.String getIsTop() {
		return this.isTop;
	}
		 
	public void setKeywords(java.lang.String value) {
		this.keywords = value;
	}
	
	public java.lang.String getKeywords() {
		return this.keywords;
	}
		 
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
		 
	public void setContents(java.lang.String value) {
		this.contents = value;
	}
	
	public java.lang.String getContents() {
		return this.contents;
	}
		 
	public void setPublicTime(java.util.Date value) {
		this.publicTime = value;
	}
	
	public java.util.Date getPublicTime() {
		return this.publicTime;
	}
		 
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
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
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TitleId",getTitleId())
			.append("Topic",getTopic())
			.append("IsTop",getIsTop())
			.append("Keywords",getKeywords())
			.append("Remark",getRemark())
			.append("Contents",getContents())
			.append("PublicTime",getPublicTime())
			.append("Creator",getCreator())
			.append("Modifier",getModifier())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("States",getStates())
			.append("SourceUrl",getSourceUrl())
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
		if(obj instanceof News == false) return false;
		if(this == obj) return true;
		News other = (News)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}


	public void setCreateTimeString(Date createTime2) {
		//��createTime2ת������Ҫ�ĸ�ʽ���ַ���
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String createTimeString1 = df.format(createTime2);
		setCreateTimeString(createTimeString1);
	}
	private String createTimeString;

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getNewsTestUrl() {
		return newsTestUrl;
	}

	public void setNewsTestUrl(String newsTestUrl) {
		this.newsTestUrl = newsTestUrl;
	}

}

