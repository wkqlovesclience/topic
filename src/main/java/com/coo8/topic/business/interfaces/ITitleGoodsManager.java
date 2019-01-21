package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.topic.model.TitleGoods;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface ITitleGoodsManager{
	 

	public TitleGoods getById(java.lang.Integer id);
	
	   
	public void save(TitleGoods entity);
	
	public void deleteById(java.lang.Integer id);
	
	public void update(TitleGoods entity);
	   
	
	public List<TitleGoods> findAll(Map<String, Object> search);

 
}
