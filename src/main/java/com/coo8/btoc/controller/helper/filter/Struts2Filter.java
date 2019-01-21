package com.coo8.btoc.controller.helper.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Struts2Filter extends StrutsPrepareAndExecuteFilter {
	 private  static Logger logger = LoggerFactory.getLogger(Struts2Filter.class);
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) req;  
        //不过滤的url  
        String url = request.getRequestURI();  
        if ("/js/ueditor/dialogs/image/up.jsp".equals(url)) {
           logger.info(url + " --使用自定义的过滤器");  
            chain.doFilter(req, res);  
        }else{  
            super.doFilter(req, res, chain);  
        }  
    }  

}
