/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.labelart.dao.inter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedArrayList;
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
public interface LabelDAO{

	public PaginatedList<Label> findPageByMapLike(Map<String, Object> Label);
	
	public int deleteById(java.lang.Integer id);
	
	public Label getById(java.lang.Integer id);

	public int update(Label entity);
	
	public int test(Label entity);
	
	public List<Label> findByMap(Map<String, Object> search);
	
	public int save(Label entity);
	
	public List<Label> getbyNewsId(int id);
	
	public String insertLabelIndex(LabelIndex entity);
	
	public List<Label> findListByMap(Map<String, Object> search);
	
	public int isAddRepeat(Map<String, Object> search);
	
	public PaginatedList<LabelIndex> findLabelIndexByMap(Map<String, Object> search);
	
	public LabelIndex getLabelIndexById(int id);
	
	public int updateLabelIndex(LabelIndex entity);
	
	public int publicLabelHomePage(String site);
	
	public int publicLabelListPage(String site);
	
	public int publicLabelPage(String id);
}
