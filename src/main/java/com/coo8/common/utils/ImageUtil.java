package com.coo8.common.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ImageUtil {
	private  static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	private ImageUtil(){}
	public static void markImage(String maskUrl,String imageUrl){
		
		if(null != maskUrl && !"".equals(maskUrl) && null != imageUrl && !"".equals(imageUrl)){
			File file  = new File(imageUrl);
			if(file.exists()){
				markImageByIcon(maskUrl,imageUrl,imageUrl,null);
			}
		}else{
			logger.info("输入参数有误  maskUrl:" + maskUrl + ";imageUrl:"+imageUrl);
		}
	}
	/**
     * 给图片添加水印、可设置水印图片旋转角度
     * @param iconPath 水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     * @param degree 水印图片旋转角度
     */ 
    private static void markImageByIcon(String iconPath, String srcImgPath, 
            String targerPath, Integer degree) { 
        try { 
            Image srcImg = ImageIO.read(new File(srcImgPath)); 
            int imgWidth = srcImg.getWidth(null);
            int imgHeight =srcImg.getHeight(null);
            BufferedImage buffImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB); 
 
            // 得到画笔对象 
            Graphics2D g = buffImg.createGraphics(); 
 
            // 设置对线段的锯齿状边缘处理 
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
 
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg 
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
 
            if (null != degree) { 
                // 设置水印旋转 
                g.rotate(Math.toRadians(degree), 
                        (double) buffImg.getWidth() / 2, (double) buffImg 
                                .getHeight() / 2); 
            } 
 
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度 
            ImageIcon imgIcon = new ImageIcon(iconPath); 
 
            // 得到Image对象。 
            Image img = imgIcon.getImage(); 
 
            float alpha = 1.0f; // 透明度 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 
                    alpha)); 
 
            // 表示水印图片的位置 
            int iconWidth =  img.getWidth(null);
            int iconHeight = img.getHeight(null);
            
            double scale = 1.0d;
            //设置水印缩放
           
            int startX = (imgWidth - (int)(iconWidth*scale))/2;
            int startY = (imgHeight - (int)(iconHeight*scale))/2;
            g.drawImage(img, startX, startY,(int)(iconWidth*scale),(int)(iconHeight*scale), null); 
 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
 
            g.dispose(); 
            String formatName = targerPath.substring(targerPath.lastIndexOf(".") + 1);
			ImageIO.write(buffImg, /* "GIF" */ formatName /* format desired */ , new File(targerPath) /* target */ );
            logger.info("图片完成添加Icon印章。。。。。。");
            
        } catch (Exception e) { 
        	logger.error(e.getMessage(), e); 
        }
    } 

}
