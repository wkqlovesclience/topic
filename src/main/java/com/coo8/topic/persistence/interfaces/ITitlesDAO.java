/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.persistence.interfaces;

import java.util.*;

import org.apache.commons.lang.StringUtils;
 





import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.*;
import com.coo8.topic.persistence.interfaces.*;
import com.coo8.topic.business.interfaces.*;  

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

 


public interface ITitlesDAO{

	public Titles getById(java.lang.Integer id);
	
	public int deleteById(java.lang.Integer id);
	
	public int  deleteBlockQueue();
	 
	public int  deleteProductQueue();
	
	public String save(Titles entity);
	 
	public int update(Titles entity);

	public int changePath();
	
	public int changeTwo();
 	
	public PaginatedList<Titles> findByMap(Map<String, Object> search);

	public List<Titles> findListByMap(Map<String, Object> search);

	public List<Titles> findListLikeByMap(Map<String, Object> search);

	public PaginatedList<Titles> findLikeByMap(Map<String, Object> search);

	public String saveDrops(List<GoodsDrops> drops);

	public PaginatedList<Titles> findDropsListByMap(Map<String, Object> search);

	public int deleteAllDrops();

	public List<Titles> findAllTitlesList();

	public int deleteDropsByObj(GoodsDrops drops);
	
	public PaginatedList<TitleIndex> findTitleIndexByMap(Map<String, Object> search);
	
	public TitleIndex getTitleIndexById(int id);

	public TitleIndex getTitleIndexByTitleId(int titleId);
	
	public int isAddRepeat(Map<String, Object> search);
	
	public String insertTitleIndex(TitleIndex entity);
	
	public int updateTitleIndex(TitleIndex entity);
	
	public int deleteTitleIndex(int id);

	public int publishGomeTitle(int id);

	public int publishCoo8Title(int id);

	public int publishGomeTitleTest(int id);

	public int publishCoo8TitleTest(int id);

	public int publicTitleHomePage(String site);

	public int publishAllTitleListPage(String site);

	public int publishAllNewsListPage(String site);
	
	public int getMaxId(String site);
	
	/**
	 * Wap专题系统首页发布
	 * 
	 * @return
	 */
	public int publicWapTitleHomePage();

	/**
	 * Wap专题页发布
	 * 
	 * @param id
	 * @return
	 */
	public int publishWapGomeTitle(int id);
	
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);
	
	public List<ErrorTitles> listDownLog(Map<String, Object> paramMap);
	
	public void addErrorWords(List<ErrorTitles> errorTitles);
	
	public void addLog(ImportLog importLog);

	public List<Titles> getByTitleName(String titleName);

	public int findListCountByMap(Map<String, Object> search);

	public int getTitleInvalidInDateCount(String createDate);

	public List<TitleInvalid> getTitleInvalidInDate(String createDate);

	public List<String> getTitleInvalidDate();
}
