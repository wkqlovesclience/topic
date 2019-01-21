package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.topic.model.gomeStores.StoresCoordinate;
import com.coo8.topic.model.gomeStores.StoresInfo;

public interface IGomeStoresManager {
	public StoresInfo getStoresInfoById(Integer id);
	public List<Integer> getStoresInfoIds(Map<String,Object> map);
	public StoresCoordinate getStoresCoordinateById(Integer id);
	public List<Integer> getStoresCoorIds(Map<String,Object> map);
	public String getDivisionName(String divisionCode);
	public boolean isStoresExist(Integer id);
	
	public String getPinyinCityName(Integer id);
	
	public List<StoresCoordinate> getStoresCoordinateList(Map<String,Object> map);
	public List<StoresInfo> getStoresInfoList();
}
