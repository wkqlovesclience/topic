/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.impl;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.persistence.interfaces.ITitlesDAO;

import org.springframework.stereotype.Repository;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.*;

import org.apache.commons.lang.StringUtils;
 








import com.coo8.topic.model.*;
import com.coo8.topic.persistence.interfaces.*;
import com.coo8.topic.business.interfaces.*;  
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */




@Repository("titlesDAO")
public class TitlesDAOImpl extends SqlMapClientDaoSupport  implements ITitlesDAO{
	
	 
	@Override
	public Titles getById(java.lang.Integer id){
		return (Titles) getSqlMapClientTemplate().queryForObject(
				"Titles.getById", id);
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("Titles.delete", id);
	}
	
	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.ITitlesDAO#deleteBlockQueue()
	 */
	@Override
	public int deleteBlockQueue() {
		return getSqlMapClientTemplate().delete("Titles.deleteBlock");
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.ITitlesDAO#deleteProductQueue()
	 */
	@Override
	public int deleteProductQueue() {
		return getSqlMapClientTemplate().delete("Titles.deleteProduct");
	}

	@Override
	public String save(Titles entity){
		Object obj = getSqlMapClientTemplate().insert(
				"Titles.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(Titles entity){
		return getSqlMapClientTemplate().update("Titles.update",
				entity);
	}
	@Override
	public PaginatedList<Titles> findByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Titles.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<Titles> paginatedArrayList = new PaginatedArrayList<Titles>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<Titles> list = this.getSqlMapClientTemplate().queryForList("Titles.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	@Override
	public PaginatedList<Titles> findLikeByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Titles.findPageLike.count", search);
		if(o==null)
			return null;
		PaginatedList<Titles> paginatedArrayList = new PaginatedArrayList<Titles>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<Titles> list = this.getSqlMapClientTemplate().queryForList("Titles.findPageLike", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	@Override
	public List<Titles> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Titles.findPage", search);
	 
	}
	@Override
	public List<Titles> findListLikeByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Titles.findPageLike", search);
	 
	}
	@Override
	public String saveDrops(List<GoodsDrops> drops) {
		 try {
			 int count = 0;
			 for(GoodsDrops obj:drops){
				 if(count++ % 100 == 0){
					 this.getSqlMapClientTemplate().getSqlMapClient().startTransaction();
					 this.getSqlMapClientTemplate().getSqlMapClient().startBatch();
				 }
				 this.getSqlMapClientTemplate().getSqlMapClient().insert("Titles.saveDrops",obj);
				 if(count % 100 == 0){
					 this.getSqlMapClientTemplate().getSqlMapClient().flushDataCache();
					 this.getSqlMapClientTemplate().getSqlMapClient().executeBatch();
					 this.getSqlMapClientTemplate().getSqlMapClient().commitTransaction();
					 this.getSqlMapClientTemplate().getSqlMapClient().endTransaction();
				 }
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}finally {
			 try {
				 this.getSqlMapClientTemplate().getSqlMapClient().flushDataCache();
				 this.getSqlMapClientTemplate().getSqlMapClient().executeBatch();
				 this.getSqlMapClientTemplate().getSqlMapClient().commitTransaction();
				 this.getSqlMapClientTemplate().getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		 return "";
		 
	}

	@Override
	public PaginatedList<Titles> findDropsListByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Titles.dropsList.count", search);
		if(o==null)
			return null;
		PaginatedList<Titles> paginatedArrayList = new PaginatedArrayList<Titles>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<Titles> list = this.getSqlMapClientTemplate().queryForList("Titles.findDropsList", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}

	@Override
	public int deleteAllDrops() {
		 return	getSqlMapClientTemplate().delete("Titles.deleteAllDrops");
	}

	@Override
	public List<Titles> findAllTitlesList() {
		return this.getSqlMapClientTemplate().queryForList(
				"Titles.findAllTitlesList");
	}

	@Override
	public int deleteDropsByObj(GoodsDrops drops) {
		return	getSqlMapClientTemplate().delete("Titles.deleteDropsByObj",drops);
	}

	//add by linchengjun 2013.4.7
	@Override
	public String insertTitleIndex(TitleIndex entity) {
		Object obj = getSqlMapClientTemplate().insert("Titles.index.insert", entity);
		if(obj != null){
			return obj.toString();
		}
		return null;
	}

	@Override
	public PaginatedList<TitleIndex> findTitleIndexByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Titles.index.count", search);
		if(o==null)
			return null;
		PaginatedList<TitleIndex> paginatedArrayList = new PaginatedArrayList<TitleIndex>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<TitleIndex> list = this.getSqlMapClientTemplate().queryForList("Titles.index.list", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	@Override
	public TitleIndex getTitleIndexById(int id) {
		return (TitleIndex) getSqlMapClientTemplate().queryForObject("Titles.index.getById", id);
	}

	@Override
	public TitleIndex getTitleIndexByTitleId(int titleId){
		return (TitleIndex) getSqlMapClientTemplate().queryForObject("Titles.index.getByTitleId", titleId);
	}

	@Override
	public int deleteTitleIndex(int id) {
		return getSqlMapClientTemplate().delete("Titles.index.delete", id);
	}

	@Override
	public int updateTitleIndex(TitleIndex entity) {
		return getSqlMapClientTemplate().update("Titles.index.update", entity);
	}

	@Override
	public int isAddRepeat(Map<String, Object> search) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Titles.index.isAddRepeat", search);
	}

	@Override
	public int publishGomeTitle(int id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("titleId", id);
		return this.getSqlMapClientTemplate().update("TitleMap.publishGomeTitle",map);
	}

	@Override
	public int publishCoo8Title(int id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("titleId", id);
		return this.getSqlMapClientTemplate().update("TitleMap.publishCoo8Title",map);
	}

	@Override
	public int publishGomeTitleTest(int id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("titleId", id);
		return this.getSqlMapClientTemplate().update("TitleMap.publishGomeTitleTest",map);
	}

	@Override
	public int publishCoo8TitleTest(int id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("titleId", id);
		return this.getSqlMapClientTemplate().update("TitleMap.publishCoo8TitleTest",map);
	}
	
	/**
	 * 专题系统首页发布
	 */
	@Override
	public int publicTitleHomePage(String site) {
		if(null == site || "".equals(site)){
			return 1;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("site", site);
		return this.getSqlMapClientTemplate().update("TitleMap.publicTitleHomePage",map);
	}
	
	/**
	 * 所有专题列表分页发布
	 */
	@Override
	public int publishAllTitleListPage(String site) {
		if(null == site || "".equals(site)){
			return 1;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("site", site);
		return this.getSqlMapClientTemplate().update("TitleMap.publishAllTitleListPage",map);
	}
	/**
	 * 所有文章列表分页发布
	 */
	@Override
	public int publishAllNewsListPage(String site) {
		if(null == site || "".equals(site)){
			return 1;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("site", site);
		return this.getSqlMapClientTemplate().update("TitleMap.publishAllNewsListPage",map);
	}

	@Override
	public int getMaxId(String site) {
		 Object obj = getSqlMapClientTemplate().queryForObject(
				"Titles.getMaxId", site);
		 if(obj==null){
			 return 0;
		 }else{
//		    return (Integer) getSqlMapClientTemplate().queryForObject(
//				"Titles.getMaxId", site);
			 return (Integer) obj;
		 }
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.ITitlesDAO#changePath()
	 */
	@Override
	public int changePath() {
		return this.getSqlMapClientTemplate().update("Titles.changePath");
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.ITitlesDAO#changeTwo()
	 */
	@Override
	public int changeTwo() {
		return this.getSqlMapClientTemplate().update("Titles.changeTwo");
	}

	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"ErrorTitles.logcount", paramMap);
		if (o == null) return null;

		PaginatedList<ImportLog> paginatedArrayList = new PaginatedArrayList<ImportLog>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<ImportLog> list = this.getSqlMapClientTemplate().queryForList(
				"ErrorTitles.listlog", paramMap,
				paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<ErrorTitles> listDownLog(Map<String, Object> paramMap) {
		String logid = (String)paramMap.get("logid");
		if(logid != null)
		{
			this.getSqlMapClientTemplate().update("ErrorTitles.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList(
				"ErrorTitles.listDownLog", paramMap);
	}

	@Override
	public void addErrorWords(final List<ErrorTitles> errorTitles) {
		if (errorTitles != null)
		{
			getSqlMapClientTemplate().execute(new SqlMapClientCallback()
			{
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException
				{
					for (ErrorTitles errorWord : errorTitles)
					{
						executor.insert("ErrorTitles.addError", errorWord);
					}
					return executor.executeBatch();
				}
			});
		}
		
	}

	@Override
	public void addLog(ImportLog importLog)
	{
		if (importLog != null)
		{
			getSqlMapClientTemplate().insert("ErrorTitles.addLog", importLog);
		}
	}

    @Override
    public List<Titles> getByTitleName(String titleName) {
       List<Titles> titles = getSqlMapClientTemplate().queryForList("Titles.getByTitleName", titleName);
       return titles;
    }

	/**
	 * Wap专题系统首页发布
	 */
	@Override
	public int publicWapTitleHomePage() {
		return this.getSqlMapClientTemplate().update("TitleMap.publicWapTitleHomePage");
	}
	
	@Override
	public int publishWapGomeTitle(int id){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("titleId", id);
		return this.getSqlMapClientTemplate().update("TitleMap.publishWapGomeTitle",map);
	}

	@Override
	public int findListCountByMap(Map<String, Object> search) {
		Object count = this.getSqlMapClientTemplate().queryForObject("Titles.findPage.count", search);
		if (null != count && !"".equals(count)) {
			return Integer.parseInt(count.toString());
		}
		return 0;
	}

	@Override
	public int getTitleInvalidInDateCount(String createDate){
		return (Integer) getSqlMapClientTemplate().queryForObject("TitlesInvalid.count", createDate);
	}

	@Override
	public List<TitleInvalid> getTitleInvalidInDate(String createDate){
		List<TitleInvalid> titleInvalids = getSqlMapClientTemplate().queryForList("TitlesInvalid.dataInDate", createDate);
		return titleInvalids;
	}

	@Override
	public List<String> getTitleInvalidDate(){
		List<String> createTimes = getSqlMapClientTemplate().queryForList("TitlesInvalid.date");
		return createTimes;
	}
	
	
}
