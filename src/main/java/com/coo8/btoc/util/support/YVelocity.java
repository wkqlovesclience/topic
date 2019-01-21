package com.coo8.btoc.util.support;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;


import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.tools.ToolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.coo8.btoc.util.StringUtil;

/**
 * this class ...
 * 
 *
 * @author <a href="mailto:songpp22@gmail.com">songpp</a>
 *
 * @version $Revision$
 *
 * @since 2010-5-26 上午07:52:30
 */
public class YVelocity {
	private YVelocity(){}
	
	private static String class_template_path = "/template/";
	
	private static String LOG_NAME = "yvelocity_log";
	
	private  static Logger logger = LoggerFactory.getLogger(LOG_NAME);
	
	private static ToolManager manager = new ToolManager();
	
	public static void init() throws Exception {
		logger.debug("velocity initializing ...");
		
		ClassPathResource tools = new ClassPathResource("tools.xml");
		manager.configure(tools.getPath());
		
		Velocity.init(PropertiesLoaderUtils
				.loadAllProperties("velocity.properties"));
		// 使用log4j为velocity的log
		Velocity.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
	      						"org.apache.velocity.runtime.log.Log4JLogChute" );
		Velocity.setProperty("runtime.log.logsystem.log4j.logger",LOG_NAME);
		
		logger.debug("velocity init over ...");
	}

	public static String parse(String vmFileName, Map<String, Object> context) {
		
		if(vmFileName == null || vmFileName.trim().length() < 1)
			return "";
		
		
		try {
			
			ClassPathResource resource = new ClassPathResource(class_template_path + vmFileName);
			
			Template t = Velocity.getTemplate(resource.getPath());
			
			Context vc = manager.createContext();

			for (Map.Entry<String, Object> p : context.entrySet()) {
				vc.put(p.getKey(), p.getValue());
			}

			StringWriter writer = new StringWriter();

			t.merge(vc, writer);
			
			writer.flush();
			writer.close();
			return writer.toString();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	
	}
	
	public static String parseString(String logTag ,String content , Map<String , Object> context){

		StringWriter sw = new StringWriter();
		if(StringUtil.isNullorBlank(content)){
			return content;
		}
		if(context==null || context.size()<=0){
			return content;
		}
		Context vc = manager.createContext();
		for(String key : context.keySet() ){
			vc.put(key, context.get(key));
		}
		try {
			Velocity.evaluate( vc, sw, logTag, content );
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		String  temp=sw.toString();
		try {
			sw.close();
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return temp;
	}
	
	
}
