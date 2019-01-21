package com.coo8.btoc.model.shoplist;

import java.sql.Timestamp;
import java.util.List;

import com.coo8.btoc.model.catalog.CatalogProduct;

/**
 * @author ����� ��Ʒ�����Ϣ.
 */

public class StockInfo {
	
	/* ����ģʽ--���� */
	private static final String MARKET_MODEL_NEGATIVE = "13D";
	
	/* ����ģʽ--ֱ�� */
	private static final String MARKET_MODEL_DIRECT = "13B";
	

	/* ��Ʒitemid */
	private String productId;
	
	/* ����ID*/
	private int cityId;
	
	/* �ֹ�˾���� */
	private String branchName;
	
	/* ���״̬: �л� ��; Ԥ��  */
	private int stockStatus;
	
	/* չʾ�Ŀ��״̬ */
	private String displayStatus;
	
	/* ������� */
	private int number;
	
	/* Ӫ��ģʽ */
	private String marketModel;
	
	/* ������ʱ�� */
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
				this.displayStatus = "����";
				return;
			} else if (marketModel.equals(MARKET_MODEL_DIRECT)) {
				this.displayStatus = "ֱ��";
				return;
			}
		}
		
		switch(stockStatus) {
		case 4:
			this.displayStatus = "�ֻ�";
			break;
		case 2:
			this.displayStatus = "��;";
			break;
		case 1:
			this.displayStatus = "Ԥ��";
			break;
		case 0:
			this.displayStatus = "�޻�";
			break;
		default:
			this.displayStatus = "δ֪";
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