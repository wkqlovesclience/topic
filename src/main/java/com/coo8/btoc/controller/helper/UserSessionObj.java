/*
 * �ļ����� UserSessionObj.java
 * 
 * �������ڣ� 2010-4-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * ԭʼ����: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.controller.helper;

import com.coo8.btoc.model.role.UserInfo;
import com.coo8.btoc.model.user.User;

/**
 * �û���½������ƽ̨���õ��û���Ϣ(������HttpSession��)
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision$
 *
 * @since 2010-4-16
 */
public class UserSessionObj {
	//�û���¼�󣬱����û����õ���Ϣ
	private User user;
	
	//����Ա��¼�󣬱������Ա���õ���Ϣ
	private UserInfo userInfo;

	private boolean cartReloaded = false;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCartReloaded() {
		return cartReloaded;
	}
	public void setCartReloaded(boolean cartReloaded) {
		this.cartReloaded = cartReloaded;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
