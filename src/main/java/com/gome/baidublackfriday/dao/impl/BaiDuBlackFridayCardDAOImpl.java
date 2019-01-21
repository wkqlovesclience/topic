package com.gome.baidublackfriday.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.baidublackfriday.dao.infer.BaiDuBlackFridayCardDAO;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayErrorCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCardImportLog;
import com.ibatis.sqlmap.client.SqlMapExecutor;


@Repository("baiDuBlackFridayCardDAO")
public class BaiDuBlackFridayCardDAOImpl extends SqlMapClientDaoSupport  implements BaiDuBlackFridayCardDAO{
	
	 
	@Override
	public BaiDuBlackFridayCard getById(java.lang.Integer id){
		return (BaiDuBlackFridayCard) getSqlMapClientTemplate().queryForObject(
				"BaiDuBlackFridayCard.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("Titles.delete", id);
	}
	
	@Override
	public String save(BaiDuBlackFridayCard entity){
		Object obj = getSqlMapClientTemplate().insert(
				"BaiDuBlackFridayCard.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(BaiDuBlackFridayCard entity){
		return getSqlMapClientTemplate().update("BaiDuBlackFridayCard.update",
				entity);
	}
	@Override
	public PaginatedList<BaiDuBlackFridayCard> findByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("BaiDuBlackFridayCard.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<BaiDuBlackFridayCard> paginatedArrayList = new PaginatedArrayList<BaiDuBlackFridayCard>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<BaiDuBlackFridayCard> list = this.getSqlMapClientTemplate().queryForList("BaiDuBlackFridayCard.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	@Override
	public List<BaiDuBlackFridayCard> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"BaiDuBlackFridayCard.findPage", search);
	 
	}
		

	@Override
	public List<BaiDuBlackFridayCard> findAllTitlesList() {
		return this.getSqlMapClientTemplate().queryForList(
				"BaiDuBlackFridayCard.findAllTitlesList");
	}

	@Override
	public PaginatedList<BaiDuBlackFridayCardImportLog> listLog(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"BaiDuBlackFridayErrorCard.logcount", paramMap);
		if (o == null) return null;

		PaginatedList<BaiDuBlackFridayCardImportLog> paginatedArrayList = new PaginatedArrayList<BaiDuBlackFridayCardImportLog>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<BaiDuBlackFridayCardImportLog> list = this.getSqlMapClientTemplate().queryForList(
				"BaiDuBlackFridayErrorCard.listlog", paramMap,
				paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<BaiDuBlackFridayErrorCard> listDownLog(Map<String, Object> paramMap) {
		String logid = (String)paramMap.get("logId");
		if(logid != null)
		{
			this.getSqlMapClientTemplate().update("BaiDuBlackFridayErrorCard.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList(
				"BaiDuBlackFridayErrorCard.listDownLog", paramMap);
	}

	@Override
	public void addError(final List<BaiDuBlackFridayErrorCard> errorCards,final Integer logID) {
		if (errorCards != null && logID !=null)
		{
			getSqlMapClientTemplate().execute(new SqlMapClientCallback()
			{
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException
				{
					for (BaiDuBlackFridayErrorCard errorCard : errorCards)
					{
						errorCard.setLogId(String.valueOf(logID));
						executor.insert("BaiDuBlackFridayErrorCard.addError", errorCard);
					}
					return executor.executeBatch();
				}
			});
		}
		
	}

	@Override
	public Integer addLog(BaiDuBlackFridayCardImportLog importLog)
	{
		if (importLog != null)
		{
			 Object insert = getSqlMapClientTemplate().insert("BaiDuBlackFridayErrorCard.addLog", importLog);
			 return Integer.valueOf(insert.toString());
		}
		return null;
	}

	@Override
	public BaiDuBlackFridayCard getByskuId(String skuId) {	
			return  (BaiDuBlackFridayCard) getSqlMapClientTemplate().queryForObject("BaiDuBlackFridayCard.getByskuId", skuId);	
	}

	@Override
	public PaginatedList<BaiDuBlackFridayCard> findLikeByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("BaiDuBlackFridayCard.findPageLike.count", search);
		if(o==null)
			return null;
		PaginatedList<BaiDuBlackFridayCard> paginatedArrayList = new PaginatedArrayList<BaiDuBlackFridayCard>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<BaiDuBlackFridayCard> list = this.getSqlMapClientTemplate().queryForList("BaiDuBlackFridayCard.findPageLike", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
		
}
