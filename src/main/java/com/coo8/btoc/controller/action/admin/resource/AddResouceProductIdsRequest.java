package com.coo8.btoc.controller.action.admin.resource;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.business.interfaces.catalog.IBtoCCatalogManager;
import com.coo8.btoc.business.interfaces.html.StaticHtmlManager;
import com.coo8.btoc.model.catalog.BtoCCatalog;
import com.coo8.btoc.model.html.StaticHtml;
import com.coo8.btoc.model.resource.ResourceCatalog;

@Namespace("/admin/resource/catalog")
public class AddResouceProductIdsRequest extends ResourceBaseAction {
	
	private static final long serialVersionUID = -1180698121125095555L;
	
	private IBtoCCatalogManager btocCatalogManager;
	private StaticHtmlManager staticHtmlManager;
	
	private List<BtoCCatalog> catalogList;
	private List<ResourceCatalog> resourceCatalogList;
	private List<StaticHtml> htmlList;
	
	private Integer resourceId;
	private Integer type;
	
	@Action(value="addProductRequest", results={
			@Result(name=INPUT, location="/admin/resource/list.action", type="redirect"),
			@Result(name="html", location="/jsp/admin/resource/admin_static_add_product.jsp", type="dispatcher" ),
			@Result(name="catalog", location="/jsp/admin/resource/admin_catalog_add_product.jsp", type="dispatcher" )
	})
	
	@Override
	public String execute() {
		if(resourceId == null || resourceId < 0)
			return INPUT;
		
		resource = resourceManager.getResourceById(resourceId);
		if(resource == null || resource.isProcedure())
			return INPUT;
		
		if(type == null || type < 1)
			type = ResourceCatalog.HEAD_OFFICE_TYPE;
		
		ResourceCatalog resourceCatalog = new ResourceCatalog();
		resourceCatalog.setType(type);
		resourceCatalog.setResourceId(resourceId);
		resourceCatalogList = resourceManager
			.queryResourceCatalog(resourceCatalog);
		
		if(ResourceCatalog.isHead(type)) {
			catalogList = btocCatalogManager.getBigCatalogToList();
			return "catalog";
		} else {
			htmlList = staticHtmlManager.queryHtmls(null);
			return "html";
		}
		
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public void setBtocCatalogManager(IBtoCCatalogManager btocCatalogManager) {
		this.btocCatalogManager = btocCatalogManager;
	}

	public List<BtoCCatalog> getCatalogList() {
		return catalogList;
	}

	public List<ResourceCatalog> getResourceCatalogList() {
		return resourceCatalogList;
	}

	public void setStaticHtmlManager(StaticHtmlManager staticHtmlManager) {
		this.staticHtmlManager = staticHtmlManager;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<StaticHtml> getHtmlList() {
		return htmlList;
	}
	
}