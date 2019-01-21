
package com.coo8.btoc.model.itemsfitting;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;
import com.coo8.btoc.model.items.BtoCItems;


public class ItemsFitting  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int status_able = 1;
	public static final int status_unable = 0;
	
	/**
	 * 主键
	 */
	private int id ;
	/**
	 * 主商品ID
	 */
	private int itemid;
	private String itemname;
	/**
	 * 商品ID
	 */
	private int fittingid;
	private String fittingname;
	private int priority;
	private Date inputdate;
	private String inputer;
	private Date updatetime;
	private String updater;
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getFittingid() {
		return fittingid;
	}
	public void setFittingid(int fittingid) {
		this.fittingid = fittingid;
	}
	public String getFittingname() {
		return fittingname;
	}
	public void setFittingname(String fittingname) {
		this.fittingname = fittingname;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public String getInputer() {
		return inputer;
	}
	public void setInputer(String inputer) {
		this.inputer = inputer;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	private BtoCItems fittingItem;
	public BtoCItems getFittingItem() {
		return fittingItem;
	}
	public void setFittingItem(BtoCItems fittingItem) {
		this.fittingItem = fittingItem;
	}
	
	
}
