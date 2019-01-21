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


public class TitleGoods  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TitleGoods";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_GOODS_ID = "goodsId";
	public static final String ALIAS_GOODS_NO = "goodsNo";
	public static final String ALIAS_TITLE_ID = "titleId";
	
	 
	 
	//columns START
	private java.lang.Integer id;
	private java.lang.String goodsId;
	private java.lang.String goodsNo;
	private java.lang.Integer titleId;
	//columns END

	public TitleGoods(){
	}

	public TitleGoods(
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
		 
		 
	public java.lang.String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.String goodsId) {
		this.goodsId = goodsId;
	}

	public void setGoodsNo(java.lang.String value) {
		this.goodsNo = value;
	}
	
	public java.lang.String getGoodsNo() {
		return this.goodsNo;
	}
		 
	public void setTitleId(java.lang.Integer value) {
		this.titleId = value;
	}
	
	public java.lang.Integer getTitleId() {
		return this.titleId;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GoodsId",getGoodsId())
			.append("GoodsNo",getGoodsNo())
			.append("TitleId",getTitleId())
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
		if(obj instanceof TitleGoods == false) return false;
		if(this == obj) return true;
		TitleGoods other = (TitleGoods)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

