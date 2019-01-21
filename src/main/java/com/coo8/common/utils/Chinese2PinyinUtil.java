package com.coo8.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Chinese2PinyinUtil {
	
	private  static Logger logger = LoggerFactory.getLogger(Chinese2PinyinUtil.class);
	
	private Chinese2PinyinUtil(){}
	/**
	 * 
	 * ����ת����ƴ��
	 * @param ChineseStr ����
	 * @return
	 */
	public static String parseChinese(String ChineseStr){
		StringBuilder pinyin = new StringBuilder("");
		
		//����ƴ����ʽ�����  
		HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
		hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);    //ƴ����ʽΪ��д
		hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //ƴ����������
		hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);     //V����ƴ������		
		ChineseStr = QuanBanSwitch(ChineseStr.trim()); //ȫ��ת���
		
		char[] chrArr = ChineseStr.toCharArray();
		
		try {
			
			for(int i=0;i<chrArr.length;i++){
				char c = chrArr[i];  
				if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")){//���ֽ��д���
					
					String[] ret = PinyinHelper.toHanyuPinyinStringArray(chrArr[i],hanYuPinOutputFormat);
					if(null != ret && ret[0] != null && !"".equals(ret[0])){
						pinyin.append(ret[0].trim());
					}
					
				}else{//�����ַ�ԭ�����
					pinyin.append(c);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			logger.error(e.getMessage(), e);
		}
		return pinyin.toString();
	}
	
	/**
	 * Unicode ȫ��ת���
	 * @param QJstr
	 * @return
	 */
	public static final String QuanBanSwitch(String QJstr) {   
        String outStr = "";   
        String Tstr = "";   
        byte[] b = null;   
  
        for (int i = 0; i < QJstr.length(); i++) {   
            try {   
                Tstr = QJstr.substring(i, i + 1);   
                b = Tstr.getBytes("unicode");   
            } catch (java.io.UnsupportedEncodingException e) {   
            	logger.error(e.getMessage(), e);   
            }   
            if(b[2] == -1) {
                b[3] = (byte) (b[3] + 32);   
                b[2] = 0;   
                try {   
                    outStr = outStr + new String(b, "unicode");   
                } catch (java.io.UnsupportedEncodingException e) {   
                	logger.error(e.getMessage(), e);   
                }   
            } else  
                outStr = outStr + Tstr;   
        }   
        return outStr;   
    }  
}
