/*
 * 文件名： ConfigUtil.java
 * 
 * 创建日期： 2010-3-15
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 配置文件搜索类
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision$
 *
 * @since 2010-3-15
 */
public class ConfigUtil {

	
	
	private  static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
			
	private static String webContentPath;
	
	private ConfigUtil(){
		
	}

	/**
	 * 获取应用的绝对路径。例如：E:\workspace\MyCourseCrawler\web
	 * 
	 * @return 返回 webContentPath。
	 */
	public static String getWebContentPath() {

		if (webContentPath == null) {

			return new File("web").getAbsolutePath();
		}

		return webContentPath;
	}

	/**
	 * @param webContentPath
	 *            要设置的 webContentPath。
	 */
	public static void setWebContentPath(String webContentPath) {
		ConfigUtil.webContentPath = webContentPath;
	}

	/**
	 * 获取要读取的路径文件
	 * 
	 * @return 返回一个绝对路径
	 */
	public static String getConfigPath() {

		return getWebContentPath() + "/WEB-INF/classes/config";
	}

	/**
	 * 获取配置文件的绝对路径
	 * 
	 * @param path
	 *            应用程序的绝对路径
	 * 
	 * @param cfgFileName
	 *            要读取配置文件的名字 例如spring这样就会读取所有和Spring相关的XML文件
	 * 
	 * @return 返回配置文件的绝对路径
	 */
	public static String[] getFullConfigLocationArray(String path,
			String cfgFileName) {

		return getConfigLocationArray(path, cfgFileName, "", true);
	}

	/**
	 * 获取配置文件和Classpath同级的路径
	 * 
	 * @param path
	 *            应用程序的绝对路径
	 * 
	 * @param cfgFileName
	 *            要读取配置文件的名字 例如spring这样就会读取所有和Spring相关的XML文件
	 * 
	 * @return 返回和Classpath同级的路径
	 */
	public static String[] getConfigLocationArray(String path,
			String cfgFileName) {

		return getConfigLocationArray(path, cfgFileName, "", false);
	}

	/**
	 * 根据应用程序的路径和配置文件的名字以及什么样的路径条件来返回文件的路径
	 * 
	 * @param path
	 *            应用程序的绝对路径
	 * 
	 * @param cfgFileName
	 *            要读取配置文件的名字 例如spring这样就会读取所有和Spring相关的XML文件
	 * 
	 * @param isFullPath
	 *            是否要获取绝对路径还是和classpath同级的路径
	 * 
	 * @return 返回文件的路径
	 */
	public static String[] getConfigLocationArray(String path,
			String cfgFileName, boolean isFullPath) {
		return getConfigLocationArray(path, cfgFileName, "", isFullPath);
	}

	/**
	 * 通过程序的绝对路径，要读取配置文件的名字，文件的前缀名，要获取的路径条件获得文件的路径
	 * 
	 * @param path
	 *            应用程序的绝对路径
	 * 
	 * @param cfgFileName
	 *            要读取配置文件的名字 例如spring
	 * 
	 * @param filePrefix
	 *            文件的前缀
	 * 
	 * 
	 * @param isFullPath
	 *            是否要获取绝对路径还是和classpath同级的路径
	 * 
	 * @return 返回文件的路径
	 */
	public static String[] getConfigLocationArray(String path,
			String cfgFileName, String filePrefix, boolean isFullPath) {

		String curPath = "/WEB-INF/classes/config";

		// 根据配置文件的绝对路径创建目录对象
		File dir = new File(path + curPath);
		List<String> files = new ArrayList<String>();
		getConfigLocations(dir, curPath, cfgFileName, filePrefix, files,
				isFullPath);
		if(logger.isInfoEnabled()){
			logger.info("path=="+curPath+" filePrefix==="+filePrefix) ;
		}
		return (String[]) files.toArray(new String[0]);
	}

	/**
	 * 根据文件对象和默认路径还路径的条件来把需要被读取的文件名存放到files集合中
	 * 
	 * @param dir
	 *            文件对象
	 * 
	 * @param curPath
	 *            配置的默认路径/WEB-INF/classes/config
	 * 
	 * @param cfgFileName
	 *            要读取配置文件的名字 例如spring
	 * 
	 * @param filePrefix
	 *            文件的前缀
	 * 
	 * @param files
	 *            存放需要被加载的文件的集合
	 * 
	 * @param isFullPath
	 *            是否要获取绝对路径还是和classpath同级的路径
	 */
	private static void getConfigLocations(File dir, String curPath,
			String cfgFileName, String filePrefix, List<String> files,
			boolean isFullPath) {
		
		//查看这个文件下有多少个文件，例如这个文件可能是一个目录，下面有很多文件。
		String[] fileNames = dir.list();
		
		//如果为空不做任何操作
		if (fileNames == null){
			return;
		}
		String filePath = dir.getPath();
		for (int i = 0; i < fileNames.length; i++) {
			if (".".equals(fileNames[i]) || "..".equals(fileNames[i])){
				continue;
			}
			
			//File.separator表示按照操作系统的文件符号来做
			if (fileNames[i].startsWith(cfgFileName)
					&& fileNames[i].endsWith(".xml")) {
				if (isFullPath){
					files.add(filePath + File.separator + fileNames[i]);
				}else{
					files.add(filePrefix + curPath + File.separator + fileNames[i]);
				continue;
				}
			}
			
			//如果这个文件是目录的化就需要进行递归检索出来直到他是文件为止。
			File file = new File(filePath + File.separator + fileNames[i]);
			if (file.isDirectory()) {
				// recursion
				getConfigLocations(file, curPath + File.separator + fileNames[i],
						cfgFileName, filePrefix, files, isFullPath);
			}
		}
	}
}
