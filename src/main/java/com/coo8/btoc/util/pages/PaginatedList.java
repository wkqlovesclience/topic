/*
 * �ļ����� PaginatedList.java
 * 
 * �������ڣ� Apr 28, 2010
 *
 * Copyright(C) 2010, by caowei.
 *
 * ԭʼ����: <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 *
 */
package com.coo8.btoc.util.pages;

import java.util.List;

/**
 * 
 * �������б���Ҫ�����ڲ�ѯ���ݿ�ʱ�����ؽ��Ҫ��ҳ����
 * 
 * @author <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 * 
 * @version $Revision$
 * 
 * @since Apr 28, 2010
 */
public interface PaginatedList<V> extends List<V> {
	/**
	 * ȡ��ÿҳ�ļ�¼��
	 * 
	 * @return ��ҳ��¼��
	 */
	int getPageSize();

	/**
	 * ȡ�ù��ж���ҳ
	 * 
	 * @return ��ҳ��
	 */
	int getTotalPage();

	/**
	 * ȡ���ܼ�¼��
	 * 
	 * @return �ܼ�¼��
	 */
	int getTotalRec();

	/**
	 * ȡ�õ�ǰҳ
	 * 
	 * @return ��ǰҳ
	 */
	int getPageIndex();

	/**
	 * �жϵ�ǰҳ�ǲ��ǵ�һҳ
	 * 
	 * @return ���
	 */
	boolean isFirstPage();

	/**
	 * �жϵ�ǰҳ�ǲ������һҳ
	 * 
	 * @return ���
	 */
	boolean isLastPage();

	/**
	 * ���ϼ�¼��Χ�Ŀ�ʼλ��
	 * 
	 * @return λ��
	 */
	int getStartPos();

	/**
	 * ���ϼ�¼��Χ�Ľ���λ��
	 * 
	 * @return ����λ��
	 */
	int getEndPos();
	
	/**
	 * @return ҳ����ʾҳ�ĵ�һҳ
	 */
	int getBegin();
	/**
	 * 
	 * @return ҳ����ʾҳ�����һҳ
	 */
	int getEnd();
	
	/**
	 * Ĭ��ҳ��С
	 */
	public static final int DEFAULT_PAGE_SIZE = 40;
	
	
	/**
	 * �����Խ�������Struts���ʹ��
	 */
	public static final String COMMONLIST_TAG_KEY = "paginatedlist_tag_key";
	
	/**
	 * ÿ��ҳ��ֻ��ʾ5 ���� 1 2 3 4 5 
	 */
	public static final int DEFAULT_DISPALY_PAGE_COUNT=10;
}
