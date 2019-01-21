package com.gome.productwords.model;


import java.util.Date;
/**
 * create by wangkeqiang-ds
 * 商品词错误信息
 */
public class ProductWordsErrorCard {
	private Integer id;
	private String productWordsBase;//词根
	private String productWordsName;//商品词
	private Date createTime;
	private String creator;
	private Integer type;
	private String logId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductWordsBase() {
		return productWordsBase;
	}

	public void setProductWordsBase(String productWordsBase) {
		this.productWordsBase = productWordsBase;
	}

	public String getProductWordsName() {
		return productWordsName;
	}

	public void setProductWordsName(String productWordsName) {
		this.productWordsName = productWordsName;
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

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Override
	public String toString() {
		return "ProductWordsErrorCard{" +
				"id=" + id +
				", productWordsBase='" + productWordsBase + '\'' +
				", productWordsName='" + productWordsName + '\'' +
				", createTime=" + createTime +
				", creator='" + creator + '\'' +
				", type=" + type +
				", logId='" + logId + '\'' +
				'}';
	}
}
