package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.topic.business.interfaces.IGomeStoresManager;
import com.coo8.topic.model.gomeStores.StoresCoordinate;
import com.coo8.topic.model.gomeStores.StoresInfo;
import com.coo8.topic.persistence.interfaces.IGomeStoresDAO;
import com.gome.dragon.mds.client.area.GomeAreaSearch;
import com.gome.dragon.mds.client.dto.area.GomeAreaSearchDto;
import com.gome.dragon.mds.client.dto.area.GomeDistrictDto;

public class GomeStoresManagerImpl implements IGomeStoresManager {

	private IGomeStoresDAO gomeStoresDao;
	
    private GomeAreaSearch gomeAreaSearch;
	
	public IGomeStoresDAO getGomeStoresDao() {
		return gomeStoresDao;
	}

	public void setGomeStoresDao(IGomeStoresDAO gomeStoresDao) {
		this.gomeStoresDao = gomeStoresDao;
	}

	/**
	 * @return the gomeAreaSearch
	 */
	public GomeAreaSearch getGomeAreaSearch() {
		return gomeAreaSearch;
	}

	/**
	 * @param gomeAreaSearch the gomeAreaSearch to set
	 */
	public void setGomeAreaSearch(GomeAreaSearch gomeAreaSearch) {
		this.gomeAreaSearch = gomeAreaSearch;
	}

	@Override
	public StoresInfo getStoresInfoById(Integer id) {
		return gomeStoresDao.getStoresInfoById(id);
	}

	@Override
	public List<Integer> getStoresInfoIds(Map<String, Object> map) {
		return gomeStoresDao.getStoresInfoIds(map);
	}

	@Override
	public StoresCoordinate getStoresCoordinateById(Integer id) {
		StoresCoordinate storesCoor = gomeStoresDao.getStoresCoordinateById(id);
		
		String cityId = storesCoor.getCityId();
		String cityName = "";
		GomeDistrictDto dto = gomeAreaSearch.queryDistrictInfoByDivisionCode(cityId);
		if(dto != null  && null != dto.getDivisionName()){
			cityName = dto.getDivisionName();
		}
		storesCoor.setCityName(cityName);
		
		/*
		 * ÔÝÇÒ×¢ÊÍµô
		String provinceId = storesCoor.getProvinceId();
		String provinceName = gomeStoresDao.getDivisionName(provinceId);
		storesCoor.setProvinceName(provinceName);
		*/
		
		return storesCoor;
	}

	@Override
	public String getDivisionName(String divisionCode) {
		GomeDistrictDto dto = gomeAreaSearch.queryDistrictInfoByDivisionCode(divisionCode);
		String devisionName = "";
		if(dto != null && null != dto.getDivisionName()){
			devisionName = dto.getDivisionName();
		}
		return devisionName;
	}

	@Override
	public boolean isStoresExist(Integer id) {
		Integer result = gomeStoresDao.isStoresExist(id);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public String getPinyinCityName(Integer id) {
		StoresCoordinate storesCoor = gomeStoresDao.getStoresCoordinateById(id);
		if(storesCoor == null || "".equals(storesCoor.getCityId())){
			return "";
		}
		String cityId = storesCoor.getCityId();
		//String cityName = gomeStoresDao.getDivisionName(cityId);
		GomeDistrictDto dto = gomeAreaSearch.queryDistrictInfoByDivisionCode(cityId);
		if(dto == null || "".equals(dto.toString())){
			return "";
		}
		
		String cityName = dto.getDivisionName();
		
		if(cityName == null || "".equals(cityName.trim())){
			return "";
		}
		
		String dealCityName = cityName.replace("ÊÐ", "");
		return Chinese2PinyinUtil.parseChinese(dealCityName);
	}

	@Override
	public List<StoresCoordinate> getStoresCoordinateList(Map<String, Object> map) {
		return gomeStoresDao.getStoresCoordinateList(map);
	}

	@Override
	public List<StoresInfo> getStoresInfoList() {
		return gomeStoresDao.getStoresInfoList();
	}

	@Override
	public List<Integer> getStoresCoorIds(Map<String, Object> map) {
		return gomeStoresDao.getStoresCoorIds(map);
	}

}
