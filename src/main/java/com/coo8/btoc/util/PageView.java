package com.coo8.btoc.util;

import java.util.List;


/**
 * 最终类：只要提供一个 QueryResult 、当前页、每页显示数量
 * @param <T> 要查询的实体类
 */
public class PageView<T> {
	/** 页码开始索引和结束索引 **/
	private PageIndex pageindex;
	/** 分页数据 **/
	private List<T> records;
	/** 总页数 **/
	private long totalpage = 1;
	/** 每页显示记录数 **/
	private int maxresult = 12;

	/** 总记录数 **/
	private long totalrecord;
	/** 当前页码 **/
	private int currentpage = 1;
	/** 页码数量 **/
	private int pagecode = 10;
	/** 开始页码 */
	private long startindex;
	/** 结束页码 */
	private long endindex;
	
	/**
	 * @param maxresult 每页显示数量
	 * @param currentpage 要查询的页
	 * @param totalrecord 总记录数
	 * @param queryResult 当页数据
	 */
	public PageView(int maxresult, int currentpage, long totalrecord, List<T> queryResult) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
		setTotalrecord(totalrecord);
		setRecords(queryResult);
	}
	/**
	 * @param maxresult 每页显示数量
	 * @param currentpage 要查询的页
	 * @param totalrecord 总记录数
	 * @param queryResult 当页数据
	 * @param pagecode 页码数量
	 */
	public PageView(int maxresult, int currentpage, long totalrecord, List<T> queryResult, int pagecode) {
		this.pagecode = pagecode;
		this.maxresult = maxresult;
		this.currentpage = currentpage;
		setTotalrecord(totalrecord);
		setRecords(queryResult);
	}
	
	/**
	 * 得到数据库查询 index
	 * @param currentpage 当前页码
	 * @param maxresult 每页数据量
	 * @return [begin,end]
	 */
	public static int[] getDateBeginEnd(int currentpage, int maxresult){
		int[] r = new int[2];
		r[0] = (currentpage - 1) *  maxresult + 1;
		r[1] = currentpage * maxresult - 1;
		return r;
	}
	
	/** 要获取记录的开始索引 **/
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
	/** 设置总记录数、总页数  */
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);
	}
	/** 设置总页数 */
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageindex = PageIndex.getPageIndex(currentpage, pagecode, totalpage);
	}
	public List<T> getRecords() {
		return records;
	}
	/** List数据 */
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
 * 提供：显示页、显示数量、总共的页数
 * 得到：显示头、显示尾、当前页（PageIndex)，用于底部选择要查看的页数
 * 使用：PageIndex index = PageIndex.getPageIndex(查询页,每页数量,总页数)
 */
class PageIndex {
	/** 开始查询 */
	private long startindex;
	/** 结束查询 */
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
	 * @param currentPage 当前页码
	 * @param viewpagecount 显示页码数量
	 * @param totalpage 总页码数
	 * @return
	 */
	public static PageIndex getPageIndex(int currentPage, long viewpagecount, long totalpage){
		/** 前活动线 */
		long linebefore = currentPage - viewpagecount/2;
		/** 后活动线 */
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

