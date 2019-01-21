package com.coo8.topic.expenditure.pojo;

import java.util.Date;

import lombok.Data;

/**
 * ��������POJO
 * @author yangjunjie-ds
 *
 */
@Data
public class ChannelDetailThird {
	/**����id*/
	private int id;
	/**��������*/
	private String thirdName;
	/**һ������id*/
	private long stairId;
	/**��������id*/
	private long secondId;
	/**����ʱ��*/
	private Date createDate;
	/**ɾ����ʶ*/
	private boolean delFlag;
	/**��ע*/
	private String remark;
}
