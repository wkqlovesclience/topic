/*
 * �ļ����� PaginatedArrayList.java
 * 
 * �������ڣ� Apr 28, 2010
 *
 * Copyright(C) 2010, by caowei.
 *
 * ԭʼ����: <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 *
 */
package com.coo8.btoc.util.pages;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * �������б���Ҫ�����ڲ�ѯ���ݿ�ʱ�����ؽ��Ҫ��ҳ����
 * 
 * @author <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 * 
 * @version $Revision$
 * 
 * @since Apr 28, 2010
 */
@SuppressWarnings("serial")
public final class PaginatedArrayList<V> extends ArrayList<V> implements
		PaginatedList<V> {
	 private  static Logger logger = LoggerFactory.getLogger(PaginatedArrayList.class);
	 protected static final EmptyList EMPTY_LIST= new EmptyList();
	
	/**
	 * ��ǰҳ��
	 */
	private int pageIndex = 1;

	/**
	 * ÿҳ��ʾ��¼��������ֵС��0ʱ��Ӧ���Ƿ������м�¼
	 */
	private int pageSize = 20;

	/**
	 * ��ҳ��
	 */
	private int totalPage;

	/**
	 * �ܼ�¼��
	 */
	private int totalRec;

	/**
	 * ��ʼ��¼��
	 */
	private int startPos;

	/**
	 * ������¼��
	 */
	private int endPos;

	/**
	 * ��ʾҳ�ĵ�һҳ
	 */
	private int begin;
	/**
	 * ��ʾҳ�����һҳ
	 */
	private int end;
	
	/**
	 * ͨ���ܼ�¼����ÿҳ��Ŀ��ҳ��С������ʵ����ͬʱ��������������ֵ��<br>
	 * ------------------------------------------------------------------------
	 * --<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2003-1-9<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * ------------------------------------------------------------------------
	 * --
	 * 
	 * @param pageIndex
	 *            ��ǰҳ
	 * @param pageSize
	 *            ҳ��С
	 * @param totalRec
	 *            �ܼ�¼��
	 * @roseuid 40B2F47B0219
	 */
	public PaginatedArrayList(int totalRec, int pageIndex, int pageSize) {
		super(pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
		calculate(totalRec, pageIndex, pageSize, DEFAULT_DISPALY_PAGE_COUNT);
	}

	/**
	 * 
	 * @param pageIndex
	 *            ��ǰҳ
	 * @param pageSize
	 *            ҳ��С
	 * @param totalRec
	 *            �ܼ�¼��
	 * @roseuid 40B2F47B0219
	 * @param displayPageSize
	 *            ����ʾ����ҳ
	 */
	public PaginatedArrayList(int totalRec, int pageIndex, int pageSize,
			int displayPageSize) {
		super(pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
		if (displayPageSize < 0) {
			displayPageSize = DEFAULT_DISPALY_PAGE_COUNT;
		}
		calculate(totalRec, pageIndex, pageSize, displayPageSize);
	}

	public void setPageInfo(int totalRec, int pageIndex, int pageSize) {
		calculate(totalRec, pageIndex, pageSize, DEFAULT_DISPALY_PAGE_COUNT);
	}

	/**
	 * ͨ���ܼ�¼������ǰҳ��ÿҳ��¼����������������<br>
	 * ------------------------------------------------------------------------
	 * --<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2003-1-9<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * ------------------------------------------------------------------------
	 * --
	 * 
	 * @param recNum
	 *            �ܼ�¼��
	 * @param pageIndex
	 *            ��ǰҳ
	 * @param pageSize
	 *            ҳ��С
	 * @roseuid 40B2F47B0238
	 */
	private void calculate(int recNum, int pageIndex, int pageSize,
			int displayPageSize) {
		// ---------------------------------
		// �ܼ�¼��С��1û���κ��κμ������塣
		// ---------------------------------
		if (recNum < 1) {
			this.begin = 1;
			this.end = 1;
			return;
		}

		// ---------------------------------
		// ҳ��СΪ0��ʱ��������
		// ---------------------------------
		if (pageSize == 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		// ---------------------------------
		// ��ǰҳС��1��ʱ��Ĭ��Ϊ��1ҳ
		// ---------------------------------
		if (pageIndex < 1)
			pageIndex = 1;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalRec = recNum;

		if (pageSize > 0) {
			// ---------------------------------
			// ��ҳ��=�ܼ�¼/ҳ��С������������Ҫ��һλ
			// ---------------------------------
			if (recNum % pageSize > 0)
				totalPage = recNum / pageSize + 1;
			else
				totalPage = recNum / pageSize;

			// ---------------------------------
			// ��ǰҳ���ܴ�����ҳ����
			// ---------------------------------
			if (pageIndex > totalPage)
				this.pageIndex = totalPage;
			if (this.pageIndex < 1)
				this.pageIndex = 1;
			// --------------------------------------
			// �������ʾ�ĵ�һҳ���һҳ��ֵ
			// --------------------------------------
			boolean flag = displayPageSize % 2 == 0;
			int mid = flag ? displayPageSize / 2 : displayPageSize / 2 + 1;
			if (this.pageIndex <= mid) {
				this.begin = 1;
				this.end = displayPageSize >= this.totalPage ? this.totalPage
						: displayPageSize;
				this.end=this.end<=this.totalPage?this.end:this.totalPage;
			} else if (this.pageIndex >= this.totalPage - (mid - 1)) {
				this.end = this.totalPage;
				this.begin = 1 > this.totalPage - (mid - 1) ? 1
						: this.totalPage - (displayPageSize - 1);
				this.begin=this.begin>0?this.begin:1;
			} else {
				if (flag) {
					this.begin = this.pageIndex - (mid - 1);
					this.end = this.pageIndex + mid;
				} else {
					this.begin = this.pageIndex - (mid - 1);
					this.end = this.pageIndex + (mid - 1);
				}
			}

			// ---------------------------------
			// �������ݿ��¼�Ŀ�ʼ���ͽ�������
			// ---------------------------------
			startPos = (this.pageIndex - 1) * pageSize;
			endPos = this.pageIndex * pageSize;
		} else {
			// ---------------------------------
			// ҳ��СС��0��ʱ��Ӧ�������м�¼��
			// ---------------------------------
			startPos = 0;
			endPos = recNum;
			this.begin = 1;
			this.end = 1;
		}

	}

	/**
	 * ȡ��ÿҳ�ļ�¼��
	 * 
	 * @return ��ҳ��¼��
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ȡ�ù��ж���ҳ
	 * 
	 * @return ��ҳ��
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * ȡ���ܼ�¼��
	 * 
	 * @return �ܼ�¼��
	 */
	public int getTotalRec() {
		return totalRec;
	}

	/**
	 * ȡ�õ�ǰҳ
	 * 
	 * @return ��ǰҳ
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * �жϵ�ǰҳ�ǲ��ǵ�һҳ
	 * 
	 * @return ���
	 */
	public boolean isFirstPage() {
		return pageIndex == 1;
	}

	/**
	 * �жϵ�ǰҳ�ǲ������һҳ
	 * 
	 * @return ���
	 */
	public boolean isLastPage() {
		return pageIndex == totalPage;
	}

	/**
	 * ���ϼ�¼��Χ�Ŀ�ʼλ��
	 * 
	 * @return λ��
	 */
	public int getStartPos() {
		return startPos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coo8.btoc.util.pages.PaginatedList#getBegin()
	 */
	@Override
	public int getBegin() {
		return this.begin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coo8.btoc.util.pages.PaginatedList#getEnd()
	 */
	@Override
	public int getEnd() {
		return this.end;
	}

	/**
	 * ���ϼ�¼��Χ�Ľ���λ��
	 * 
	 * @return ����λ��
	 */
	public int getEndPos() {
		return endPos;
	}

	/**
	 * ��ʾ��ҳ��־<br>
	 * ����գ� getPage(CommonList,int,String)����<br>
	 * ------------------------------------------------------------------------
	 * --<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2003-1-9<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * ------------------------------------------------------------------------
	 * --
	 * 
	 * @param cl
	 *            �б�
	 * @return ���
	 * @roseuid 40B2F47B0249
	 */
	public static String getPage(PaginatedList cl) {
		return getPage(cl, 10, null);
	}

	/**
	 * ��ʾ��ҳ��־<br>
	 * ����գ� getPage(CommonList,int,String)����<br>
	 * ------------------------------------------------------------------------
	 * --<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2003-1-9<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * ------------------------------------------------------------------------
	 * --
	 * 
	 * @param cl
	 *            �б�
	 * @param pageNum
	 *            ��ʾ����Ŀ
	 * @return ���
	 * @roseuid 40B2F47B0267
	 */
	public static String getPage(PaginatedList cl, int pageNum) {
		return getPage(cl, pageNum, null);
	}

	/**
	 * ��ʾ��ҳ��־<br>
	 * ��ʾ��ҳ��־���˷��������Ľ��Ҫ��f.js���ʹ�á�<br>
	 * ���ؽ����������������<br>
	 * &lt;a href="JavaScript:tunePage(0,'pageNo')">|&lt;&lt;/a> &lt;a
	 * href="JavaScript:tunePage(90,'pageNo')">&lt;&lt;/a> &lt;a
	 * href="JavaScript:tunePage(0,'pageNo')">...&lt;/a> &lt;a
	 * href="JavaScript:tunePage(100,'pageNo')">&lt;font
	 * color='#FF0000'>&lt;b>11&lt;/b>&lt;/font>&lt;/a> &lt;a
	 * href="JavaScript:tunePage(110,'pageNo')">12&lt;/a> .......... &lt;a
	 * href="JavaScript:tunePage(190,'pageNo')">20&lt;/a> &lt;a
	 * href="JavaScript:tunePage(200,'pageNo')">...&lt;/a> &lt;a
	 * href="JavaScript:tunePage(110,'pageNo')">&gt;&lt;/a> &lt;a
	 * href="JavaScript:tunePage(290,'pageNo')">&gt;|&lt;/a><br>
	 * ��ʾ���ս��Ӧ����������<br>
	 * |&lt; &lt; ... 11 -- 20. ... > >|<br>
	 * ------------------------------------------------------------------------
	 * --<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2003-1-9<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * ------------------------------------------------------------------------
	 * --
	 * 
	 * @param cl
	 *            �б�
	 * @param pageNum
	 *            ÿҳ�п��Է�ҳ����Ŀ
	 * @param strPage
	 *            ��ҳ����
	 * @return ���
	 * @roseuid 40B2F47B0286
	 */
	public static String getPage(PaginatedList cl, int pageNum, String strPage) {
		// ---------------------------------
		// ���巵�ؽ������
		// ---------------------------------
		String rValue = null;
		try {
			if (cl != null) {
				// ---------------------------------
				// ���б�Ϊnull����Ч
				// ---------------------------------
				StringBuilder buffer = new StringBuilder();

				// ---------------------------------
				// strPage����Ϊ�ա�js��Ĭ��ΪpageNo
				// ---------------------------------
				strPage = strPage != null && strPage.length() > 0 ? ",\""
						+ strPage + "\"" : "";

				// ---------------------------------
				// Ĭ��Ϊÿҳ��ʾ10���ɷ�ҳ�����֡�
				// ---------------------------------
				if (pageNum < 1)
					pageNum = 10;
				// ---------------------------------
				// �����ܼ�¼������ǰҳ����ҳ����ǩ
				// ---------------------------------
				buffer.append("����[<font color='#FF0000'>" + cl.getTotalRec()
						+ "</font>]����¼��" + cl.getPageIndex() + "/"
						+ cl.getTotalPage() + "ҳ��");
				if (cl.getPageIndex() > 1) {
					// ---------------------------------
					// ��ǰҳ���ǵ�һҳ��
					// ��Ҫ�����һҳ����һҳ��ǩ
					// ---------------------------------
					buffer
							.append(" <a href='JavaScript:tunePage(1"
									+ strPage
									+ ")'>|&lt;</a> <a href='JavaScript:tunePage("
									+ (cl.getPageIndex() - 1) + strPage + ")'>&lt;</a>");
				}

				// ---------------------------------
				// �����ǰҳ����ÿҳ��ʾҳ������
				// ��Ҫ��ʾ�������Ϸ��ı�ǩ(��ǩ�ǣ�...)
				// ---------------------------------
				int currentNum = (cl.getPageIndex() % pageNum == 0 ? (cl
						.getPageIndex() / pageNum) - 1 : (int) (cl
						.getPageIndex() / pageNum))
						* pageNum;
				if (currentNum < 0)
					currentNum = 0;
				if (cl.getPageIndex() > pageNum) {
					buffer
							.append(" <a href='JavaScript:tunePage("
									+ (currentNum - pageNum + 1) + strPage + ")'>...</a>");
				}

				// ---------------------------------
				// ��ʾ�м���õķ�ҳ��
				// ---------------------------------
				for (int i = 0; i < pageNum; i++) {
					if ((currentNum + i + 1) > cl.getTotalPage()
							|| cl.getTotalPage() < 2)
						break;
					buffer
							.append(" <a href='JavaScript:tunePage("
									+ (currentNum + i + 1)
									+ strPage
									+ ")'>"
									+ (currentNum + i + 1 == cl.getPageIndex() ? "<font color='#FF0000'><b>"
											+ (currentNum + i + 1)
											+ "</b></font>"
											: (currentNum + i + 1) + "") + "</a>");
				}

				// ---------------------------------
				// �����δ�������һ�棬
				// ��Ҫ����������·��ı�ǩ(��ǩ�ǣ�...)
				// ---------------------------------
				if (cl.getTotalPage() > (currentNum + pageNum))
					buffer
							.append(" <a href='JavaScript:tunePage("
									+ (currentNum + 1 + pageNum) + strPage + ")'>...</a>");

				// ---------------------------------
				// �����ǰҳ�������һҳ��
				// ��Ҫ������һҳ�����һҳ����
				// ---------------------------------
				if (cl.getPageIndex() < cl.getTotalPage()) {
					buffer.append(" <a href='JavaScript:tunePage("
							+ (cl.getPageIndex() + 1) + strPage
							+ ")'>&gt;</a> <a href='JavaScript:tunePage("
							+ cl.getTotalPage() + strPage + ")'>&gt;|</a>");
				}
				rValue = buffer.toString();
				// ---------------------------------
				// ��ʽ�ͷ���Դ
				// ---------------------------------
				buffer.setLength(0);
				buffer = null;
			} else {
				rValue = "";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rValue = "";
		}
		cl = null;
		return rValue;
	}

	public static String getAjaxPage(PaginatedList cl) {
		return getAjaxPage(cl, 10, null, null);
	}

	public static String getAjaxPage(PaginatedList cl, String funcName) {
		if (funcName == null || funcName.length() == 0)
			return getAjaxPage(cl, cl.getPageSize(), null, null);
		else
			return getAjaxPage(cl, cl.getPageSize(), null, funcName);
	}

	public static String getAjaxPage(PaginatedList cl, int pageNum,
			String strPage, String funcName) {
		// ---------------------------------
		// ���巵�ؽ������
		// ---------------------------------

		String rValue = null;

		if (funcName == null || funcName.length() == 0)
			funcName = "tuneAjaxPage";

		try {
			if (cl != null) {
				// ---------------------------------
				// ���б�Ϊnull����Ч
				// ---------------------------------
				StringBuilder buffer = new StringBuilder();

				// ---------------------------------
				// strPage����Ϊ�ա�js��Ĭ��ΪpageNo
				// ---------------------------------
				strPage = strPage != null && strPage.length() > 0 ? ",\""
						+ strPage + "\"" : "";

				// ---------------------------------
				// Ĭ��Ϊÿҳ��ʾ10���ɷ�ҳ�����֡�
				// ---------------------------------
				if (pageNum < 1)
					pageNum = 10;
				// ---------------------------------
				// �����ܼ�¼������ǰҳ����ҳ����ǩ
				// ---------------------------------
				buffer.append("����[<font color='#FF0000'>" + cl.getTotalRec()
						+ "</font>]����¼��" + cl.getPageIndex() + "/"
						+ cl.getTotalPage() + "ҳ��");
				if (cl.getPageIndex() > 1) {
					// ---------------------------------
					// ��ǰҳ���ǵ�һҳ��
					// ��Ҫ�����һҳ����һҳ��ǩ
					// ---------------------------------
					buffer.append(" <a href='JavaScript:"
									+ funcName
									+ "(1"
									+ strPage
									+ ","
									+ pageNum
									+ ")'><font face='Webdings'>9</font></a> <a href='JavaScript:"
									+ funcName + "(" + (cl.getPageIndex() - 1)
									+ strPage + "," + pageNum + ")'><font face='Webdings'>7</font></a>");
				}

				// ---------------------------------
				// �����ǰҳ����ÿҳ��ʾҳ������
				// ��Ҫ��ʾ�������Ϸ��ı�ǩ(��ǩ�ǣ�...)
				// ---------------------------------
				int currentNum = (cl.getPageIndex() % pageNum == 0 ? (cl
						.getPageIndex() / pageNum) - 1 : (int) (cl
						.getPageIndex() / pageNum))
						* pageNum;
				if (currentNum < 0)
					currentNum = 0;
				if (cl.getPageIndex() > pageNum) {
					buffer.append(" <a href='JavaScript:" + funcName + "("
							+ (currentNum - pageNum + 1) + strPage + ","
							+ pageNum + ")'>...</a>");
				}

				// ---------------------------------
				// ��ʾ�м���õķ�ҳ��
				// ---------------------------------
				for (int i = 0; i < pageNum; i++) {
					if ((currentNum + i + 1) > cl.getTotalPage()
							|| cl.getTotalPage() < 2)
						break;
					buffer
							.append(" <a href='JavaScript:"
									+ funcName
									+ "("
									+ (currentNum + i + 1)
									+ strPage
									+ ","
									+ pageNum
									+ ")'>"
									+ (currentNum + i + 1 == cl.getPageIndex() ? "<font color='#FF0000'><b>"
											+ (currentNum + i + 1)
											+ "</b></font>"
											: (currentNum + i + 1) + "") + "</a>");
				}

				// ---------------------------------
				// �����δ�������һ�棬
				// ��Ҫ����������·��ı�ǩ(��ǩ�ǣ�...)
				// ---------------------------------
				if (cl.getTotalPage() > (currentNum + pageNum))
					buffer.append(" <a href='JavaScript:" + funcName + "("
							+ (currentNum + 1 + pageNum) + strPage + ","
							+ pageNum + ")'>...</a>");

				// ---------------------------------
				// �����ǰҳ�������һҳ��
				// ��Ҫ������һҳ�����һҳ����
				// ---------------------------------
				if (cl.getPageIndex() < cl.getTotalPage()) {
					buffer
							.append(" <a href='JavaScript:"
									+ funcName
									+ "("
									+ (cl.getPageIndex() + 1)
									+ strPage
									+ ","
									+ pageNum
									+ ")'><font face='Webdings'>8</font></a> <a href='JavaScript:"
									+ funcName + "(" + cl.getTotalPage()
									+ strPage + "," + pageNum + ")'><font face='Webdings'>:</font></a>");
				}
				rValue = buffer.toString();
				// ---------------------------------
				// ��ʽ�ͷ���Դ
				// ---------------------------------
				buffer.setLength(0);
				buffer = null;
			} else {
				rValue = "";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rValue = "";
		}
		cl = null;
		return rValue;
	}

	public static String getAjaxPage(PaginatedList cl, int pageNum,
			String strPage) {
		// ---------------------------------
		// ���巵�ؽ������
		// ---------------------------------

		String rValue = null;

		try {
			if (cl != null) {
				// ---------------------------------
				// ���б�Ϊnull����Ч
				// ---------------------------------
				StringBuilder buffer = new StringBuilder();

				// ---------------------------------
				// strPage����Ϊ�ա�js��Ĭ��ΪpageNo
				// ---------------------------------
				strPage = strPage != null && strPage.length() > 0 ? ",\""
						+ strPage + "\"" : "";

				// ---------------------------------
				// Ĭ��Ϊÿҳ��ʾ10���ɷ�ҳ�����֡�
				// ---------------------------------
				if (pageNum < 1)
					pageNum = 10;
				// ---------------------------------
				// �����ܼ�¼������ǰҳ����ҳ����ǩ
				// ---------------------------------
				buffer.append("����[<font color='#FF0000'>" + cl.getTotalRec()
						+ "</font>]����¼��" + cl.getPageIndex() + "/"
						+ cl.getTotalPage() + "ҳ��");
				if (cl.getPageIndex() > 1) {
					// ---------------------------------
					// ��ǰҳ���ǵ�һҳ��
					// ��Ҫ�����һҳ����һҳ��ǩ
					// ---------------------------------
					buffer
							.append(" <a href='JavaScript:tuneAjaxPage(1"
									+ strPage
									+ ")'><font face='Webdings'>9</font></a> <a href='JavaScript:tuneAjaxPage("
									+ (cl.getPageIndex() - 1) + strPage + ")'><font face='Webdings'>7</font></a>");
				}

				// ---------------------------------
				// �����ǰҳ����ÿҳ��ʾҳ������
				// ��Ҫ��ʾ�������Ϸ��ı�ǩ(��ǩ�ǣ�...)
				// ---------------------------------
				int currentNum = (cl.getPageIndex() % pageNum == 0 ? (cl
						.getPageIndex() / pageNum) - 1 : (int) (cl
						.getPageIndex() / pageNum))
						* pageNum;
				if (currentNum < 0)
					currentNum = 0;
				if (cl.getPageIndex() > pageNum) {
					buffer
							.append(" <a href='JavaScript:tuneAjaxPage("
									+ (currentNum - pageNum + 1) + strPage + ")'>...</a>");
				}

				// ---------------------------------
				// ��ʾ�м���õķ�ҳ��
				// ---------------------------------
				for (int i = 0; i < pageNum; i++) {
					if ((currentNum + i + 1) > cl.getTotalPage()
							|| cl.getTotalPage() < 2)
						break;
					buffer
							.append(" <a href='JavaScript:tuneAjaxPage("
									+ (currentNum + i + 1)
									+ strPage
									+ ")'>"
									+ (currentNum + i + 1 == cl.getPageIndex() ? "<font color='#FF0000'><b>"
											+ (currentNum + i + 1)
											+ "</b></font>"
											: (currentNum + i + 1) + "") + "</a>");
				}

				// ---------------------------------
				// �����δ�������һ�棬
				// ��Ҫ����������·��ı�ǩ(��ǩ�ǣ�...)
				// ---------------------------------
				if (cl.getTotalPage() > (currentNum + pageNum))
					buffer
							.append(" <a href='JavaScript:tuneAjaxPage("
									+ (currentNum + 1 + pageNum) + strPage + ")'>...</a>");

				// ---------------------------------
				// �����ǰҳ�������һҳ��
				// ��Ҫ������һҳ�����һҳ����
				// ---------------------------------
				if (cl.getPageIndex() < cl.getTotalPage()) {
					buffer
							.append(" <a href='JavaScript:tuneAjaxPage("
									+ (cl.getPageIndex() + 1)
									+ strPage
									+ ")'><font face='Webdings'>8</font></a> <a href='JavaScript:tuneAjaxPage("
									+ cl.getTotalPage() + strPage + ")'><font face='Webdings'>:</font></a>");
				}
				rValue = buffer.toString();
				// ---------------------------------
				// ��ʽ�ͷ���Դ
				// ---------------------------------
				buffer.setLength(0);
				buffer = null;
			} else {
				rValue = "";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rValue = "";
		}
		cl = null;
		return rValue;
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> List<T> emptyList() {
		return (List<T>) EMPTY_LIST;
	}
	
	private static class EmptyList extends AbstractList<Object>
		implements RandomAccess, Serializable,PaginatedList<Object> {
		
		private static final long serialVersionUID = 8842843931221139166L;
		@Override
        public int size() {return 0;}
		@Override
        public boolean contains(Object obj) {return false;}
		@Override
        public Object get(int index)	 {
            throw new IndexOutOfBoundsException("Index: "+index);
        }

        // Preserves singleton property
        private Object readResolve() {
            return EMPTY_LIST;
        }

		@Override
		public int getPageSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getTotalPage() {
			return 0;
		}

		@Override
		public int getTotalRec() {
			return 0;
		}

		@Override
		public int getPageIndex() {
			return 0;
		}

		@Override
		public boolean isFirstPage() {
			return false;
		}

		@Override
		public boolean isLastPage() {
			return false;
		}

		@Override
		public int getStartPos() {
			return 0;
		}

		@Override
		public int getEndPos() {
			return 0;
		}

		@Override
		public int getBegin() {
			return 0;
		}

		@Override
		public int getEnd() {
			return 0;
		}
        
    }

	public int getSize() {
		return this.size();
	}

	public Object[] getDatas() {
		return this.toArray();
	}

	/**
	 * �뱣�������ע��
	 * 
	 * 
	 * js ��ҳ���� function tunePage(toPageNo,pageNo) { try { var topage = 1;
	 * if(typeof(toPageNo) != "number" || toPageNo < 1) topage = 1; else topage
	 * = toPageNo; var olds = window.location.searchSubject; if(typeof(pageNo)
	 * == "undefined" || pageNo == "") pageNo = "pageNo"; var news = "";
	 * if(olds.length > 1) { olds = olds.substring(1,olds.length); var arrays =
	 * olds.split("&"); for (var i = 0; i < arrays.length ; i++) {
	 * if(arrays[i].indexOf(pageNo + "=") < 0 && arrays[i].length > 1) { news +=
	 * "&" + arrays[i]; } } if(news.length > 1) { news = "?" +
	 * news.substring(1,news.length) + "&" + pageNo + "=" + topage; } else {
	 * news = "?" + pageNo + "=" + topage; } } else { news = "?" + pageNo + "="
	 * + topage; } window.location = window.location.pathname + news; } catch(e)
	 * { window.location = window.location.pathname +
	 * window.location.searchSubject; } } function sAll(thisObj,dObj) { try {
	 * var l = eval(dObj + ".length"); if(typeof(l) == "undefined") {
	 * eval(dObj).checked = thisObj.checked; } else { for(var i = 0; i < l; i++)
	 * { if(!eval(dObj + "[" + i + "]").disabled) eval(dObj + "[" + i +
	 * "]").checked = thisObj.checked; } } } catch(e){} }
	 */

}
