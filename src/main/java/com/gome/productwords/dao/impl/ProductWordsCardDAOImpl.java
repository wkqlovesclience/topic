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
	
	 //ͨ������id��ѯ
	@Override
	public ProductWordsCard getById(Integer id){
		return (ProductWordsCard) getSqlMapClientTemplate().queryForObject("ProductWordsCard.getById", id);
	}

	@Override
	public ProductWordsCard getByIdForDubbo(Integer id) {
		return (ProductWordsCard) getSqlMapClientTemplate().queryForObject("ProductWordsCard.InterfaceUseGetById", id);
	}

	//ͨ������idɾ��
	@Override
	public int deleteById(Integer id){
	   return	getSqlMapClientTemplate().delete("ProductWordsCard.delete", id);
	}


	//������Ʒ��
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
	 * ��ҳ��ͨ��PaginatedListʵ�֣�����δ����Ի�Ŀɲ鿴PaginatedArrayList�Ĵ��룬��ֱ��CVʹ�ã���Ҫ��Ӧ��ȷ�Ĳ���
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
	 * ��ҳ��ͨ��PaginatedListʵ�֣�����δ����Ի�Ŀɲ鿴PaginatedArrayList�Ĵ��룬��ֱ��CVʹ�ã���Ҫ��Ӧ��ȷ�Ĳ���
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
	 * ������־��
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
	 * �ϴ���Ʒ��ʱ�����ȸ�����Ʒ�ʴ����������ݿ�Ĳ��أ������ڻ��������Ʒ�ʴ�����������ж�Ӧ����־id������ʱ������־id��ѯ������Ʒ�ʣ�������
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
	 * ��Ӵ�����Ʒ�ʼ���Ϣ
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
	 * �����־
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
	 * ����productWordsName��Ʒ�ʴ�������
	 */
	@Override
	public ProductWordsCard getByProductWordsName(String productWordsName) {
			return  (ProductWordsCard) getSqlMapClientTemplate().queryForObject("ProductWordsCard.getByProductWordsName", productWordsName);
	}

	/**
	 *
	 * @param search
	 * @return PaginatedList
	 * ������ѯ
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
	 * ����ָ���ĳ�ʼid��ĩβid ��������  ��ͬ����ģ��Ĵ����ֱ��CV
	 */
	@Override
	public List<ProductWordsCard> getByRangeId(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().queryForList("ProductWordsCard.range", paramMap);
	}

	/**
	 *
	 * @param
	 * @return
	 * ҵ��Ҫ��ǰ̨Ҫ��չʾͬһ��Ʒ�ʴʸ��µ�16�����ݣ�
	 * ������16�����չʾ����16���Ĳ���
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
	 * չʾnum�����µ���Ʒ��
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
