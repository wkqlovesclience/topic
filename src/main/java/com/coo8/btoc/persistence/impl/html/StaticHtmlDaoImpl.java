package com.coo8.btoc.persistence.impl.html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.html.StaticHtml;
import com.coo8.btoc.persistence.interfaces.html.StaticHtmlDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

public class StaticHtmlDaoImpl extends SqlMapClientDaoSupport implements
		StaticHtmlDao {

	@Override
	public void insertHtml(StaticHtml html) {
		getSqlMapClientTemplate().insert("StaticHtml.insertHtml", html);
	}

	@Override
	public void updateHtml(StaticHtml html) {
		getSqlMapClientTemplate().update("StaticHtml.updateHtml", html);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StaticHtml> queryHtmls(StaticHtml html) {
		if(isNotNeedSplitPage(html))
			return getSqlMapClientTemplate()
				.queryForList("StaticHtml.queryHtmls", html);
		
		int count = queryCount(html);
		if(count <= 0)
			return PaginatedArrayList.emptyList();
		
		PaginatedList<StaticHtml> htmlPageList = new PaginatedArrayList<StaticHtml>(
				count, html.getPageIndex(), html.getPageSize());
		
		List<StaticHtml> temp = getSqlMapClientTemplate().queryForList(
				"StaticHtml.queryHtmls", html, htmlPageList.getStartPos(), 
				html.getPageSize());
		
		htmlPageList.addAll(temp);
		
		return htmlPageList;
	}
	
	private boolean isNotNeedSplitPage(StaticHtml html) {
		return html == null || html.getPageSize() == null 
			|| html.getPageSize() < 1;
	}
	
	private int queryCount(StaticHtml html) {
		Integer count = (Integer)getSqlMapClientTemplate().
			 queryForObject("StaticHtml.queryHtmlCounts", html);
		
		return count == null ? 0 : count.intValue();
	}
	
	@Override
	public StaticHtml selectHtmlById(Integer id) {
		return (StaticHtml)getSqlMapClientTemplate().
			queryForObject("StaticHtml.selectById", id);
	}
	
	@Override
	public int updateStaticPublish(int id){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return this.getSqlMapClientTemplate().update("StaticHtml.updateStaticPublish",map);
	}

	@Override
	public int updateCatalogPublish(int id, int staticId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("catalogId", id);
		map.put("staticId", staticId);
		return this.getSqlMapClientTemplate().update("StaticHtml.updateCatalogPublish",map);
	}

	@Override
	public void deleteStaticHtml(StaticHtml html) {
		getSqlMapClientTemplate().delete("StaticHtml.deleteStaticHtml", html);
	}
	
	
}
