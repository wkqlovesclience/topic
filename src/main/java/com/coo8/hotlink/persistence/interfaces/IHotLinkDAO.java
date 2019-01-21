package com.coo8.hotlink.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.hotlink.model.HotLink;

public interface IHotLinkDAO {
	
	public PaginatedList<HotLink> findPaginatedHotLinkByMap(Map<String, Object> search);
	
	public HotLink getHotLinkById(Integer id);
	
	public String save(HotLink hotLink);
	
    public int update(HotLink hotLink);
	
	public int delete(Integer id); 
	
	public List<HotLink> findListByMap(Map<String, Object> search);
	
	public HotLink getByNamePcUrl(HotLink hotLink);
}
