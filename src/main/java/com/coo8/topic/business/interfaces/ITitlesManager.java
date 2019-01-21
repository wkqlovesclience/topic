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
	//10��22�����
	public void deletePublish();
	
	//2016/3/1����titlesר����е�pathΪ"topic/"+path����ʽ�����ڽ��ר�ⷢ����404����
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
	 * Wapר��ϵͳ��ҳ����
	 * 
	 * @return
	 */
	public int publicWapTitleHomePage();
	
	/**
	 * Wapר��ҳ����
	 * 
	 * @param id
	 * @return
	 */
	public int publishWapTitle(int id);
	
	/**
	 * @desc ��ȡ��Ʒ������־�б�
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);
	
	/**
	 * @desc �������ʧ����Ʒ�����б�
	 * @param paramMap
	 * @return
	 */
	public List<ErrorTitles> listDownLog(Map<String, Object> paramMap);
	
	/**
	 * @desc ��Ӵ�����Ʒ����
	 * @param errorAnchorKeyWords
	 */
	public void addErrorWords(List<ErrorTitles> errorTitles);
	
	/**
	 * @desc �����־
	 * @param importLog
	 */
	public void addLog(ImportLog importLog);

	/**
	 * ����ϴ���ר���Ƿ��ظ�
	 */

	public List<Titles> getByTitleName(String titleName);

	/**
	 * ר���ϴ�ʱֱ�Ӵ���ר����������
	 */

	public String insertTitleIndex(Titles titles) ;

	public List<Map<String,Object>> checkIsInvalid(String titleName) ;

	public int getTitleInvalidInDateCount(String createDate);

	public List<TitleInvalid> getTitleInvalidInDate(String createDate);


	public List<String> getTitleInvalidDate();
}
