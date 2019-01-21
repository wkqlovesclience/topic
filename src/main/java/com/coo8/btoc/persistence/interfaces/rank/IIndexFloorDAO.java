package com.coo8.btoc.persistence.interfaces.rank;

import java.util.Map;

import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.util.pages.PaginatedList;


/**
 * @author ������
 * @date 2017-6-2
 * @project_name topic_trunk
 * @company �����������޹�˾
 * @desc
 */
public interface IIndexFloorDAO {
	/*
	 * ��ѯ������ҳ¥�����
	 */
	PaginatedList<IndexFloor> selectAllIndexFloor(Map<String, Object> search);

	/*
	 * ���ݷ���id ��ѯ������Ϣ
	 */
	IndexFloor selectIndexFloorById(Integer id);

	/*
	 * ���¥�������Ϣ
	 */
	int save(IndexFloor entity);

	/*
	 * ɾ��¥�������Ϣ
	 */
	int deleteByIndexFloorId(Integer id);

	/*
	 * �޸�¥�������Ϣ
	 */
	int updateIndexFloorById(IndexFloor entity);
  
}
