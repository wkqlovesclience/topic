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


public class Titles  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Titles";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PATHS = "paths";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_SOURCES = "sources";
	public static final String ALIAS_PER_SOURCE = "goodsId";
	public static final String ALIAS_END_SOURCE = "goodsName";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_PUBLIC_STAT = "publicStat";
	public static final String ALIAS_CHECK_STAT = "checkStat";
	public static final String ALIAS_INDEX_STAT = "indexStat";
	public static final String ALIAS_SITE = "site";
	
	 
	 
	//columns START
	private java.lang.Integer id;
	private java.lang.String paths;
	private java.lang.String title;
	private java.lang.String sources;
	private java.lang.String goodsId;
	private java.lang.String skuId;
	private java.lang.String site;
	private java.lang.String goodsName;
	private java.lang.String creator;
	private java.lang.String modifier;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.String publicStat;
	private java.lang.String checkStat;
	private java.lang.String indexStat;
	private java.lang.String tags;
	//columns END

//	add columns START
	private java.lang.Integer newscount;
	private java.lang.String editStat;
	 
	public Titles(){
	}

	public Titles(
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
		 
	public void setPaths(java.lang.String value) {
		this.paths = value;
	}
	
	public java.lang.String getPaths() {
		return this.paths;
	}
		 
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
		 
	public void setSources(java.lang.String value) {
		this.sources = value;
	}
	
	public java.lang.String getSources() {
		return this.sources;
	}
		 

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	public java.lang.String getModifier() {
		return modifier;
	}

	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
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
		 
	public void setPublicStat(java.lang.String value) {
		this.publicStat = value;
	}
	
	public java.lang.String getPublicStat() {
		return this.publicStat;
	}
		 
	public void setCheckStat(java.lang.String value) {
		this.checkStat = value;
	}
	
	public java.lang.String getCheckStat() {
		return this.checkStat;
	}
	
	public java.lang.String getIndexStat() {
		return indexStat;
	}

	public void setIndexStat(java.lang.String indexStat) {
		this.indexStat = indexStat;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Paths",getPaths())
			.append("Title",getTitle())
			.append("Sources",getSources())
			.append("GoodsId",getGoodsId())
			.append("GoodsName",getGoodsName())
			.append("SkuId",getSkuId())
			.append("Creator",getCreator())
			.append("Modifier",getModifier())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("PublicStat",getPublicStat())
			.append("CheckStat",getCheckStat())
			.append("NewsCount",getNewscount())
			.append("EditStat",getEditStat())
			.append("IndexStat",getIndexStat())
			.append("Site",getSite())
			.toString();
	}
	
	public java.lang.String getTags() {
		return tags;
	}

	public void setTags(java.lang.String tags) {
		this.tags = tags;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Titles == false) return false;
		if(this == obj) return true;
		Titles other = (Titles)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.Integer getNewscount() {
		return newscount;
	}

	public void setNewscount(java.lang.Integer newscount) {
		this.newscount = newscount;
	}

	public java.lang.String getEditStat() {
		return editStat;
	}

	public void setEditStat(java.lang.String editStat) {
		this.editStat = editStat;
	}
	public java.lang.String getSite() {
		return site;
	}

	public void setSite(java.lang.String site) {
		this.site = site;
	}

	public java.lang.String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.String goodsId) {
		this.goodsId = goodsId;
	}
	
	public java.lang.String getSkuId() {
		return skuId;
	}

	public void setSkuId(java.lang.String skuId) {
		this.skuId = skuId;
	}
}

