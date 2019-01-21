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


public class KeywordsRel  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "KeywordsRel";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE_ID = "titleId";
	public static final String ALIAS_KEYWORDS = "keywords";
	
	 
	 
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer titleId;	
	private java.lang.Integer keywords;
	//columns END
	
	public KeywordsRel(){
	}

	public KeywordsRel(
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
		 
	public void setKeywords(java.lang.Integer value) {
		this.keywords = value;
	}
	
	public java.lang.Integer getKeywords() {
		return this.keywords;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TitleId",getTitleId())
			.append("Keywords",getKeywords())
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
		if(obj instanceof KeywordsRel == false) return false;
		if(this == obj) return true;
		KeywordsRel other = (KeywordsRel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

