package com.coo8.topic.model;

import java.io.Serializable;

/**
 * �ȴ������ӿڷ�������
 * 
 * @author fanqingxia
 *
 */
public class HotSearchwordVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6865459341207868130L;

	/**
	 * �ȴ�
	 */
	private String title;

	private String hotwordsUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHotwordsUrl() {
		return hotwordsUrl;
	}

	public void setHotwordsUrl(String hotwordsUrl) {
		this.hotwordsUrl = hotwordsUrl;
	}

}
