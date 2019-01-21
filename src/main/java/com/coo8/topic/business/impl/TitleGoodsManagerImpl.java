package com.coo8.topic.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.topic.business.interfaces.ITitleGoodsManager;
import com.coo8.topic.model.TitleGoods;
import com.coo8.topic.persistence.interfaces.ITitleGoodsDAO;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


 
public class TitleGoodsManagerImpl   implements ITitleGoodsManager{

	private ITitleGoodsDAO titleGoodsDAO;
	public void setTitleGoodsDAO(ITitleGoodsDAO dao) {
		this.titleGoodsDAO = dao;
	} 

	 
	@Transactional(readOnly=true)
	public TitleGoods getById(java.lang.Integer id){
		return titleGoodsDAO.getById( id);
	}
	@Transactional
	public void deleteById(java.lang.Integer id){
		titleGoodsDAO.deleteById(id);
	}
	 
	@Transactional
	public void save(TitleGoods entity){
		titleGoodsDAO.save(entity);
	}
	 
	@Transactional
	public void update(TitleGoods entity){
		titleGoodsDAO.update(entity);
	}
 
	@Transactional(readOnly=true)
   public List<TitleGoods> findAll(Map<String, Object> search){
		return titleGoodsDAO.findByMap(search);
	}

}
