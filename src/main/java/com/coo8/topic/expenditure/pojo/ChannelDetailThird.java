package com.coo8.topic.expenditure.pojo;

import java.util.Date;

import lombok.Data;

/**
 * 三级渠道POJO
 * @author yangjunjie-ds
 *
 */
@Data
public class ChannelDetailThird {
	/**渠道id*/
	private int id;
	/**渠道名称*/
	private String thirdName;
	/**一级渠道id*/
	private long stairId;
	/**二级渠道id*/
	private long secondId;
	/**创建时间*/
	private Date createDate;
	/**删除标识*/
	private boolean delFlag;
	/**备注*/
	private String remark;
}
