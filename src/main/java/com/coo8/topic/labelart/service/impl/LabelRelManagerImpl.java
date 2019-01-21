package com.coo8.topic.labelart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.topic.business.interfaces.IKeywordsRelManager;
import com.coo8.topic.labelart.dao.inter.LabelRelDAO;
import com.coo8.topic.labelart.modal.LabelRel;
import com.coo8.topic.labelart.service.inter.LabelRelManager;
import com.coo8.topic.model.KeywordsRel;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


 
public class LabelRelManagerImpl   implements LabelRelManager{

	private LabelRelDAO labelRelDao;
	 
	public LabelRelDAO getLabelRelDao() {
		return labelRelDao;
	}
	public void setLabelRelDao(LabelRelDAO labelRelDao) {
		this.labelRelDao = labelRelDao;
	}
	@Transactional(readOnly=true)
	public LabelRel getById(java.lang.Integer id){
		return labelRelDao.getById( id);
	}
	@Transactional
	public void deleteById(java.lang.Integer id){
		labelRelDao.deleteById(id);
	}
	 
	@Transactional
	public void save(LabelRel entity){
		labelRelDao.save(entity);
	}
	 
	@Transactional
	public void update(LabelRel entity){
		labelRelDao.update(entity);
	}
 
	@Transactional(readOnly=true)
   public List<LabelRel> findAll(Map<String, Object> search){
		return labelRelDao.findByMap(search);
	}

}
