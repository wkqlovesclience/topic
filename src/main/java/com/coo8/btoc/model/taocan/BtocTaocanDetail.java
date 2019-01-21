package com.coo8.btoc.model.taocan;

import java.sql.Timestamp;
import java.util.List;

import com.coo8.btoc.model.shoplist.StockInfo;



/**
 * �ײ���ϸ��Ʒ��ʵ��
 * @author ����
 * @since 2011-3-16
 * 
 */
public class BtocTaocanDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5550603959559196856L;
	private Integer tcDetailId;//ID
	private Integer tcDetailNumber;//�ײ�ID
	private Integer tcDetailFitemid;//��ƷID
	private Integer tcDetailPriority;//�����ֶ�
	private Integer tcDetailFcount;//����Ʒ����
	private Integer tcDetailStatus;//�ײ�����Ʒ�Ƿ�������Ʒ
	private Integer tcstatus;//�ײ�����Ʒ��״̬
	private Timestamp tcDetailInputdate;//¼��ʱ��
	private String tcDetailInputer;//¼����
	private Timestamp tcDetailUpdatedtime;//����ʱ��
	private String tcDetailUpdater;//������
	private Double tcDetailReduceprice;//��Ʒ���ټ۸�
	private Integer tcPriceId;
	
	private String productname;//��Ʒ����
	private Integer id;//��ƷId
	private double originalprice;//��Ʒ�۸�
	private String mainimg6;//ͼƬ
	private String mainimg5;
	private String city;//��
	private String itemid;
	private int limitcount;
	private List<StockInfo> liststock;
	


	public List<StockInfo> getListstock() {
		return liststock;
	}

	public void setListstock(List<StockInfo> liststock) {
		this.liststock = liststock;
	}

	public int getLimitcount() {
		return limitcount;
	}

	public void setLimitcount(int limitcount) {
		this.limitcount = limitcount;
	}

	/** Ĭ�Ϲ�����*/
	public BtocTaocanDetail() {
	}

	/** ȫ�ι�����*/
	


	public Integer getTcDetailId() {
		return this.tcDetailId;
	}

	public BtocTaocanDetail(Integer tcDetailId, Integer tcDetailNumber,
			Integer tcDetailFitemid, Integer tcDetailPriority,
			Integer tcDetailFcount, Integer tcDetailStatus,Integer tcstatus,
			Timestamp tcDetailInputdate, String tcDetailInputer,
			Timestamp tcDetailUpdatedtime, String tcDetailUpdater,
			Double tcDetailReduceprice, Integer tcPriceId, String productname,
			double originalprice,Integer id) {
		super();
		this.tcDetailId = tcDetailId;
		this.tcDetailNumber = tcDetailNumber;
		this.tcDetailFitemid = tcDetailFitemid;
		this.tcDetailPriority = tcDetailPriority;
		this.tcDetailFcount = tcDetailFcount;
		this.tcDetailStatus = tcDetailStatus;
		this.tcDetailInputdate = tcDetailInputdate;
		this.tcDetailInputer = tcDetailInputer;
		this.tcstatus=tcstatus;
		this.tcDetailUpdatedtime = tcDetailUpdatedtime;
		this.tcDetailUpdater = tcDetailUpdater;
		this.tcDetailReduceprice = tcDetailReduceprice;
		this.id=id;
		this.tcPriceId = tcPriceId;
		this.productname = productname;
		this.originalprice = originalprice;
	}

	public void setTcDetailId(Integer tcDetailId) {
		this.tcDetailId = tcDetailId;
	}

	public Integer getTcDetailNumber() {
		return tcDetailNumber;
	}

	public void setTcDetailNumber(Integer tcDetailNumber) {
		this.tcDetailNumber = tcDetailNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMainimg6() {
		return mainimg6;
	}

	public void setMainimg6(String mainimg6) {
		this.mainimg6 = mainimg6;
	}

	public Integer getTcDetailFitemid() {
		return tcDetailFitemid;
	}

	public void setTcDetailFitemid(Integer tcDetailFitemid) {
		this.tcDetailFitemid = tcDetailFitemid;
	}


	public Integer getTcDetailFcount() {
		return this.tcDetailFcount == null ? 0 : this.tcDetailFcount;
	}

	public void setTcDetailFcount(Integer tcDetailFcount) {
		this.tcDetailFcount = tcDetailFcount;
	}

	public Integer getTcDetailStatus() {
		return this.tcDetailStatus;
	}

	public void setTcDetailStatus(Integer tcDetailStatus) {
		this.tcDetailStatus = tcDetailStatus;
	}

	public Timestamp getTcDetailInputdate() {
		return this.tcDetailInputdate;
	}

	public void setTcDetailInputdate(Timestamp tcDetailInputdate) {
		this.tcDetailInputdate = tcDetailInputdate;
	}

	public String getTcDetailInputer() {
		return this.tcDetailInputer;
	}

	public void setTcDetailInputer(String tcDetailInputer) {
		this.tcDetailInputer = tcDetailInputer;
	}

	public Timestamp getTcDetailUpdatedtime() {
		return this.tcDetailUpdatedtime;
	}

	public void setTcDetailUpdatedtime(Timestamp tcDetailUpdatedtime) {
		this.tcDetailUpdatedtime = tcDetailUpdatedtime;
	}

	public String getTcDetailUpdater() {
		return this.tcDetailUpdater;
	}

	public void setTcDetailUpdater(String tcDetailUpdater) {
		this.tcDetailUpdater = tcDetailUpdater;
	}

	public Integer getTcPriceId() {
		return tcPriceId;
	}

	public void setTcPriceId(Integer tcPriceId) {
		this.tcPriceId = tcPriceId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public double getOriginalprice() {
		return originalprice;
	}

	public void setOriginalprice(double originalprice) {
		this.originalprice = originalprice;
	}
	public Integer getTcDetailPriority() {
		return tcDetailPriority;
	}

	public void setTcDetailPriority(Integer tcDetailPriority) {
		this.tcDetailPriority = tcDetailPriority;
	}


	@Override
	public String toString() {
		return "BtocTaocanDetail [tcDetailId=" + tcDetailId
				+ ", tcDetailNumber=" + tcDetailNumber + ", tcDetailFitemid="
				+ tcDetailFitemid + ", tcDetailPriority=" + tcDetailPriority
				+ ", tcDetailFcount=" + tcDetailFcount + ", tcDetailStatus="
				+ tcDetailStatus + ", tcstatus=" + tcstatus
				+ ", tcDetailInputdate=" + tcDetailInputdate
				+ ", tcDetailInputer=" + tcDetailInputer
				+ ", tcDetailUpdatedtime=" + tcDetailUpdatedtime
				+ ", tcDetailUpdater=" + tcDetailUpdater
				+ ", tcDetailReduceprice=" + tcDetailReduceprice
				+ ", tcPriceId=" + tcPriceId + ", productname=" + productname
				+ ", id=" + id + ", originalprice=" + originalprice
				+ ", mainimg6=" + mainimg6 + ", mainimg5=" + mainimg5
				+ ", city=" + city + ", itemid=" + itemid + ", limitcount="
				+ limitcount + "]";
	}

	public Double getTcDetailReduceprice() {
		return tcDetailReduceprice;
	}

	public void setTcDetailReduceprice(Double tcDetailReduceprice) {
		this.tcDetailReduceprice = tcDetailReduceprice;
	}

	public String getMainimg5() {
		return mainimg5;
	}

	public void setMainimg5(String mainimg5) {
		this.mainimg5 = mainimg5;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Integer getTcstatus() {
		return tcstatus;
	}

	public void setTcstatus(Integer tcstatus) {
		this.tcstatus = tcstatus;
	}

}