/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.labelart.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.labelart.dao.inter.LabelDAO;
import com.coo8.topic.labelart.modal.Label;
import com.coo8.topic.labelart.modal.LabelIndex;
import com.coo8.topic.model.TitleIndex;

/**
 * @author  z.Bo
 * @version 1.0
 * @since 1.0
 */
@Repository("labelDAO")
public class LabelDAOImpl extends SqlMapClientDaoSupport  implements LabelDAO{
	
	@Override
	public PaginatedList<Label> findPageByMapLike(Map<String, Object> Label) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Label.findPageLike.count", Label);
		if(o==null)
			return null;
		PaginatedList<Label> paginatedArrayList = new PaginatedArrayList<Label>(Integer.parseInt(o.toString()), (Integer)Label.get("pageNumber"),(Integer)Label.get("pageSize"));
		List<Label> list = this.getSqlMapClientTemplate().queryForList("Label.findPageLike", Label, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	@Override
	public int deleteById(java.lang.Integer id){
	   return	getSqlMapClientTemplate().delete("Label.delete", id);
	}
	
	@Override
	public Label getById(java.lang.Integer id){
		return (Label) getSqlMapClientTemplate().queryForObject(
				"Label.getById", id);
	}
	
	@Override
	public int update(Label entity){
		return getSqlMapClientTemplate().update("Label.update",
				entity);
	}
	
	@Override
	public int test(Label entity){
		 getSqlMapClientTemplate().insert("Label.test",entity);
		 return 1;
	}
	
	@Override
	public List<Label> findByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Label.findPage", search);
	}
	
	@Override
	public int save(Label entity){
		Object obj = getSqlMapClientTemplate().insert(
				"Label.insert", entity);
		if (obj != null) {
			return   ((Integer)obj).intValue();
		}
		return 0; 
		 
	} 
	
	@Override
	public List<Label> getbyNewsId(int id){
		List<Label> list  = this.getSqlMapClientTemplate().queryForList("Label.getbyNewsId", id);
		return list;
	}
	
	@Override
	public String insertLabelIndex(LabelIndex entity) {
		Object obj = getSqlMapClientTemplate().insert("Label.index.insert", entity);
		if(obj != null){
			return obj.toString();
		}
		return null;
	}
	
	@Override
	public List<Label> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
					"Label.findPage", search);
	}
	
	@Override
	public int isAddRepeat(Map<String, Object> search) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Label.index.isAddRepeat", search);
	}
	
	@Override
	public PaginatedList<LabelIndex> findLabelIndexByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("LabelIndex.index.count", search);
		if(o==null)
			return null;
		PaginatedList<LabelIndex> paginatedArrayList = new PaginatedArrayList<LabelIndex>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<LabelIndex> list = this.getSqlMapClientTemplate().queryForList("LabelIndex.index.list", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}
	
	@Override
	public LabelIndex getLabelIndexById(int id) {
		return (LabelIndex) getSqlMapClientTemplate().queryForObject("LabelIndex.index.getById", id);
	}
	
	@Override
	public int updateLabelIndex(LabelIndex entity) {
		return getSqlMapClientTemplate().update("LabelIndex.index.update", entity);
	}
	
	/**
	 * 标签大全 维度索引页 发布队列
	 */
	@Override
	public int publicLabelHomePage(String site) {
		if(null == site || "".equals(site)){
			return 1;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("site", site);
		map.put("input_date",new Date());
		map.put("rfid",93);
		getSqlMapClientTemplate().insert("Label.publicLabelHomePage",map);
		return 1;
	}
	
	@Override
	public int publicLabelListPage(String site) {
		if(null == site || "".equals(site)){
			return 1;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("site", site);
		map.put("input_date",new Date());
		map.put("rfid",94);
		getSqlMapClientTemplate().insert("Label.publicLabelListPage",map);
		return 1;
	}
	
	
	/**
	 * 标签大全 列表页 发布队列
	 */
	@Override
	public int publicLabelPage(String id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("site", "gome");
		map.put("input_date",new Date());
		map.put("rfid",id);
		map.put("templateid", 92);
		getSqlMapClientTemplate().insert("Label.publicLabelPage",map);
		return 1;
	}
	
}
