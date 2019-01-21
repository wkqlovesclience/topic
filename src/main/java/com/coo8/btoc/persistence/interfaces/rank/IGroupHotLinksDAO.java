/**
 * 
 */
package com.coo8.btoc.persistence.interfaces.rank;

import java.util.Map;

import com.coo8.btoc.model.rank.GroupHotLinks;
import com.coo8.btoc.util.pages.PaginatedList;

/**
 * @author wangfufu
 *
 */
public interface IGroupHotLinksDAO {
	/*
	 * ��ѯ���а���������
	 */
	PaginatedList<GroupHotLinks> selectAllGroupHotLinks(Map<String, Object> search);

	/*
	 * ���ݷ���id ��ѯ
	 */
	GroupHotLinks selectGroupHotLinksById(Integer id);

	/*
	 * �����������
	 */
	int save(GroupHotLinks entity);

	/*
	 * ɾ������������Ϣ
	 */
	int deleteByGroupHotLinksId(Integer id);

	/*
	 * �޸�������Ϣ
	 */
	int updateGroupHotLinksById(GroupHotLinks entity);
}
