package com.coo8.topic.expenditure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coo8.topic.expenditure.pojo.ChannelDetailSecond;
import com.coo8.topic.expenditure.pojo.ChannelDetailStair;
import com.coo8.topic.expenditure.pojo.ChannelDetailThird;

import lombok.Data;

/**
 * ERM��̨����DTO
 * @author yangjunjie-ds
 *	@date 2018��10��11��10:55:38
 */

@Data
public class ExpenditureDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	/*** �˿�*/
	private  String port;
	/*** �˿�Id*/
	private  int portId;
	/*** һ������*/
	private ChannelDetailStair chanStair;
	/*** ��������*/
	private ChannelDetailSecond chanSecond;
	/*** ��������*/
	private ChannelDetailThird chanThird;
	/*** UV*/
	private int UV;
	/*** ������*/
	private int orderNum;
	/*** ���۶�*/
	private BigDecimal sale;
	/*** ���Ѷ�*/
	private BigDecimal expenditure;
	/*** �޸���*/
	private String mender;
	/*** ����ʱ��*/
	private String timestamp;
	/*** ��ע*/
	private String remark;

}
