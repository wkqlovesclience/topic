package com.coo8.btoc.business.impl.html;

import java.util.List;

import com.coo8.btoc.business.interfaces.html.StaticHtmlManager;
import com.coo8.btoc.business.interfaces.resource.ResourceManager;
import com.coo8.btoc.model.html.StaticHtml;
import com.coo8.btoc.model.template.CatalogTemplate;
import com.coo8.btoc.persistence.interfaces.html.StaticHtmlDao;
import com.coo8.btoc.persistence.interfaces.template.TemplateDao;

public class StaticHtmlManagerImpl implements StaticHtmlManager {
	
	private StaticHtmlDao staticHtmlDao;
	private TemplateDao templateDao;
	private ResourceManager resourceManager;
	
	public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	public void setStaticHtmlDao(StaticHtmlDao staticHtmlDao) {
		this.staticHtmlDao = staticHtmlDao;
	}

	@Override
	public void createHtml(StaticHtml html) {
		staticHtmlDao.insertHtml(html);
	}

	@Override
	public void updateHtml(StaticHtml html) {
		staticHtmlDao.updateHtml(html);
	}

	@Override
	public StaticHtml selectById(Integer id) {
		return staticHtmlDao.selectHtmlById(id);
	}

	@Override
	public List<StaticHtml> queryHtmls(StaticHtml html) {
		return staticHtmlDao.queryHtmls(html);
	}
	
	@Override
	public int updateStaticPublish(int id){
		return staticHtmlDao.updateStaticPublish(id);
	}

	@Override
	public int updateCatalogPublish(int id, int staticId) {
		return staticHtmlDao.updateCatalogPublish(id, staticId);
	}

	@Override
	public void deleteStaticHtml(StaticHtml html) {
		staticHtmlDao.deleteStaticHtml(html);
		
		if (html.isCatalogType()) {
			deleteUnLoadCatalogTemplate(html);
		}
		
	}

	private void deleteUnLoadCatalogTemplate(StaticHtml html) {
		CatalogTemplate ct = new CatalogTemplate();
		ct.setCatalogId(html.getRefId());
		ct.setType(CatalogTemplate.BIG_CATALOG_TYPE);
		ct.setTemplateId(html.getTemplateId());
		
		templateDao.deleteCatalogTemplate(ct);
	}
	/**
	 * 删除静态页，并且清除静态页中的resourcecatalog和缓存
	 */
    @Override
    public void deleteHtmlandRedis(StaticHtml html) {
        deleteStaticHtml(html);
        //删除原静态页下的所有产品位(resourcecatalog)和缓存
        resourceManager.deleteResourceCatalogByHtmlId(html.getId());
    }
    /**
     * 更改静态页，并且清除静态页中的resourcecatalog和缓存
     */
    @Override
    public void updateHtmlandRedis(StaticHtml html) {
        updateHtml(html);
      //删除原静态页下的所有产品位(resourcecatalog)和缓存
        resourceManager.deleteResourceCatalogByHtmlId(html.getId());
    }
	
}
