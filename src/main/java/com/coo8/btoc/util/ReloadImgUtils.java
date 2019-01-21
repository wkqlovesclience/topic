package com.coo8.btoc.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.common.utils.ImageUtil;

/**
 * 2011-12-07
 * 
 * @author 梁子兴 该类一般用于提取网页源代码中所有img标签。若为外网图片，会将此图片下载到本地并上传至coo8服务器并返回路径；
 *         并将网页中相应的img标签的src改为上述路径。
 */

public class ReloadImgUtils {
	private static final String s1 = "<img";
	private static final String s2 = "src=\"";
	private static final String s3 = "http://";
	private static final String s4 = "\"";
	private static final String s5 = ">";

	private static String maskUrl = ""; //设置水印路径
	private  static Logger logger = LoggerFactory.getLogger(ReloadImgUtils.class);
	
	private ReloadImgUtils(){}

	public static String reloadImgForUeditorSource(String source) {
		if (source == null || "".equals(source))
			return "nosource";
		int a = 0, b = 0, c = 0, d = 0, e = 0;
		List<String> oldSrcList = new ArrayList<String>();
		Map<String ,String> newSrcMap = new HashMap<String, String>();
		for (int i = 0; i < source.length(); i++) {
			if (i == 0) {
				i = source.indexOf(s1);
				if (i == -1)
					break;
				if (i == 0)
					i = 3;
				b = source.indexOf(s2, i);
				if (b == -1)
					break;
				c = source.indexOf(s3, b);
				if (c == -1)
					break;
				d = source.indexOf(s4, c);
				if (d == -1)
					break;
				e = source.indexOf(s5, d);
				if (e == -1)
					break;
				if (b > i && b < c && c < d && d < e) {
					// 获取图片地址，下载上传，更改路径
					String src = source.substring(c, d);
					
					String path = getImgPathFromWebSite(src);
					
					if(!StringUtil.isNullorBlank(src)&&!StringUtil.isNullorBlank(path)){
						oldSrcList.add(src);
						newSrcMap.put(src, path);
					}
				}
				i = e;
				continue;
			}
			a = source.indexOf(s1, i);
			if (a == -1)
				break;
			b = source.indexOf(s2, a);
			if (b == -1)
				break;
			c = source.indexOf(s3, b);
			if (c == -1)
				break;
			d = source.indexOf(s4, c);
			if (d == -1)
				break;
			e = source.indexOf(s5, d);
			if (e == -1)
				break;
			if (b > i && b < c && c < d && d < e) {
				// 获取图片地址，下载上传，更改路径
				String src = source.substring(c, d);
			
				String path = getImgPathFromWebSite(src);
			
				if(!StringUtil.isNullorBlank(src)&&!StringUtil.isNullorBlank(path)){
					oldSrcList.add(src);
					newSrcMap.put(src, path);
				}
			}
			i = e;
		}
          
		if(!oldSrcList.isEmpty()&& !newSrcMap.isEmpty()){
			for(String s:oldSrcList){
				if(StringUtil.isNullorBlank(s))
					continue;
				return source.replace(s, newSrcMap.get(s));
			}
		}
		return source;
	}


	/**
	 * 获得一个源代码字符串中<img 标签的个数
	 * 
	 * @param source
	 * @return int
	 */

	public static int getImgNum(String source) {
		if (source == null || "".equals(source))
			return 0;
		int j = 0;
		for (int i = 0; i < source.length(); i++) {
			if (i == 0) {
				i = source.indexOf(s1);
				if (i == -1)
					break;
				if (i == 0)
					i = 3;
				j++;
				continue;
			}
			i = source.indexOf(s1, i);
			if (i == -1)
				break;
			j++;
		}
		return j;
	}

