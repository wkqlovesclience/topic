package com.gome.productwords.dao.impl;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.productwords.dao.infer.ProductWordsCardDAO;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.productwords.model.ProductWordsCardImportLog;
import com.gome.productwords.model.ProductWordsErrorCard;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wangkeqiang-ds
 */
@Repository("productWordsCardDAO")
public class ProductWordsCardDAOImpl extends SqlMapClientDaoSupport  implements ProductWordsCardDAO {
	
	 //通过主键id查询
	@Override
	public ProductWordsCard getById(Integer id){
		return (ProductWordsCard) getSqlMapClientTemplate().queryForObject("ProductWordsCard.getById", id);
	}

	@Override
	public ProductWordsCard getByIdForDubbo(Integer id) {
		return (ProductWordsCard) getSqlMapClientTemplate().queryForObject("ProductWordsCard.InterfaceUseGetById", id);
	}

	//通过主键id删除
	@Override
	public int deleteById(Integer id){
	   return	getSqlMapClientTemplate().delete("ProductWordsCard.delete", id);
	}


	//插入商品词
	@Override
	public String save(ProductWordsCard entity){
		Object obj = getSqlMapClientTemplate().insert(
				"ProductWordsCard.insert", entity);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
		 
	} 


	@Override
	public int update(ProductWordsCard entity){
		return getSqlMapClientTemplate().update("ProductWordsCard.update", entity);
	}

	/**
	 *
	 * @param search
	 * @return
	 * 分页是通过PaginatedList实现，对这段代码迷惑的可查看PaginatedArrayList的代码，可直接CV使用，但要对应正确的参数
	 */
	@Override
	public PaginatedList<ProductWordsCard> findByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("ProductWordsCard.findPageLike.count", search);
		if(o==null)
			return null;
		PaginatedList<ProductWordsCard> paginatedArrayList = new PaginatedArrayList<ProductWordsCard>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<ProductWordsCard> list = this.getSqlMapClientTemplate().queryForList("ProductWordsCard.findPageLike", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}


	/**
	 *
	 * @param search
	 * @return
	 * 分页是通过PaginatedList实现，对这段代码迷惑的可查看PaginatedArrayList的代码，可直接CV使用，但要对应正确的参数
	 */

	@Override
	public List<ProductWordsCard> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"ProductWordsCard.findPageLike", search);
	 
	}

	/**
	 *
	 * @param paramMap
	 * @return
	 * 操作日志表
	 */
	@Override
	public PaginatedList<ProductWordsCardImportLog> listLog(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"ProductWordsErrorCard.logcount", paramMap);
		if (o == null) return null;

		PaginatedList<ProductWordsCardImportLog> paginatedArrayList = new PaginatedArrayList<ProductWordsCardImportLog>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<ProductWordsCardImportLog> list = this.getSqlMapClientTemplate().queryForList(
				"ProductWordsErrorCard.listlog", paramMap,
				paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	/**
	 *
	 * @param paramMap
	 * @return
	 * 上传商品词时，会先根据商品词词名进行数据库的查重，若存在会存至“商品词错误表”，并带有对应的日志id，导出时根据日志id查询错误商品词，并返回
	 */
	@Override
	public List<ProductWordsErrorCard> listDownLog(Map<String, Object> paramMap) {
		String logid = (String)paramMap.get("logId");
		if(logid != null)
		{
			this.getSqlMapClientTemplate().update("ProductWordsErrorCard.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList(
				"ProductWordsErrorCard.listDownLog", paramMap);
	}

	/**
	 *
	 * @param errorCards
	 * @param logID
	 * 添加错误商品词及信息
	 */
	@Override
	public void addError(final List<ProductWordsErrorCard> errorCards, final Integer logID) {
		if (errorCards != null && logID !=null)
		{
			getSqlMapClientTemplate().execute(new SqlMapClientCallback()
			{
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException
				{
					for (ProductWordsErrorCard errorCard : errorCards)
					{
						errorCard.setLogId(String.valueOf(logID));
						executor.insert("ProductWordsErrorCard.addError", errorCard);
					}
					return executor.executeBatch();
				}
			});
		}
		
	}

	/**
	 *
	 * @param importLog
	 * @return
	 * 添加日志
	 */
	@Override
	public Integer addLog(ProductWordsCardImportLog importLog)
	{
		if (importLog != null)
		{
			 Object insert = getSqlMapClientTemplate().insert("ProductWordsErrorCard.addLog", importLog);
			 return Integer.valueOf(insert.toString());
		}
		return null;
	}

	/**
	 *
	 * @param productWordsName
	 * @return
	 * 根据productWordsName商品词词名查重
	 */
	@Override
	public ProductWordsCard getByProductWordsName(String productWordsName) {
			return  (ProductWordsCard) getSqlMapClientTemplate().queryForObject("ProductWordsCard.getByProductWordsName", productWordsName);
	}

	/**
	 *
	 * @param search
	 * @return PaginatedList
	 * 条件查询
	 */
	@Override
	public PaginatedList<ProductWordsCard> findLikeByMap(Map<String, Object> search) {

		Object o = this.getSqlMapClientTemplate().queryForObject("ProductWordsCard.findPageLike.count", search);
		if (o == null)
			return null;

		PaginatedList<ProductWordsCard> paginatedArrayList = new PaginatedArrayList<ProductWordsCard>(
				Integer.parseInt(o.toString()), (Integer) search.get("pageNumber"),
				(Integer) search.get("pageSize"));

		List<ProductWordsCard> list = this.getSqlMapClientTemplate().queryForList("ProductWordsCard.findPageLike", search,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());

		if (list != null){
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList; 
	}


	/**
	 *
	 * @param paramMap
	 * @return
	 * 根据指定的初始id到末尾id 批量导出  相同功能模块的代码可直接CV
	 */
	@Override
	public List<ProductWordsCard> getByRangeId(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().queryForList("ProductWordsCard.range", paramMap);
	}

	/**
	 *
	 * @param
	 * @return
	 * 业务要求：前台要求展示同一商品词词根下的16条数据，
	 * 若不够16条随机展示不够16条的部分
	 */
	@Override
	public List<ProductWordsCard> getByRandomSize(Integer randomSize) {
		return getSqlMapClientTemplate().queryForList("ProductWordsCard.randomSize", randomSize);
	}

	@Override
	public List<ProductWordsCard> getByProductWordsBase(String productWordsBase) {
		return getSqlMapClientTemplate().queryForList("ProductWordsCard.getByProductWordsBase", productWordsBase);
	}

	@Override
	public Integer getSumByBeginLetter(Map<String, Object> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProductWordsCard.findPageLike.letterCount", paramMap);
	}

	/**
	 * 展示num条最新的商品词
	 * @return
	 */
	@Override
	public List<ProductWordsCard> getFreshProductWords(Integer num) {
		return getSqlMapClientTemplate().queryForList("ProductWordsCard.getRefreshProductWords",num);
	}

	@Override
	public List<ProductWordsCard> getFriendProductWordByID(Integer productWordsId) {
		return getSqlMapClientTemplate().queryForList("ProductWordsCard.singleSameProductWordBase", productWordsId);
	}

	@Override
	public Integer getCountIsEnable() {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProductWordsCard.getIsEnableCount");
	}

	@Override
	public Integer getCountNotEnable() {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProductWordsCard.getNotEnableCount");
	}
}
