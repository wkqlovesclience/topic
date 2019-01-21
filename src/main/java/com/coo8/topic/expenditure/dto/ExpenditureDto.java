package com.coo8.topic.expenditure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coo8.topic.expenditure.pojo.ChannelDetailSecond;
import com.coo8.topic.expenditure.pojo.ChannelDetailStair;
import com.coo8.topic.expenditure.pojo.ChannelDetailThird;

import lombok.Data;

/**
 * ERM后台花费DTO
 * @author yangjunjie-ds
 *	@date 2018年10月11日10:55:38
 */

@Data
public class ExpenditureDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	/*** 端口*/
	private  String port;
	/*** 端口Id*/
	private  int portId;
	/*** 一级渠道*/
	private ChannelDetailStair chanStair;
	/*** 二级渠道*/
	private ChannelDetailSecond chanSecond;
	/*** 三级渠道*/
	private ChannelDetailThird chanThird;
	/*** UV*/
	private int UV;
	/*** 订单量*/
	private int orderNum;
	/*** 销售额*/
	private BigDecimal sale;
	/*** 花费额*/
	private BigDecimal expenditure;
	/*** 修改者*/
	private String mender;
	/*** 更新时间*/
	private String timestamp;
	/*** 备注*/
	private String remark;

}
