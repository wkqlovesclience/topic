/**
 * @author tianyu-ds
 * @date 2013-9-30
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 标签的持久层
 */
package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.topic.model.Tag;

import com.coo8.btoc.util.pages.PaginatedList;

public interface ITagDAO
{
	/**
	 * @desc 获取所有的标签对象
	 * @return
	 */
	public List<Tag> all();
	
	/**
	 * @desc 添加标签
	 * @param tag
	 */
	public void add(Tag tag);
	
	/**
	 * @desc 删除标签
	 * @param id
	 */
	public void delete(List<Integer> ids);
	
	/**
	 * @desc 获取某个标签
	 * @param id
	 */
	public Tag getById(Integer id);
	
	/**
	 * @desc 获取子标签列表
	 * @param parentId
	 */
	public List<Tag> getByParentId(Integer parentId);
	
	/**
	 * @desc 获取子标签列表
	 * @param paramMap
	 */
	public PaginatedList<Tag> getByParentId(Map<String, Object> paramMap);
	
	/**
	 * @desc 获取所有子标签列表
	 */
	public List<Tag> getAllFirstTag();
	
	/**
	 * @desc 获取所有子标签列表
	 */
	public PaginatedList<Tag> getChildren(Map<String, Object> paramMap);
	
	/**
	 * @desc 获取标签列表
	 * @param paramMap
	 */
	public PaginatedList<Tag> list(Map<String, Object> paramMap);
	
	/**
	 * @desc 获取标签列表总数
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);
	
	/**
	 * @desc 修改标签
	 * @param tag
	 */
	public void update(Tag tag);
	
	/**
	 * 二级标签下对应热搜词的数量
	 */
	public Integer productCountOfSubtag(Integer subtagId);
	
	/**
	 * 一级标签下面所含有的二级标签的数目
	 */
	public Integer subtagCountOfFirstTag(Integer tagId);
	
	/**
	 * 通过二级标签ID+名称获取特定数据
	 */
	public Tag getTagFromExcel(String tagName);
}
