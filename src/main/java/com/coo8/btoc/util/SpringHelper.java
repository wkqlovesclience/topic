package com.coo8.btoc.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class SpringHelper implements ApplicationContextAware {

	private static ApplicationContext context;
	private static ApplicationContext context_Resource;
	@Override
	public void setApplicationContext(ApplicationContext appcontext)
			throws BeansException {
		SpringHelper.context = appcontext;
	}

	/**
	 * 通过制定的名称获得Bean对象
	 * 
	 * @param name
	 */
	public synchronized static Object getBean(String name) {
		if (context == null) {
			 initApplicationContext();
		}
		return context.getBean(name);
	}
	public synchronized static Object getBeanByResource(String name) {
		return getApplicationContextByResource().getBean(name);
	}

	public synchronized static void initWeb(ServletContext sc) {
		context = WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	/**
	 * 通过class类型获得Bean对象
	 * 
	 * @param clazz
	 */
	public static Object getBean(Class clzz) {
		return getBean(clzz.getName());
	}
	public static Object getBeanByResource(Class clzz) {
		return getBeanByResource(clzz.getName());
	}

	/**
	 * 手动初始化spring方法
	 */
	public static void initApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"classpath*://**spring*.xml");
		}
	}
	
	public static ApplicationContext getApplicationContextByResource(){
		if(context_Resource == null){
			context_Resource = new ClassPathXmlApplicationContext(new String[]{
					"/**/spring*.xml"
			});
		}
		return context_Resource;
	}
}
