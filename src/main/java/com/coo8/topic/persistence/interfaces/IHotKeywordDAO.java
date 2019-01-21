/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 热词数据持久层接口
 */
package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.ErrorHotKeyword;
import com.coo8.topic.model.HotKeyword;
import com.coo8.topic.model.ImportLog;

public interface IHotKeywordDAO {
	/**
	 * @desc 添加热词
	 * @param hotKeyWord
	 */
	public void add(List<HotKeyword> hotKeyWords);

	/**
	 * @desc 添加错误热词
	 * @param errorHotKeyWords
	 */
	public void addErrorWords(List<ErrorHotKeyword> errorHotKeyWords);

	/**
	 * @desc 添加日志
	 * @param importLog
	 */
	public void addLog(ImportLog importLog);

	/**
	 * @desc 删除热词
	 * @param id
	 */
	public void delete(List<Integer> ids);

	/**
	 * @desc 获取某个热词
	 * @param id
	 */
	public HotKeyword getById(Integer id);

	/**
	 * @desc 获取热词列表
	 * @param paramMap
	 */
	public PaginatedList<HotKeyword> list(Map<String, Object> paramMap);

	/**
	 * @desc 获取不分页热词列表
	 * @param paramMap
	 */
	public List<HotKeyword> listAll(Map<String, Object> paramMap);

	/**
	 * @desc 获取热词日志列表
	 * @param paramMap
	 */
	public PaginatedList<ImportLog> listLog(Map<String, Object> paramMap);

	/**
	 * @desc 获取热词范围
	 * @param paramMap
	 */
	public List<HotKeyword> getByRangeId(Map<String, Object> paramMap);

	/**
	 * @desc 下载添加失败热词列表
	 * @param paramMap
	 * @return
	 */
	public List<ErrorHotKeyword> listDownLog(Map<String, Object> paramMap);

	/**
	 * @desc 获取热词列表总数
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);

	/**
	 * @desc 修改热词
	 * @param hotKeyWord
	 */
	public void update(HotKeyword hotKeyWord);

	/**
	 * @desc 发布热词
	 * @param hotKeyWord
	 */
	public void publish(HotKeyword hotKeyWord);

	/**
	 * 获取最新插入的热词ID
	 */
	public int getLastedInsertId();

	/**
	 * 发布程序：预览
	 */
	public int publishGomeHotWordsTest(int id);

	/**
	 * 发布程序：发布存储过程的调用
	 */
	public int publishGomeHotWords(int id);

	/**
	 * 发布程序：发布存储过程的调用
	 */
	public int publishWapGomeHotWords(int id);

	public List<HotKeyword> selectHotKeywordlist(Map<String, Object> params);
}
