/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 热词持久层数据库操作
 */
package com.coo8.topic.persistence.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotKeyword;
import com.coo8.topic.model.HotKeyword;
import com.coo8.topic.model.ImportLog;
import com.coo8.topic.persistence.interfaces.IHotKeywordDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class HotKeywordDAOImpl extends SqlMapClientDaoSupport implements IHotKeywordDAO {
	@Override
	public void add(final List<HotKeyword> hotKeyWords) {
		if (hotKeyWords != null) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					for (HotKeyword hotKeyWord : hotKeyWords) {
						executor.insert("HotKeyword.add", hotKeyWord);
					}
					return executor.executeBatch();
				}
			});
		}
	}

	@Override
	public void addLog(ImportLog importLog) {
		if (importLog != null) {
			getSqlMapClientTemplate().insert("HotKeyword.addLog", importLog);
		}
	}

	@Override
	public void addErrorWords(final List<ErrorHotKeyword> errorHotKeyWords) {
		if (errorHotKeyWords != null) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					for (ErrorHotKeyword errorWord : errorHotKeyWords) {
						executor.insert("HotKeyword.addError", errorWord);
					}
					return executor.executeBatch();
				}
			});
		}
	}

	@Override
	public void delete(final List<Integer> ids) {
		if (ids != null) {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					for (Integer id : ids) {
						executor.delete("HotKeyword.delete", id);
					}
					return executor.executeBatch();
				}
			});
		}
	}

	@Override
	public HotKeyword getById(Integer id) {
		return (HotKeyword) getSqlMapClientTemplate().queryForObject("HotKeyword.getById", id);
	}

	@Override
	public PaginatedList<HotKeyword> list(Map<String, Object> paramMap) {
		Object o = this.getSqlMapClientTemplate().queryForObject("HotKeyword.count", paramMap);
		if (o == null)
			return null;

		PaginatedList<HotKeyword> paginatedArrayList = new PaginatedArrayList<HotKeyword>(
				Integer.parseInt(o.toString()), (Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<HotKeyword> list = this.getSqlMapClientTemplate().queryForList("HotKeyword.list", paramMap,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());

		if (list != null)
			paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<HotKeyword> listAll(Map<String, Object> paramMap) {
		List<HotKeyword> arrayList = new ArrayList<HotKeyword>();
		Object o = this.getSqlMapClientTemplate().queryForObject("HotKeyword.count", paramMap);
		if (o == null)
			return arrayList;



		List<HotKeyword> list = this.getSqlMapClientTemplate().queryForList("HotKeyword.list", paramMap);

		if (list != null)
			arrayList.addAll(list);

		return arrayList;
	}

	@Override
	public List<ErrorHotKeyword> listDownLog(Map<String, Object> paramMap) {
		String logid = (String) paramMap.get("logid");
		if (logid != null) {
			this.getSqlMapClientTemplate().update("HotKeyword.downLogStatus", logid);
		}
		return this.getSqlMapClientTemplate().queryForList("HotKeyword.listDownLog", paramMap);
	}

	@Override
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap) {
		Object o = this.getSqlMapClientTemplate().queryForObject("HotKeyword.logcount", paramMap);
		if (o == null)
			return null;

		PaginatedList<ImportLog> paginatedArrayList = new PaginatedArrayList<ImportLog>(Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"), (Integer) paramMap.get("pageSize"));

		List<ImportLog> list = this.getSqlMapClientTemplate().queryForList("HotKeyword.listlog", paramMap,
				paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());

		if (list != null)
			paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public Integer count(Map<String, Object> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject("HotKeyword.count", paramMap);
	}

	@Override
	public void update(HotKeyword hotKeyWord) {
		getSqlMapClientTemplate().update("HotKeyword.update", hotKeyWord);
	}

	@Override
	public void publish(HotKeyword hotKeyWord) {
		getSqlMapClientTemplate().update("HotKeyword.publish", hotKeyWord);
	}

	@Override
	public List<HotKeyword> getByRangeId(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().queryForList("HotKeyword.range", paramMap);
	}

	@Override
	public int publishGomeHotWordsTest(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotkeyId", id);
		return getSqlMapClientTemplate().update("HotKeyword.publishGomeHotWordsTest", map);
	}

	@Override
	public int publishGomeHotWords(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotkeyId", id);
		return getSqlMapClientTemplate().update("HotKeyword.publishGomeHotWords", map);
	}

	@Override
	public int publishWapGomeHotWords(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotkeyId", id);
		return getSqlMapClientTemplate().update("HotKeyword.publishWapGomeHotWords", map);
	}

	@Override
	public int getLastedInsertId() {
		Object result = getSqlMapClientTemplate().queryForObject("HotKeyword.getLastedInsertId");
		if (result == null) {
			return 0;
		}
		Integer realResult = (Integer) result - 1;
		return realResult;
	}

	@Override
	public List<HotKeyword> selectHotKeywordlist(Map<String, Object> params) {

		return this.getSqlMapClientTemplate().queryForList("HotKeyword.selectHotKeywordlist", params);
	}

}
