package com.coo8.hotlink.business.impl;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.hotlink.business.interfaces.IHotLinkManager;
import com.coo8.hotlink.model.HotLink;
import com.coo8.hotlink.persistence.interfaces.IHotLinkDAO;

public class HotLinkManagerImpl implements IHotLinkManager {
	
	private IHotLinkDAO hotLinkDAO;
	
	/**
	 * @return the hotLinkDAO
	 */
	public IHotLinkDAO getHotLinkDAO() {
		return hotLinkDAO;
	}

	/**
	 * @param hotLinkDAO the hotLinkDAO to set
	 */
	public void setHotLinkDAO(IHotLinkDAO hotLinkDAO) {
		this.hotLinkDAO = hotLinkDAO;
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#findPaginatedHotLinkByMap(java.util.Map)
	 */
	@Override
	public PaginatedList<HotLink> findPaginatedHotLinkByMap(
			Map<String, Object> search) {
		// TODO Auto-generated method stub
		return hotLinkDAO.findPaginatedHotLinkByMap(search);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#getHotLinkById(java.lang.Integer)
	 */
	@Override
	public HotLink getHotLinkById(Integer id) {
		// TODO Auto-generated method stub
		return hotLinkDAO.getHotLinkById(id);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#save(com.coo8.hotlink.model.HotLink)
	 */
	@Override
	public String save(HotLink hotLink) {
		// TODO Auto-generated method stub
		return hotLinkDAO.save(hotLink);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#update(com.coo8.hotlink.model.HotLink)
	 */
	@Override
	public void update(HotLink hotLink) {
		// TODO Auto-generated method stub
		hotLinkDAO.update(hotLink);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		hotLinkDAO.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#findListByMap(java.util.Map)
	 */
	@Override
	public List<HotLink> findListByMap(Map<String, Object> search) {
		// TODO Auto-generated method stub
		return hotLinkDAO.findListByMap(search);
	}

	/* (non-Javadoc)
	 * @see com.coo8.hotlink.business.interfaces.IHotLinkManager#getByNamePcUrl(com.coo8.hotlink.model.HotLink)
	 */
	@Override
	public HotLink getByNamePcUrl(HotLink hotLink) {
		return hotLinkDAO.getByNamePcUrl(hotLink);
	}
	
	

	
	
}
