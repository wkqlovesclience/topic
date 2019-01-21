package com.gome.baidublackfriday.dao.infer;



import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayErrorCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCardImportLog;  


public interface BaiDuBlackFridayCardDAO{
	
	
	public BaiDuBlackFridayCard getByskuId(String skuId);

	public BaiDuBlackFridayCard getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
		
	public String save(BaiDuBlackFridayCard entity);
	 
	public int update(BaiDuBlackFridayCard entity);
	
	public PaginatedList<BaiDuBlackFridayCard> findByMap(Map<String, Object> search);

	public List<BaiDuBlackFridayCard> findListByMap(Map<String, Object> search);
	
	public List<BaiDuBlackFridayCard> findAllTitlesList();
	
	public PaginatedList<BaiDuBlackFridayCardImportLog> listLog(Map<String, Object> paramMap);
	
	public List<BaiDuBlackFridayErrorCard> listDownLog(Map<String, Object> paramMap);
	
	public void addError(List<BaiDuBlackFridayErrorCard> errorCard,Integer logID);
	
	public Integer addLog(BaiDuBlackFridayCardImportLog importLog);
	
	public PaginatedList<BaiDuBlackFridayCard> findLikeByMap(Map<String, Object> search);
}
