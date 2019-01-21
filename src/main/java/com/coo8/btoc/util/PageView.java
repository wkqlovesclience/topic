package com.coo8.btoc.util;

import java.util.List;


/**
 * �����ֻࣺҪ�ṩһ�� QueryResult ����ǰҳ��ÿҳ��ʾ����
 * @param <T> Ҫ��ѯ��ʵ����
 */
public class PageView<T> {
	/** ҳ�뿪ʼ�����ͽ������� **/
	private PageIndex pageindex;
	/** ��ҳ���� **/
	private List<T> records;
	/** ��ҳ�� **/
	private long totalpage = 1;
	/** ÿҳ��ʾ��¼�� **/
	private int maxresult = 12;

	/** �ܼ�¼�� **/
	private long totalrecord;
	/** ��ǰҳ�� **/
	private int currentpage = 1;
	/** ҳ������ **/
	private int pagecode = 10;
	/** ��ʼҳ�� */
	private long startindex;
	/** ����ҳ�� */
	private long endindex;
	
	/**
	 * @param maxresult ÿҳ��ʾ����
	 * @param currentpage Ҫ��ѯ��ҳ
	 * @param totalrecord �ܼ�¼��
	 * @param queryResult ��ҳ����
	 */
	public PageView(int maxresult, int currentpage, long totalrecord, List<T> queryResult) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
		setTotalrecord(totalrecord);
		setRecords(queryResult);
	}
	/**
	 * @param maxresult ÿҳ��ʾ����
	 * @param currentpage Ҫ��ѯ��ҳ
	 * @param totalrecord �ܼ�¼��
	 * @param queryResult ��ҳ����
	 * @param pagecode ҳ������
	 */
	public PageView(int maxresult, int currentpage, long totalrecord, List<T> queryResult, int pagecode) {
		this.pagecode = pagecode;
		this.maxresult = maxresult;
		this.currentpage = currentpage;
		setTotalrecord(totalrecord);
		setRecords(queryResult);
	}
	
	/**
	 * �õ����ݿ��ѯ index
	 * @param currentpage ��ǰҳ��
	 * @param maxresult ÿҳ������
	 * @return [begin,end]
	 */
	public static int[] getDateBeginEnd(int currentpage, int maxresult){
		int[] r = new int[2];
		r[0] = (currentpage - 1) *  maxresult + 1;
		r[1] = currentpage * maxresult - 1;
		return r;
	}
	
	/** Ҫ��ȡ��¼�Ŀ�ʼ���� **/
	public int getFirstResult() {
		return (this.currentpage-1)*this.maxresult;
	}
	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
	
	public long getTotalrecord() {
		return totalrecord;
	}
	/** �����ܼ�¼������ҳ��  */
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);
	}
	/** ������ҳ�� */
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageindex = PageIndex.getPageIndex(currentpage, pagecode, totalpage);
	}
	public List<T> getRecords() {
		return records;
	}
	/** List���� */
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public PageIndex getPageindex() {
		return pageindex;
	}
	public long getTotalpage() {
		return totalpage;
	}
	public int getMaxresult() {
		return maxresult;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public long getStartindex() {
		return getPageindex().getStartindex();
	}
	public long getEndindex() {
		return getPageindex().getEndindex();
	}
	public void setPageindex(PageIndex pageindex) {
		this.pageindex = pageindex;
	}
	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public void setStartindex(long startindex) {
		this.startindex = startindex;
	}
	public void setEndindex(long endindex) {
		this.endindex = endindex;
	}
	
	
	@Override
	public String toString() {
		return "PageView [startindex=" + getStartindex() + ", endindex=" + getEndindex()
				+ ", currentpage=" + currentpage + ", maxresult=" + maxresult
				+ ", pagecode=" + pagecode + ", totalpage=" + totalpage 
				+ ", totalrecord=" + totalrecord + "]";
	}

}
	
/**
 * �ṩ����ʾҳ����ʾ�������ܹ���ҳ��
 * �õ�����ʾͷ����ʾβ����ǰҳ��PageIndex)�����ڵײ�ѡ��Ҫ�鿴��ҳ��
 * ʹ�ã�PageIndex index = PageIndex.getPageIndex(��ѯҳ,ÿҳ����,��ҳ��)
 */
class PageIndex {
	/** ��ʼ��ѯ */
	private long startindex;
	/** ������ѯ */
	private long endindex;

	public PageIndex(long startindex, long endindex) {
		this.startindex = startindex;
		this.endindex = endindex;
	}
	public long getStartindex() {
		return startindex;
	}
	public void setStartindex(long startindex) {
		this.startindex = startindex;
	}
	public long getEndindex() {
		return endindex;
	}
	public void setEndindex(long endindex) {
		this.endindex = endindex;
	}
	/**
	 * @param currentPage ��ǰҳ��
	 * @param viewpagecount ��ʾҳ������
	 * @param totalpage ��ҳ����
	 * @return
	 */
	public static PageIndex getPageIndex(int currentPage, long viewpagecount, long totalpage){
		/** ǰ��� */
		long linebefore = currentPage - viewpagecount/2;
		/** ���� */
		long lineafter = currentPage + viewpagecount/2; 
		
		if(linebefore < 1){
			linebefore = 1;
			lineafter = viewpagecount > totalpage?totalpage:viewpagecount;
			return new PageIndex(linebefore, lineafter);
		}
		if(lineafter > totalpage){
			lineafter = totalpage;
			linebefore = (totalpage - viewpagecount) > 0?(totalpage - viewpagecount):1;
		}
		return new PageIndex(linebefore, lineafter);
	}

	
}

