package com.coo8.btoc.business.interfaces.rank;

import java.util.Map;
import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.util.pages.PaginatedList;

public interface IIndexFloorManager {
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
