/**
 * 
 */
package com.coo8.btoc.model.rank;

import java.util.Date;

/**
 * @author wangfufu
 *
 */
public class GroupHotLinks {
	private Integer ID;
	private String GROUP_ID;
	private String GROUP_NAME;
	private String LINK_NAME;
	private String LINK_URL;
	private Integer PRIORITY;
	private Boolean IS_VALID;
	private Date CREATE_TIME;
	private Date UPDATE_TIME;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getGROUP_ID() {
		return GROUP_ID;
	}

	public void setGROUP_ID(String gROUP_ID) {
		GROUP_ID = gROUP_ID;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}

	public String getLINK_NAME() {
		return LINK_NAME;
	}

	public void setLINK_NAME(String lINK_NAME) {
		LINK_NAME = lINK_NAME;
	}

	public String getLINK_URL() {
		return LINK_URL;
	}

	public void setLINK_URL(String lINK_URL) {
		LINK_URL = lINK_URL;
	}

	public Integer getPRIORITY() {
		return PRIORITY;
	}

	public void setPRIORITY(Integer pRIORITY) {
		PRIORITY = pRIORITY;
	}

	public Boolean getIS_VALID() {
		return IS_VALID;
	}

	public void setIS_VALID(Boolean iS_VALID) {
		IS_VALID = iS_VALID;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

	
}