	/**
	 * 依据网址获取图片
	 * 
	 * @param WebSite
	 * @return Image
	 */
	public static String getImageByWebSite(String WebSite,String filename) {
		byte[] data = null;
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(WebSite);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6000);
			is = conn.getInputStream();
			if (conn.getResponseCode() == 200) {
				data = readInputStream(is);
			} else {
				data = null;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			if(null != conn){
				conn.disconnect();
			}			
		}
		if (data == null || data.length == 0)
			return null;
         String path = uploadImg(data, filename);
		return path;
	}

	/**
	 * 依据网址获取文件名，返回网址字符串最后一个"/"后面的字符串
	 * 例如http://www.baidu.com/index.html,结果为：index.html
	 * 
	 * @param website
	 * @return String
	 */
	public static String getFileNameByWebSite(String website) {
		if (website == null || "".equals(website))
			return null;
		int a = website.lastIndexOf("/");
		if (a == -1)
			return null;
		String result = website.substring(a + 1);
		return result;
	}

	/**
	 * 从图片地址中获取图片格式
	 * 
	 * @param website
	 * @return String
	 */
	public static String getFileTypeByWebSite(String website) {
		if (website == null || "".equals(website))
			return null;
		int a = website.lastIndexOf("/");
		if (a == -1)
			return null;
		String temp = website.substring(a + 1);
		if (temp == null || "".equals(temp))
			return null;
		int b = temp.indexOf(".");
		if (b == -1)
			return null;
		String result = temp.substring(b + 1);
		return result;
	}

	/**
	 * 把流信息读到byte数组里面
	 * @param is
	 * @return
	 */
	
	public static byte[] readInputStream(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//限定图片大小为3M
		byte[] buffer = new byte[3145728];
		int length = -1;
		byte[] data = baos.toByteArray();
		try {
			while ((length = is.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			baos.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return data;
		}
	
		try {
			is.close();
			baos.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return data;
		}
		return data;
	}

	/**
	 * 上传图片到配置文件指定的位置
	 * 
	 * @param file
	 * @param fileName
	 * @return String result
	 */
	public static String uploadImg(byte[] data, String fileName) {
		String imgUploadAdress = ReloadableConfig.getProperty(
				"ImgUploadAdress", "/app/coo8_img/");
		//将接收到的byte流写到服务器
		String filePath = imgUploadAdress + File.separatorChar +  fileName;
		try {
			File file = new File(filePath);
			if (!file.getParentFile().isDirectory()) {
				file.getParentFile().mkdirs();
			}
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					file));
			for (int i = 0; i < data.length; i++) {
				out.write(data[i]);
			}
			out.flush();
			out.close();
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		//图片添加水印
		if(null != maskUrl && !"".equals(maskUrl)){
			ImageUtil.markImage( maskUrl, filePath);
		}
		
		//检查文件是否是图片，是则返回路径，否则返回空
		String path = "http://img" + rd() + ".gomein.net.cn/image/bbcimg/topic_img/publish/catalog/" + fileName;
		if (checkFileIsImage(filePath)){
			return path;
		}
		return null;
	}

	/**
	 * 1-9的随机数
	 * 
	 * @return
	 */
	public static int rd() {
		return new Random().nextInt(9) + 1;
	}

	/**
	 * 检查一个文件名是否包含中文
	 */
	public static boolean checkFileNameIsContainChinese(String fileName) {
		if (fileName == null || "".equals(fileName))
			return false;
		if (fileName.getBytes().length != fileName.length())
			return true;
		return false;
	}

	/**
	 * 检查配置文件指定目录下是否有同名文件
	 */
	public static boolean checkIsExitSameNameFile(String filename) {
		if (filename == null || "".equals(filename))
			return false;
		String imgUploadAdress = ReloadableConfig.getProperty(
				"ImgUploadAdress", "/app/coo8_img/");
		String filepath = imgUploadAdress + File.separatorChar + filename;
		File file = new File(filepath);
		if (file.exists())
			return true;
		return false;
	}

	/**
	 * 2011-11-15 梁子兴 生成一串随机数的新文件名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getNewFileName(String filename) {
		if (filename != null && !"".equals(filename)) {
			String typ = filename.substring(filename.indexOf(".") + 1);
			String newFilename = new Random().nextLong() + "." + typ;
			if (checkIsExitSameNameFile(newFilename)) {
				return getNewFileName(newFilename);
			}
			return newFilename;
		}
		return null;
	}

	/**
	 * 检查文件名是否合法，是则返回原文件名，否则返回一个由随机数构成的文件名
	 */
	public static String getLegalFileNameFromName(String filename) {
		if (filename == null || "".equals(filename))
			return null;
		if (checkFileNameIsContainChinese(filename)) {
			return getNewFileName(filename);
			
		}
		if (checkIsExitSameNameFile(filename)) {
			return getNewFileName(filename);
			
		}
		return filename;
	}
	
	/**
	 * 检查文件名是否合法，是则返回原文件名，否则返回一个由随机数构成的文件名
	 */
	public static String getLegalFileNameFromWebSite(String website) {
		if(website==null||"".equals(website))
			return null;
		String filename = getFileNameByWebSite(website);
		if (filename == null || "".equals(filename))
			return null;
		if (checkFileNameIsContainChinese(filename)) {
			filename = getNewFileName(filename);
			return filename;
		}
		if (checkIsExitSameNameFile(filename)) {
			filename = getNewFileName(filename);
			return filename;
		}
		return filename;
	}
	
	
	/**
	 * 检查图片地址是否指向coo8 
	 * 
	 * img1.gomein.net.cn
	 * img2.gomein.net.cn
	 * img3.gomein.net.cn
	 * img4.gomein.net.cn
	 * img5.gomein.net.cn
	 * img6.gomein.net.cn
	 * img7.gomein.net.cn
	 * img8.gomein.net.cn
	 * img9.gomein.net.cn
	 *   
	 * @return
	 */
	public static boolean checkWebSiteIsCoo8Own(String website){
		if(website==null||"".equals(website))
			return false;
		boolean flag = false;
		
		String urlRegex = "(http://)?img[\\d]{1}.gomein.net.cn";
		
		if(website.trim().matches(urlRegex)){
			flag = true;
		}
		
		return flag;
	}
	/**
	 * 依据网址获得图片
	 * 合并操作
	 */
	public static String getImgPathFromWebSite(String website){
		if(website==null||"".equals(website))
			return null;
	     if (checkWebSiteIsCoo8Own(website))
	    	 return null;
	     String filename = getLegalFileNameFromWebSite(website);
	     String path = getImageByWebSite(website,filename);
		return path;
	}
	
	/**
	 * 验证指定路径的文件是否是图片
	 */
	public static boolean checkFileIsImage(String filepath) {
		if (filepath == null || "".equals(filepath))
			return false;
		File file = new File(filepath);
		if (!file.exists() || !file.canRead())
			return false;
		BufferedImage bi = null;
		try {
			// 检查文件是否是图片
			bi = javax.imageio.ImageIO.read(file);
			try {
				// 判断文件图片是否能正常显示,有些图片编码不正确
				bi.getType();
				return true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public static String getMaskUrl() {
		return maskUrl;
	}

	public static void setMaskUrl(String maskUrl) {
		ReloadImgUtils.maskUrl = maskUrl;
	}
		
}
