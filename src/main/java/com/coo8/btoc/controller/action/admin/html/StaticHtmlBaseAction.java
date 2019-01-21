package com.coo8.btoc.controller.action.admin.html;

import java.util.List;

import com.coo8.btoc.business.interfaces.catalog.IBtoCCatalogManager;
import com.coo8.btoc.business.interfaces.html.StaticHtmlManager;
import com.coo8.btoc.business.interfaces.resource.ResourceManager;
import com.coo8.btoc.controller.action.CommonAction;
import com.coo8.btoc.model.catalog.BtoCCatalog;
import com.coo8.btoc.model.html.StaticHtml;
import com.coo8.common.utils.StringUtil;

public class StaticHtmlBaseAction extends CommonAction {

	private static final long serialVersionUID = 1167856895090709664L;
	
	protected StaticHtmlManager staticHtmlManager;
	protected IBtoCCatalogManager btocCatalogManager;
	
	protected StaticHtml html;
	
	protected List<StaticHtml> htmlList;
	protected List<BtoCCatalog> catalogList;
	protected ResourceManager resourceManager;
	public StaticHtml getHtml() {
		return html;
	}
	public void setHtml(StaticHtml html) {
		this.html = html;
	}
	public List<StaticHtml> getHtmlList() {
		return htmlList;
	}
	
	public List<BtoCCatalog> getCatalogList() {
		return catalogList;
	}
	public void setStaticHtmlManager(StaticHtmlManager staticHtmlManager) {
		this.staticHtmlManager = staticHtmlManager;
	}
	
	protected boolean isUnavailableId() {
		return html == null || html.getId() == null || html.getId() < 1;
	}
	
	protected boolean verifyPath() {
		return html == null || StringUtil.isNullorBlank(html.getPath());
	}
	public void setBtocCatalogManager(IBtoCCatalogManager btocCatalogManager) {
		this.btocCatalogManager = btocCatalogManager;
	}
    public ResourceManager getResourceManager() {
        return resourceManager;
    }
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
	
	
}