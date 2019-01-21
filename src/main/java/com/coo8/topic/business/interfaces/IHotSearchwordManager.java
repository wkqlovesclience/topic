package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotSearchword;
import com.coo8.topic.model.HotSearchword;
import com.coo8.topic.model.ImportSearchLog;

/**
 * 搜索热词管理
 * 
 * @author fanqingxia
 *
 */
public interface IHotSearchwordManager {
	/**
	 * @desc 添加热词
	 * @param hotSearchword
	 */
	public void add(List<HotSearchword> hotSearchword);

	/**
	 * @desc 添加日志
	 * @param importLog
	 */
	public void addLog(ImportSearchLog importSearchLog);

	/**
	 * @desc 删除热词
	 * @param id
	 */
	public void delete(List<Integer> ids);

	/**
	 * @desc 添加错误热词
	 * @param errorHotKeyWords
	 */
	public void addErrorWords(List<ErrorHotSearchword> errorHotSearchWords);

	/**
	 * @desc 获取某个热词
	 * @param id
	 */
	public HotSearchword getById(Integer id);

	/**
	 * @desc 获取热词范围
	 * @param paramMap
	 */
	public List<HotSearchword> getByRangeId(Map<String, Object> paramMap);

	/**
	 * @desc 获取热词列表
	 * @param paramMap
	 */
	public PaginatedList<HotSearchword> list(Map<String, Object> paramMap);

	/**
	 * @desc 获取不分页热词列表
	 * @param paramMap
	 */
	public List<HotSearchword> listAll(Map<String, Object> paramMap);

	/**
	 * @desc 下载添加失败热词列表
	 * @param paramMap
	 * @return
	 */
	public List<ErrorHotSearchword> listDownLog(Map<String, Object> paramMap);

	/**
	 * @desc 获取热词日志列表
	 * @param paramMap
	 */
	public PaginatedList<ImportSearchLog> listLog(Map<String, Object> paramMap);

	/**
	 * @desc 获取热词列表总数
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);

	/**
	 * @desc 修改热词
	 * @param HotSearchword
	 */
	public void update(HotSearchword HotSearchword);

	/**
	 * @desc 发布热词
	 * @param HotSearchword
	 */
	public void publish(HotSearchword HotSearchword);

	/**
	 * 获取最新插入的热词ID
	 */
	public int getLastedInsertId();

	/**
	 * 非模糊查询热词
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<HotSearchword> listHotSearch(Map<String, Object> paramMap);

	/**
	 * 查询相关热搜词
	 * 
	 * @param map
	 * @return
	 */
	public List<HotSearchword> getHotkeyRelatedKeywords(Map<String, Object> map);

	/**
	 * 初始化相关热词
	 */
	public void initReatedHotWords() throws Exception;
	
	/**
	 * 按条件查询指定数量、指定状态 热词
	 * @param paramMap
	 * @return
	 */
	List<Integer> listLimit(Map<String, Object> paramMap);
}
