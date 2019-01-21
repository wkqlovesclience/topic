/*
 * �ļ����� StringUtil.java
 * 
 * �������ڣ� 2010-3-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * ԭʼ����: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.StringTokenizer;

import org.apache.oro.text.perl.Perl5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public final class StringUtil {
	private  static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	private StringUtil(){
		
	}

	/**
	 * ��ָ�����ַ�������URL
	 * 
	 * @param url
	 *            Ҫ�����URL
	 * @param charset
	 *            �ַ���
	 * @return ������URL
	 */
	public static String encodeURL(String url, String charset) {
		if ( url != null && url.length() > 0 ) {
			try {
				return URLEncoder.encode(url, charset);
			}
			catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
				return url;
			}
		}
		return url;
	}

	/**
	 * ��ָ�����ַ���������ַ����ĳ���
	 * 
	 * @param txt
	 *            Ҫ�������ַ���
	 * @param charset
	 *            ����
	 * @return �ַ����ĳ���
	 */
	public static int getStrLength(String txt, String charset) {
		try {
			return txt.getBytes(charset).length;
		}
		catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return txt.length();
		}
	}

	/**
	 * ȥ��ָ���ַ�������β�����ַ�
	 * 
	 * @param source
	 *            ָ���ַ���
	 * @param beTrim
	 *            Ҫȥ���������ַ�
	 * @return ȥ�������ַ�����ַ���
	 */
	public static String trimStringWithAppointedChar(String source,
			String beTrim) {
		if ( !"".equalsIgnoreCase(source) ) {
			// ѭ��ȥ���ַ����׵�beTrim�ַ�
			String beginChar = source.substring(0, 1);
			while (beginChar.equalsIgnoreCase(beTrim)) {
				source = source.substring(1, source.length());
				beginChar = source.substring(0, 1);
			}

			// ѭ��ȥ���ַ���β��beTrim�ַ�
			String endChar = source.substring(source.length() - 1, source
					.length());
			while (endChar.equalsIgnoreCase(beTrim)) {
				source = source.substring(0, source.length() - 1);
				endChar = source
						.substring(source.length() - 1, source.length());
			}
		}
		return source;
	}

	/**
	 * ȥ��ָ���ַ�������β�����ַ�
	 * 
	 * @param source
	 *            ָ���ַ���
	 * @param beTrim
	 *            Ҫȥ���������ַ�
	 * @param endTrim
	 *            Ҫȥ�������ַ�
	 * @return ȥ�������ַ�����ַ���
	 */
	public static String trimStringWithAppointedChar(String source,
			String beTrim, String endTrim) {
		if ( !"".equalsIgnoreCase(source) ) {
			// ѭ��ȥ���ַ����׵�beTrim�ַ�
			String beginChar = source.substring(0, 2);
			while (beginChar.equalsIgnoreCase(beTrim)) {
				source = source.substring(2, source.length());
				beginChar = source.substring(0, 2);
			}

			// ѭ��ȥ���ַ���β��beTrim�ַ�
			String endChar = source.substring(source.length() - 1, source
					.length());
			while (endChar.equalsIgnoreCase(endTrim)) {
				source = source.substring(0, source.length() - 1);
				endChar = source
						.substring(source.length() - 1, source.length());
			}
		}
		return source;
	}

	/**
	 * ��sign�ָ��ַ���data(���ж�data�Ƿ����˫���ŵ�)
	 * 
	 * @param data
	 *            Ҫ��ֵ��ַ���
	 * @param sign
	 *            �ָ���
	 * @return list �ָ����List
	 */
	public static List<String> spit(String data, String sign) {
		StringTokenizer stkzer = new StringTokenizer(data, sign);
		String temp = "";
		List<String> list = new ArrayList<String>();
		while (stkzer.hasMoreTokens()) {
			temp = stkzer.nextToken();
			list.add(temp);
		}
		return list;
	}

	/**
	 * ��sign�ָ��ַ���data(data�Ǵ���˫���ŵ�) ��:"system_id","type","command_line",
	 * 
	 * @param data
	 *            Ҫ��ֵ��ַ���
	 * @param sign
	 *            �ָ���
	 * @return list �ָ����List
	 */
	public static List<String> spitWithQuotationMark(String data, String sign) {

		List<String> keysWithQuotationMark = new ArrayList<String>();

		String[] tempData = data.split(sign);
		for (int i = 0; i < tempData.length; i++) {
			keysWithQuotationMark.add(tempData[i]);
		}

		List<String> keys = new ArrayList<String>();
		// ��ʱ�õ���keyֵ�б��Ǵ���˫���ŵģ��±ߵ�ѭ����˫����ȥ��
		for (int i = 0; i < keysWithQuotationMark.size(); i++) {
			String eachKey = (String) keysWithQuotationMark.get(i);
			String key = "";
			if ( eachKey.length() != 0
					&& "\"".equalsIgnoreCase(eachKey.substring(0, 1)) ) {
				eachKey = eachKey.substring(1, eachKey.length());
			}
			if ( eachKey.length() != 0
					&& 
					"\"".equalsIgnoreCase(eachKey.substring(eachKey.length() - 1)) ) {
				eachKey = eachKey.substring(0, eachKey.length() - 1);
			}
			key = eachKey;

			keys.add(key);
		}
		return keys;
	}

	/**
	 * �ж��ַ���Ϊnull����Ϊ""
	 * 
	 * @param value
	 *            Ҫ�жϵ��ַ���
	 * @return �Ƿ�Ϊnull����Ϊ""
	 */
	public static boolean isNullorBlank(String value) {
		return null == value || "".equals(value);
	}

	/**
	 * ȥ��ָ���ַ������˵Ŀո�
	 * 
	 * @param value
	 *            ָ�����ַ���
	 * @return ȥ�����˿ո����ַ�������������ָ���ַ�����null������""��
	 */
	public static String trim(String value) {
		if ( value == null ) {
			return "";
		} else {
			return value.trim();
		}
	}

	/**
	 * ��ָ���ַ��������˼��ϵ�����"'"
	 * 
	 * @param value
	 *            ָ�����ַ���
	 * 
	 * @return �ӹ������ŵ��ַ��������������ַ�����null������null��
	 */
	public static String sem(String value) {
		if ( value == null ) {
			return null;
		} else {
			return "'" + value + "'";

		}
	}

	/**
	 * ��ָ��������ת��Ϊָ�����ȵ��ַ��������ಿ����"#"��䡣���磺intToStrWithSharp(1000, 6)->"##1000"
	 * 
	 * @param value
	 *            Ҫת��������
	 * @param length
	 *            ת������ַ�������
	 * @return ת������ַ��������ָ���ĳ���С��������λ������ֻ�������֡����磺intToStrWithSharp(1000,
	 *         2)->"1000"
	 */
	public static String intToStrWithSharp(Integer value, int length) {
		int valueLength = value.toString().length();
		int diff = length - valueLength;

		if ( value.intValue() < Integer.MAX_VALUE ) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < diff; i++) {
				sb.append('#');
			}
			sb.append(value.intValue());
			return sb.toString();
		} else {
			return "-1";
		}
	}

	/**
	 * �ж�һ��String�����Ƿ�Ϊnull��Ϊnull����""�����򷵻�str����
	 * 
	 * @param str
	 *            Ҫ�жϵ�String����
	 * @return str�����""
	 */
	public static String getEmptyStringIfNull(String str) {
		if ( str == null )
			return "";
		return str;
	}

	/**
	 * ��һ��byte����ת��Ϊ�ַ���
	 * 
	 * @param arr
	 *            Ҫת����byte����
	 * @return ת���õ��ַ�������������length=0���򷵻�""��
	 */
	public static String bytetoString(byte[] arr) {
		String str = "";
		for (int i = 1; i < arr.length; i++) {			 
			if ( Integer.toHexString(arr[i] & 0xff).length() == 1 ) {
				str = str + "0" + Integer.toHexString(arr[i] & 0xff);;
			} else {
				str = str + Integer.toHexString(arr[i] & 0xff);;
			}
		}
		return str;
	}

	/**
	 * �����ַ����õ�Integer.
	 * 
	 * @param str1
	 *            String
	 * @return Integer
	 */
	public static Integer myparseIntObj(String str1) {
		try {
			if ( isBlank(str1) ) {
				return null;
			} else {
				// 16����
				if ( str1.startsWith("0x") ) {
					String sLast = str1.substring(2);
					return Integer.valueOf(sLast, 16);
				} else {
					return Integer.valueOf(str1);
				}
			}
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * ����һ���ַ���,�õ�һ������,�������,����Ϊȱʡֵ-1.
	 * 
	 * @param str1
	 *            String
	 * @return int
	 */
	public static int myparseInt(String str1) {
		return myparseInt(str1, -1);
	}

	/**
	 * ����һ���ַ���,�õ�һ������,�������,����Ϊȱʡֵ. ���һ���ַ�����0x��ͷ,����Ϊ��16���Ƶ�.
	 * 
	 * @param str1
	 *            �ַ���
	 * @param nDefault
	 *            ȱʡֵ
	 * @return int
	 */
	public static int myparseInt(String str1, int nDefault) {
		int result;
		try {
			if ( isBlank(str1) ) {
				result = nDefault;
			} else {
				// 16����
				if ( str1.startsWith("0x") ) {
					String sLast = str1.substring(2);
					result = Integer.parseInt(sLast, 16);
				} else {
					result = Integer.parseInt(str1);
				}
			}
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			result = nDefault;
		}
		return result;
	}

	/**
	 * ����һ���ַ����õ�float,�������,����һ��ȱʡֵ-1.
	 * 
	 * @param str1
	 *            String
	 * @return float
	 */
	public static float myparseFloat(String str1) {
		return myparseFloat(str1, -1);
	}

	/**
	 * ����һ���ַ����õ�float,�������,����һ��ȱʡֵ.
	 * 
	 * @param str1
	 *            String
	 * @param nDefault
	 *            ȱʡֵ
	 * @return float
	 */
	public static float myparseFloat(String str1, float nDefault) {
		float result;
		try {
			result = isBlank(str1) ? nDefault : Float.parseFloat(str1);
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			result = nDefault;
		}
		return result;
	}

	/**
	 * ����һ���ַ����õ�Float,�������,����null.
	 * 
	 * @param str1
	 *            String
	 * @return Float(may be null)
	 */
	public static Float myparseFloatObj(String str1) {
		try {
			if ( isBlank(str1) ) {
				return null;
			} else {
				return Float.valueOf(str1);
			}
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * ����һ���ַ����õ�long,�������,����һ��ȱʡֵ -1.
	 * 
	 * @param str1
	 *            String
	 * @return long
	 */
	public static long myparseLong(String str1) {
		return myparseLong(str1, -1);
	}

	/**
	 * ����һ���ַ����õ�long,�������,����һ��ȱʡֵ .
	 * 
	 * @param str1
	 *            �ַ���
	 * @param nDefault
	 *            ȱʡֵ
	 * @return long
	 */
	public static long myparseLong(String str1, long nDefault) {
		long result;
		try {
			result = isBlank(str1) ? nDefault : Long.parseLong(str1);
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			result = nDefault;
		}
		return result;
	}

	/**
	 * ����һ���ַ����õ�Long,�������,����null .
	 * 
	 * @param str1
	 *            �ַ���
	 * @return Long
	 */
	public static Long myparseLongObj(String str1) {
		try {
			if ( isBlank(str1) ) {
				return null;
			} else {
				// 16����
				if ( str1.startsWith("0x") ) {
					String sLast = str1.substring(2);
					return Long.valueOf(sLast, 16);
				} else {
					return Long.valueOf(str1);
				}
			}
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Ϊ��ʾ/�༭��ת����ֵ�����ն���ת��Ϊ�մ�.
	 * 
	 * @param astr
	 *            �ַ�����ֵ
	 * @return ����ַ���Ϊ��,�򷵻ؿմ�(����null),�������,ԭ������
	 */
	public static String getShowStr(String astr) {
		return (null == astr) ? "" : astr;
	}

	/**
	 * ����һ���ַ�����n����Ϻ���ַ���.
	 * 
	 * @param sStr
	 *            ԭ�ַ���
	 * @param nRate
	 *            ����
	 * @return ��Ϻõ��ַ���
	 */
	public static String getManyStr(String sStr, int nRate) {
		StringBuilder strBF = new StringBuilder();
		for (int i = 0; i < nRate; i++) {
			strBF.append(sStr);
		}
		return strBF.toString();
	}

	/**
	 * ��ʽ������:���ض������ַ���.
	 * 
	 * @param aNum
	 *            ��ʽ��������
	 * @param aLength
	 *            ����
	 * @return ��ʽ���õ��ַ���.
	 */
	public static String formatNumber(int aNum, int aLength) {
		String sNum = Integer.toString(aNum);

		int nLength = aLength - sNum.length();
		if ( nLength < 1 ) {
			return sNum;
		}

		for (int i = 1; i <= nLength; i++) {
			sNum = "0" + sNum;
		}
		return sNum;
	}

	/**
	 * ���ݸ�ʽ������������ַ���.
	 * 
	 * @param aFloat
	 *            ������
	 * @param nSyo
	 *            �ַ�����ʽ,�ο�NumberFormat��˵��.
	 * @return String
	 */
	public static String getShowFloat(float aFloat, String nSyo) {
		NumberFormat astr = NumberFormat.getInstance();
		((DecimalFormat) astr).applyPattern(nSyo);

		return astr.format(aFloat);
	}

	/**
	 * �����������ȡһ���ַ�������,�����,����ȱʡֵ.
	 * 
	 * @param aPROP
	 *            ���Ծ��
	 * @param itemName
	 *            ��������
	 * @param sDefault
	 *            ȱʡֵ
	 * @return String
	 */
	public static String getPROPString(PropertyResourceBundle aPROP,
			String itemName, String sDefault) {
		String aValue = "";
		try {
			if ( null != aPROP ) {
				aValue = aPROP.getString(itemName);
			}
		}
		catch (MissingResourceException e) {
			logger.error(e.getMessage(), e);
		}

		if ( isTrimEmpty(aValue) ) {
			aValue = sDefault;
		}
		return aValue;
	}

	/**
	 * �����������ȡһ���ַ�������,�����,����"".
	 * 
	 * @param aPROP
	 *            ���Ծ��
	 * @param itemName
	 *            ��������
	 * @return String
	 */
	public static String getPROPString(PropertyResourceBundle aPROP,
			String itemName) {
		return getPROPString(aPROP, itemName, "");
	}

	/**
	 * ����һ���ַ�����Ŀ�����.
	 * 
	 * ���ȱʡ����Ϊ��,������ȱʡ����ΪԴ����.
	 * 
	 * @param aStr
	 *            Դ�ַ���
	 * @param sDefaultEncode
	 *            ȱʡ����
	 * @param srcCharSet
	 *            Դ����
	 * @param destCharSet
	 *            Ŀ�����
	 * @return �������ַ���
	 */
	public static String getEXTCHARSETString(String aStr,
			String sDefaultEncode, String srcCharSet, String destCharSet) {
		String lastDefaultEncode = sDefaultEncode;
		String strTemp = null;

		try {
			strTemp = aStr;

			if ( isBlank(lastDefaultEncode) ) {
				lastDefaultEncode = srcCharSet;
			}
			// ���Դ�ַ���������Ŀ���ַ���
			if ( !(sDefaultEncode.equalsIgnoreCase(destCharSet)) ) {
				if ( strTemp != null ) {
					if ( isTrimEmpty(lastDefaultEncode) ) {
						strTemp = new String(strTemp.getBytes(), destCharSet);
					} else {
						strTemp = new String(strTemp
								.getBytes(lastDefaultEncode), destCharSet);
					}
				}
			}
		}
		catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}

		return (strTemp == null) ? "" : strTemp;
	}

	/**
	 * �Ա������ַ������н���.
	 * 
	 * @param astr
	 *            String
	 * @param encoding
	 *            ����
	 * @return String
	 */
	public static String urlDecode(String astr, String encoding) {
		if ( isBlank(astr) ) {
			return "";
		}
		String aRes = astr;
		try {
			aRes = URLDecoder.decode(astr, encoding);
			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return aRes;
	}

	/**
	 * ���ַ�������url����.
	 * 
	 * 1.3��1.4��ͬ,����GBK����. ����л���1.4���ڿ�������һ������.
	 * 
	 * @param astr
	 *            Դ�ַ���
	 * @param encoding
	 *            ����
	 * @return String
	 */
	public static String urlEncode(String astr, String encoding) {
		if ( isBlank(astr) ) {
			return "";
		}
		String aRes = astr;
		try {
			
			aRes = URLEncoder.encode(astr, encoding);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return aRes;
	}

	/**
	 * �ַ����Ƿ�Ϊ��:null���߳���Ϊ0.
	 * 
	 * @param astr
	 *            Դ�ַ���.
	 * @return boolean
	 */
	public static boolean isBlank(String astr) {
		return (null == astr) || (astr.length() == 0);
	}

	/**
	 * ȥ�����ҿո���ַ����Ƿ�Ϊ��.
	 * 
	 * @param astr
	 *            String
	 * @return boolean
	 */
	public static boolean isTrimEmpty(String astr) {
		if ( (null == astr) || (astr.length() == 0) ) {
			return true;
		}
		if ( isBlank(trim(astr)) ) {
			return true;
		}
		return false;
	}

	/**
	 * ���չ������ַ������ַ���������.
	 * 
	 * @param pattern
	 *            ��ֹ���,��ʽΪ: /,/
	 * @param aStr
	 *            Ҫ��ֵ��ַ���
	 * @return ��ֺ���ַ�������
	 */
	public static String[] splitString(String pattern, String aStr) {
		List<Object> alist = new ArrayList<Object>();
		Perl5Util util = new Perl5Util();
		util.split(alist, pattern, aStr);
		String[] astrs = new String[alist.size()];
		alist.toArray(astrs);
		return astrs;
	}

	/**
	 * ���չ������ַ������ַ���������.
	 * 
	 * @param pattern
	 *            ��ֹ���,��ʽΪ: /,/
	 * @param aStr
	 *            Ҫ��ֵ��ַ���
	 * @param nLimit
	 *            ��ִ���
	 * @return ��ֺ���ַ�������
	 */
	public static String[] splitString(String pattern, String aStr, int nLimit) {
		List<Object> alist = new ArrayList<Object>();
		Perl5Util util = new Perl5Util();
		util.split(alist, pattern, aStr, nLimit);
		String[] astrs = new String[alist.size()];
		alist.toArray(astrs);
		return astrs;
	}

	/**
	 * ����ַ����������б���.
	 * 
	 * @param pattern
	 *            ��ֹ���
	 * @param aStr
	 *            Ҫ��ֵ��ַ���
	 * @return ��ֺ�������б�
	 */
	public static List splitStrToList(String pattern, String aStr) {
		List alist = new ArrayList();
		Perl5Util util = new Perl5Util();
		util.split(alist, pattern, aStr);
		return alist;
	}

	/**
	 * ��ȡһ�����ȵ��ַ���,����ָ���ı������жϳ���. ����ָ������ΪGBK,��һ������Ϊ2���ַ�����
	 * 
	 * @param astr
	 *            String
	 * @param nlength
	 *            int
	 * @param destEncode
	 *            String
	 * @return String
	 */
	public static String msubstr(String astr, int nlength, String destEncode) {
		byte[] mybytes;
		try {
			mybytes = astr.getBytes(destEncode);

			if ( mybytes.length <= nlength ) {
				return astr;
			}
			if ( nlength < 1 ) {
				return "";
			}

			for (int i = 0; i < astr.length(); i++) {
				String aTestStr = astr.substring(0, i + 1);
				int nDestLength = aTestStr.getBytes(destEncode).length;
				if ( nDestLength > nlength ) {
					if ( i == 0 ) {
						return "";
					} else {
						return astr.substring(0, i);
					}
				}
			}

			return astr;
		}
		catch (java.io.UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}

	/**
	 * ���ش�ʡ�Ա�ǵĽضϵ��ַ���.
	 * 
	 * @param astr
	 *            Դ�ַ���
	 * @param nlength
	 *            �ضϵĳ���
	 * @param aDot
	 *            ��׺
	 * @param encoding
	 *            ����
	 * @return String
	 */
	public static String getDotMsubstr(String astr, int nlength, String aDot,
			String encoding) {
		byte[] mybytes = astr.getBytes();

		if ( mybytes.length <= nlength ) {
			return astr;
		}

		int nLastLen = nlength - aDot.length();

		if ( nLastLen < 1 ) {
			nLastLen = 1;

		}
		return msubstr(astr, nLastLen, encoding) + aDot;

	}

	/**
	 * �õ��ַ������ַ����� ����ָ������ⶨ.
	 * 
	 * @param astr
	 *            String
	 * @param sDestEncode
	 *            String
	 * @return int
	 */
	public static int mlength(String astr, String sDestEncode) {
		try {
			return astr.getBytes(sDestEncode).length;
		}
		catch (java.io.UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return astr.getBytes().length;
		}
	}

	/**
	 * ����2���ַ���.
	 * 
	 * @param aOriStr
	 *            Դ�ַ���
	 * @param aLinkSign
	 *            ���ӱ��
	 * @param aLinkStr
	 *            Ҫ���ӵ��ַ���
	 * @return String
	 */
	public static String link2Str(String aOriStr, String aLinkSign,
			String aLinkStr) {
		if ( isBlank(aOriStr) ) {
			return aLinkStr;
		} else {
			return aOriStr + aLinkSign + aLinkStr;
		}
	}

	/**
	 * �����ַ�������.
	 * 
	 * @param astrBf
	 *            StringBuilder
	 * @param aryStr
	 *            String[]
	 * @return StringBuilder
	 */
	public static StringBuilder linkAryStr(StringBuilder astrBf, String[] aryStr) {
		for (int i = 0; i < aryStr.length; i++) {
			astrBf.append(aryStr[i]);
		}
		return astrBf;
	}

	/**
	 * �����ַ�������.
	 * 
	 * @param aryStr
	 *            String[]
	 * @param sSign
	 *            String
	 * @return String
	 */
	public static String linkAryStr(String[] aryStr, String sSign) {
		StringBuilder asbf = new StringBuilder();
		if ( null == aryStr ) {
			return asbf.toString();
		}

		for (int i = 0; i < aryStr.length; i++) {
			if ( i > 0 ) {
				asbf.append(sSign);
			}
			asbf.append(aryStr[i]);
		}
		return asbf.toString();
	}

	/**
	 * �ַ����滻,��dest�滻astr�����Sign.
	 * 
	 * @param str1
	 *            Դ�ַ���
	 * @param sign
	 *            Ҫ���滻�ı��
	 * @param dest
	 *            �滻��ı��
	 * @return String
	 */
	public static String replace(String str1, String sign, String dest) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}

		Perl5Util util = new Perl5Util();
		String lastSign = sign.replace('`', ' ');
		String lastDest = dest.replace('`', ' ');
		astr = util.substitute("s`" + lastSign + "`" + lastDest + "`g", astr);

		return astr;
	}

	/**
	 * ���û����������ԭ����ʾ,����html����html��ʽ��ʾ. ��Ӧԭ���toTextSee. ���а�������س�,�ո�,Tab��,������
	 * <Ϊ&lt;
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2TextShow(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}

		Perl5Util util = new Perl5Util();
		astr = util.substitute("s#\t#&nbsp;&nbsp;#g", astr);
		astr = util.substitute("s#\r##g", astr);

		astr = util.substitute("s#\n{3,}#\n\n#g", astr);
		astr = util.substitute("s#^\n+#\n#g", astr);

		astr = util.substitute("s#\n#<br>#g", astr);

		astr = util.substitute("s#\\s\\s\\s#&nbsp; &nbsp;#g", astr);
		astr = util.substitute("s#\\s\\s#&nbsp;&nbsp;#g", astr);

		return astr;

	}

	/**
	 * ���ַ�������,��ȫ��������ʱ��ԭ����ʾ. html��ʽ�Ĳ���Ҳ����Ϊ�ı�.
	 * 
	 * @param aStr
	 *            String
	 * @return String
	 */
	public static String str2PureTextShow(String aStr) {
		return str2TextShow(str2TextHtml(aStr));
	}

	/**
	 * ���ַ�������,����༭.
	 * 
	 * @param aStr
	 *            String
	 * @return String
	 */
	public static String str2InputTextEdit(String aStr) {
		return getShowStr(str2InputText(aStr));
	}

	/**
	 * �����ַ���,���봦��,��Ҫ����html���ӵ�ֵ.
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2URLValue(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}
		// ֱ�ӱ���
		astr = str2TextHtmlRidSpace(astr);

		return astr;
	}

	/**
	 * �����ַ���,���봦��,��Ҫ����html�����ڲ�������ֵ. ����javascript�Ĳ���
	 * 
	 * %22 => " %3c => < %3e => > &amp; =>& %20 =>�ո�
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2JSUrlFuncValue(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}
		Perl5Util util = new Perl5Util();
		astr = util.substitute("s#\"#%22#g", astr);
		astr = util.substitute("s/</%3c/g", astr);
		astr = util.substitute("s/>/%3e/g", astr);
		astr = util.substitute("s/&/&amp;/g", astr);
		astr = util.substitute("s/ /%20/g", astr);
		return astr;
	}

	/**
	 * �����ַ���,���봦��,��Ҫ����onclick,onchange��javascript��ֵ.
	 * 
	 * ���ű��滻Ϊ \&quot;
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2JSCommValue(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}

		Perl5Util util = new Perl5Util();
		astr = util.substitute("s/&/&amp;/g", astr);
		astr = util.substitute("s#<#&lt;#g", astr);
		astr = util.substitute("s/>/&gt;/g", astr);
		astr = util.substitute("s/\"/\\\\&quot;/g", astr);
		return astr;
	}

	/**
	 * ת���ַ���, ������ͨxml��ֵ. < replaced with &lt; > replaced with &gt; & replaced
	 * with &amp; " replaced with &quot; ' replaced with &apos;
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2TextXML(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}

		Perl5Util util = new Perl5Util();
		astr = util.substitute("s/&/&amp;/", astr);
		astr = util.substitute("s#<#&lt;#g", astr);
		astr = util.substitute("s/>/&gt;/g", astr);
		astr = util.substitute("s/\"/&quot;/", astr);
		astr = util.substitute("s/'/&apos;/", astr);
		return astr;
	}

	/**
	 * �����ַ���:����input��Ԫ�ص�value:��ӦtoHtml_in.
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2InputText(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}
		Perl5Util util = new Perl5Util();
		astr = util.substitute("s/&/&amp;/", astr);
		astr = util.substitute("s#<#&lt;#g", astr);
		astr = util.substitute("s/>/&gt;/g", astr);
		astr = util.substitute("s/\"/&quot;/g", astr);
		return astr;
	}

	/**
	 * ��html�е������ַ�����Ϊ����ʾ�������һ��:ԭ���ӦtoHtml_all.
	 * 
	 * @param astr
	 *            String
	 * @return String
	 */
	public static String str2TextHtml(String astr) {
		if ( isBlank(astr) ) {
			return "";
		}
		String result = astr;
		Perl5Util util = new Perl5Util();
		result = util.substitute("s/&/&amp;/g", result);
		result = util.substitute("s#<#&lt;#g", result);
		result = util.substitute("s/>/&gt;/g", result);
		result = util.substitute("s/ /&nbsp;/g", result);
		result = util.substitute("s/\"/&quot;/g", result);
		return result;
	}

	/**
	 * ��html�е������ַ�����Ϊ����ʾ�������һ��:��������ո� .
	 * 
	 * @param astr
	 *            String
	 * @return String
	 */
	public static String str2TextHtmlRidSpace(String astr) {
		if ( isBlank(astr) ) {
			return "";
		}

		String result = astr;

		Perl5Util util = new Perl5Util();
		result = util.substitute("s/&/&amp;/g", result);
		result = util.substitute("s#<#&lt;#g", result);
		result = util.substitute("s/>/&gt;/g", result);
		result = util.substitute("s/\"/&quot;/g", result);
		return result;
	}

	/**
	 * ���ַ����е�˫�����滻Ϊ\",����htmlԪ�صĸ�ֵ,��������ֵ����""�м�ʱ.
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2HtmlValue(String str1) {
		String sStr = str1;
		if ( isBlank(sStr) ) {
			return "";
		}

		Perl5Util util = new Perl5Util();
		sStr = util.substitute("s/\"/\\\\\"/g", sStr);
		return sStr;
	}

	/**
	 * ������sql���ַ����滻�ַ����еĵ�����Ϊ����������.
	 * 
	 * ����ǲ�ѯ���Sqlʱ��Ҫ,��ο�CommSQLFunc��
	 * 
	 * @param str1
	 *            String
	 * @return String
	 */
	public static String str2TextSql(String str1) {
		String astr = str1;
		if ( isBlank(astr) ) {
			return "";
		}

		Perl5Util util = new Perl5Util();
		astr = util.substitute("s/\'/\'\'/g", astr);
		return astr;
	}

	/**
	 * if a String identify "true".
	 * 
	 * @param aPropString
	 *            �ַ���
	 * @return true if y,yes,true,1
	 */
	public static boolean isTrueString(String aPropString) {
		String strTemp = aPropString.toLowerCase(Locale.US);
		return  strTemp.startsWith("true") || strTemp.startsWith("yes")
				|| strTemp.startsWith("1") || strTemp.startsWith("y");
	}

	/**
	 * ���ַ�����ָ�����ַ����������ַ������滻 ���� ָ�����ַ��� "sdupipo" ���������p���ַ��������g
	 * 
	 * @param source
	 *            ָ���ַ���
	 * @param target
	 *            Ҫ��������ַ���
	 * @param replace
	 *            �����ַ���
	 * @return �滻����ַ���
	 */
	public static String stringReplace(String source, String target,
			String replace) {
		if ( source != null && target != null && replace != null ) {
			StringBuilder StringBuilder = new StringBuilder(source.length() + 256);

			int i = -1;
			do {
				i++;

				i = source.indexOf(target);
				if ( i > -1 ) {
					StringBuilder.append(source.substring(0, i));

					StringBuilder.append(replace);

					source = source.substring(i + target.length());
				}
			}
			while (i != -1);

			StringBuilder.append(source);

			return StringBuilder.toString();
		} else {
			return source;
		}
	}

	/**
	 * ת����ȫ�ǵķ�����
	 * 
	 * ȫ�ǿո�Ϊ12288����ǿո�Ϊ32
	 * 
	 * �����ַ����(33-126)��ȫ��(65281-65374)�Ķ�Ӧ��ϵ�ǣ������65248
	 * 
	 * @param input
	 *            Ҫת�����ַ���
	 * 
	 * @return ȫ���ַ���
	 */
	public static String ToSBC(String input) {

		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if ( c[i] == 32 ) {
				c[i] = (char) 12288;
				continue;
			}
			if ( c[i] < 127 )
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * ת���ɰ�ǵķ���
	 * 
	 * ȫ�ǿո�Ϊ12288����ǿո�Ϊ32
	 * 
	 * �����ַ����(33-126)��ȫ��(65281-65374)�Ķ�Ӧ��ϵ�ǣ������65248
	 * 
	 * @param input
	 *            Ҫת�����ַ���
	 * 
	 * @return ���ذ���ַ���
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if ( c[i] == 12288 ) {
				c[i] = (char) 32;
				continue;
			}
			if ( c[i] > 65280 && c[i] < 65375 )
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static String romoveEnter(String input) {
		return input.replaceAll("\r", "").replaceAll("\n", "");
	}
	
	public static void main(String[] args) {
		logger.info(msubstr("����123��������qwe���а��ǵ���",3,"gbk"));
	}
}
