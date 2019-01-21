package com.coo8.hotlink.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.hotlink.model.HotLink;
import com.coo8.hotlink.persistence.interfaces.IHotLinkDAO;

/**
 * @author  zhanghan
 * @version 1.0
 * @since 1.0
 */
@Repository("hotLinkDAO")
public class HotLinkDAOImpl extends SqlMapClientDaoSupport implements IHotLinkDAO {

	@Override
	public PaginatedList<HotLink> findPaginatedHotLinkByMap(
			Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("HotLink.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<HotLink> paginatedArrayList = new PaginatedArrayList<HotLink>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<HotLink> list = this.getSqlMapClientTemplate().queryForList("HotLink.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}

	@Override
	public HotLink getHotLinkById(Integer id) {
		return (HotLink)getSqlMapClientTemplate().queryForObject(
				"HotLink.getById",id);
	}

	@Override
	public String save(HotLink hotLink) {
		Object obj = getSqlMapClientTemplate().insert(
				"HotLink.insert", hotLink);
		if (obj != null) {
			return   obj.toString();
		}
		return null; 
	}

	@Override
	public int update(HotLink hotLink) {
		return getSqlMapClientTemplate().update("HotLink.update",
				hotLink);
	}

	@Override
	public int delete(Integer id) {
		return getSqlMapClientTemplate().delete("HotLink.delete", id);
	}

	@Override
	public List<HotLink> findListByMap(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList(
				"HotLink.findListLike", search);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.persistence.interfaces.IHotLinkDAO#getByNamePcUrl(com.coo8.hotlink.model.HotLink)
	 */
	@Override
	public HotLink getByNamePcUrl(HotLink hotLink) {
		// TODO Auto-generated method stub
		return  (HotLink)getSqlMapClientTemplate().queryForObject("HotLink.getByWhere");
	}

	
	
}
