/**
 * 
 */
package com.coo8.btoc.business.impl.rank;

import java.util.Map;

import com.coo8.btoc.business.interfaces.rank.IGroupHotLinksManager;
import com.coo8.btoc.model.rank.GroupHotLinks;
import com.coo8.btoc.persistence.interfaces.rank.IGroupHotLinksDAO;
import com.coo8.btoc.util.pages.PaginatedList;

/**
 * @author wangfufu
 *
 */
public class GroupHotManagerImpl implements  IGroupHotLinksManager {
	
	private IGroupHotLinksDAO groupHotLinksDAO;
	
	@Override
	public PaginatedList<GroupHotLinks> selectAllGroupHotLinks(Map<String, Object> search){
		return groupHotLinksDAO.selectAllGroupHotLinks(search);
	}
    
	@Override
	public GroupHotLinks selectGroupHotLinksById(Integer id) {
		return groupHotLinksDAO.selectGroupHotLinksById(id);
	}
	 
	@Override
	public int save(GroupHotLinks entity) {
		return groupHotLinksDAO.save(entity);
	}

	@Override
	public int deleteByGroupHotLinksId(Integer id) {
		return groupHotLinksDAO.deleteByGroupHotLinksId(id);
	}

	@Override
	public int updateGroupHotLinksById(GroupHotLinks entity) {
		return groupHotLinksDAO.updateGroupHotLinksById(entity);
	}
	
	 
	public IGroupHotLinksDAO getGroupHotLinksDAO() {
		return groupHotLinksDAO;
	}

	public void setGroupHotLinksDAO(IGroupHotLinksDAO groupHotLinksDAO) {
		this.groupHotLinksDAO = groupHotLinksDAO;
	}
	
	
	
	
}
