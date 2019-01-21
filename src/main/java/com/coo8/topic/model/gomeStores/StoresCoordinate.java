package com.coo8.topic.model.gomeStores;

import java.io.Serializable;

public class StoresCoordinate implements Serializable{

	private static final long serialVersionUID = 1L;
	// 主键
	private int id;
	
	// 城市id
	private String cityId;
	
	// 城市Name
	private String cityName;
		
	// 省id
	private String provinceId;
	
	// 省Name
	private String provinceName;
		
	// 标题
	private String title;
	
	// 内容
	private String content;
	
	// 省地位坐标(中心坐标纵坐标)
	private String pLocationY;
	
	// 省地位坐标(中心坐标横坐标)
	private String pLocationX;
	
	// 地位坐标(中心坐标纵坐标)
	private String locationY;
	
	// 地位坐标(中心坐标横坐标)
	private String locationX;
	
	// 店铺坐标(横坐标)
	private String storeX;
	
	// 店铺坐标(纵坐标)
	private String storeY;
	
	// 店铺类型(1店面,2自提)
	private String storeType;

	// 市地图层级
	private String mapLevel;

	// 省地图层级
	private String pMapLevel ;
	
	// 门店名称(不包含html)
	private String name ;
	
	// 门店地址(不包含html)
	private String address ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getpLocationY() {
		return pLocationY;
	}

	public void setpLocationY(String pLocationY) {
		this.pLocationY = pLocationY;
	}

	public String getpLocationX() {
		return pLocationX;
	}

	public void setpLocationX(String pLocationX) {
		this.pLocationX = pLocationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getStoreX() {
		return storeX;
	}

	public void setStoreX(String storeX) {
		this.storeX = storeX;
	}

	public String getStoreY() {
		return storeY;
	}

	public void setStoreY(String storeY) {
		this.storeY = storeY;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getMapLevel() {
		return mapLevel;
	}

	public void setMapLevel(String mapLevel) {
		this.mapLevel = mapLevel;
	}

	public String getpMapLevel() {
		return pMapLevel;
	}

	public void setpMapLevel(String pMapLevel) {
		this.pMapLevel = pMapLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
