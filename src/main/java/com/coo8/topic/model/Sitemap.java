package com.coo8.topic.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Sitemap implements Serializable {
	
	//alias
	public static final String TABLE_ALIAS = "sitemap";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CATEGORY = "category";
	public static final String ALIAS_SKU = "sku";
	public static final String ALIAS_BIGIMG = "bigimg";
	public static final String ALIAS_MERCHANT = "merchant";
	public static final String ALIAS_TOPIC = "topic";
	public static final String ALIAS_SALES = "sales";
	public static final String ALIAS_CATEGORYBRAND = "categoryBrand"; 
	public static final String ALIAS_COMMENTS_CONSULT = "commentsConsult";
	
	public static final String ALIAS_SITE = "site";
	public static final String ALIAS_UPDATE_DATE = "update_date";
	
	public static final String GOME_SITEMAP_CATEOGRY = "category";
	public static final String GOME_SITEMAP_PRODUCT  = "product";
	public static final String GOME_SITEMAP_BIGIMAGE = "bigimage";
	public static final String GOME_SITEMAP_TOPIC    = "topic";
	public static final String GOME_SITEMAP_SALES    = "sales";
	public static final String GOME_SITEMAP_CATEGORYBRAND    = "categoryBrand";
	public static final String GOME_SITEMAP_COMMENTS_CONSULT = "commentsConsult";
	
	public static final String COO8_SITEMAP_CATEOGRY = "products";
	public static final String COO8_SITEMAP_PRODUCT  = "product";
	public static final String COO8_SITEMAP_BIGIMAGE = "bigimage";
	public static final String COO8_SITEMAP_TOPIC    = "catalog";
	public static final String COO8_SITEMAP_MALL     = "mall";
	
	private Integer id;
	private Integer category;
	private Integer sku;
	private Integer bigimg;
	private Integer merchant;
	private Integer topic;
	private Integer sales;
	private Integer categoryBrand;
	private Integer commentsConsult;
	
	private String site;
	private Date update_date;
	
	private String categoryStatus;
	private String skuStatus;
	private String bigimgStatus;
	private String merchantStatus;
	private String topicStatus;
	private String salesStatus;
	private String categoryBrandStatus;
	private String commentsConsultStatus;
	
	private String operator1;
	private String operator2;
	private String operator3;
	private String operator4;
	private String operator5;
	private String operator6;
	private String operator7;
	private String operator8;
	
	public Integer getCommentsConsult() {
		return commentsConsult;
	}

	public void setCommentsConsult(Integer commentsConsult) {
		this.commentsConsult = commentsConsult;
	}

	public String getCommentsConsultStatus() {
		return commentsConsultStatus;
	}

	public void setCommentsConsultStatus(String commentsConsultStatus) {
		this.commentsConsultStatus = commentsConsultStatus;
	}

	public String getOperator8() {
		return operator8;
	}

	public void setOperator8(String operator8) {
		this.operator8 = operator8;
	}

	public Integer getCategoryBrand() {
		return categoryBrand;
	}

	public void setCategoryBrand(Integer categoryBrand) {
		this.categoryBrand = categoryBrand;
	}

	public String getCategoryBrandStatus() {
		return categoryBrandStatus;
	}

	public void setCategoryBrandStatus(String categoryBrandStatus) {
		this.categoryBrandStatus = categoryBrandStatus;
	}

	public String getOperator7() {
		return operator7;
	}

	public void setOperator7(String operator7) {
		this.operator7 = operator7;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(String salesStatus) {
		this.salesStatus = salesStatus;
	}

	public String getOperator6() {
		return operator6;
	}

	public void setOperator6(String operator6) {
		this.operator6 = operator6;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Category",getCategory())
			.append("Topic",getTopic())
			.append("Sku",getSku())
			.append("Bigimg",getBigimg())
			.append("Merchant",getMerchant())
			.append("Site",getSite())
			.append("sales",getSales())
			.append("categoryBrand",getCategoryBrand())
			.append("commentsConsult",getCommentsConsult())
			.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public Integer getBigimg() {
		return bigimg;
	}

	public void setBigimg(Integer bigimg) {
		this.bigimg = bigimg;
	}

	public Integer getMerchant() {
		return merchant;
	}

	public void setMerchant(Integer merchant) {
		this.merchant = merchant;
	}

	public Integer getTopic() {
		return topic;
	}

	public void setTopic(Integer topic) {
		this.topic = topic;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
	}

	public String getBigimgStatus() {
		return bigimgStatus;
	}

	public void setBigimgStatus(String bigimgStatus) {
		this.bigimgStatus = bigimgStatus;
	}

	public String getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(String merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public String getTopicStatus() {
		return topicStatus;
	}

	public void setTopicStatus(String topicStatus) {
		this.topicStatus = topicStatus;
	}

	public String getOperator1() {
		return operator1;
	}

	public void setOperator1(String operator1) {
		this.operator1 = operator1;
	}

	public String getOperator2() {
		return operator2;
	}

	public void setOperator2(String operator2) {
		this.operator2 = operator2;
	}

	public String getOperator3() {
		return operator3;
	}

	public void setOperator3(String operator3) {
		this.operator3 = operator3;
	}

	public String getOperator4() {
		return operator4;
	}

	public void setOperator4(String operator4) {
		this.operator4 = operator4;
	}

	public String getOperator5() {
		return operator5;
	}

	public void setOperator5(String operator5) {
		this.operator5 = operator5;
	}

}
