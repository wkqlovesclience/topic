package com.coo8.topic.model.gomeStores;

import java.io.Serializable;

public class StoresInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// ����
	private String id;
	
	// ����
	private String storeName;
	
	// ��ַ
	private String storeAddress;
	
	// ����id
	private String storeCoordinate;
	
	// �绰
	private String storePhone;

	// ����
	private String storeFax;

	// Ӫҵʱ��
	private String storeBusinesstime;

	// ��ַ
	private String storeWebsite;

	// ͣ����Ϣ
	private String storePort;

	// ����·��
	private String storeBusroutes;

	// ������Ϣ
	private String storeOthers;

	// ����ͼƬ
	private String storeImageurl;

	// ������
	private String storeSynopsis;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreCoordinate() {
		return storeCoordinate;
	}

	public void setStoreCoordinate(String storeCoordinate) {
		this.storeCoordinate = storeCoordinate;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getStoreFax() {
		return storeFax;
	}

	public void setStoreFax(String storeFax) {
		this.storeFax = storeFax;
	}

	public String getStoreBusinesstime() {
		return storeBusinesstime;
	}

	public void setStoreBusinesstime(String storeBusinesstime) {
		this.storeBusinesstime = storeBusinesstime;
	}

	public String getStoreWebsite() {
		return storeWebsite;
	}

	public void setStoreWebsite(String storeWebsite) {
		this.storeWebsite = storeWebsite;
	}

	public String getStorePort() {
		return storePort;
	}

	public void setStorePort(String storePort) {
		this.storePort = storePort;
	}

	public String getStoreBusroutes() {
		return storeBusroutes;
	}

	public void setStoreBusroutes(String storeBusroutes) {
		this.storeBusroutes = storeBusroutes;
	}

	public String getStoreOthers() {
		return storeOthers;
	}

	public void setStoreOthers(String storeOthers) {
		this.storeOthers = storeOthers;
	}

	public String getStoreImageurl() {
		return storeImageurl;
	}

	public void setStoreImageurl(String storeImageurl) {
		this.storeImageurl = storeImageurl;
	}

	public String getStoreSynopsis() {
		return storeSynopsis;
	}

	public void setStoreSynopsis(String storeSynopsis) {
		this.storeSynopsis = storeSynopsis;
	}

}
