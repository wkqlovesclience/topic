package com.gome.baidublackfriday.manager.infer;


import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayErrorCard;
import com.gome.stage.item.PriceInfo;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCardImportLog;


public interface BaiDuBlackFridayManager{
	 
    public com.gome.bg.interfaces.system.bean.PromotionCard getDataById(String productId,String skuId);
  
    public com.gome.bg.interfaces.system.bean.PromotionCard getAllDataById(String productId,String skuId);
   
    public BaiDuBlackFridayCard getById(java.lang.Integer id);

    public BaiDuBlackFridayCard  getByskuId(String skuId);

	public String save(BaiDuBlackFridayCard entity);
	 
	public void update(BaiDuBlackFridayCard entity);
	
	public PaginatedList<BaiDuBlackFridayCard> findByMap(Map<String, Object> search);

	public List<BaiDuBlackFridayCard> findListByMap(Map<String, Object> search);
	
	public List<BaiDuBlackFridayCard> findAllTitlesList();
	
	public PaginatedList<BaiDuBlackFridayCardImportLog> listLog(Map<String, Object> paramMap);
	
	public List<BaiDuBlackFridayErrorCard> listDownLog(Map<String, Object> paramMap);
	
	public void addError(List<BaiDuBlackFridayErrorCard> baiDuBlackFridayErrorCards,Integer logID);
	
	public Integer addLog(BaiDuBlackFridayCardImportLog importLog);
	//≈–∂œskuId «∑Ò”––ß°£
	public boolean checkSkuId(String productId , String skuId);
	
	public PaginatedList<BaiDuBlackFridayCard> findLikeByMap(Map<String, Object> search);

	public PriceInfo getPriceInfo(String skuId, String areaCode);

}
