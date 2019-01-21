package com.coo8.topic.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GoodsDrops implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1895160749268683244L;
	
	//alias
	public static final String TABLE_ALIAS = "GoodsDrops";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLEID = "titleId";
	public static final String ALIAS_GOODSID = "goodsId";
	public static final String ALIAS_GSTATUS = "gstatus";
	
	//columns START
	private java.lang.Integer id;     //编号
	private java.lang.String goodsId; //商品编号
	private java.lang.Integer titleId; //
	private java.lang.String gstatus; //商品状态
	//columns END
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("titleId",getGoodsId())
			.append("goodsId",getGoodsId())
			.append("gstatus",getGstatus())
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
		if(obj instanceof GoodsDrops == false) return false;
		if(this == obj) return true;
		GoodsDrops other = (GoodsDrops)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}


	public java.lang.String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.String goodsId) {
		this.goodsId = goodsId;
	}

	public java.lang.String getGstatus() {
		return gstatus;
	}

	public void setGstatus(java.lang.String gstatus) {
		this.gstatus = gstatus;
	}
	public GoodsDrops() {
	}
	public GoodsDrops(String goodsId, String gstatus) {
		super();
		this.goodsId = goodsId;
		this.gstatus = gstatus;
	}
	public GoodsDrops(Integer titleId ,String goodsId, String gstatus) {
		super();
		this.titleId = titleId;
		this.goodsId = goodsId;
		this.gstatus = gstatus;
	}

	public java.lang.Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(java.lang.Integer titleId) {
		this.titleId = titleId;
	}
	
	
}
