package com.coo8.topic.expenditure.pojo;

import java.util.Date;

import lombok.Data;

/**
 * 渠道POJO
 * @author yangjunjie-ds
 *
 */
@Data
public class ChannelDetailStair {
	/**渠道id*/
	private long id;
	/**端口id*/
	private int portId;
	/**渠道名称*/
	private String stairName;
	/**创建时间*/
	private Date createDate;
	/**删除标识*/
	private boolean delFlag;
	/**备注*/
	private String remark;
}
