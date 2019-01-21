/**
 * @author tianyu-ds
 * @date 2013-9-30
 * @project_name topic_trunk
 * @company �����������޹�˾
 * @desc ��ǩ�ĳ־ò�
 */
package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.topic.model.Tag;

import com.coo8.btoc.util.pages.PaginatedList;

public interface ITagDAO
{
	/**
	 * @desc ��ȡ���еı�ǩ����
	 * @return
	 */
	public List<Tag> all();
	
	/**
	 * @desc ��ӱ�ǩ
	 * @param tag
	 */
	public void add(Tag tag);
	
	/**
	 * @desc ɾ����ǩ
	 * @param id
	 */
	public void delete(List<Integer> ids);
	
	/**
	 * @desc ��ȡĳ����ǩ
	 * @param id
	 */
	public Tag getById(Integer id);
	
	/**
	 * @desc ��ȡ�ӱ�ǩ�б�
	 * @param parentId
	 */
	public List<Tag> getByParentId(Integer parentId);
	
	/**
	 * @desc ��ȡ�ӱ�ǩ�б�
	 * @param paramMap
	 */
	public PaginatedList<Tag> getByParentId(Map<String, Object> paramMap);
	
	/**
	 * @desc ��ȡ�����ӱ�ǩ�б�
	 */
	public List<Tag> getAllFirstTag();
	
	/**
	 * @desc ��ȡ�����ӱ�ǩ�б�
	 */
	public PaginatedList<Tag> getChildren(Map<String, Object> paramMap);
	
	/**
	 * @desc ��ȡ��ǩ�б�
	 * @param paramMap
	 */
	public PaginatedList<Tag> list(Map<String, Object> paramMap);
	
	/**
	 * @desc ��ȡ��ǩ�б�����
	 * @param paramMap
	 */
	public Integer count(Map<String, Object> paramMap);
	
	/**
	 * @desc �޸ı�ǩ
	 * @param tag
	 */
	public void update(Tag tag);
	
	/**
	 * ������ǩ�¶�Ӧ���Ѵʵ�����
	 */
	public Integer productCountOfSubtag(Integer subtagId);
	
	/**
	 * һ����ǩ���������еĶ�����ǩ����Ŀ
	 */
	public Integer subtagCountOfFirstTag(Integer tagId);
	
	/**
	 * ͨ��������ǩID+���ƻ�ȡ�ض�����
	 */
	public Tag getTagFromExcel(String tagName);
}
