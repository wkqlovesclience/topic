package com.coo8.btoc.model.present;

import java.sql.Timestamp;
import java.util.List;

import com.coo8.btoc.model.shoplist.StockInfo;

/**
 * ��Ʒʵ���� BtocPresent entity. @author Fangyu
 */

public class BtocPresent implements java.io.Serializable {

	public static final int status_able = 1;
	public static final int status_unable = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer presentId;// ��Ʒ����
	private Integer productId;// ����ƷID
	private String presentName;// ��Ʒ����
	private String presentDesc;// ��Ʒ����
	private Integer presentPriority;//�����ֶ�
	private Integer presentNum;//��Ʒ����
	private Integer presentStatus;// ״̬
	private Timestamp presentInputdate;// ¼��ʱ��
	private String presentInputer;// ¼����
	private Timestamp presentUpdatedtime;// ����ʱ��
	private String presentUpdater;// ������
	private Integer uid;//��ƷID
	
	private String mainimg6;//ͼƬ
	private Double presentprice;
	private List<StockInfo> liststock;

	public List<StockInfo> getListstock() {
		return liststock;
	}

	public void setListstock(List<StockInfo> liststock) {
		this.liststock = liststock;
	}

	public Double getPresentprice() {
		return presentprice;
	}

	public void setPresentprice(Double presentprice) {
		this.presentprice = presentprice;
	}

	/** Ĭ�Ϲ����� */
	public BtocPresent() {
		//Ĭ�Ϲ�����
	}

	public BtocPresent(Integer presentId, Integer productId,
			String presentName, String presentDesc, Integer presentStatus,
			Integer presentPriority,Integer presentNum,
			Timestamp presentInputdate, String presentInputer,
			Timestamp presentUpdatedtime, String presentUpdater) {
		super();
		this.presentId = presentId;
		this.productId = productId;
		this.presentName = presentName;
		this.presentPriority=presentPriority;
		this.presentNum=presentNum;
		this.presentDesc = presentDesc;
		this.presentStatus = presentStatus;
		this.presentInputdate = presentInputdate;
		this.presentInputer = presentInputer;
		this.presentUpdatedtime = presentUpdatedtime;
		this.presentUpdater = presentUpdater;
	}

	// ������ƷID

	public Integer getPresentId() {
		return this.presentId;
	}

	// @param presentId ��ƷID
	public void setPresentId(Integer presentId) {
		this.presentId = presentId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getPresentName() {
		return this.presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

	public String getMainimg6() {
		return mainimg6;
	}

	public void setMainimg6(String mainimg6) {
		this.mainimg6 = mainimg6;
	}

	public String getPresentDesc() {
		return this.presentDesc;
	}

	public void setPresentDesc(String presentDesc) {
		this.presentDesc = presentDesc;
	}

	public Integer getPresentStatus() {
		return this.presentStatus;
	}

	public void setPresentStatus(Integer presentStatus) {
		this.presentStatus = presentStatus;
	}

	public Timestamp getPresentInputdate() {
		return this.presentInputdate;
	}

	public void setPresentInputdate(Timestamp presentInputdate) {
		this.presentInputdate = presentInputdate;
	}

	public String getPresentInputer() {
		return this.presentInputer;
	}

	public void setPresentInputer(String presentInputer) {
		this.presentInputer = presentInputer;
	}

	public Timestamp getPresentUpdatedtime() {
		return this.presentUpdatedtime;
	}

	public void setPresentUpdatedtime(Timestamp presentUpdatedtime) {
		this.presentUpdatedtime = presentUpdatedtime;
	}

	public String getPresentUpdater() {
		return this.presentUpdater;
	}

	public void setPresentUpdater(String presentUpdater) {
		this.presentUpdater = presentUpdater;
	}

	public Integer getPresentPriority() {
		return presentPriority;
	}

	public void setPresentPriority(Integer presentPriority) {
		this.presentPriority = presentPriority;
	}

	public Integer getPresentNum() {
		return presentNum;
	}

	public void setPresentNum(Integer presentNum) {
		this.presentNum = presentNum;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "BtocPresent [presentId=" + presentId + ", productId="
				+ productId + ", presentName=" + presentName + ", presentDesc="
				+ presentDesc + ", presentPriority=" + presentPriority
				+ ", presentNum=" + presentNum + ", presentStatus="
				+ presentStatus + ", presentInputdate=" + presentInputdate
				+ ", presentInputer=" + presentInputer
				+ ", presentUpdatedtime=" + presentUpdatedtime
				+ ", presentUpdater=" + presentUpdater + ", uid=" + uid
				+ ", mainimg6=" + mainimg6 + ", presentprice=" + presentprice
				+ "]";
	}
	

}