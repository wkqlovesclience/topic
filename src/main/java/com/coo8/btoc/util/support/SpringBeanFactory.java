/*
 * 文件名： SpringBeanFactory.java
 * 
 * 创建日期： 2010-4-30
 *
 * Cretead by <a href="mailto:songpp22@gmail.com">songpp</a>.
 *
 */
package com.coo8.btoc.util.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 *
 * @author <a href="mailto:songpp22@gmail.com">songpp</a>
 *
 * @version $Revision$
 *
 * @since 2010-4-30 上午09:20:04
 */
public class SpringBeanFactory implements ApplicationContextAware{
	

	static ApplicationContext factory = null;
	
	
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		factory = arg0;
	}

	public static Object getServiceBean(String service)
    {
        return factory.getBean(service);
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz)
    {
    	return (T)factory.getBean(clazz.getSimpleName());
    }
    	
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String clazz)
    {
    	return (T)factory.getBean(clazz);
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T getServiceBean(Class<T> clazz)
    {
    	return (T)factory.getBean(clazz.getSimpleName());
    }
}
