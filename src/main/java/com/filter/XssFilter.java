package com.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import com.coo8.common.diamond.PropertiesUtils;
import com.gome.bas.XssRequestWrapper;

public class XssFilter implements Filter {
	public boolean getUseXssFilter(){
		return PropertiesUtils.getBooleanValueByKey("help.security.xssFilter", true);
	}
	public String getIgnoreUrlStr(){
		return PropertiesUtils.getStringValueByKey("xssFilter.ignoreUrls");
	}
	private Map<String, String> urlMap = new HashMap<String, String>();

	@Override
	public void destroy() {
		// to nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// xss过滤�?关，默认启用xss过滤�?�?
		boolean useXssFilter = getUseXssFilter();
		if (!useXssFilter) {
			chain.doFilter(httpServletRequest, response);
		} else {
			String uri = httpServletRequest.getRequestURI();
			// 默认xss忽略url
			boolean isIgnoreUrl = false;
			if (urlMap != null && urlMap.size() > 0) {
				if (StringUtils.isNotEmpty(urlMap.get(uri))) {
					isIgnoreUrl = true;
				}
			}
			if (isIgnoreUrl) {
				chain.doFilter(httpServletRequest, response);
			} else {
				XssRequestWrapper xssRequest = new XssRequestWrapper(
						httpServletRequest);
				chain.doFilter(xssRequest, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String ignoreUrlStr = getIgnoreUrlStr();
		if (StringUtils.isNotBlank(ignoreUrlStr)) {
			String ignoreUrls[] = ignoreUrlStr.split(",");
			for (int i = 0; i < ignoreUrls.length; i++) {
				urlMap.put(ignoreUrls[i], "true");
			}
		}
	}
}
