package com.coo8.topic.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AladdinKeywordsRef implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3497463478462482942L;
	//alias
	public static final String TABLE_ALIAS = "AladdinKeywordsRef";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_AKEYWORDS = "akeywords";
	public static final String ALIAS_CATALOGID = "catalogid";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";

	private Integer id;
	private Integer akeywords;
	private String catalogid;
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Akeywords",getAkeywords())
			.append("Catalogid",getCatalogid())
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
		if(obj instanceof AladdinKeywordsRef == false) return false;
		if(this == obj) return true;
		AladdinKeywordsRef other = (AladdinKeywordsRef)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAkeywords() {
		return akeywords;
	}
	public void setAkeywords(Integer akeywords) {
		this.akeywords = akeywords;
	}
	public String getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(String catalogid) {
		this.catalogid = catalogid;
	}

}
