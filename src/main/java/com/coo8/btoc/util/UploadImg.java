package com.coo8.btoc.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.controller.action.admin.block.BlockPublishAction;

public class UploadImg {
	
	private  static Logger logger = LoggerFactory.getLogger(BlockPublishAction.class);
	/**
	 * 1-5的随机数
	 * @return
	 */
	public static int rd(){
		int i=(int) (Math.random() * 6);
		if(i==0){
			return rd();
		}
		return i;
	}
	/**
	 * 上传标签图片 2011-10-21 13:23:19 songfan
	 * 
	 */
	public  String uploadImg(File upload,String uploadFileName ,Integer imgLimit) {
		String typ=uploadFileName.substring(uploadFileName.indexOf(".") + 1);
		File file = new File("/app/deploy/btoc_activity/images/tablibs/");//上传到的目录
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			byte[] l = org.apache.commons.io.FileUtils.readFileToByteArray(upload);
			if (l.length > imgLimit) {
				return "big";
			}
			if("png".equals(typ.trim())||"jpg".equals(typ.trim())||"gif".equals(typ.trim())||"bmp".equals(typ.trim())){
				org.apache.commons.io.FileUtils.copyFile(upload, new File(file,
						uploadFileName));
			}else{
				return "typ";
			}			
		} catch (Exception e) {		
			logger.error(e.getMessage(), e);
		}
		return "http://app.gome.com.cn/topics/images/actnew/tablibs/";
	}

	/**
	 * 上传 头图，专题图 2011-10-21 13:22:59 songfan
	 * @param upload
	 * @param uploadFileName
	 * @return
	 */
	public  String uploadImg2(File upload,String uploadFileName ) {
		String typ=uploadFileName.substring(uploadFileName.indexOf(".") + 1);
		File file = new File("/app/deploy/btoc_activity/images/zt2011090501");//上传到的目录
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			byte[] l = org.apache.commons.io.FileUtils.readFileToByteArray(upload);
			if (l.length > 1024000) {
				return "big";
			}
			if("png".equals(typ.trim())||"jpg".equals(typ.trim())||"gif".equals(typ.trim())||"bmp".equals(typ.trim())){
				org.apache.commons.io.FileUtils.copyFile(upload, new File(file,
						uploadFileName));
			}else{
				return "typ";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "http://p"+this.rd()+".51mdq.com/img/actnew/zt2011090501";
	}
	/**
	 * 上传文章 头图
	 * @param upload
	 * @param uploadFileName
	 * @return
	 */
	public  String uploadImg3(File upload,String uploadFileName,String rename) {
		String typ=uploadFileName.substring(uploadFileName.indexOf(".") + 1);
		File file = new File("/app/deploy/btoc_activity/images/wenzhagn2017");//上传到的目录
		 file.setExecutable(true);//设置可执行权限  
         file.setReadable(true);//设置可读权限  
         file.setWritable(true);//设置可写权限  
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			byte[] l = org.apache.commons.io.FileUtils.readFileToByteArray(upload);
			if (l.length > 1024000) {
				return "big";
			}
			if("png".equals(typ.trim())||"jpg".equals(typ.trim())||"gif".equals(typ.trim())||"bmp".equals(typ.trim())){
				org.apache.commons.io.FileUtils.copyFile(upload, new File(file,
						rename+"."+typ));
			}else{
				return "typ";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "http://app.gome.com.cn/topics/images/actnew/wenzhagn2017/"+rename+"."+typ;
	}
}
