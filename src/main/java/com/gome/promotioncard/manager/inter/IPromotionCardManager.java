package com.gome.promotioncard.manager.inter; 

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ImportLog;
import com.gome.promotioncard.model.ErrorCard;
import com.gome.promotioncard.model.PromotionCard;


public interface IPromotionCardManager{
	 
    public com.gome.bg.interfaces.system.bean.PromotionCard getDataById(String productId,String skuId);
  
    public com.gome.bg.interfaces.system.bean.PromotionCard getAllDataById(String productId,String skuId);
   
    public PromotionCard getById(java.lang.Integer id);

    public PromotionCard  getByskuId(String skuId);

	public String save(PromotionCard entity);
	 
	public void update(PromotionCard entity);
	
	public PaginatedList<PromotionCard> findByMap(Map<String, Object> search);

	public List<PromotionCard> findListByMap(Map<String, Object> search);
	
	public List<PromotionCard> findAllTitlesList();
	
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);
	
	public List<ErrorCard> listDownLog(Map<String, Object> paramMap);
	
	public void addError(List<ErrorCard> ErrorCard,String logId);
	
	public String addLog(ImportLog importLog);
	//≈–∂œskuId «∑Ò”––ß°£
	public boolean checkSkuId(String productId , String skuId);
	
	public PaginatedList<PromotionCard> findLikeByMap(Map<String, Object> search);

}
