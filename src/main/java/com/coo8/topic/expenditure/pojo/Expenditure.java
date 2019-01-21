package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * ERM后台花费POJO
 * @author yangjunjie-ds
 *	@date 2018年10月11日10:55:38
 */

@Data
public class Expenditure{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	/*** 端口*/
	private  int portId;
	/*** 一级渠道*/
	private long stairChanId;
	/*** 二级渠道*/
	private long secondChanId;
	/*** 三级渠道*/
	private int thirdChanId;
	/*** UV*/
	private int UV;
	/*** 订单量*/
	private int orderNum;
	/*** 销售额*/
	private BigDecimal sale;
	/*** 花费额*/
	private BigDecimal expenditure;
	/*** 创建者*/
	private String creator;
	/*** 修改者*/
	private String mender;
	/*** 创建时间*/
	private Date createDate;
	/*** 修改时间*/
	private Date updateDate;
	/*** 删除标记*/
	private boolean delFlag;
	/*** 备注*/
	private String remark;

	private String startDate;

	private String endDate;

}
