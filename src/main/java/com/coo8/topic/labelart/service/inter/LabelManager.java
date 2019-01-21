package com.coo8.topic.labelart.service.inter; 

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.labelart.modal.Label;
import com.coo8.topic.labelart.modal.LabelIndex;
import com.coo8.topic.model.TitleIndex;
import com.coo8.topic.model.Titles;

/**
 * @author  z.Bo
 * @version 1.0
 * @since 1.0
 */

public interface LabelManager{
	  
	public PaginatedList<Label> findPageByMapLike(Map<String, Object> Label);
	
	public void deleteById(java.lang.Integer id);
	
	public Label getById(java.lang.Integer id);
	
	public void update(Label entity);
	
	public void test(Label entity);
	
	public List<Label> findAll(Map<String, Object> search);
	
	public int save(Label entity);
	
	//����id��ѯ���±�ǩ�ķ���
	public List<Label> getLabelbyNewsId(int id);
	
	public String insertLabelIndex(LabelIndex entity);
	
	public List<Label> findListByMap(Map<String, Object> search);
	
	public int isAddRepeat(Map<String, Object> search);
	
	public PaginatedList<LabelIndex> findLabelIndexByMap(Map<String, Object> search);
	
	public LabelIndex getLabelIndexById(int id);
	
	public int updateLabelIndex(LabelIndex entity);
	
	//��ǩ��ȫ ά������ҳ ����
	public int publicLabelHomePage(String site);
	
	//��ǩ��ȫ ά������ҳ �б�ҳ����
	public int publicLabelListPage(String site);
	
	//��ǩ��ȫ �б�ҳ ����
	public int publicLabelPage(String id);
	
}
