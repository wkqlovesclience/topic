package com.coo8.btoc.model.shoplist;

import java.sql.Timestamp;
import java.util.List;

import com.coo8.btoc.model.catalog.CatalogProduct;

/**
 * @author 丁淑红 商品库存信息.
 */

public class StockInfo {
	
	/* 销售模式--负卖 */
	private static final String MARKET_MODEL_NEGATIVE = "13D";
	
	/* 销售模式--直运 */
	private static final String MARKET_MODEL_DIRECT = "13B";
	

	/* 商品itemid */
	private String productId;
	
	/* 城市ID*/
	private int cityId;
	
	/* 分公司名字 */
	private String branchName;
	
	/* 库存状态: 有货 在途 预定  */
	private int stockStatus;
	
	/* 展示的库存状态 */
	private String displayStatus;
	
	/* 库存数量 */
	private int number;
	
	/* 营销模式 */
	private String marketModel;
	
	/* 库存更新时间 */
	private Timestamp updateTime;
	
	private Integer position;
	
	private Integer listCatalogId;
	
	//for search
	private List<CatalogProduct> catalogProductList;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}	

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {		
		this.stockStatus = stockStatus;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void initDisplayStatus() {
		if (marketModel != null) {
			if (marketModel.equals(MARKET_MODEL_NEGATIVE)) {
				this.displayStatus = "负卖";
				return;
			} else if (marketModel.equals(MARKET_MODEL_DIRECT)) {
				this.displayStatus = "直运";
				return;
			}
		}
		
		switch(stockStatus) {
		case 4:
			this.displayStatus = "现货";
			break;
		case 2:
			this.displayStatus = "在途";
			break;
		case 1:
			this.displayStatus = "预订";
			break;
		case 0:
			this.displayStatus = "无货";
			break;
		default:
			this.displayStatus = "未知";
			break;		
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}		

	public String getMarketModel() {
		return marketModel;
	}

	public void setMarketModel(String marketModel) {
		this.marketModel = marketModel;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getListCatalogId() {
		return listCatalogId;
	}

	public void setListCatalogId(Integer listCatalogId) {
		this.listCatalogId = listCatalogId;
	}

	public List<CatalogProduct> getCatalogProductList() {
		return catalogProductList;
	}

	public void setCatalogProductList(List<CatalogProduct> catalogProductList) {
		this.catalogProductList = catalogProductList;
	}	
	
}