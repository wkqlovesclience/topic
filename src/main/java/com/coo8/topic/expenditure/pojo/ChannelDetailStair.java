package com.coo8.topic.expenditure.pojo;

import java.util.Date;

import lombok.Data;

/**
 * ����POJO
 * @author yangjunjie-ds
 *
 */
@Data
public class ChannelDetailStair {
	/**����id*/
	private long id;
	/**�˿�id*/
	private int portId;
	/**��������*/
	private String stairName;
	/**����ʱ��*/
	private Date createDate;
	/**ɾ����ʶ*/
	private boolean delFlag;
	/**��ע*/
	private String remark;
}
