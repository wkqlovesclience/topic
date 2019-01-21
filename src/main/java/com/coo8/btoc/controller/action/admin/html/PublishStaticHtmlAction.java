package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;

public class PublishStaticHtmlAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = 4230388050232371180L;
	
	private Integer id;
	
	public void setId(int id) {
		this.id = id;
	}

	@Action("publish")
	public String publish(){
		if(id == null)
			return null;
		
		html=staticHtmlManager.selectById(id);
		if(html == null) return null;
		
		if(html.isCatalogType())
			staticHtmlManager.updateCatalogPublish(html.getRefId(), html.getId());
		else
			staticHtmlManager.updateStaticPublish(id);
		
		return null;
	}
	
}
