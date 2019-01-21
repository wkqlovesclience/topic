package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/admin/html")
public class StaticHtmlDeleteAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = 1017013036967573612L;
	
	
	@Action(value="delete")
	public void delete() {
		if(isUnavailableId())
			return ;
		
		html = staticHtmlManager.selectById(html.getId());
		staticHtmlManager.deleteHtmlandRedis(html);
	}
	
}
