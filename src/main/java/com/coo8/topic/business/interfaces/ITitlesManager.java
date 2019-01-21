package com.coo8.topic.business.interfaces; 

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.*;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public interface ITitlesManager{
	 

	public Titles getById(java.lang.Integer id);
	
	   
	public String save(Titles entity);
	
	public void deleteById(java.lang.Integer id);
	//10月22日清表
	public void deletePublish();
	
	//2016/3/1更改titles专题表中的path为"topic/"+path的样式，用于解决专题发布的404问题
	public void changePath();
	
	public void update(Titles entity);
	   
	public PaginatedList<Titles> findByMap(Map<String, Object> search);
	
	public List<Titles> findListByMap(Map<String, Object> search);

	public int publishTitle(int id, String site);
	
	public int publishTitleTest(int id, String site);

	public String checkItemByATG(String id);
	
	public String checkItemName(String id);

	public PaginatedList<Titles> findLikeByMap(Map<String, Object> search);
	
	public List<Titles> findListLikeByMap(Map<String, Object> search);
	
	public List<Integer> findGoodsListByInt(int search);
	
	public String saveDrops(List<GoodsDrops> drops);


	public PaginatedList<Titles> findDropsListByMap(Map<String, Object> search);
	
	public int deleteAllDrops();

	public List<Titles> findAllTitlesList();


	public int deleteDropsByObj(GoodsDrops drops);



	public String getATGItemDescByGoodId(String goodsId);


	public List<Titles> findListByGoodId(String goodsId);
	
	public PaginatedList<TitleIndex> findTitleIndexByMap(Map<String, Object> search);
	
	public TitleIndex getTitleIndexById(int id);

	public TitleIndex getTitleIndexByTitleId(int titleId);
	
	public int isAddRepeat(Map<String, Object> search);
	
	public String insertTitleIndex(TitleIndex entity);
	
	public int updateTitleIndex(TitleIndex entity);
	
	public int deleteTitleIndex(int id);


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
	public int publishWapTitle(int id);
	
	/**
	 * @desc 获取商品主题日志列表
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);
	
	/**
	 * @desc 下载添加失败商品主题列表
	 * @param paramMap
	 * @return
	 */
	public List<ErrorTitles> listDownLog(Map<String, Object> paramMap);
	
	/**
	 * @desc 添加错误商品主题
	 * @param errorAnchorKeyWords
	 */
	public void addErrorWords(List<ErrorTitles> errorTitles);
	
	/**
	 * @desc 添加日志
	 * @param importLog
	 */
	public void addLog(ImportLog importLog);

	/**
	 * 检查上传的专题是否重复
	 */

	public List<Titles> getByTitleName(String titleName);

	/**
	 * 专题上传时直接存入专题索引表中
	 */

	public String insertTitleIndex(Titles titles) ;

	public List<Map<String,Object>> checkIsInvalid(String titleName) ;

	public int getTitleInvalidInDateCount(String createDate);

	public List<TitleInvalid> getTitleInvalidInDate(String createDate);


	public List<String> getTitleInvalidDate();
}
