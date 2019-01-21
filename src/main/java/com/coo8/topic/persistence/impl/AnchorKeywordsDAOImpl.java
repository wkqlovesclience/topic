package com.coo8.topic.persistence.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.ErrorAnchorKeyword;
import com.coo8.topic.model.ImportLog;
import com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;


/**
 * @author  zhanghan
 * @version 1.0
 * @since 1.0
 */
@Repository("anchorKeywordsDAO")
public class AnchorKeywordsDAOImpl  extends SqlMapClientDaoSupport  implements IAnchorKeywordsDAO {

	@Override
	public List<AnchorKeywords> findAnchorKeywords() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("AnchorKeywords.findAll");
	}

	@Override
	public String save(AnchorKeywords anchorKeywords) {
		// TODO Auto-generated method stub
		Object obj = getSqlMapClientTemplate().insert(
				"AnchorKeywords.insert", anchorKeywords);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
	}

	@Override
	public int update(AnchorKeywords anchorKeywords) {
		return getSqlMapClientTemplate().update("AnchorKeywords.update",
				anchorKeywords);
	}

	@Override
	public int delete(Integer id) {
		return getSqlMapClientTemplate().delete("AnchorKeywords.delete", id);
	}
	
	
	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().delete("AnchorKeywords.deleteAll");
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#getById(java.lang.Integer)
	 */
	@Override
	public AnchorKeywords getById(Integer id) {
		// TODO Auto-generated method stub
		return (AnchorKeywords)getSqlMapClientTemplate().queryForObject(
				"AnchorKeywords.getById", id);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#findByMap(java.util.Map)
	 */
	@Override
	public PaginatedList<AnchorKeywords> findByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("AnchorKeywords.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<AnchorKeywords> paginatedArrayList = new PaginatedArrayList<AnchorKeywords>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<AnchorKeywords> list = this.getSqlMapClientTemplate().queryForList("AnchorKeywords.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#findListLikeByMap(java.util.Map)
	 */
	@Override
	public List<AnchorKeywords> findListLikeByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
				"AnchorKeywords.findPageLike", search);
	}

	
	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"AnchorKeywords.logcount", paramMap);
		if (o == null) return null;

		PaginatedList<ImportLog> paginatedArrayList = new PaginatedArrayList<ImportLog>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<ImportLog> list = this.getSqlMapClientTemplate().queryForList(
				"AnchorKeywords.listlog", paramMap,
				paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#addErrorWords(java.util.List)
	 */
	@Override
	public void addErrorWords(final List<ErrorAnchorKeyword> errorAnchorKeyWords) {
		// TODO Auto-generated method stub

		if (errorAnchorKeyWords != null)
		{
			getSqlMapClientTemplate().execute(new SqlMapClientCallback()
			{
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException
				{
					for (ErrorAnchorKeyword errorWord : errorAnchorKeyWords)
					{
						executor.insert("AnchorKeywords.addError", errorWord);
					}
					return executor.executeBatch();
				}
			});
		}
		
	}
	
	

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#listDownLog(java.util.Map)
	 */
	@Override
	public List<ErrorAnchorKeyword> listDownLog(Map<String, Object> paramMap) {
		String logid = (String)paramMap.get("logid");
		if(logid != null)
		{
			this.getSqlMapClientTemplate().update("AnchorKeywords.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList(
				"AnchorKeywords.listDownLog", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#addLog(com.coo8.topic.model.ImportLog)
	 */
	@Override
	public void addLog(ImportLog importLog)
	{
		if (importLog != null)
		{
			getSqlMapClientTemplate().insert("AnchorKeywords.addLog", importLog);
		}
	}

	/* (non-Javadoc)
	 * @see com.coo8.topic.persistence.interfaces.IAnchorKeywordsDAO#getByNamePcUrl(com.coo8.topic.model.AnchorKeywords)
	 */
	@Override
	public AnchorKeywords getByNamePcUrl(AnchorKeywords entity) {
		return (AnchorKeywords)getSqlMapClientTemplate().queryForObject(
				"AnchorKeywords.getByWhere", entity);
	}
	
}
