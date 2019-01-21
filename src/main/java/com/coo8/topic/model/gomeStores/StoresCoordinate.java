package com.coo8.topic.model.gomeStores;

import java.io.Serializable;

public class StoresCoordinate implements Serializable{

	private static final long serialVersionUID = 1L;
	// ����
	private int id;
	
	// ����id
	private String cityId;
	
	// ����Name
	private String cityName;
		
	// ʡid
	private String provinceId;
	
	// ʡName
	private String provinceName;
		
	// ����
	private String title;
	
	// ����
	private String content;
	
	// ʡ��λ����(��������������)
	private String pLocationY;
	
	// ʡ��λ����(�������������)
	private String pLocationX;
	
	// ��λ����(��������������)
	private String locationY;
	
	// ��λ����(�������������)
	private String locationX;
	
	// ��������(������)
	private String storeX;
	
	// ��������(������)
	private String storeY;
	
	// ��������(1����,2����)
	private String storeType;

	// �е�ͼ�㼶
	private String mapLevel;

	// ʡ��ͼ�㼶
	private String pMapLevel ;
	
	// �ŵ�����(������html)
	private String name ;
	
	// �ŵ��ַ(������html)
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
