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
			logger.info("�����������  maskUrl:" + maskUrl + ";imageUrl:"+imageUrl);
		}
	}
	/**
     * ��ͼƬ���ˮӡ��������ˮӡͼƬ��ת�Ƕ�
     * @param iconPath ˮӡͼƬ·��
     * @param srcImgPath ԴͼƬ·��
     * @param targerPath Ŀ��ͼƬ·��
     * @param degree ˮӡͼƬ��ת�Ƕ�
     */ 
    private static void markImageByIcon(String iconPath, String srcImgPath, 
            String targerPath, Integer degree) { 
        try { 
            Image srcImg = ImageIO.read(new File(srcImgPath)); 
            int imgWidth = srcImg.getWidth(null);
            int imgHeight =srcImg.getHeight(null);
            BufferedImage buffImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB); 
 
            // �õ����ʶ��� 
            Graphics2D g = buffImg.createGraphics(); 
 
            // ���ö��߶εľ��״��Ե���� 
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
 
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg 
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
 
            if (null != degree) { 
                // ����ˮӡ��ת 
                g.rotate(Math.toRadians(degree), 
                        (double) buffImg.getWidth() / 2, (double) buffImg 
                                .getHeight() / 2); 
            } 
 
            // ˮӡͼ���·�� ˮӡһ��Ϊgif����png�ģ�����������͸���� 
            ImageIcon imgIcon = new ImageIcon(iconPath); 
 
            // �õ�Image���� 
            Image img = imgIcon.getImage(); 
 
            float alpha = 1.0f; // ͸���� 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 
                    alpha)); 
 
            // ��ʾˮӡͼƬ��λ�� 
            int iconWidth =  img.getWidth(null);
            int iconHeight = img.getHeight(null);
            
            double scale = 1.0d;
            //����ˮӡ����
           
            int startX = (imgWidth - (int)(iconWidth*scale))/2;
            int startY = (imgHeight - (int)(iconHeight*scale))/2;
            g.drawImage(img, startX, startY,(int)(iconWidth*scale),(int)(iconHeight*scale), null); 
 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
 
            g.dispose(); 
            String formatName = targerPath.substring(targerPath.lastIndexOf(".") + 1);
			ImageIO.write(buffImg, /* "GIF" */ formatName /* format desired */ , new File(targerPath) /* target */ );
            logger.info("ͼƬ������Iconӡ�¡�����������");
            
        } catch (Exception e) { 
        	logger.error(e.getMessage(), e); 
        }
    } 

}
