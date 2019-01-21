
package com.gome.promotioncard.dao.inter;

import java.util.*;

import org.apache.commons.lang.StringUtils;
 





import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.*;
import com.coo8.topic.persistence.interfaces.*;
import com.gome.promotioncard.model.ErrorCard;
import com.gome.promotioncard.model.PromotionCard;
import com.coo8.topic.business.interfaces.*;  


public interface IPromotionCardDAO{
	
	
	public PromotionCard getByskuId(String skuId);

	public PromotionCard getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
		
	public String save(PromotionCard entity);
	 
	public int update(PromotionCard entity);
	
	public PaginatedList<PromotionCard> findByMap(Map<String, Object> search);

	public List<PromotionCard> findListByMap(Map<String, Object> search);
	
	public List<PromotionCard> findAllTitlesList();
	
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);
	
	public List<ErrorCard> listDownLog(Map<String, Object> paramMap);
	
	public void addError(List<ErrorCard> ErrorCard,String logId);
	
	public String addLog(ImportLog importLog);
	
	public PaginatedList<PromotionCard> findLikeByMap(Map<String, Object> search);
}
