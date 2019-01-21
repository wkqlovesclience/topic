package com.coo8.topic.model.gomeStores;

import java.io.Serializable;

public class StoresInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键
	private String id;
	
	// 店名
	private String storeName;
	
	// 地址
	private String storeAddress;
	
	// 坐标id
	private String storeCoordinate;
	
	// 电话
	private String storePhone;

	// 传真
	private String storeFax;

	// 营业时间
	private String storeBusinesstime;

	// 网址
	private String storeWebsite;

	// 停车信息
	private String storePort;

	// 公交路线
	private String storeBusroutes;

	// 其他信息
	private String storeOthers;

	// 店面图片
	private String storeImageurl;

	// 店面简介
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
