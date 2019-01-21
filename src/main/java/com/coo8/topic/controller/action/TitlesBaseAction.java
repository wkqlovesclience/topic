/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.model.TitleIndex;
import com.coo8.topic.model.Titles;
import org.gome.search.dubbo.idl.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class TitlesBaseAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(TitlesBaseAction.class);

	protected static final String DEFAULT_SORT_COLUMNS = null; 
	protected static final int COMMAND_CREATE = 0;
	protected static final int COMMAND_EDIT = 1;
	protected static final int STATUS_PUBLISH = 1; //����״̬
	protected static final int STATUS_EDIT    = 2; //������༭״̬
	protected static final String TYPES_KEYWORDS = "1"; //����-�ؼ���
	protected static final String TYPES_TAGS     = "2"; //����-��ǩ
	protected static final String TYPES_LABEL    = "3"; //����-������
	protected ITitlesManager titlesManager;
	protected Random random = new Random();
	
	protected Integer page_index=1;
	
	protected Integer page_size=10;
	protected PaginatedList<Titles> titleList;
	protected Titles titles;

	protected java.lang.Integer id;
	protected java.lang.String paths;
	protected java.lang.String title;
	protected java.lang.String sources;
	protected java.lang.Integer perSource;
	protected java.lang.Integer endSource;
	protected java.lang.String creator;

	protected java.util.Date createTime = null;
	protected java.util.Date updateTime;
	protected java.lang.String checkStat;
	protected java.lang.String editStat;	

	
	
	// add property zhangwujie 2012.8.11 start
	
	protected java.lang.String conditkey;    //��������
	protected java.lang.String conditvalue;  //����ֵ
	protected java.lang.String tempTime;	 //��ѯʱ��
	protected java.lang.Integer idBegin;     //��ѯ����ID�������
	protected java.lang.Integer idEnd;       //��ѯ����ID�ұ�����
	protected java.lang.Integer tempStat = 0;    //��ʱֵ
	
	//add property linchengjun 2013.4.8 start
	protected PaginatedList<TitleIndex> titleIndexList;		//ר�������б�
	protected TitleIndex titleIndex;						//ר������ʵ����
	
	public PaginatedList<TitleIndex> getTitleIndexList() {
		return titleIndexList;
	}

	public void setTitleIndexList(PaginatedList<TitleIndex> titleIndexList) {
		this.titleIndexList = titleIndexList;
	}
	public TitleIndex getTitleIndex() {
		return titleIndex;
	}

	public void setTitleIndex(TitleIndex titleIndex) {
		this.titleIndex = titleIndex;
	}//modified by linchengjun  end

	
	public java.lang.String getConditkey() {
		return conditkey;
	}

	public void setConditkey(java.lang.String conditkey) {
		this.conditkey = conditkey;
	}

	public java.lang.String getConditvalue() {
		return conditvalue;
	}

	public void setConditvalue(java.lang.String conditvalue) {
		this.conditvalue = conditvalue;
	}

	public java.lang.String getTempTime() {
		return tempTime;
	}

	public void setTempTime(java.lang.String tempTime) {
		this.tempTime = tempTime;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
		 
	public void setPaths(java.lang.String value) {
		this.paths = value;
	}
	
	public java.lang.String getPaths() {
		return this.paths;
	}
		 
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
		 
	public void setSources(java.lang.String value) {
		this.sources = value;
	}
	
	public java.lang.String getSources() {
		return this.sources;
	}
		 
	public void setPerSource(java.lang.Integer value) {
		this.perSource = value;
	}
	
	public java.lang.Integer getPerSource() {
		return this.perSource;
	}
		 
	public void setEndSource(java.lang.Integer value) {
		this.endSource = value;
	}
	
	public java.lang.Integer getEndSource() {
		return this.endSource;
	}
		 
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
		 
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Titles getTitles() {
		return titles;
	}

	public void setTitles(Titles titles) {
		this.titles = titles;
	}

	public void setTitlesManager(ITitlesManager titlesManager) {
		this.titlesManager = titlesManager;
	}

	public PaginatedList<Titles> getTitleList() {
		return titleList;
	}

	public void setTitleList(PaginatedList<Titles> titleList) {
		this.titleList = titleList;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.String getCheckStat() {
		return checkStat;
	}

	public void setCheckStat(java.lang.String checkStat) {
		this.checkStat = checkStat;
	}

	public java.lang.Integer getIdBegin() {
		return idBegin;
	}

	public void setIdBegin(java.lang.Integer idBegin) {
		this.idBegin = idBegin;
	}

	public java.lang.Integer getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(java.lang.Integer idEnd) {
		this.idEnd = idEnd;
	}

	public java.lang.String getEditStat() {
		return editStat;
	}

	public void setEditStat(java.lang.String editStat) {
		this.editStat = editStat;
	}

	public java.lang.Integer getTempStat() {
		return tempStat;
	}

	public void setTempStat(java.lang.Integer tempStat) {
		this.tempStat = tempStat;
	}



}
