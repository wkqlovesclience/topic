package com.gome.promotioncard.dao.impl;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

import org.springframework.stereotype.Repository;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.*;
import com.coo8.topic.model.*;
import com.gome.promotioncard.dao.inter.IPromotionCardDAO;
import com.gome.promotioncard.model.ErrorCard;
import com.gome.promotioncard.model.PromotionCard;
import com.ibatis.sqlmap.client.SqlMapExecutor;


@Repository("promotionCardDAO")
public class PromotionCardDAOImpl extends SqlMapClientDaoSupport  implements IPromotionCardDAO{
	
	 
	@Override
	public PromotionCard getById(java.lang.Integer id){
		return (PromotionCard) getSqlMapClientTemplate().queryForObject(
				"PromotionCard.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("Titles.delete", id);
	}
	
	@Override
	public String save(PromotionCard entity){
		Object obj = getSqlMapClientTemplate().insert(
				"PromotionCard.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(PromotionCard entity){
		return getSqlMapClientTemplate().update("PromotionCard.update",
				entity);
	}
	@Override
	public PaginatedList<PromotionCard> findByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("PromotionCard.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<PromotionCard> paginatedArrayList = new PaginatedArrayList<PromotionCard>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<PromotionCard> list = this.getSqlMapClientTemplate().queryForList("PromotionCard.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	@Override
	public List<PromotionCard> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"PromotionCard.findPage", search);
	 
	}
		

	@Override
	public List<PromotionCard> findAllTitlesList() {
		return this.getSqlMapClientTemplate().queryForList(
				"PromotionCard.findAllTitlesList");
	}

	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"ErrorCard.logcount", paramMap);
		if (o == null) return null;

		PaginatedList<ImportLog> paginatedArrayList = new PaginatedArrayList<ImportLog>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<ImportLog> list = this.getSqlMapClientTemplate().queryForList(
				"ErrorCard.listlog", paramMap,
				paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<ErrorCard> listDownLog(Map<String, Object> paramMap) {
		String logid = (String)paramMap.get("logId");
		if(logid != null)
		{
			this.getSqlMapClientTemplate().update("ErrorCard.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList(
				"ErrorCard.listDownLog", paramMap);
	}

	@Override
	public void addError(final List<ErrorCard> errorCards,final String logId) {
		if (errorCards != null)
		{
			getSqlMapClientTemplate().execute(new SqlMapClientCallback()
			{
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException
				{
					for (ErrorCard errorCard : errorCards)
					{
						errorCard.setLogId(logId);
						executor.insert("ErrorCard.addError", errorCard);
					}
					return executor.executeBatch();
				}
			});
		}
		
	}

	@Override
	public String addLog(ImportLog importLog)
	{
		Object obj = getSqlMapClientTemplate().insert(
				"ErrorCard.addLog", importLog);
		if (obj != null) {
			return   obj.toString();
		}
		return null;
	}

	@Override
	public PromotionCard getByskuId(String skuId) {	
			return  (PromotionCard) getSqlMapClientTemplate().queryForObject("PromotionCard.getByskuId", skuId);	
	}

	@Override
	public PaginatedList<PromotionCard> findLikeByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("PromotionCard.findPageLike.count", search);
		if(o==null)
			return null;
		PaginatedList<PromotionCard> paginatedArrayList = new PaginatedArrayList<PromotionCard>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<PromotionCard> list = this.getSqlMapClientTemplate().queryForList("PromotionCard.findPageLike", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
		
}
