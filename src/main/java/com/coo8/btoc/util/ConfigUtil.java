/*
 * �ļ����� ConfigUtil.java
 * 
 * �������ڣ� 2010-3-15
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * ԭʼ����: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * �����ļ�������
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
	 * ��ȡӦ�õľ���·�������磺E:\workspace\MyCourseCrawler\web
	 * 
	 * @return ���� webContentPath��
	 */
	public static String getWebContentPath() {

		if (webContentPath == null) {

			return new File("web").getAbsolutePath();
		}

		return webContentPath;
	}

	/**
	 * @param webContentPath
	 *            Ҫ���õ� webContentPath��
	 */
	public static void setWebContentPath(String webContentPath) {
		ConfigUtil.webContentPath = webContentPath;
	}

	/**
	 * ��ȡҪ��ȡ��·���ļ�
	 * 
	 * @return ����һ������·��
	 */
	public static String getConfigPath() {

		return getWebContentPath() + "/WEB-INF/classes/config";
	}

	/**
	 * ��ȡ�����ļ��ľ���·��
	 * 
	 * @param path
	 *            Ӧ�ó���ľ���·��
	 * 
	 * @param cfgFileName
	 *            Ҫ��ȡ�����ļ������� ����spring�����ͻ��ȡ���к�Spring��ص�XML�ļ�
	 * 
	 * @return ���������ļ��ľ���·��
	 */
	public static String[] getFullConfigLocationArray(String path,
			String cfgFileName) {

		return getConfigLocationArray(path, cfgFileName, "", true);
	}

	/**
	 * ��ȡ�����ļ���Classpathͬ����·��
	 * 
	 * @param path
	 *            Ӧ�ó���ľ���·��
	 * 
	 * @param cfgFileName
	 *            Ҫ��ȡ�����ļ������� ����spring�����ͻ��ȡ���к�Spring��ص�XML�ļ�
	 * 
	 * @return ���غ�Classpathͬ����·��
	 */
	public static String[] getConfigLocationArray(String path,
			String cfgFileName) {

		return getConfigLocationArray(path, cfgFileName, "", false);
	}

	/**
	 * ����Ӧ�ó����·���������ļ��������Լ�ʲô����·�������������ļ���·��
	 * 
	 * @param path
	 *            Ӧ�ó���ľ���·��
	 * 
	 * @param cfgFileName
	 *            Ҫ��ȡ�����ļ������� ����spring�����ͻ��ȡ���к�Spring��ص�XML�ļ�
	 * 
	 * @param isFullPath
	 *            �Ƿ�Ҫ��ȡ����·�����Ǻ�classpathͬ����·��
	 * 
	 * @return �����ļ���·��
	 */
	public static String[] getConfigLocationArray(String path,
			String cfgFileName, boolean isFullPath) {
		return getConfigLocationArray(path, cfgFileName, "", isFullPath);
	}

	/**
	 * ͨ������ľ���·����Ҫ��ȡ�����ļ������֣��ļ���ǰ׺����Ҫ��ȡ��·����������ļ���·��
	 * 
	 * @param path
	 *            Ӧ�ó���ľ���·��
	 * 
	 * @param cfgFileName
	 *            Ҫ��ȡ�����ļ������� ����spring
	 * 
	 * @param filePrefix
	 *            �ļ���ǰ׺
	 * 
	 * 
	 * @param isFullPath
	 *            �Ƿ�Ҫ��ȡ����·�����Ǻ�classpathͬ����·��
	 * 
	 * @return �����ļ���·��
	 */
	public static String[] getConfigLocationArray(String path,
			String cfgFileName, String filePrefix, boolean isFullPath) {

		String curPath = "/WEB-INF/classes/config";

		// ���������ļ��ľ���·������Ŀ¼����
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
	 * �����ļ������Ĭ��·����·��������������Ҫ����ȡ���ļ�����ŵ�files������
	 * 
	 * @param dir
	 *            �ļ�����
	 * 
	 * @param curPath
	 *            ���õ�Ĭ��·��/WEB-INF/classes/config
	 * 
	 * @param cfgFileName
	 *            Ҫ��ȡ�����ļ������� ����spring
	 * 
	 * @param filePrefix
	 *            �ļ���ǰ׺
	 * 
	 * @param files
	 *            �����Ҫ�����ص��ļ��ļ���
	 * 
	 * @param isFullPath
	 *            �Ƿ�Ҫ��ȡ����·�����Ǻ�classpathͬ����·��
	 */
	private static void getConfigLocations(File dir, String curPath,
			String cfgFileName, String filePrefix, List<String> files,
			boolean isFullPath) {
		
		//�鿴����ļ����ж��ٸ��ļ�����������ļ�������һ��Ŀ¼�������кܶ��ļ���
		String[] fileNames = dir.list();
		
		//���Ϊ�ղ����κβ���
		if (fileNames == null){
			return;
		}
		String filePath = dir.getPath();
		for (int i = 0; i < fileNames.length; i++) {
			if (".".equals(fileNames[i]) || "..".equals(fileNames[i])){
				continue;
			}
			
			//File.separator��ʾ���ղ���ϵͳ���ļ���������
			if (fileNames[i].startsWith(cfgFileName)
					&& fileNames[i].endsWith(".xml")) {
				if (isFullPath){
					files.add(filePath + File.separator + fileNames[i]);
				}else{
					files.add(filePrefix + curPath + File.separator + fileNames[i]);
				continue;
				}
			}
			
			//�������ļ���Ŀ¼�Ļ�����Ҫ���еݹ��������ֱ�������ļ�Ϊֹ��
			File file = new File(filePath + File.separator + fileNames[i]);
			if (file.isDirectory()) {
				// recursion
				getConfigLocations(file, curPath + File.separator + fileNames[i],
						cfgFileName, filePrefix, files, isFullPath);
			}
		}
	}
}
