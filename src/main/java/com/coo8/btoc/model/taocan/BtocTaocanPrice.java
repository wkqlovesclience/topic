package com.coo8.btoc.model.taocan;

import java.sql.Timestamp;
import java.util.List;

/**
 * 套餐价格表实体
 * 
 * @author 方玉
 * @since 2011-3-16
 * 
 */
public class BtocTaocanPrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tcPriceId;//ID
	private Integer itemid;//主商品ID
	private String tcPriceName;//套餐显示名
	private Double tcPriceOldPrice;//套餐原价格
	private Double tcPriceNewPrice;//套餐现价格
	private Integer tcPricePriority;//排序字段
	private Integer tcPriceStatus;//状态
	private Timestamp tcPriceInputdate;//录入时间
	private String tcPriceInputer;//录入者
	private Timestamp tcPriceUpdatedtime;//更新时间
	private String tcPrceUpdater;//更新者
	private Double total;//商品总价
	private Double youhuijia;//商品优惠价
	
	
	
	private String productname;
	private String mainimg6;
	private List<BtocTaocanDetail> listtc;
	

	public Double getYouhuijia() {
		return youhuijia;
	}

	public String getMainimg6() {
		return mainimg6;
	}

	public void setMainimg6(String mainimg6) {
		this.mainimg6 = mainimg6;
	}

	public void setYouhuijia(Double youhuijia) {
		this.youhuijia = youhuijia;
	}

	public List<BtocTaocanDetail> getListtc() {
		return listtc;
	}

	public void setListtc(List<BtocTaocanDetail> listtc) {
		this.listtc = listtc;
	}

	/** 默认构造器 */
	public BtocTaocanPrice() {
	}
	
	public BtocTaocanPrice(Integer tcPriceId) {
		this.tcPriceId = tcPriceId;
	}

	/** 全参构造器 */
	public BtocTaocanPrice(Integer tcPriceId, Integer itemid,
			String tcPriceName, Double tcPriceOldPrice, Double tcPriceNewPrice,
			Integer tcPricePriority, Integer tcPriceStatus,
			Timestamp tcPriceInputdate, String tcPriceInputer,
			Timestamp tcPriceUpdatedtime, String tcPrceUpdater) {
		this.tcPriceId = tcPriceId;
		this.itemid = itemid;
		this.tcPriceName = tcPriceName;
		this.tcPriceOldPrice = tcPriceOldPrice;
		this.tcPriceNewPrice = tcPriceNewPrice;
		this.tcPricePriority = tcPricePriority;
		this.tcPriceStatus = tcPriceStatus;
		this.tcPriceInputdate = tcPriceInputdate;
		this.tcPriceInputer = tcPriceInputer;
		this.tcPriceUpdatedtime = tcPriceUpdatedtime;
		this.tcPrceUpdater = tcPrceUpdater;
	}

	public Integer getTcPriceId() {
		return this.tcPriceId;
	}

	public void setTcPriceId(Integer tcPriceId) {
		this.tcPriceId = tcPriceId;
	}

	public Integer getItemid() {
		return this.itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getTcPriceName() {
		return this.tcPriceName;
	}

	public void setTcPriceName(String tcPriceName) {
		this.tcPriceName = tcPriceName;
	}

	public Double getTcPriceOldPrice() {
		return this.tcPriceOldPrice;
	}

	public void setTcPriceOldPrice(Double tcPriceOldPrice) {
		this.tcPriceOldPrice = tcPriceOldPrice;
	}

	public Double getTcPriceNewPrice() {
		return this.tcPriceNewPrice;
	}

	public void setTcPriceNewPrice(Double tcPriceNewPrice) {
		this.tcPriceNewPrice = tcPriceNewPrice == null ? 0.00 : tcPriceNewPrice;
	}

	public Integer getTcPriceStatus() {
		return this.tcPriceStatus;
	}

	public Integer getTcPricePriority() {
		return tcPricePriority;
	}

	public void setTcPricePriority(Integer tcPricePriority) {
		this.tcPricePriority = tcPricePriority;
	}

	public void setTcPriceStatus(Integer tcPriceStatus) {
		this.tcPriceStatus = tcPriceStatus;
	}

	public Timestamp getTcPriceInputdate() {
		return this.tcPriceInputdate;
	}

	public void setTcPriceInputdate(Timestamp tcPriceInputdate) {
		this.tcPriceInputdate = tcPriceInputdate;
	}

	public String getTcPriceInputer() {
		return this.tcPriceInputer;
	}

	public void setTcPriceInputer(String tcPriceInputer) {
		this.tcPriceInputer = tcPriceInputer;
	}

	public Timestamp getTcPriceUpdatedtime() {
		return this.tcPriceUpdatedtime;
	}

	public void setTcPriceUpdatedtime(Timestamp tcPriceUpdatedtime) {
		this.tcPriceUpdatedtime = tcPriceUpdatedtime;
	}

	public String getTcPrceUpdater() {
		return this.tcPrceUpdater;
	}

	public void setTcPrceUpdater(String tcPrceUpdater) {
		this.tcPrceUpdater = tcPrceUpdater;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}


	@Override
	public String toString() {
		return "BtocTaocanPrice [tcPriceId=" + tcPriceId + ", itemid=" + itemid
				+ ", tcPriceName=" + tcPriceName + ", tcPriceOldPrice="
				+ tcPriceOldPrice + ", tcPriceNewPrice=" + tcPriceNewPrice
				+ ", tcPricePriority=" + tcPricePriority + ", tcPriceStatus="
				+ tcPriceStatus + ", tcPriceInputdate=" + tcPriceInputdate
				+ ", tcPriceInputer=" + tcPriceInputer
				+ ", tcPriceUpdatedtime=" + tcPriceUpdatedtime
				+ ", tcPrceUpdater=" + tcPrceUpdater + ", total=" + total
				+ ", youhuijia=" + youhuijia + ", productname=" + productname
				+ ", mainimg6=" + mainimg6 + ", listtc=" + listtc + "]";
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}