package com.coo8.btoc.business.helpler.appendapply;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.coo8.btoc.controller.action.admin.block.BlockPublishAction;

public class AppendApplyConfig {
    private static PropertiesConfiguration props;
    private  static Logger logger = LoggerFactory.getLogger(AppendApplyConfig.class);
    static {
        ClassPathResource res=new ClassPathResource("config.properties");
        
        props = new PropertiesConfiguration();
        try {
            props.setEncoding("GBK");
            props.load(res.getPath());
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }finally{
            res=null;
        }
}
/**
 * 
 * @return ����һ��ȡ������ƷID�����ID
 * һ����ƷID�����ID��ȷ��һ�����
 */
public static int getMaxPidAndArea(){
    try{
        return Integer.parseInt((String)props.getProperty("excutePidAndAreaNum"));
    }catch(Exception e){
    	logger.error(e.getMessage(), e);
        return 15;
    }
}

/**
 * 
 * @return �����̳߳صĴ�С
 */
public static int getThreahPoolSize(){
    try{
         return Integer.parseInt((String)props.getProperty("noticeThreadSize"));
    }catch(Exception e){
    	logger.error(e.getMessage(), e);
        return 5;
    }
}
/**
 * ���ط�����Ʒ�̳߳ش�С
 * @return
 */
public static int getPublishThreadPoolSize()
{
    try{
        return Integer.parseInt((String)props.getProperty("publishItemThreadSize"));
   }catch(Exception e){
	   logger.error(e.getMessage(), e);
       return 10;
   }
}

/**
 * ���ص���֪ͨ�ʼ�TITLE
 * @return
 */
public static String getArrivalnoticetitle()
{
    try{
        return (String)props.getProperty("arrivalnoticetitle");
   }catch(Exception e){
	   logger.error(e.getMessage(), e);
       return "��͹�����Ʒ����֪ͨ";
   }
}
/**
 * ���ؽ���֪ͨ�ʼ�TITLE
 * @return
 */
public static String getPricecutnoticetitle()
{
    try{
        return (String)props.getProperty("pricecutnoticetitle");
   }catch(Exception e){
	   logger.error(e.getMessage(), e);
       return "��͹�����Ʒ����֪ͨ";
   }
}

private AppendApplyConfig() {
}
}
