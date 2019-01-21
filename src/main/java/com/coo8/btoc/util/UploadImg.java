package com.coo8.btoc.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.controller.action.admin.block.BlockPublishAction;

public class UploadImg {
	
	private  static Logger logger = LoggerFactory.getLogger(BlockPublishAction.class);
	/**
	 * 1-5�������
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
	 * �ϴ���ǩͼƬ 2011-10-21 13:23:19 songfan
	 * 
	 */
	public  String uploadImg(File upload,String uploadFileName ,Integer imgLimit) {
		String typ=uploadFileName.substring(uploadFileName.indexOf(".") + 1);
		File file = new File("/app/deploy/btoc_activity/images/tablibs/");//�ϴ�����Ŀ¼
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
	 * �ϴ� ͷͼ��ר��ͼ 2011-10-21 13:22:59 songfan
	 * @param upload
	 * @param uploadFileName
	 * @return
	 */
	public  String uploadImg2(File upload,String uploadFileName ) {
		String typ=uploadFileName.substring(uploadFileName.indexOf(".") + 1);
		File file = new File("/app/deploy/btoc_activity/images/zt2011090501");//�ϴ�����Ŀ¼
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
	 * �ϴ����� ͷͼ
	 * @param upload
	 * @param uploadFileName
	 * @return
	 */
	public  String uploadImg3(File upload,String uploadFileName,String rename) {
		String typ=uploadFileName.substring(uploadFileName.indexOf(".") + 1);
		File file = new File("/app/deploy/btoc_activity/images/wenzhagn2017");//�ϴ�����Ŀ¼
		 file.setExecutable(true);//���ÿ�ִ��Ȩ��  
         file.setReadable(true);//���ÿɶ�Ȩ��  
         file.setWritable(true);//���ÿ�дȨ��  
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
