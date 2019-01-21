package com.coo8.btoc.controller.helper.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserLoginFilter implements Filter{
	 private  static Logger logger = LoggerFactory.getLogger(UserLoginFilter.class);
	@Override
	public void destroy() {
		logger.info("UserLoginFilter.destroy.................");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
 
        HttpSession session = ((HttpServletRequest)request).getSession();
        
         if(islogin(request, session))
        	chain.doFilter(request, response);
		else{
			 ((HttpServletResponse)response).sendRedirect("http://topic.coo8.com/");
		} 
	}

	private boolean islogin(ServletRequest request, HttpSession session) {
		String requestUrl = ((HttpServletRequest)request).getRequestURI();
		Object username = session.getAttribute("userName") ;
		if(requestUrl.indexOf(".action")>=0){
			logger.info(requestUrl);
		}
			
	   
		/*
		 * 需要排除的请求
		 */
	    boolean exceptFlag = (requestUrl.indexOf("/News/assessBad.action") >= 0 )  //文章差评请求
	    		|| ( requestUrl.indexOf("/News/assessGood.action") >= 0 )		   //文章好评请求	
	    		|| ( requestUrl.indexOf("/News/getNewsAssess.action") >=0 )		   //获得文章评论数请求	
	    		|| ( "/".equals(requestUrl) ) 									   //登录JSP页面请求		
	    		|| ( requestUrl.indexOf("/admin/role/loginCheck.action")>=0 ) ;	   //登录Action处理请求
	    
	    /*
	     * 用户是否登录
	     */
	    boolean flag = ( null != username) && (!"".equals(username)) && (!"null".equals(username)) ;
	    
		return flag || exceptFlag;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info(".....初始化登录权限：com.coo8.btoc.controller.helper.filter.UserLoginFilter.....");
	}

}
