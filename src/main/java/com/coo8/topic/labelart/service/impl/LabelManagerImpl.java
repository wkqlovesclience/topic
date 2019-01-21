package com.coo8.topic.labelart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.labelart.dao.inter.LabelDAO;
import com.coo8.topic.labelart.modal.Label;
import com.coo8.topic.labelart.modal.LabelIndex;
import com.coo8.topic.labelart.service.inter.LabelManager;
import com.coo8.topic.model.TitleIndex;

/**
 * @author  z.Bo
 * @version 1.0
 * @since 1.0
 */


 
public class LabelManagerImpl   implements LabelManager{

	private LabelDAO labelDao;
	 
	@Override
	public PaginatedList<Label> findPageByMapLike(Map<String, Object> Label) {
		return labelDao.findPageByMapLike(Label);
	}
	
	@Transactional
	public void deleteById(java.lang.Integer id){
		labelDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Label getById(java.lang.Integer id){
		return labelDao.getById( id);
	}
	
	@Transactional
	public void update(Label entity){
		labelDao.update(entity);
	}
	
	@Transactional(readOnly=true)
	public List<Label> findAll(Map<String, Object> search){
		return labelDao.findByMap(search);
	}
	
	@Transactional
	public void test(Label entity){
		labelDao.test(entity);
	}

	public LabelDAO getLabelDao() {
		return labelDao;
	}

	public void setLabelDao(LabelDAO labelDao) {
		this.labelDao = labelDao;
	}
	
	@Transactional
	public int save(Label entity){
		return labelDao.save(entity);
	}
	
	@Transactional
	public List<Label> getLabelbyNewsId(int id){
		return labelDao.getbyNewsId(id);
	}
	
	@Override
	public String insertLabelIndex(LabelIndex entity) {
		return labelDao.insertLabelIndex(entity);
	}
	
	@Transactional(readOnly = true)
	public List<Label> findListByMap(Map<String, Object> search) {
		return labelDao.findListByMap(search);
	}
	
	@Override
	public int isAddRepeat(Map<String, Object> search) {
		return labelDao.isAddRepeat(search);
	}
	
	@Override
	public PaginatedList<LabelIndex> findLabelIndexByMap(Map<String, Object> search) {
		return labelDao.findLabelIndexByMap(search);
	}
	
	@Override
	public int publicLabelHomePage(String site) {
		return labelDao.publicLabelHomePage(site);
	}
	
	@Override
	public int publicLabelListPage(String site) {
		return labelDao.publicLabelListPage(site);
	}
	
	@Override
	public int publicLabelPage(String id) {
		return labelDao.publicLabelPage(id);
	}
	
	@Override
	public LabelIndex getLabelIndexById(int id) {
		return labelDao.getLabelIndexById(id);
	}
	
	@Override
	public int updateLabelIndex(LabelIndex entity) {
		return labelDao.updateLabelIndex(entity);
	}

}
