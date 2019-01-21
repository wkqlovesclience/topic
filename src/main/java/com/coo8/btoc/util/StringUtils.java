package com.coo8.btoc.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.config.ReloadableConfig;

public class StringUtils {

	public static String DatereplaceChar(String date){
		return date.replaceAll("-", "_").replaceAll(":", "_").replaceAll(" ", "_");
	}
   
	private  static Logger log = LoggerFactory.getLogger(ReloadableConfig.class);
	
	private StringUtils(){}

    public static String replaceChar(int istr, String strchar) {
        return String.valueOf(istr).replaceAll(strchar, "x");
    }

    /**
     * check String is null or not;
     * 
     * @param checkStr
     * @return boolean
     */
    public static boolean isNull(String checkStr) {
        return checkStr == null || checkStr.trim().length() == 0 || "null".equalsIgnoreCase(checkStr.trim());
    }

    public static boolean isNotNull(String checkStr) {
        return !isNull(checkStr);
    }

    /**
     * if input string is null return "".
     * 
     * @param input
     * @return
     */
    public static String formatNullString(String input) {
        if (isNull(input)) {
            return "";
        }
        return input;
    }

    /**
     * parse String to int
     * 
     * @param intStr
     * @param defaultInt
     * @return int
     */
    public static int parseInt(String intStr, int defaultInt) {
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            return defaultInt;
        }
    }
    
    public static double parseDouble(String intStr, double defaultInt) {
        try {
            return Double.parseDouble(intStr);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            return defaultInt;
        }
    }

    /**
     * parse String to int default is 0
     * 
     * @param intStr
     * @return int
     */
    public static int parseInt(String intStr) {
        return parseInt(intStr, 0);
    }
    
    /**
     * parse String to long
     * 
     * @param longStr
     * @param defaultLong
     * @return long
     */
    public static long parseLong(String longStr, long defaultLong) {
        try {
            return Long.parseLong(longStr);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            return defaultLong;
        }
    }

    /**
     * parse String to long default is 0
     * 
     * @param longStr
     * @return long
     */
    public static long parseLong(String longStr) {
        return parseLong(longStr, 0);
    }

    /**
     * check String is int
     * 
     * @param str
     * @return boolean
     */
    public static boolean isInt(String str) {
        Pattern pattern = Pattern.compile("(0|[1-9][0-9]*|-[1-9][0-9]*)");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * get substring term from src
     * 
     * @param src
     * @param term
     * @return String
     */
    public static String subBefore(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.indexOf(term);
            return index >= 0 ? src.substring(0, index) : src;
        }
    }

    /**
     * get substring after term from src
     * 
     * @param src
     * @param term
     * @return string
     */
    public static String subAfter(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.indexOf(term);
            return index >= 0 ? src.substring(index + term.length()) : src;
        }
    }

    /**
     * get substring last before term from src
     * 
     * @param src
     * @param term
     * @return string
     */
    public static String subLastBefore(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.lastIndexOf(term);
            return index >= 0 ? src.substring(0, index) : src;
        }
    }

    /**
     * get substring last after term from src
     * 
     * @param src
     * @param term
     * @return string
     */
    public static String subLastAfter(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.lastIndexOf(term);
            return index >= 0 ? src.substring(index + term.length()) : src;
        }
    }

    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * encode url
     * 
     * @param src
     * @return String
     */
    public static String encodeURL(String src) {
        String result = null;
        try {
            if (!StringUtils.isNull(src)) {
                result = URLEncoder.encode(src, "UTF-8");
            } else {
                result = src;
            }
        } catch (UnsupportedEncodingException e) {
        	log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * decode url
     * 
     * @param src
     * @return String
     */
    public static String decodeURL(String src) {
        String result = null;
        try {
            if (!StringUtils.isNull(src)) {
                result = URLDecoder.decode(src, "UTF-8");
            } else {
                result = src;
            }
        } catch (UnsupportedEncodingException e) {
        	log.error(e.getMessage(), e);
        }
        return result;
    }

    public static String getUserInput(HttpServletRequest req, String sInput) {
        String ts = req.getParameter(sInput);
        try {
            if (StringUtils.isNull(ts)) {
                return null;
            }
            String tempString = new String(ts.getBytes("ISO8859-1"), "UTF-8");
            if (tempString.indexOf("?") != -1) { // if convert failed
                req.setCharacterEncoding("GB2312");
                ts = req.getParameter(sInput);
            } else {
                ts = tempString; // convert success
            }
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
        return ts;
    }

    /**
     * 
     * @param input
     * @return
     */
    public static String formatXML(String input) {
        if (input == null) {
            return null;
        }
        String res = input;
        res = res.replaceAll("&", "&amp;");
        res = res.replaceAll("<", "&lt;");
        res = res.replaceAll(">", "&gt;");
        res = res.replaceAll("'", "&apos;");
        res = res.replaceAll("\"", "&quot;");
        res = res.replaceAll("\n", "<br/>");
        res = res.replaceAll("\r", "");
        res = res.replaceAll(" ", "&nbsp;");
        return res;
    }

    public static String formatUploadXML(String input) {
        if (input == null) {
            return null;
        }
        String res = input;
        res = res.replaceAll("&amp;", "&");
        res = res.replaceAll("&lt;", "<");
        res = res.replaceAll("&gt;", ">");
        res = res.replaceAll("&apos;", "'");

        res = res.replaceAll("&", "&amp;");
        res = res.replaceAll("<", "&lt;");
        res = res.replaceAll(">", "&gt;");
        res = res.replaceAll("'", "&apos;");
        return res;
    }

    public static String formatAndChar(String input) {
        if (input == null) {
            return null;
        }
        String res = input;
        res = res.replaceAll("&amp;", "&");
        return res;
    }

    public static String formatAndToAmpChar(String input) {
        if (input == null) {
            return null;
        }
        String res = input;
        res = res.replaceAll("&", "&amp;");
        return res;
    }

    /**
     * oracle ��ҳ��ѯ
     * 
     * @param sql
     * @param hasOffset
     * @return
     */
    public static String getLimitString(String sql, boolean hasOffset) {
        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
        if (hasOffset) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (hasOffset) {
            pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
        } else {
            pagingSelect.append(" ) where rownum <= ?");
        }
        return pagingSelect.toString();
    }

    public static String getCurDay() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(d);
        return s;
    }

    /**
     * 
     * @return String
     */
    public static String format() {
        return format("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 
     * @param pattern
     * @return string
     */
    public static String format(String pattern) {
        return format(pattern, new Date());
    }

    /**
     * default pattern is yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * 
     * @param pattern
     * @param date
     * @return string
     */
    public static String format(String pattern, Date date) {
        if (date == null) {
            return null;
        } else {
            return new SimpleDateFormat(pattern).format(date);
        }
    }

    /**
     * 
     * @param pattern
     * @param locale
     * @param text
     * @return
     */
    public static Date parse(String pattern, Locale locale, String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
        try {
            if (text != null && text.trim().length() != 0) {
                return dateFormat.parse(text);
            } else {
                return new Date();
            }
        } catch (ParseException e) {
        	log.error(e.getMessage(), e);
            return new Date();
        }
    }

    /**
     * 
     * @param pattern
     * @param text
     * @return Date
     */
    public static Date parse(String pattern, String text) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            if (text != null && text.trim().length() != 0) {
                return dateFormat.parse(text);
            } else {
                return new Date();
            }
        } catch (ParseException e) {
        	log.error(e.getMessage(), e);
            return new Date();
        }
    }

    /**
     * pattern is yyyy-MM-dd HH:mm:ss
     * 
     * @param text
     * @return Date
     */
    public static Date parse(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (text != null && text.trim().length() != 0) {
                return dateFormat.parse(text);
            } else {
                return new Date();
            }
        } catch (ParseException e) {
        	log.error(e.getMessage(), e);
            return new Date();
        }
    }
    
    public static String formatYYMMDD(Date date) {
        return format("yyyy-MM-dd", date);
    }
    
    public static int reverseBytes(int i) {
        return (i >>> 24) |
               ((i >>   8) &   0xFF00) |
               ((i <<   8) & 0xFF0000) |
               (i << 24);
    }
    
    public static String subString(String begin,String end,String src){
    	return StringUtils.subLastBefore(StringUtils.subAfter(src, begin),end);
    }
    
    /**
     * 
     * @param src
     * @return byte[]
     */
    public static byte[] encodeBase64(byte[] src) {
        return Base64.encodeBase64(src);
    }

    /**
     * 
     * @param src
     * @return byte[]
     */
    public static byte[] encodeBase64(String src) {
        return encodeBase64(src.getBytes());
    }

    /**
     * 
     * @param src
     * @return byte[]
     */
    public static byte[] decodeBase64(byte[] src) {
        return Base64.decodeBase64(src);
    }

    /**
     * 
     * @param src
     * @return byte[]
     */
    public static byte[] decodeBase64(String src) {
        return decodeBase64(src.getBytes());
    }
    
	/**
	 * ��ȡҳ�洫����ֵ 
	 * @param request
	 * @param paraName
	 * @param defaultValue
	 * @return 
	 */
	public static String getParameter(HttpServletRequest request,
			String paraName, String defaultValue) {
		String param = request.getParameter(paraName);
		if (null == param || "".equals(param))
			return defaultValue;
		return param;
	}
	
	/**
	 * ����,������λ
	 * */
	public static String divideRate(double d_income,int i_acc){
		String s_convert = "0.00";
		try{
			if(Integer.parseInt(s_convert) == 0){
				return "0.00";
			}
			double d_convert = 0.00;
			String s_fee = String.valueOf(d_income);
			String s_acc = String.valueOf(i_acc);
			
			BigDecimal b_acc = new BigDecimal(s_acc);
			BigDecimal b_fee = new BigDecimal(s_fee);
				
			d_convert =  b_fee.divide(b_acc, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			s_convert = String.valueOf(d_convert);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			return "0.00";
		}

		return s_convert;
	}
	/**
	 * ����,������λ,�Ӱٷֱ�%
	 * */
	public static String divideTwo(double d_fee,int i_accnew){
		String s_convert = "0.00";
		try{
			double d_acc = new Double(i_accnew).doubleValue();
			
			BigDecimal b_acc = new BigDecimal(Double.toString(d_acc));
			BigDecimal b_fee = new BigDecimal(Double.toString(d_fee));
				
			s_convert =  b_fee.divide(b_acc, 2, BigDecimal.ROUND_HALF_UP).toString();
		}catch(Exception e){
			log.error(e.getMessage(), e);
			return "0.00";
		}
		return s_convert;
		
	}
	/**
	 * ����,������λ,�Ӱٷֱ�%
	 * */
	public static String divide(int i_fee,int i_acc){
		String s_convert = "0.00%";
		try{
			if(i_fee == 0){
				return "0.00%";
			}
			double d_convert = 0.00;
			String s_fee = String.valueOf(i_fee);
			String s_acc = String.valueOf(i_acc);
			
			
			BigDecimal b_acc = new BigDecimal(s_acc);

			BigDecimal b_fee = new BigDecimal(s_fee);

			d_convert =  b_fee.divide(b_acc, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			d_convert = mul(d_convert,new Double(100.00).doubleValue());
			s_convert = d_convert + "%";
		}catch(Exception e){
			log.error(e.getMessage(), e);
			return "0.00%";
		}
		return s_convert;
	}
	 public static double mul(double v1, double v2) {
		  BigDecimal b1 = new BigDecimal(Double.toString(v1));
		  BigDecimal b2 = new BigDecimal(Double.toString(v2));
		  return b1.multiply(b2).doubleValue();
	}
	 public static String divide(double d_fee,double i_accnew) {
			String s_convert = "0.00";
			try{
				BigDecimal b_acc = new BigDecimal(Double.toString(i_accnew));
				BigDecimal b_fee = new BigDecimal(Double.toString(d_fee * 100));
					
				s_convert =  b_fee.divide(b_acc, 2, BigDecimal.ROUND_HALF_UP).toString();
			}catch(Exception e){
				log.error(e.getMessage(), e);
				return "0.00";
			}

			return s_convert + "%";
			
		}

		private final static String PFCHARSET = "GBK";
		
		/**
		 * �������ܣ�ȥ�����һλ�ַ�
		 * �����:chenhewei ���ʱ��:2010-08-22
		 * 
		 * @return
		 * @param o
		 */
		public static String subStr(String object,String regex) {
			if (object != null&&object.lastIndexOf(regex)>0)
			{
				return object.substring(0,object.lastIndexOf(regex));
			}
			return object;
		}
		/**
		 * �������ܣ��ָ��ַ���
		 * �����:chenhewei ���ʱ��:2010-08-22
		 * 
		 * @return
		 * @param o
		 */
		public static String [] spilt(String object,String regex) {
			return NVL(object).split(regex);
		}
		
		/**
		 * �������ܣ����Map���Ƿ���NULLֵ��������ѯ��ʱ���ֶ���ĳһ��ֵΪnullʱͳһ�滻Ϊ��NULL��������ڿ���ʲô������ʾ
		 * �����:zengjl ���ʱ��:2006-08-22
		 * 
		 * @return
		 * @param o
		 */
		public static Object checkMapNULL(Object o) {
			if (o != null)
				return "null".equals(o) ? "" : o;
			return "";
		}

		/**
		 * �������ܣ���null�滻Ϊ��"" �����:zengjl ���ʱ��:2006-08-22
		 * 
		 * @return
		 * @param o
		 */
		public static Object checkNull(Object o) {
			if (o != null){
				return  o;
			}
			return "";
		}

		/**
		 * ��������:��������������ʱ,����Ĭ��ֵ
		 * 
		 * @return
		 * @param defaultint
		 * @param str
		 */
		public static int getNullInt(Object str, int defaultint) {
			if (str == null)
				return defaultint;
			try {
				int a = Integer.parseInt((String) str);
				return a;
			} catch (NumberFormatException e) {
				log.error(e.getMessage(), e);
				return defaultint;
			}
		}

		/**
		 * ��������:��������������ʱ,����Ĭ��ֵ
		 * 
		 * @return
		 * @param defaultint
		 * @param str
		 */
		public static float getNullFloat(Object str, float defaultint) {
			try {
				float a = Float.parseFloat((String) str);
				return a;
			} catch (NumberFormatException e) {
				log.error(e.getMessage(), e);
				return defaultint;
			}
		}

		public static String getCurrentPath() {
			String str = "";
			try {
				str = StringUtils.class.getProtectionDomain().getCodeSource()
						.toString();
				str = str.substring(str.indexOf("/"), str.lastIndexOf("/"));
				str = str.substring(1, str.indexOf("WEB-INF") + 8);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
			return str;
		}

		public static List rangeDelStr(String delStr) {
			StringTokenizer tokenizer = new StringTokenizer(delStr, "|");
			List returnList = new ArrayList();
			while (tokenizer.hasMoreElements()) {
				String str = (String) tokenizer.nextElement();
				returnList.add(new Long(str));
			}

			return returnList;
		}

		/**
		 * �Ƿ�Ϊ��
		 * 
		 * @param str
		 * @return
		 */
		public static boolean isEmpty(String str) {
			if (null != str && str.trim().length() > 0) {
				return false;
			} else {
				return true;
			}
		}

		/**
		 * �жϼ����Ƿ�Ϊ��
		 * 
		 * @param list
		 * @return
		 */
		public static boolean isEmpty(Collection c) {
			if (!c.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

		public static boolean isEmpty(Object o) {
			if (o == null)
				return true;
			else
				return false;
		}

		/**
		 * ��ȡ�滻�ַ���
		 * 
		 * @param str
		 * @param rpstr
		 * @return
		 * @throws Exception
		 */
		public final static String nvl(String str, String rpstr) throws Exception {
			if (isEmpty(str))
				return rpstr;
			return str;
		}

		/**
		 * ��ȡ�ַ���
		 * 
		 * @param str
		 * @param rpstr
		 * @return
		 * @throws Exception
		 */
		public final static String nvl(Object str, String rpstr) throws Exception {
			if (str == null)
				return rpstr;
			return str.toString();
		}

		/**
		 * ��ȡ���ַ���
		 * 
		 * @param str
		 * @return
		 * @throws Exception
		 */
		public final static String nvl(String str) throws Exception {
			return nvl(str, "");
		}

		/**
		 * ��ȡHashMap���������
		 * 
		 * @param hm
		 * @param hname
		 * @return
		 * @throws Exception
		 */
		public final static String nvl(Map hm, String hname) throws Exception {
			if (hm == null) {
				return "";
			} else {
				if ((hm.get(hname) == null) || ("".equals(hm.get(hname)))) {
					return "";
				} else {
					return (String) hm.get(hname);
				}
			}
		}

		/**
		 * ����ƽ̨ת������
		 * 
		 * @param para
		 * @return
		 * @throws Exception
		 */
		public static String encode(String para) throws Exception {
			return URLEncoder.encode(para, PFCHARSET);
		}

		// ����ƽ̨����
		public static String decode(String para) throws Exception {
			return URLDecoder.decode(para, PFCHARSET);
		}

		/**
		 * �ַ�����ת�� ע��LINUX����������Ĭ�ϵ�ʹ��UTF-8
		 * 
		 * @param str
		 * @return
		 */
		public final static String getGBString(String str) {
			String str2 = "";

			try {
				str2 = new String(str.getBytes("iso-8859-1"), PFCHARSET);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return str2;

		}

		/**
		 * ת���ַ�����
		 * 
		 * @param str
		 * @return
		 */
		public final static String getUTF8(String str) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes("utf-8");
					} catch (Exception e) {						
						log.error(e.getMessage(), e);
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
			return sb.toString();
		}

		/**
		 * ���ݼ��ϴ���Options
		 * 
		 * @param vlist
		 * @param vid
		 * @param vname
		 * @return
		 */
		public final static String getOptByList(List<HashMap> vlist, String vid,
				String vname) throws Exception {
			StringBuilder sb = new StringBuilder();
			/* �ж����ݼ����Ƿ�Ϊ�� */
			if (!vlist.isEmpty())
				return "";
			/* ȡ���� */
			int z = vlist.size();
			for (int i = 0; i < z; i++) {
				HashMap rs = vlist.get(i);
				sb.append("<option value='").append(nvl(rs, vid.toUpperCase()))
						.append("'>").append(nvl(rs, vname.toUpperCase())).append(
								"</option>");
			}
			return sb.toString();
		}

		/**
		 * ����ѡ��
		 * 
		 * @param vlist
		 *            �����
		 * @param vid
		 *            ID����
		 * @param vname
		 *            VALUE����
		 * @param defValue
		 *            Ĭ��ֵ
		 * @return
		 * @throws Exception
		 */
		public final static String getOptByList(List<HashMap> vlist, String vid,
				String vname, String defValue) throws Exception {
			StringBuilder sb = new StringBuilder();
			/* �ж����ݼ����Ƿ�Ϊ�� */
			if (!vlist.isEmpty())
				return "";
			/* ȡ���� */
			int z = vlist.size();
			for (int i = 0; i < z; i++) {
				HashMap rs = vlist.get(i);
				if (nvl(rs, vid.toUpperCase()).equals(nvl(defValue))) {
					sb.append("<option value='").append(nvl(rs, vid.toUpperCase()))
							.append("' selected >").append(
									nvl(rs, vname.toUpperCase())).append(
									"</option>");
				} else {
					sb.append("<option value='").append(nvl(rs, vid.toUpperCase()))
							.append("'>").append(nvl(rs, vname.toUpperCase()))
							.append("</option>");
				}
			}
			return sb.toString();
		}

		/**
		 * ��ȡ���ַ���
		 * 
		 * @param src
		 * @param length
		 * @return
		 */
		public static String getSubStr(String src, int length) {
			if (null == src || src.length() < length) {
				return src;
			} else {
				return src.substring(0, length) + "......";
			}
		}


		/**
		 * ��IP��ַת���ɳ���������
		 * 
		 * @param paramString
		 * @return
		 * @throws Exception
		 */
		public static long getTransLongByIP(String paramString) throws Exception {
			long l = 16777216L * getLong(paramString.split("\\.")[0]) + 65536L
					* getLong(paramString.split("\\.")[1]) + 256L
					* getLong(paramString.split("\\.")[2])
					+ getLong(paramString.split("\\.")[3]);
			return l;
		}

		/**
		 * �����ַ�����ȡ������
		 * 
		 * @param paramString
		 * @return
		 * @throws Exception
		 * @throws NumberFormatException
		 */
		public static final long getLong(String paramString) throws Exception,
				NumberFormatException {
			if (paramString == null)
				throw new Exception("getLong(String strName):Input value is NULL!");
			try {
				return Long.parseLong(paramString.trim());
			} catch (NumberFormatException localNumberFormatException) {
				log.error(localNumberFormatException.getMessage(), localNumberFormatException);
			}
			return 0L;
		}

		/**
		 * ����˵������ָ���ĸ�ʽ����ϵͳ��ǰʱ�䡣
		 * 
		 * @param format
		 *            ʱ���ʽ�����磺yyyy-MM-dd
		 * @return ��ǰϵͳʱ�ַ���
		 * @throws Exception
		 *             �쳣��Ϣ
		 */
		public static String getNow(String format) throws Exception {
			Date date = new Date(System.currentTimeMillis());
			if (isEmpity(format))
				format = "yyyy-MM-dd HH:mm:ss";
			return formatDate(date, format);
		}
		public static String getNow() throws Exception {
			Date date = new Date(System.currentTimeMillis());
			return formatDate(date,"yyyy-MM-dd HH:mm:ss");
		}

		/**
		 * ����˵������ȡ��ǰ���ڣ�����Ϊ��java.sql.Date�� ����Ԥ����SQL���PreparedStatement�����ֶεĸ�ֵ��
		 * 
		 * @author NetBoy
		 * @return java.sql.Date
		 * @throws AppException
		 * @throws Exception
		 *             �쳣��Ϣ
		 */
		public static Date getCurrentDate() throws Exception {

			return java.sql.Date.valueOf(StringUtils.getNow("yyyy-MM-dd"));
		}

		/**
		 * ����˵������ָ����ʽ��ʽ�����ڶ��󣬲����ַ�����ʽ���ء�
		 * 
		 * @param date
		 *            ���ڶ���
		 * @param format
		 *            ���ڵ�Ŀ���ʽ������[yyyy-MM-dd HH:mm:ss]��[yyyyMMddHHmmss]
		 * @return ָ����ʽ�������ַ���
		 */
		public static String formatDate(java.util.Date date, String format){
			String rs = null;

			if (date != null) {
				if (!StringUtils.isEmpity(format)) {
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					rs = sdf.format(date);
				}
			} 

			return rs;
		}

		/**
		 * ����˵�����ж�ָ�����ַ����Ƿ�Ϊ��null��"" ���� ȫ�ǿո�
		 * ע�⣺�˷�������������ȷ����Ӧ����StringUtils.isEmpty(String str)��
		 * 
		 * @param str
		 *            �ַ���
		 * @return �Ƿ�Ϊ��
		 */
		public static boolean isEmpity(String str) {
			if (null != str && str.trim().length() > 0) {
				return false;
			} else {
				return true;
			}
		}

		/**
		 * ����˵�������ַ���ת��Ϊ��������ת��ʧ�ܷ���ָ����Ĭ��ֵ��
		 * 
		 * @author NetBoy
		 * @param str
		 *            �ַ���
		 * @param defaultVal
		 *            ʧ��ʱ���ص�Ĭ��ֵ
		 * @return ����
		 */
		public static int str2Int(String str, int defaultVal) {
			if (StringUtils.isEmpity(str))
				return defaultVal;

			try {
				return Integer.parseInt(str.trim());
			} catch (NumberFormatException e) {
				log.error(e.getMessage(), e);
				return defaultVal;
			}
		}

		/**
		 * ����˵�������ַ���ת��Ϊ��������ת��ʧ�ܷ���-1��
		 * 
		 * @param str
		 *            �ַ���
		 * @return ����
		 * @throws Exception
		 *             ת��ʧ���쳣
		 */
		public static int str2Int(String str) {
			return StringUtils.str2Int(str, -1);
		}

		/**
		 * ����˵����������ת��Ϊ��������ת��ʧ�ܷ���ָ����Ĭ��ֵ��
		 * 
		 * @author NetBoy
		 * @param str
		 *            �ַ���
		 * @param defaultVal
		 *            ʧ��ʱ���ص�Ĭ��ֵ
		 * @return ����
		 */
		public static int obj2Int(Object o, int defaultVal) {

			return StringUtils.str2Int(StringUtils.NVL(o), defaultVal);
		}

		/**
		 * ����˵����������ת��Ϊ��������ת��ʧ�ܷ���-1��
		 * 
		 * @author NetBoy
		 * @param o
		 *            Object����
		 * @return ����
		 */
		public static int obj2Int(Object o) {

			return StringUtils.str2Int(StringUtils.NVL(o));
		}

		/**
		 * ����˵����������Ϊnull���򷵻�""�����򷵻ض����toString()ֵ�� ע���������������null2Empity(String
		 * str)��null2Empity(Object o)������
		 * 
		 * @author NetBoy
		 * @ctime 2008-3-5 20:05:19
		 * @param o
		 *            ����
		 * @return �ַ���
		 */
		public static String NVL(Object o) {
			return StringUtils.NVL(o, "");
		}

		/**
		 * ����˵����������Ϊnull���򷵻�Ԥ�����ֵ�����򷵻ض����toString()ֵ��
		 * 
		 * @author NetBoy
		 * @ctime 2008-3-5 20:05:21
		 * @param o
		 *            ����
		 * @param strReturn
		 *            ����Ϊnullʱ�ķ���ֵ
		 * @return �ַ���
		 */
		public static String NVL(Object o, String strReturn) {
			return (o == null) ? strReturn : o.toString();
		}

		/**
		 * �������ܣ�����Ӧ�����ķ����滻�ַ����е�Ӣ�ķ��š� �����:zengjl ���ʱ��:2006-08-22
		 * 
		 * @return ����ȫ���ĵķ���
		 * @param obj
		 *            �ַ���
		 */
		public static String replaceEn(Object obj) {
			StringBuilder lastStr = new StringBuilder();
			int k = 1; // ��¼�����š����Ϊ�����������š��������ҵ�����
			String str = "";
			if (obj != null) {
				str = (String) obj;
				for (int i = 0; i < str.length(); i++) {
					char strs = str.charAt(i);
					char c1 = '\'';
					char c2 = '(';
					char c3 = ')';
					char c4 = '<';
					char c5 = '>';
					char c6 = '/';
					char c7 = ',';
					char c8 = '.';
					// Ӣ�ĵ�����
					if (strs == c1) {
						if (k % 2 == 1) {
							strs = '��';
						} else {
							strs = '��';
						}
						k++;
					}
					// Ӣ��������
					if (strs == c2) {
						strs = '��';
					}
					// Ӣ��������
					if (strs == c3) {
						strs = '��';
					}
					if (strs == c4) {
						strs = '��';
					}
					if (strs == c5) {
						strs = '��';
					}
					if (strs == c6) {
						strs = '/';
					}
					if (strs == c7) {
						strs = '��';
					}
					if (strs == c8) {
						strs = '.';
					}
					lastStr.append(strs);

				}
			} else {
				lastStr.append("");
			}
			return lastStr.toString();
		}

		public static String getDeleteString(String[] id) {
			String temp = "";
			for (int i = 0; i < id.length; i++) {
				temp += id[i] + ",";

			}
			temp = temp.substring(0, temp.length() - 1);
			return temp;
		}

		/**
		 * ����˵������ȡ���ڵ��� ����dateStr<2007-10-18 00:00:00.0>������<2007-10-18>
		 * 
		 * @author NetBoy
		 * @param dateStr
		 *            �������ַ���
		 * @return �������ա��ַ���
		 */
		public static String showDate(String dateStr) {

			if (StringUtils.isEmpty(dateStr)) {
				return "";
			}

			dateStr = dateStr.trim();

			if (dateStr.indexOf(' ') != -1) {
				return dateStr.substring(0, dateStr.indexOf(" "));
			}

			return dateStr;
		}
		 /**
	     * �������
	     * @param date ����
	     * @return �������
	     */
	    public static int getYear(java.util.Date date) {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.get(java.util.Calendar.YEAR);
	    }
	    public static int getYear() {
	      
	        return getYear(new java.util.Date());
	    }
		 /**
	     * �����·�
	     * @param date ����
	     * @return �����·�
	     */
	    public static int getMonth(java.util.Date date) {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.get(java.util.Calendar.MONTH) + 1;
	    }
	    //���ص�ǰ���ڵ��·�
	    public static int getMonth() {
	       return getMonth(new java.util.Date());
	    }
	    /**
	     * �����շ�
	     * @param date ����
	     * @return �����շ�
	     */
	    public static int getDay(java.util.Date date) {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.get(java.util.Calendar.DAY_OF_MONTH);
	    }
	    //���ص�ǰ���ڵ��·�
	    public static int getDay() {
	       return getDay(new java.util.Date());
	    }
	    //ͨ���ֽڷ���M
	    public static double getM(long l)
	    {
	    	double rValue=0.0;
	    	if(l>=0)
	    	{
	    		rValue= l/1024.0/1024.0;
	    		
	    	}
	    	return rValue;
	    }
	    public static String toTrim(String str)
	    {
	    	return NVL(str).trim();
	    }

		public static String formatNumber(String paramString, double paramDouble) {
			DecimalFormat localDecimalFormat = new DecimalFormat(paramString);
			return localDecimalFormat.format(paramDouble);
		}

		public static String formatNumber(String paramString, long paramLong) {
			DecimalFormat localDecimalFormat = new DecimalFormat(paramString);
			return localDecimalFormat.format(paramLong);
		}

}
