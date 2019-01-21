package com.gome.baidublackfriday.manager.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.baidublackfriday.dao.infer.BaiDuBlackFridayCardDAO;
import com.gome.baidublackfriday.manager.infer.BaiDuBlackFridayManager;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayErrorCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCardImportLog;
import com.gome.bg.interfaces.system.service.SeoPromotionCardDubboService;
import com.gome.frontSe.interfaces.IProdDetailService;
import com.gome.promotioncard.dao.inter.IPromotionCardDAO;
import com.gome.stage.interfaces.item.IGomeProcessService;
import com.gome.stage.item.PriceInfo;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public class BaiDuBlackFridayManagerImpl implements BaiDuBlackFridayManager {

	private  static Logger logger = LoggerFactory.getLogger(BaiDuBlackFridayManagerImpl.class);
	private IPromotionCardDAO promotionCardDAO;
	private BaiDuBlackFridayCardDAO baiDuBlackFridayCardDAO;
	protected IProdDetailService prodDetailService;
	protected SeoPromotionCardDubboService seoPromotionCardDubboService;
	private IGomeProcessService gomeProcessService;
	
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

	public BaiDuBlackFridayCardDAO getBaiDuBlackFridayCardDAO() {
		return baiDuBlackFridayCardDAO;
	}

	public void setBaiDuBlackFridayCardDAO(BaiDuBlackFridayCardDAO baiDuBlackFridayCardDAO) {
		this.baiDuBlackFridayCardDAO = baiDuBlackFridayCardDAO;
	}

	public IGomeProcessService getGomeProcessService() {
		return gomeProcessService;
	}

	public void setGomeProcessService(IGomeProcessService gomeProcessService) {
		this.gomeProcessService = gomeProcessService;
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
	public String save(BaiDuBlackFridayCard entity) {
		if (entity.getId() != null) {
			logger.info("进行update操作");
			baiDuBlackFridayCardDAO.update(entity);
			return entity.getId().toString();
		} else {
			return baiDuBlackFridayCardDAO.save(entity);
		}
	}

	@Transactional
	public void update(BaiDuBlackFridayCard entity) {
		baiDuBlackFridayCardDAO.update(entity);
	}

	@Transactional(readOnly = true)
	public PaginatedList<BaiDuBlackFridayCard> findByMap(Map<String, Object> search) {
		return baiDuBlackFridayCardDAO.findByMap(search);
	}

	@Transactional(readOnly = true)
	public List<BaiDuBlackFridayCard> findListByMap(Map<String, Object> search) {
		return baiDuBlackFridayCardDAO.findListByMap(search);
	}


	@Override
	public List<BaiDuBlackFridayCard> findAllTitlesList() {
		return baiDuBlackFridayCardDAO.findAllTitlesList();
	}
	
	@Override
	public PaginatedList<BaiDuBlackFridayCardImportLog> listLog(Map<String, Object> paramMap) {
		return baiDuBlackFridayCardDAO.listLog(paramMap);
	}

	@Override
	public List<BaiDuBlackFridayErrorCard> listDownLog(Map<String, Object> paramMap) {
		return baiDuBlackFridayCardDAO.listDownLog(paramMap);
	}

	@Override
	public void addError(List<BaiDuBlackFridayErrorCard> baiDuBlackFridayErrorCards,Integer logID) {		
		baiDuBlackFridayCardDAO.addError(baiDuBlackFridayErrorCards,logID);
	}

	@Override
	public Integer addLog(BaiDuBlackFridayCardImportLog importLog) {
		return  baiDuBlackFridayCardDAO.addLog(importLog);
	}

	@Override
	public BaiDuBlackFridayCard getByskuId(String skuId) {		
		return baiDuBlackFridayCardDAO.getByskuId(skuId);
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
	public PaginatedList<BaiDuBlackFridayCard> findLikeByMap(Map<String, Object> search) {
	    PaginatedList<BaiDuBlackFridayCard> promotionCardList = baiDuBlackFridayCardDAO.findLikeByMap(search);
	    com.gome.bg.interfaces.system.bean.PromotionCard  baiducard = null;
	    for(BaiDuBlackFridayCard card : promotionCardList){
			try{
			      baiducard = seoPromotionCardDubboService.getPromotionCardSimple(card.getProductId(), card.getSkuId());
		     }catch(Exception e){
		    	 if(null != baiducard){
		    	   logger.error("调用接口参数ProductId: "+baiducard.getProductId()+"  skuId: "+baiducard.getSkuId() +"调用接口异常信息： "+e.getMessage());
		    	 }else{
		    		 logger.error("调用接口参数ProductId: "+baiducard);
		    	 }
		     }		
			//通过DUBBO接口调用查出商品实时具体信息 填充到商品中
			if(baiducard != null){
				card.setProductName(baiducard.getProductName());
				card.setGomePrice(baiducard.getGomePrice());
                card.setPrice(baiducard.getPrice());
				card.setImgUrl(baiducard.getImgUrl());
				card.setOnSale(baiducard.isOnSale());
			}
		}
		return promotionCardList;
	}
	public PriceInfo getPriceInfo(String skuId, String areaCode) {
		return  gomeProcessService.getAreaPrice(skuId, areaCode);
	}
	
	@Override
	public BaiDuBlackFridayCard getById(Integer id) {
		return  baiDuBlackFridayCardDAO.getById(id);
	}
	
}
