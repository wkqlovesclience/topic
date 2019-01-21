package com.coo8.topic.persistence.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotSearchword;
import com.coo8.topic.model.HotSearchword;
import com.coo8.topic.model.ImportSearchLog;
import com.coo8.topic.persistence.interfaces.IHotSearchwordDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class HotSearchwordDAOImpl extends SqlMapClientDaoSupport implements IHotSearchwordDAO {

	@Override
	public void add(final List<HotSearchword> hotSearchwords) {

		if (hotSearchwords != null) {

			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {

					for (HotSearchword hotSearchword : hotSearchwords) {

						executor.insert("HotSearchword.add", hotSearchword);
					}

					return executor.executeBatch();
				}
			});
		}

	}

	@Override
	public void addErrorWords(final List<ErrorHotSearchword> errorHotSearchWords) {
		if (errorHotSearchWords != null) {

			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {

					for (ErrorHotSearchword errorWord : errorHotSearchWords) {

						executor.insert("HotSearchword.addError", errorWord);
					}
					return executor.executeBatch();
				}
			});
		}
	}

	@Override
	public void addLog(ImportSearchLog importSearchLog) {
		if (importSearchLog != null) {

			getSqlMapClientTemplate().insert("HotSearchword.addLog", importSearchLog);
		}

	}

	@Override
	public void delete(final List<Integer> ids) {
		if (ids != null) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {

					for (Integer id : ids) {

						executor.delete("HotSearchword.delete", id);
					}
					return executor.executeBatch();
				}
			});
		}

	}

	@Override
	public HotSearchword getById(Integer id) {
		return (HotSearchword) getSqlMapClientTemplate().queryForObject("HotSearchword.getById", id);
	}

	@Override
	public PaginatedList<HotSearchword> list(Map<String, Object> paramMap) {
		Object o = this.getSqlMapClientTemplate().queryForObject("HotSearchword.count", paramMap);
		if (o == null)
			return null;

		PaginatedList<HotSearchword> paginatedArrayList = new PaginatedArrayList<HotSearchword>(
				Integer.parseInt(o.toString()), (Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<HotSearchword> list = this.getSqlMapClientTemplate().queryForList("HotSearchword.list", paramMap,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());

		if (list != null)
			paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<HotSearchword> listAll(Map<String, Object> paramMap) {
		List<HotSearchword> arrayList = new ArrayList<HotSearchword>();
		Object o = this.getSqlMapClientTemplate().queryForObject("HotSearchword.count", paramMap);
		if (o == null)
			return arrayList;



		List<HotSearchword> list = this.getSqlMapClientTemplate().queryForList("HotSearchword.list", paramMap);

		if (list != null)
			arrayList.addAll(list);

		return arrayList;
	}

	@Override
	public List<HotSearchword> listHotSearch(Map<String, Object> paramMap) {
		List<HotSearchword> arrayList = new ArrayList<HotSearchword>();
		Object o = this.getSqlMapClientTemplate().queryForObject("HotSearchword.count", paramMap);
		if (o == null)
			return arrayList;

	

		List<HotSearchword> list = this.getSqlMapClientTemplate().queryForList("HotSearchword.listHotSearch", paramMap);

		if (list != null)
			arrayList.addAll(list);

		return arrayList;
	}

	@Override
	public PaginatedList<ImportSearchLog> listLog(Map<String, Object> paramMap) {
		Object o = this.getSqlMapClientTemplate().queryForObject("HotSearchword.logcount", paramMap);
		if (o == null)
			return null;

		PaginatedList<ImportSearchLog> paginatedArrayList = new PaginatedArrayList<ImportSearchLog>(
				Integer.parseInt(o.toString()), (Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<ImportSearchLog> list = this.getSqlMapClientTemplate().queryForList("HotSearchword.listlog", paramMap,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());

		if (list != null)
			paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<HotSearchword> getByRangeId(Map<String, Object> paramMap) {

		return getSqlMapClientTemplate().queryForList("HotSearchword.range", paramMap);
	}

	@Override
	public List<ErrorHotSearchword> listDownLog(Map<String, Object> paramMap) {
		String logid = (String) paramMap.get("logid");
		if (logid != null) {
			this.getSqlMapClientTemplate().update("HotSearchword.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList("HotSearchword.listDownLog", paramMap);
	}

	@Override
	public Integer count(Map<String, Object> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HotSearchword.count", paramMap);
	}

	@Override
	public void update(HotSearchword hotSearchWord) {
		getSqlMapClientTemplate().update("HotSearchword.update", hotSearchWord);

	}

	@Override
	public void publish(HotSearchword hotSearchWord) {
		getSqlMapClientTemplate().update("HotSearchword.publish", hotSearchWord);

	}

	@Override
	public int getLastedInsertId() {

		Object result = getSqlMapClientTemplate().queryForObject("HotSearchword.getLastedInsertId");
		if (result == null) {
			return 0;
		}
		Integer realResult = (Integer) result - 1;
		return realResult;

	}

	/**
	 * 查询相关热搜词
	 * 
	 * @param map
	 * @return
	 */
	public List<HotSearchword> getHotkeyRelatedKeywords(Map<String, Object> paramMap) {

		return this.getSqlMapClientTemplate().queryForList("HotSearchword.getHotkeyRelatedKeywords", paramMap);
	}
	
	
	@Override
	public List<Integer> listLimit(Map<String, Object> paramMap) {
		List<Integer> arrayInt = this.getSqlMapClientTemplate().queryForList("HotSearchword.listLimit", paramMap);

		return arrayInt;
	}

}
