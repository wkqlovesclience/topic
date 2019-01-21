package com.coo8.hotlink.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.hotlink.model.HotLink;

public interface IHotLinkManager {

    public PaginatedList<HotLink> findPaginatedHotLinkByMap(Map<String, Object> search);
	
	public HotLink getHotLinkById(Integer id);
	
	public String save(HotLink hotLink);
	
    public void update(HotLink hotLink);
	
	public void delete(Integer id); 
	
	public List<HotLink> findListByMap(Map<String, Object> search);
	
	public HotLink getByNamePcUrl(HotLink hotLink);
}
