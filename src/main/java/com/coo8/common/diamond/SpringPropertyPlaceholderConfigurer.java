package com.coo8.common.diamond;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhangdapeng
 * 
 */
@Component
public class SpringPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private List<String> diamondList;

	public List<String> getDiamondList() {
		return diamondList;
	}

	public void setDiamondList(List<String> diamondList) {
		this.diamondList = diamondList;	
	}
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		Properties properties = PropertiesUtils.getProperties(diamondList);
		this.setProperties(properties);
		for (Iterator<Object> iterator = properties.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String value = (String) properties.get(key);
			props.setProperty(key, value);
		}
		super.processProperties(beanFactoryToProcess, properties);
	}

}
