package com.coo8.topic.expenditure.pojo;

import java.util.Date;

import lombok.Data;

/**
 * ��������POJO
 * @author yangjunjie-ds
 *
 */
@Data
public class ChannelDetailSecond {
	/**����id*/
	private long id;
	/**��������*/
	private String secondName;
	/**һ������id*/
	private long stairId;
	/**����ʱ��*/
	private Date createDate;
	/**ɾ����ʶ*/
	private boolean delFlag;
	/**��ע*/
	private String remark;
}
