package com.coo8.topic.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AladdinKeywords implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2697504392191388376L;
	
	//alias
	public static final String TABLE_ALIAS = "AladdinKeywords";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAMES = "names";
	public static final String ALIAS_DESCR = "descr";
	public static final String ALIAS_STATUS = "status"; 
	public static final String ALIAS_CATALOGID = "catalogId";
	
	private Integer id ;
	private String names;
	private String descr;
	private String status;
	private String catalogId;
	private String catalogName;   //商品品牌名
	
	private String productUrl;    //泛需求模板中商品URL
	private String productTitle;  //泛需求模板中商品标题
	private String frameUrl;      //泛需求框架URL
	private boolean showTag = true;//是否在XML文件中显示
	private String related = "";   //关联状态 数据库关联查询使用
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Names",getNames())
			.append("Descr",getDescr())
			.append("Status",getStatus())
			.append("catalogId",getStatus())
			.append("catalogName",getStatus())
			.append("productUrl",getStatus())
			.append("productTitle",getStatus())
			.append("frameUrl",getStatus())
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
		if(obj instanceof AladdinKeywords == false) return false;
		if(this == obj) return true;
		AladdinKeywords other = (AladdinKeywords)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getFrameUrl() {
		return frameUrl;
	}
	public void setFrameUrl(String frameUrl) {
		this.frameUrl = frameUrl;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public boolean isShowTag() {
		return showTag;
	}
	public void setShowTag(boolean showTag) {
		this.showTag = showTag;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
}
