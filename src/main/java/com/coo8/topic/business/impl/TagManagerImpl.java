package com.coo8.topic.business.impl;

import com.coo8.topic.business.interfaces.ITagManager;

import com.coo8.topic.model.Tag;
import com.coo8.topic.persistence.interfaces.ITagDAO;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;

public class TagManagerImpl implements ITagManager
{
	private ITagDAO tagDao;

	@Override
	public List<Tag> all()
	{
		return tagDao.all();
	}

	@Transactional
	@Override
	public void add(Tag tag)
	{
		tagDao.add(tag);
	}

	@Transactional
	@Override
	public void update(Tag tag)
	{
		tagDao.update(tag);
	}

	@Transactional
	@Override
	public void delete(List<Integer> ids)
	{
		tagDao.delete(ids);
	}

	@Override
	public Tag getById(Integer id)
	{
		return tagDao.getById(id);
	}

	@Override
	public PaginatedList<Tag> getByParentId(Map<String, Object> paramMap)
	{
		return tagDao.getByParentId(paramMap);
	}

	@Override
	public List<Tag> getByParentId(Integer parentId)
	{
		return tagDao.getByParentId(parentId);
	}

	@Override
	public List<Tag> getAllFirstTag()
	{
		return tagDao.getAllFirstTag();
	}

	@Override
	public PaginatedList<Tag> getChildren(Map<String, Object> paramMap)
	{
		return tagDao.getChildren(paramMap);
	}

	@Override
	public PaginatedList<Tag> list(Map<String, Object> paramMap)
	{
		return tagDao.list(paramMap);
	}

	@Override
	public Integer count(Map<String, Object> paramMap)
	{
		return null;
	}

	public ITagDAO getTagDao()
	{
		return tagDao;
	}

	public void setTagDao(ITagDAO tagDao)
	{
		this.tagDao = tagDao;
	}

	@Override
	public Integer productCountOfSubtag(Integer subtagId) {
		return tagDao.productCountOfSubtag(subtagId);
	}

	@Override
	public Integer subtagCountOfFirstTag(Integer tagId) {
		return tagDao.subtagCountOfFirstTag(tagId);
	}

	@Override
	public Tag getTagFromExcel(String tagName) {
		return tagDao.getTagFromExcel(tagName);
	}

}
