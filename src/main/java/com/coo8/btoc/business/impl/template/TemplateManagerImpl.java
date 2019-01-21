package com.coo8.btoc.business.impl.template;

import java.util.List;
import java.util.regex.Matcher;

import com.coo8.btoc.business.interfaces.template.TemplateManager;
import com.coo8.btoc.model.block.Block;
import com.coo8.btoc.model.template.CatalogTemplate;
import com.coo8.btoc.model.template.Template;
import com.coo8.btoc.model.template.TemplateHistory;
import com.coo8.btoc.persistence.impl.block.helper.StoreBlockCallBack;
import com.coo8.btoc.persistence.interfaces.block.BlockDao;
import com.coo8.btoc.persistence.interfaces.template.TemplateDao;
import com.coo8.btoc.util.PatternUtil;
import com.yesky.common.utils.StringUtil;

public class TemplateManagerImpl implements TemplateManager {
	
	
	private TemplateDao templateDao;
	
	private BlockDao blockDao;
	
	@Override
	public void createTemplate(Template template) {
		templateDao.insertTemplate(template);
		templateDao.insertTemplateHistory(template);
		
		parseBlockTagsFromTemplate(template, new StoreBlockCallBack() {
			
			@Override
			public void store(Template template, String blockName, int type) {
				if (isExsitBlock(template, blockName))
					return;
				storeBlock(template, blockName, type);
			}
			
			private boolean isExsitBlock(Template template, String blockName) {
				List<Block> blocks = blockDao.
					queryBlock(template.getId(), blockName);
				
				return ! blocks.isEmpty();
			}
		});
	}

	private void parseBlockTagsFromTemplate(Template template, StoreBlockCallBack call) {
		if (StringUtil.isNullorBlank(template.getContent()))
			return;
		
		Matcher contentMatcher = PatternUtil.blockPattern(template.getContent());
		
		while (contentMatcher.find()) {
			String blockTag = contentMatcher.group();
			call.store(template, 
					parseBlockTag(PatternUtil.blockNamePattern(blockTag)), 
					parseBlockType(blockTag));
		}
	}
	
	private String parseBlockTag(Matcher blockTagMatcher) {
		String name = null;
		if(blockTagMatcher.find())
			name = blockTagMatcher.group();
		
		if(StringUtil.isNullorBlank(name)){
			return "";
		}else{
		  name = name.replaceAll("('|\")", "");
		 return name.split("=")[1];
		}
	}
	
	private int parseBlockType(String blockTag) {
		String type = parseBlockTag(PatternUtil.blockTypePattern(blockTag));
		
		return Integer.parseInt(type);
	}
	
	private void storeBlock(Template template, String blockName, int type) {
		Block block = new Block();
		block.setName(blockName);
		block.setTemplateId(template.getId());
		block.setCreator(template.getCreator());
		block.setType(type);
		block.setStatus(Block.DISABLED);
		block.setSite(template.getSite());
		blockDao.insert(block);
	}
	
	@Override
	public void updateTemplate(Template template) {
		backupTemplate(template);
		
		templateDao.updateTemplate(template);
		
		parseBlockTagsFromTemplate(template, new StoreBlockCallBack() {
			
			@Override
			public void store(Template template, String blockName, int type) {
				if (isExsitBlock(template, blockName))
					return;
				
				storeBlock(template, blockName, type);
			}
			
			private boolean isExsitBlock(Template template, String blockName) {
				List<Block> blocks = blockDao.
					queryBlock(template.getId(), blockName);
				
				return ! blocks.isEmpty();
			}
		});
	}

	private void backupTemplate(Template template) {
		Template history = new Template();
		history.setId(template.getId());
		history.setContent(template.getContent());
		history.setCreator(template.getUpdator());
		history.setName(template.getName());
		history.setDescription(template.getDescription());
		
		templateDao.insertTemplateHistory(history);
	}
	
	@Override
	public void enableTemplate(Template template) {
		template.setStatus(Template.ENABLED);
		
		templateDao.updateTemplate(template);
	}

	@Override
	public void disableTemplate(Template template) {
		template.setStatus(Template.DISABLED);
		
		templateDao.updateTemplate(template);
	}
	
	@Override
	public void applyTemplate(Integer id, String updator) {
		TemplateHistory history = templateDao.queryLastTemplateHistory(id);
		if(history == null)
			return ;
		
		Template template = new Template();
		template.setContent(history.getContent());
		template.setId(id);
		template.setUpdator(updator);
		
		templateDao.applyTemplate(template);
	}
	
	@Override
	public Template getUpdateTemplateById(Integer id) {
		Template template = templateDao.getTemplateById(id);
		
		if(template == null)
			return null;
		TemplateHistory history = templateDao.queryLastTemplateHistory(id);
		String content = "";
		if(null != history && null != history.getContent()){
			content = history.getContent();
		}
		template.setContent(content);
		
		return template;
	}
	
	@Override
	public void updateLoadTemplate(CatalogTemplate catalogTemplate) {
		CatalogTemplate ct = templateDao.
				selectCatalogTemplateByCidAndType(catalogTemplate);
		
		if(isReLoad(ct)) {
			catalogTemplate.setId(ct.getId());
			templateDao.updateCatalogTemplate(catalogTemplate);
		} else {
			templateDao.insertCatalogTemplate(catalogTemplate);
		}
	}
	
	private boolean isReLoad(CatalogTemplate ct) {
		return ct != null;
	}
	
	@Override
	public void deleteCatalogTemplate(CatalogTemplate catalogTemplate) {
		templateDao.deleteCatalogTemplate(catalogTemplate);
	}
	
	@Override
	public CatalogTemplate selectCatalogTemplateByCidAndType(CatalogTemplate ct) {
		return templateDao.selectCatalogTemplateByCidAndType(ct);
	}
	
	@Override
	public Template getTemplateById(Integer id) {
		return templateDao.getTemplateById(id);
	}

	@Override
	public List<Template> queryTemplates(Template template) {
		return templateDao.queryTemplates(template);
	}

	public TemplateDao getTemplateDao() {
		return templateDao;
	}

	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	public void setBlockDao(BlockDao blockDao) {
		this.blockDao = blockDao;
	}

}
