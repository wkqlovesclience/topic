/*
 * 文件名： UserSessionObj.java
 * 
 * 创建日期： 2010-4-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.controller.helper;

import com.coo8.btoc.model.role.UserInfo;
import com.coo8.btoc.model.user.User;

/**
 * 用户登陆后整个平台常用的用户信息(保存在HttpSession中)
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision$
 *
 * @since 2010-4-16
 */
public class UserSessionObj {
	//用户登录后，保存用户常用的信息
	private User user;
	
	//管理员登录后，保存管理员常用的信息
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
