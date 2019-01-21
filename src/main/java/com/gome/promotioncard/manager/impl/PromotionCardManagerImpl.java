package com.gome.promotioncard.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ImportLog;
import com.gome.bg.interfaces.system.service.SeoPromotionCardDubboService;
import com.gome.frontSe.interfaces.IProdDetailService;
import com.gome.promotioncard.dao.inter.IPromotionCardDAO;
import com.gome.promotioncard.manager.inter.IPromotionCardManager;
import com.gome.promotioncard.model.ErrorCard;
import com.gome.promotioncard.model.PromotionCard;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public class PromotionCardManagerImpl implements IPromotionCardManager {

	private  static Logger logger = LoggerFactory.getLogger(PromotionCardManagerImpl.class);
	private IPromotionCardDAO promotionCardDAO;
	protected IProdDetailService prodDetailService;
	protected SeoPromotionCardDubboService seoPromotionCardDubboService;
	
	public SeoPromotionCardDubboService getSeoPromotionCardDubboService() {
		return seoPromotionCardDubboService;
	}

	public void setSeoPromotionCardDubboService(SeoPromotionCardDubboService seoPromotionCardDubboService) {
		this.seoPromotionCardDubboService = seoPromotionCardDubboService;
	}

	public IProdDetailService getProdDetailService() {
		return prodDetailService;
	}

	public void setProdDetailService(IProdDetailService prodDetailService) {
		this.prodDetailService = prodDetailService;
	}
	public void setPromotionCardDAO(IPromotionCardDAO promotionCardDAO) {
		this.promotionCardDAO = promotionCardDAO;
	}
	
	public IPromotionCardDAO getPromotionCardDAO() {
		return promotionCardDAO;
	}

	@Transactional(readOnly = true)
	public com.gome.bg.interfaces.system.bean.PromotionCard getDataById(String productId,String skuId) {
		 com.gome.bg.interfaces.system.bean.PromotionCard baiDuCard = null; 
		try{
			baiDuCard = seoPromotionCardDubboService.getPromotionCardSimple(productId, skuId);
	     }catch(Exception e){
	    	 if(null != baiDuCard){
	    	 logger.error("调用接口参数ProductId: "+baiDuCard.getProductId()+"  skuId: "+baiDuCard.getSkuId() +"调用接口异常信息： "+e.getMessage());
	    	 }else{
	    		 logger.error("调用接口参数ProductId: "+baiDuCard);
	    	 }
	     }			
		 return  baiDuCard;
	} 
	
	@Transactional(readOnly = true)
	public com.gome.bg.interfaces.system.bean.PromotionCard getAllDataById(String productId,String skuId) {
		 com.gome.bg.interfaces.system.bean.PromotionCard baiDuCard = null; 
			try{
				baiDuCard = seoPromotionCardDubboService.getPromotionCardDetail(productId, skuId);
		     }catch(Exception e){
		    	 if(null != baiDuCard){
			    	 logger.error("调用接口参数ProductId: "+baiDuCard.getProductId()+"  skuId: "+baiDuCard.getSkuId() +"调用接口异常信息： "+e.getMessage());
			    	 }else{
			    		 logger.error("调用接口参数ProductId: "+baiDuCard);
			    	 }
		    }
		return  baiDuCard;
	} 

	@Transactional
	public String save(PromotionCard entity) {
		if (entity.getId() != null && !"".equals(String.valueOf(entity.getId()))) {
			promotionCardDAO.update(entity);
			return entity.getId().toString();
		} else {
			return promotionCardDAO.save(entity);
		}
	}

	@Transactional
	public void update(PromotionCard entity) {
		promotionCardDAO.update(entity);
	}

	@Transactional(readOnly = true)
	public PaginatedList<PromotionCard> findByMap(Map<String, Object> search) {
		return promotionCardDAO.findByMap(search);
	}

	@Transactional(readOnly = true)
	public List<PromotionCard> findListByMap(Map<String, Object> search) {
		return promotionCardDAO.findListByMap(search);
	}


	@Override
	public List<PromotionCard> findAllTitlesList() {
		return promotionCardDAO.findAllTitlesList();
	}
	
	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap) {
		return promotionCardDAO.listLog(paramMap);
	}

	@Override
	public List<ErrorCard> listDownLog(Map<String, Object> paramMap) {
		return promotionCardDAO.listDownLog(paramMap);
	}

	@Override
	public void addError(List<ErrorCard> errorTitles,String logId) {		
		promotionCardDAO.addError(errorTitles,logId);
	}

	@Override
	public String addLog(ImportLog importLog) {
		return promotionCardDAO.addLog(importLog);
	}

	@Override
	public PromotionCard getByskuId(String skuId) {		
		return promotionCardDAO.getByskuId(skuId);
	}

	@Override
	public boolean checkSkuId(String productId, String skuId) {
		if(skuId.isEmpty()){
			return false;
		}
		String[] properties = new String[] { "brandItemId", "thridCatItemId", "skuItemIds" };
		Map<String, String> categoryInfoMap = prodDetailService.getProductMultiProperties(productId,
				properties);
		if (categoryInfoMap != null) {
			String skuIds = String.valueOf(categoryInfoMap.get("skuItemIds"));			
			return skuIds.contains(skuId);			
		}
		return false;
	}

	@Override
	public PaginatedList<PromotionCard> findLikeByMap(Map<String, Object> search) {
	    PaginatedList<PromotionCard> PromotionCardList = promotionCardDAO.findLikeByMap(search);
	    com.gome.bg.interfaces.system.bean.PromotionCard  baiducard = null;
	    for(PromotionCard card : PromotionCardList){
			try{
			      baiducard = seoPromotionCardDubboService.getPromotionCardSimple(card.getProductId(), card.getSkuId());
		     }catch(Exception e){
		    	 if(null != baiducard){
		    	   logger.error("调用接口参数ProductId: "+baiducard.getProductId()+"  skuId: "+baiducard.getSkuId() +"调用接口异常信息： "+e.getMessage());
		    	 }else{
		    		 logger.error("调用接口参数ProductId: "+baiducard);
		    	 }
		     }		
			
			if(baiducard != null){
				card.setProductName(baiducard.getProductName());
				card.setImgUrl(baiducard.getImgUrl());
				card.setOnSale(baiducard.isOnSale());
			}
		}		
		return PromotionCardList;
	}

	@Override
	public PromotionCard getById(Integer id) {
		return  promotionCardDAO.getById(id);
	}
	
}
