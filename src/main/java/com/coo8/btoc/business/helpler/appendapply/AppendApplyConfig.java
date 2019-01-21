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
 * @return 返回一次取多少商品ID与地区ID
 * 一个商品ID与地区ID可确定一个库存
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
 * @return 返回线程池的大小
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
 * 返回发布商品线程池大小
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
 * 返回到货通知邮件TITLE
 * @return
 */
public static String getArrivalnoticetitle()
{
    try{
        return (String)props.getProperty("arrivalnoticetitle");
   }catch(Exception e){
	   logger.error(e.getMessage(), e);
       return "库巴购物商品到货通知";
   }
}
/**
 * 返回降价通知邮件TITLE
 * @return
 */
public static String getPricecutnoticetitle()
{
    try{
        return (String)props.getProperty("pricecutnoticetitle");
   }catch(Exception e){
	   logger.error(e.getMessage(), e);
       return "库巴购物商品降价通知";
   }
}

private AppendApplyConfig() {
}
}
