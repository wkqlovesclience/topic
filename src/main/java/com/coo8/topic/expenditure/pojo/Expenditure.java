package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * ERM��̨����POJO
 * @author yangjunjie-ds
 *	@date 2018��10��11��10:55:38
 */

@Data
public class Expenditure{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	/*** �˿�*/
	private  int portId;
	/*** һ������*/
	private long stairChanId;
	/*** ��������*/
	private long secondChanId;
	/*** ��������*/
	private int thirdChanId;
	/*** UV*/
	private int UV;
	/*** ������*/
	private int orderNum;
	/*** ���۶�*/
	private BigDecimal sale;
	/*** ���Ѷ�*/
	private BigDecimal expenditure;
	/*** ������*/
	private String creator;
	/*** �޸���*/
	private String mender;
	/*** ����ʱ��*/
	private Date createDate;
	/*** �޸�ʱ��*/
	private Date updateDate;
	/*** ɾ�����*/
	private boolean delFlag;
	/*** ��ע*/
	private String remark;

	private String startDate;

	private String endDate;

}
