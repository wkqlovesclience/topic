package com.coo8.topic.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.topic.model.gomeStores.StoresCoordinate;
import com.coo8.topic.model.gomeStores.StoresInfo;
import com.coo8.topic.persistence.interfaces.IGomeStoresDAO;

public class GomeStoresDAOImpl extends SqlMapClientDaoSupport implements IGomeStoresDAO {

	private String getNameSpace(){
		return "gomeStores";
	}
	
	@Override
	public StoresInfo getStoresInfoById(Integer id) {
		return (StoresInfo) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getStoresInfoById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getStoresInfoIds(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(getNameSpace()+".getStoresInfoIds", map);
	}

	@Override
	public StoresCoordinate getStoresCoordinateById(Integer id) {
		return (StoresCoordinate) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getStoresCoordinateById", id);
	}

	@Override
	public String getDivisionName(String divisionCode) {
		return (String) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getDivisionName", divisionCode);
	}

	@Override
	public Integer isStoresExist(Integer id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNameSpace()+".isStoresExist", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoresCoordinate> getStoresCoordinateList(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(getNameSpace()+".getStoresCoordinateList", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoresInfo> getStoresInfoList() {
		return getSqlMapClientTemplate().queryForList(getNameSpace()+".getStoresInfoList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getStoresCoorIds(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(getNameSpace()+".getStoresCoorIds", map);
	}

}
