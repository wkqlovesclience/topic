package com.gome.promotioncard.model;

import java.util.Date;
import java.util.List;

public class PromotionCard {
	private Integer id;
	private String skuId;
	private String productId;
	private Date createDate;
	private Date updateDate;
	private String creator;
	private String modifier;
	private String content;
	private Integer isDelete;
	private Integer isEditor;
	private String productName;
	private String imgUrl;
	private String source;
	private String price;//���ռۣ�չʾ
	private boolean onSale;	//չʾ
	private String comments;//չʾ
	private String brandName;//չʾ
	private String firstCatItemId;//չʾ
	private String secCatItemId;//չʾ
	private String thridCatItemId;//չʾ
	private String firstCatName;//չʾ
	private String secCatName;//չʾ
	private String thridCatName;//չʾ
	private String gomePrice;//������
	private String promPrice;//��/����	
	private String wapPrice;//����ר���
	private List<String> tagList;//չʾ
	private List<String> imgUrlList;//չʾ	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean isOnSale() {
		return onSale;
	}
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getFirstCatItemId() {
		return firstCatItemId;
	}
	public void setFirstCatItemId(String firstCatItemId) {
		this.firstCatItemId = firstCatItemId;
	}
	public String getSecCatItemId() {
		return secCatItemId;
	}
	public void setSecCatItemId(String secCatItemId) {
		this.secCatItemId = secCatItemId;
	}
	public String getThridCatItemId() {
		return thridCatItemId;
	}
	public void setThridCatItemId(String thridCatItemId) {
		this.thridCatItemId = thridCatItemId;
	}
	public String getFirstCatName() {
		return firstCatName;
	}
	public void setFirstCatName(String firstCatName) {
		this.firstCatName = firstCatName;
	}
	public String getSecCatName() {
		return secCatName;
	}
	public void setSecCatName(String secCatName) {
		this.secCatName = secCatName;
	}
	public String getThridCatName() {
		return thridCatName;
	}
	public void setThridCatName(String thridCatName) {
		this.thridCatName = thridCatName;
	}
	public String getGomePrice() {
		return gomePrice;
	}
	public void setGomePrice(String gomePrice) {
		this.gomePrice = gomePrice;
	}
	public String getPromPrice() {
		return promPrice;
	}
	public void setPromPrice(String promPrice) {
		this.promPrice = promPrice;
	}
	public String getWapPrice() {
		return wapPrice;
	}
	public void setWapPrice(String wapPrice) {
		this.wapPrice = wapPrice;
	}
	public List<String> getTagList() {
		return tagList;
	}
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}
	public List<String> getImgUrlList() {
		return imgUrlList;
	}
	public void setImgUrlList(List<String> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}
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
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsEditor() {
		return isEditor;
	}
	public void setIsEditor(Integer isEditor) {
		this.isEditor = isEditor;
	}
		
}
