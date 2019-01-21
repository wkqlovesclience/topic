package com.coo8.btoc.controller.action.admin.role;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.common.business.impl.CommonManagerImpl;
import com.coo8.common.business.impl.CommonManagerImplServiceLocator;
import com.coo8.im.system.role.model.Func;
import com.coo8.im.system.user.model.User;
import com.coo8.topic.controller.action.BaseAction;

@Namespace("/admin/role")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 6176463207232079833L;
	private User userinfo;
	private String username;
	private String password;
	private  static Logger logger = LoggerFactory.getLogger(LoginAction.class);
	
	private static final String USER_LOGIN_NAME = "user_login_name";

	private CommonManagerImpl manager;

	private Func[] userFunc;

	private String selectFunc;


	private String sys_flag;

	@Action(value = "loginCheck", results = {
			@Result(name = "loginOK", location = "/ChooseTopic.jsp"),
			@Result(name = "loginError", location = "/LoginError.jsp") })
	public String loginCheck() {
		
		try {
			CommonManagerImplServiceLocator implServiceLocator = new CommonManagerImplServiceLocator();
			manager = implServiceLocator.getCommonManagerImpl();
			if(username == null || password == null || "".equals(username) || "".equals(password)){
				return "loginError";
			}
			userinfo = manager.selectUser(username, password);

			if (userinfo != null && userinfo.getPerson_name() != null && !"".equals(userinfo.getPerson_name())) 
			{ 
				userinfo.getPerson_password();
				userFunc = manager.getFuncList(userinfo.getPerson_name(),"005472059E6547DAA32060C98D96D02A");
				selectFunc = manager.getFuncs(userinfo.getPerson_name(),"005472059E6547DAA32060C98D96D02A");
 
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("userName", userinfo.getPerson_name());
				session.setAttribute("userFunc", userFunc);
				session.setAttribute("urls", selectFunc);
			} else {
				return "loginError";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "loginOK";
		
	}
	
	/**
	 * @author linchengjun
	 * 用途：避开库巴集成管理系统的权限验证，方便开发测试，还需要修改index.jsp的跳转指向；
	 * 		  或者修改UserLoginFilter.java的islogin函数，直接返回true
	 * @return String
	 */
	@Action(value = "loginExceptCheck", results = {
			@Result(name = "loginOK", location = "/ChooseTopic.jsp"),
			@Result(name = "loginError", location = "/LoginError.jsp") })
	public String loginExceptCheck() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		try {
			username = "admin";
			userFunc = manager.getFuncList(username,"005472059E6547DAA32060C98D96D02A");
			selectFunc = manager.getFuncs(username,"005472059E6547DAA32060C98D96D02A");
			session.setAttribute("userName",username);
			session.setAttribute("userFunc", userFunc);
			session.setAttribute("urls", selectFunc);
		} catch (RemoteException e) {
			logger.error(e.getMessage(), e);
			return "loginError";
		}
		return "loginOK";
	}

	@Action(value = "logout", results = { @Result(name = "success", location = "/index.jsp") })
	public String logout() {
		HttpSession session = ServletActionContext.getRequest()
		.getSession();
		 session.setAttribute("userName", "");
		 clearCookie(USER_LOGIN_NAME);  
		 
		return SUCCESS;
	}
	@Action(value = "switchSite")
	public void switchSite(){
		try {
			this.getResponse().getWriter().write("<script type=\"text/javascript\">" +
					"function init(){" +
					"window.parent.document.location.href=\"/ChooseTopic.jsp\";" +
					"}" +
					"</script><body onload=\"init();\">");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	@Action(value = "gotoLoginPage")
	public void gotoLoginPage(){
		try {
			this.getResponse().getWriter().write("<script type=\"text/javascript\">" +
					"function init(){" +
					"window.parent.document.location.href=\"/admin/role/logout.action\";" +
					"}" +
					"</script><body onload=\"init();\">");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void clearCookie(String key) {
		Cookie cookie = new Cookie(key, null);
		cookie.setSecure(true);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		this.getResponse().addCookie(cookie); 
	}

	public String getSys_flag() {
		return sys_flag;
	}

	public void setSys_flag(String sys_flag) {
		this.sys_flag = sys_flag;
	}

	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
