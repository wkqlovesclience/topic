/*
 * 文件名： BaseModel.java
 * 
 * 创建日期： 2010-4-23
 *
 * Cretead by <a href="mailto:songpp22@gmail.com">songpp</a>.
 *
 */
package com.coo8.btoc.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:songpp22@gmail.com">songpp</a>
 * 
 * @version $Revision$
 * 
 * @since 2010-4-23
 */
public class BaseModel implements Serializable {

	private  static Logger logger = LoggerFactory.getLogger(BaseModel.class);
	private static final long serialVersionUID = -5116468685582201715L;

	/**
	 * toString
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for (Field f : fields) {
				f.setAccessible(true);
				builder.append(f.getName(), f.get(this)).append("\n");
			}
		}
		catch (Exception e) { // Suppress
			logger.error(e.getMessage(), e);
			builder.append("toString builder encounter an error");
		}
		return builder.toString();
	}
}
