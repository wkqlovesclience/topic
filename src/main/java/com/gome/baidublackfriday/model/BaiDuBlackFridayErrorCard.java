package com.gome.baidublackfriday.model;


import java.util.Date;

public class BaiDuBlackFridayErrorCard {
	private Integer id;
	private String skuId;
	private String productId;
	private String tag;//打折类型
	private String productType;//商品类型
	private String logId;
	private Date createTime;
	private String creator;
	private Integer type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	@Override
	public String toString() {
		return "BaiDuBlackFridayErrorCard [id=" + id + ", skuId=" + skuId + ", productId=" + productId + ", tag=" + tag
				+ ", productType=" + productType + ", logId=" + logId + ", createTime=" + createTime + ", creator="
				+ creator + ", type=" + type + "]";
	}

	
   
    
	
	
	
	
}
