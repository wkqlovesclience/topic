/*
 * �ļ����� DateUtil.java
 * 
 * �������ڣ� 2010-3-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * ԭʼ����: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.config.ReloadableConfig;

/**
 * ���ڵĹ����࣬�������ַ���������֮��ת���ķ���
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision$
 *
 * @since 2009-10-16
 */
public class DateUtil {

	
	private  static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	/**
	 * ORA��׼ʱ���ʽ
	 */
	private static  final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * ��ʱ�����ORA��׼ʱ���ʽ
	 */
	private static final  SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static final long ONE_DAY = 24 *60 * 60 * 1000L;
	
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	
	private static final long ONE_MIN = 60 * 1000L;
	
	private DateUtil(){}
	
	/**
	 * ��������ʽΪHH:MM:SS��ʱ���ַ�����ӣ����磺00:59:06 + 01:00:59 ���� 02:00:05��
	 * 
	 * @param time1
	 *            Ҫ�ۼƵ�ʱ���ַ���
	 * @param time2
	 *            Ҫ�ۼƵ�ʱ���ַ���
	 * 
	 * @return �ۼƺ��ʱ���ַ���
	 */
	public static String addTwoTimeStr(String time1, String time2) {
		
		if (time1 != null && !"".equalsIgnoreCase(time1) && time2 != null && !"".equalsIgnoreCase(time2)) {
			String[] time1Array = time1.split(":");
			String[] time2Array = time2.split(":");
			int hour1 = (new Integer(time1Array[0])).intValue();
			int hour2 = (new Integer(time2Array[0])).intValue();
			int min1 = (new Integer(time1Array[1])).intValue();
			int min2 = (new Integer(time2Array[1])).intValue();
			int sec1 = (new Integer(time1Array[2])).intValue();
			int sec2 = (new Integer(time2Array[2])).intValue();
	
			String lastSec, lastMin, lastHour;
	
			int totalSec = sec1 + sec2;
			if (totalSec / 60 > 0) {
				// ����1���ӵ�ʱ���ۼƵ�min1��
				min1 = min1 + totalSec / 60;
			}
			if (totalSec % 60 > 9) {
				lastSec = Integer.toString(new Integer(totalSec % 60));
			} else {
				lastSec = new String("0" + Integer.toString(new Integer(totalSec % 60)));
			}
	
			int totalMin = min1 + min2;
			if (totalMin / 60 > 0) {
				// ����1���ӵ�ʱ���ۼƵ�hour1��
				hour1 = hour1 + totalMin / 60;
			}
			if (totalMin % 60 > 9) {
				lastMin = Integer.toString(new Integer(totalMin % 60));
			} else {
				lastMin = new String("0" + Integer.toString(new Integer(totalMin % 60)));
			}
	
			int totalHour = hour1 + hour2;
			if (totalHour % 24 > 9) {
				lastHour =Integer.toString( new Integer(totalHour % 24));
			} else {
				lastHour = new String("0" + Integer.toString(new Integer(totalHour % 24)));
			}
			
			return lastHour + ":" + lastMin + ":" + lastSec;
		} else if (time1 != null && !"".equalsIgnoreCase(time1)) {
			return time1.substring(0, 8);
		} else if (time2 != null && !"".equalsIgnoreCase(time2)) {
			return time2.substring(0, 8);
		} else {
			return "00:00:00";
		}
	}

	/**
	 * ����һ����׼ORAʱ���ʽ�Ŀ�¡
	 * 
	 * @return ��׼ORAʱ���ʽ�Ŀ�¡
	 */
	private static synchronized DateFormat getOraDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * ����һ���������ORAʱ���ʽ�Ŀ�¡
	 * 
	 * @return ��׼ORAʱ���ʽ�Ŀ�¡
	 */
	private static synchronized DateFormat getOraExtendDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_EXTENDED_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * �õ�ϵͳ��ǰ������ ��ʽΪYYYY-MM-DD
	 * 
	 * @return ϵͳ��ǰ������ ��ʽΪYYYY-MM-DD
	 */
	public static String getSystemCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return doTransform(calendar.get(Calendar.YEAR), calendar
				.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * ���ظ�ʽΪYYYY-MM-DD
	 * 
	 * @param year
	 *            ��
	 * @param month
	 *            ��
	 * @param day
	 *            ��
	 * @return
	 */
	private static String doTransform(int year, int month, int day) {
		StringBuilder result = new StringBuilder();
		result.append(String.valueOf(year)).append("-").append(
				month < 10 ? "0" + String.valueOf(month) : String
						.valueOf(month)).append("-").append(
				day < 10 ? "0" + String.valueOf(day) : String.valueOf(day));
		return result.toString();
	}

	/**
	 * �õ�ϵͳ��ǰ�����ں�ʱ�� ��ʽΪYYYY-MM-DD hh:mm:ss
	 * 
	 * @return ��ʽΪYYYY-MM-DD hh:mm:ss��ϵͳ��ǰ�����ں�ʱ��
	 */
	public static final String getSystemCurrentDateTime() {
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTimeInMillis(System.currentTimeMillis());
		int year = newCalendar.get(Calendar.YEAR);
		int month = newCalendar.get(Calendar.MONTH) + 1;
		int day = newCalendar.get(Calendar.DAY_OF_MONTH);
		int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
		int minute = newCalendar.get(Calendar.MINUTE);
		int second = newCalendar.get(Calendar.SECOND);
		return doTransform(year, month, day, hour, minute, second);
	}

	/**
	 * �õ�ϵͳ��ǰ��ʱ�� ��ʽΪhh:mm:ss
	 * 
	 * @return ��ʽΪhh:mm:ss��ϵͳ��ǰʱ��
	 */
	public static final String getSystemCurrentTime() {
		return getSystemCurrentDateTime().substring(11, 19);
	}

	/**
	 * ���ظ�ʽΪYYYY-MM-DD hh:mm:ss
	 * 
	 * @param year
	 *            ��
	 * @param month
	 *            ��
	 * @param day
	 *            ��
	 * @param hour
	 *            Сʱ
	 * @param minute
	 *            ����
	 * @param second
	 *            ��
	 * @return
	 */
	private static final String doTransform(int year, int month, int day,
			int hour, int minute, int second) {
		StringBuilder result = new StringBuilder();
		result.append(String.valueOf(year)).append("-").append(
				month < 10 ? "0" + String.valueOf(month) : String
						.valueOf(month)).append("-").append(
				day < 10 ? "0" + String.valueOf(day) : String.valueOf(day))
				.append(" ").append(
						hour < 10 ? "0" + String.valueOf(hour) : String
								.valueOf(hour)).append(":").append(
						minute < 10 ? "0" + String.valueOf(minute) : String
								.valueOf(minute)).append(":").append(
						second < 10 ? "0" + String.valueOf(second) : String
								.valueOf(second));
		return result.toString();
	}

	/**
	 * ������������
	 * 
	 * @return ָ�����ڵ���һ�� ��ʽ:YYYY-MM-DD
	 */
	public static synchronized String getDayBeforeToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}
	
	/**
	 * ���ָ�����ڵ���һ�������
	 * 
	 * @param dateStr ָ�������� ��ʽ:YYYY-MM-DD
	 * 
	 * @return ָ�����ڵ���һ�� ��ʽ:YYYY-MM-DD
	 */
	public static synchronized String getDayBeforeToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}

	/**
	 * ������������
	 * 
	 * @return ָ�����ڵ���һ�� ��ʽ:YYYY-MM-DD
	 */
	public static synchronized String getDayAfterToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}
	
	/**
	 * ���ָ�����ڵ���һ�������
	 * 
	 * @param dateStr ָ�������� ��ʽ:YYYY-MM-DD
	 * 
	 * @return ָ�����ڵ���һ�� ��ʽ:YYYY-MM-DD
	 */
	public static synchronized String getDayAfterToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}

	/**
	 * ���ָ�����ڵ�ǰ�󼸸��µ�����
	 * 
	 * @return ָ�����ڵĺ��漸����,����Ϊ��,����Ϊǰ.
	 */
	public static synchronized Date getDayBeforeAfterMonth(int months) {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
	}
	
	/**
	 * ���ظ�ʽΪYYYY-MM-DD hh:mm:ss
	 * 
	 * @param date
	 *            �����ʽΪORA��Сʱ����ı�׼ʱ���ʽ
	 * @return
	 */
	private static String doTransformDateTime(String date) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(date.substring(0, 4));
		buffer.append("-");
		buffer.append(date.substring(4, 6));
		buffer.append("-");
		buffer.append(date.substring(6, 8));
		buffer.append(" ");
		buffer.append(date.substring(8, 10));
		buffer.append(":");
		buffer.append(date.substring(10, 12));
		buffer.append(":");
		buffer.append(date.substring(12, 14));

		return buffer.toString();
	}
	
	/**
	 * ���ظ�ʽΪYYYY-MM-DD
	 * 
	 * @param date
	 *            �����ʽΪORA����Сʱ����ı�׼ʱ���ʽ
	 * @return
	 */
	private static String doTransformDate(String date) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(date.substring(0, 4));
		buffer.append("-");
		buffer.append(date.substring(4, 6));
		buffer.append("-");
		buffer.append(date.substring(6, 8));

		return buffer.toString();
	}

	/**
	 * ��һ�����ڶ���ת����Ϊָ�����ڡ�ʱ���ʽ���ַ����� ������ڶ���Ϊ�գ�����һ�����ַ�������.
	 * 
	 * @param theDate
	 *            Ҫת�������ڶ���
	 * @param theDateFormat
	 *            ���ص������ַ����ĸ�ʽ
	 * @return ת�����
	 */
	public static synchronized String toString(Date theDate,
			DateFormat theDateFormat) {
		if (theDate == null) {
			return "";
		} else {
			return theDateFormat.format(theDate);
		}
	}

	/**
	 * ��Date����ת���󷵻ر�ϵͳĬ�ϵ����ڸ�ʽ YYYY-MM-DD hh:mm:ss
	 * 
	 * @param theDate
	 *            Ҫת����Date����
	 * @return ת������ַ���
	 */
	public static synchronized String toDefaultString(Date theDate) {
		if (theDate == null) {
			return "";
		}
		return doTransformDateTime(toString(theDate, getOraExtendDateTimeFormat()));
	}

	/**
	 * �������ʽΪ2004-8-13 12:31:22���͵��ַ���ת��Ϊ��׼��Date����
	 * 
	 * @param dateStr
	 *            Ҫת�����ַ���
	 * 
	 * @return ת����ı�׼��Date����
	 * @throws ParseException 
	 */
	public static synchronized Date toDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dateStr);
	}

	/**
	 * �������ʽΪ2004-8-13,2004-10-8���͵��ַ���ת��Ϊ��׼��Date����,����Date���� ��Ӧ�����ڸ�ʽΪYYYY-MM-DD
	 * 00:00:00,����һ��Ŀ�ʼʱ��
	 * 
	 * @param dateStr
	 *            Ҫת�����ַ���
	 * @return ת�����Date����
	 */
	public static synchronized Date toDayStartDate(String dateStr) {
		String[] list = dateStr.split("-");
		int year = Integer.parseInt(list[0]);
		int month = Integer.parseInt(list[1]);
		int day = Integer.parseInt(list[2]);
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 0, 0, 0);
		return cale.getTime();

	}

	/**
	 * �������ʽΪ2004-8-13,2004-10-8���͵��ַ���ת��Ϊ��׼��Date����,����Date���� ��Ӧ�����ڸ�ʽΪYYYY-MM-DD
	 * 23:59:59,����һ��Ľ���ʱ��
	 * 
	 * @param dateStr
	 *            �����ʽ:2004-8-13,2004-10-8
	 * @return ת�����Date����
	 */
	public static synchronized Date toDayEndDate(String dateStr) {
		String[] list = dateStr.split("-");
		int year = Integer.parseInt(list[0]);
		int month = Integer.parseInt(list[1]);
		int day = Integer.parseInt(list[2]);
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 23, 59, 59);
		return cale.getTime();

	}

	/**
	 * ������scormʱ�����
	 * 
	 * @param scormTime1
	 *            scormʱ��,��ʽΪ00:00:00(1..2).0(1..3)
	 * @param scormTime2
	 *            scormʱ��,��ʽΪ00:00:00(1..2).0(1..3)
	 * @return ����scormʱ����ӵĽ��
	 */
	public static synchronized String addTwoScormTime(String scormTime1, String scormTime2) {
		int dotIndex1 = scormTime1.indexOf(".");
        int hh1 = Integer.parseInt(scormTime1.substring(0,2));
        int mm1 = Integer.parseInt(scormTime1.substring(3,5));
        int ss1 = 0;
        if (dotIndex1 != -1) {
        	ss1 = Integer.parseInt(scormTime1.substring(6,dotIndex1));
        }
        else {
        	ss1 = Integer.parseInt(scormTime1.substring(6,scormTime1.length()));
        }
        int ms1 = 0;
        if (dotIndex1 != -1) {
        	ms1 = Integer.parseInt(scormTime1.substring(dotIndex1 + 1,scormTime1.length()));
        }
        
        int dotIndex2 = scormTime2.indexOf(".");
        int hh2 = Integer.parseInt(scormTime2.substring(0,2));
        int mm2 = Integer.parseInt(scormTime2.substring(3,5));
        int ss2 = 0;
        if (dotIndex2 != -1) {
        	ss2 = Integer.parseInt(scormTime2.substring(6,dotIndex2));
        }
        else {
        	ss2 = Integer.parseInt(scormTime2.substring(6,scormTime2.length()));
        }
        int ms2 = 0;
        if (dotIndex2 != -1) {
        	ms2 = Integer.parseInt(scormTime2.substring(dotIndex2 + 1,scormTime2.length()));
        }
       
        int hh = 0; 
        int mm = 0;
        int ss = 0;
        int ms = 0;
        
        if(ms1+ms2 >= 1000){
            ss = 1;
            ms = ms1+ms2-1000;
        }else{
            ms = ms1+ms2;
        }
        if(ss1+ss2+ss >= 60){
            mm = 1;
            ss = ss1+ss2+ss-60;
        }else{
            ss = ss1+ss2+ss;
        }
        if(mm1+mm2+mm>=60){
            hh = 1;
            mm = mm1+mm2+mm-60;
        }else{
            mm = mm1+mm2+mm;
        }
        hh = hh + hh1 + hh2;
        
        StringBuilder sb = new StringBuilder();
        if(hh<10){
           sb.append("0").append(hh);    
        }else{
           sb.append(hh);    
        }
        sb.append(":");
        if(mm<10){
           sb.append("0").append(mm);    
        }else{
           sb.append(mm);    
        }
        sb.append(":");
        if(ss<10){
           sb.append("0").append(ss);  
        }else{
           sb.append(ss);     
        }
        sb.append(".");
        if(ms<10){
           sb.append(ms).append("00");
        }else if(ms<100){
           sb.append(ms).append("0");    
        }else{
           sb.append(ms);  
        }
        return sb.toString();
	}

	/**
	 * ����timeType���ص�ǰ�����봫�����ڵĲ�ֵ����ǰ���ڼ��������ڣ� ��Ҫ�󷵻��·ݵ�ʱ��date�����ڱ���͵�ǰ��������ȣ�
	 * ���򷵻�0�����磺2003-2-23 �� 2004-6-12����23�ź�12�Ų���ͬһ�죬�̷���0�� 2003-2-23 �� 2005-6-23
	 * ������������·ݣ������꣬����Ӧ����28�����£��� 2003-2-23 �� 2001-6-23
	 * Ҳ����������·ݣ������꣬����Ӧ����-20�����£���
	 * 
	 * @param date
	 *            Ҫ�뵱ǰ���ڱȽϵ�����
	 * @param timeType
	 *            0�����������������������1���������������������
	 * 
	 * @return ����timeType���ص�ǰ�����봫�����ڵĲ�ֵ
	 */
	public static int CompareDateWithNow(Date date, int timeType) {
		Date now = Calendar.getInstance().getTime();

		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(now);
		calendarNow.set(Calendar.HOUR, 0);
		calendarNow.set(Calendar.MINUTE, 0);
		calendarNow.set(Calendar.SECOND, 0);

		Calendar calendarPara = Calendar.getInstance();
		calendarPara.setTime(date);
		calendarPara.set(Calendar.HOUR, 0);
		calendarPara.set(Calendar.MINUTE, 0);
		calendarPara.set(Calendar.SECOND, 0);

		float nowTime = now.getTime();
		float dateTime = date.getTime();

		if (timeType == 0) {
			if (calendarNow.get(Calendar.DAY_OF_YEAR) == calendarPara
					.get(Calendar.DAY_OF_YEAR))
				return 0;
			return (calendarNow.get(Calendar.YEAR) - calendarPara
					.get(Calendar.YEAR))
					* 12
					+ calendarNow.get(Calendar.MONTH)
					- calendarPara.get(Calendar.MONTH);
		} else {
			float result = nowTime - dateTime;
			int day = 24 * 60 * 60 * 1000;
			result = result / day;
			Float resultFloat = new Float(result);
			float fraction = result - resultFloat.intValue();
			if (fraction > 0.5) {
				return resultFloat.intValue() + 1;
			} else if (fraction < -0.5) {
				return resultFloat.intValue() - 1;
			} else {
				return resultFloat.intValue();
			}
		}
	}

	/**
	 * �жϵ�ǰ�·��Ƿ������12��
	 * 
	 * @return �������12��
	 */
	public static boolean isLastMonth() {
		if ("12".equals(month().toString()))
			return true;
		return false;
	}

	/**
	 * ��õ�ǰ���ڵ����
	 * 
	 * @return ��ǰ���ڵ����
	 */
	public static Integer year() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.YEAR));
	}

	/**
	 * ��õ�ǰ���ڵ��·�
	 * 
	 * @return ��ǰ���ڵ��·�
	 */
	public static Integer month() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * ��õ�ǰ���ڵ�ǰһ���·�,�����ǰ��1�£�����12
	 * 
	 * @return ��ǰ���ڵ�ǰһ���·�
	 */
	public static Integer beforeMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		if (month == 0) {
			return new Integer(12);
		} else {
			return new Integer(month);
		}
	}

	/**
	 * ��õ�ǰ���ڵĺ�һ���·�,�����ǰ��12�£�����1
	 * 
	 * @return ��ǰ���ڵĺ�һ���·�
	 */
	public static Integer nextMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		if (month == 11) {
			return new Integer(1);
		} else {
			return new Integer(month + 2);
		}
	}

	/**
	 * ��õ�ǰ���ڵ���
	 * 
	 * @return ��ǰ���ڵ���
	 */
	public static Integer day() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * ��õ�ǰʱ���Сʱ
	 * 
	 * @return ��ǰʱ���Сʱ
	 */
	public static Integer hour() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * ��õ�ǰʱ��ķ���
	 * 
	 * @return ��ǰʱ��ķ���
	 */
	public static Integer minute() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.MINUTE));
	}

	/**
	 * ��õ�ǰʱ�����
	 * 
	 * @return ��ǰʱ�����
	 */
	public static Integer second() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.SECOND));
	}

	/**
	 * ��õ�ǰ�������Ǳ��µĵڼ���
	 * 
	 * @return ��ǰ�������Ǳ��µĵڼ���
	 */
	public static int WeekofMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * ��õ�ǰ�������Ǳ����ڵĵڼ���
	 * 
	 * @return ��ǰ�������Ǳ����ڵĵڼ���
	 */
	public static int DayofWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * ���������м���
	 * 
	 * @return ������м���
	 */
	public static int getMaxWeekofMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * �õ���ǰ���ڵ��ַ�������ʽ��YYYY-MM-DD
	 * 
	 * @return ��ǰ���ڵ��ַ�������ʽ��YYYY-MM-DD
	 */
	public static String Date() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder date = new StringBuilder();
		date.append(calendar.get(Calendar.YEAR));
		date.append('-');
		date.append(calendar.get(Calendar.MONTH) + 1);
		date.append('-');
		date.append(calendar.get(Calendar.DAY_OF_MONTH));
		return date.toString();
	}

	/**
	 * �õ���ǰ����ʱ����ַ�������ʽ��YYYY-MM-DD HH:MM:SS
	 * 
	 * @return ��ǰ����ʱ����ַ�������ʽ��YYYY-MM-DD HH:MM:SS
	 */
	public static String DateTime() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder date = new StringBuilder();
		date.append(calendar.get(Calendar.YEAR));
		date.append('-');
		date.append(calendar.get(Calendar.MONTH) + 1);
		date.append('-');
		date.append(calendar.get(Calendar.DAY_OF_MONTH));
		date.append(' ');
		date.append(calendar.get(Calendar.HOUR_OF_DAY));
		date.append(':');
		date.append(calendar.get(Calendar.MINUTE));
		date.append(':');
		date.append(calendar.get(Calendar.SECOND));
		return date.toString();
	}

	/**
	 * �Ƚ�2�����ڴ�С
	 * 
	 * @param startTime
	 *            ��ʼ����
	 * @param endTime
	 *            ��������
	 * @return �Ƚ�2�����ڴ�С��>0��startTime>endTime 0:startTime=endTime <0:startTime<endTime
	 * 
	 * @throws ParseException
	 */
	public static int compareTwoDate(String startTime, String endTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date b = formatter.parse(startTime);
		Date c = formatter.parse(endTime);

		return b.compareTo(c);
	}
	
	/**
	 * �Ƚ�һ�������Ƿ���ָ�������ڶ���
	 * 
	 * @param nowSysDateTime
	 *            Ҫ�жϵ�����
	 * @param startTime
	 *            ��ʼ����
	 * @param endTime
	 *            ��������
	 * @return nowSysDateTime��startTime��endTime�з���true
	 * @throws ParseException
	 */
	public static boolean compareThreeDate(String nowSysDateTime,
			String startTime, String endTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date b = formatter.parse(startTime);
		Date a = formatter.parse(nowSysDateTime);
		Date c = formatter.parse(endTime);
		if (a.compareTo(b) >= 0 && a.compareTo(c) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �õ�ĳһ���ĳ�µ�ǰ/��һ��������һ�� ����õ�2002��1�µ�ǰһ���������� (2002,1,-1) =2001.
	 * 
	 * @param year
	 *            ʱ�������
	 * @param month
	 *            ʱ�����·�
	 * @param pastMonth
	 *            ��ʱ�����·ݾ���,������ʾ��ǰ��ʱ��,������ʾ�Ժ��ʱ��
	 * @return ������
	 */
	public static int getYearPastMonth(int year, int month, int pastMonth) {
		return year
				+ (int) Math.floor((double) (month - 1 + pastMonth)
						/ (double) 12);
	}

	/**
	 * �õ�ĳ���µ��¼����������Ǹ���.
	 * 
	 * @param month
	 *            ��ǰ��
	 * @param pastMonth
	 *            �͵�ǰ�µ��������
	 * @return Ŀ������
	 */
	public static int getMonthPastMonth(int month, int pastMonth) {
		return ((12 + month - 1 + pastMonth) % 12) + 1;
	}

	/**
	 * �����·ݵļ�����.
	 * 
	 * 0��ʾ�Ƿ��·�.��������1,2,3,4.
	 * 
	 * @param nMonth
	 *            int
	 * @return int
	 */
	public static int getQuarterbyMonth(int nMonth) {
		final int[] monthQuarters = { 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };

		if (nMonth >= 0 && nMonth <= 12) {
			return monthQuarters[nMonth];
		} else {
			return 0;
		}
	}

	/**
	 * �õ���ǰ����.
	 * 
	 * @return ��ǰ���ڵ�java.sql.Date��ʽ
	 */
	public static java.sql.Date getNowSqlDate() {
		java.util.Date aDate = new java.util.Date();
		return new java.sql.Date(aDate.getTime());
	}

	/**
	 * �õ���ǰ����.
	 * 
	 * @return ��ǰ���ڵ�java.util.Date��ʽ
	 */
	public static java.util.Date getNowDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * �õ���ǰʱ��.
	 * 
	 * @return java.sql.Time
	 */
	public static java.sql.Timestamp getNowTimestamp() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	/**
	 * �õ�ĳ��ʱ����ַ�����ʾ,��ʽΪyyyy-MM-dd HH:mm.
	 * 
	 * @param aTime
	 *            Ҫ������ʱ��
	 * @return String
	 */
	public static String getTimeShow(java.sql.Timestamp aTime) {
		if (null == aTime) {
			return "";
		}
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.US);
		return sdfTemp.format(aTime);
	}

	/**
	 * �����Լ��ĸ�ʽ��ʾʱ��.
	 * 
	 * @param aTime
	 *            Ҫ������ʱ��
	 * @param aFormat
	 *            ����SimpleDateFormat�Ĺ���ĸ�ʽ
	 * @return �ַ���
	 */
	public static String getSelfTimeShow(java.sql.Timestamp aTime,
			String aFormat) {
		if (null == aTime) {
			return "";
		}
		SimpleDateFormat sdfTemp = new SimpleDateFormat(aFormat, Locale.US);
		return sdfTemp.format(aTime);
	}

	/**
	 * �����Լ��ĸ�ʽ��ʾʱ��.
	 * 
	 * @param aTime
	 *            Ҫ������ʱ��
	 * @param aFormat
	 *            ����SimpleDateFormat�Ĺ���ĸ�ʽ
	 * @return �ַ���
	 */
	public static String getSelfTimeShow(java.sql.Date aTime, String aFormat) {
		if (null == aTime) {
			return "";
		}
		SimpleDateFormat sdfTemp = new SimpleDateFormat(aFormat, Locale.US);
		return sdfTemp.format(aTime);
	}

	/**
	 * ��ѯĳ���µ�����.
	 * 
	 * @param year
	 *            ��
	 * @param month
	 *            ��
	 * @return �µ�����
	 */
	public static int getDayInMonth(int year, int month) {
		final int[] dayNumbers = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		int result;
		if ((month == 2) && ((year % 4) == 0) && ((year % 100) != 0)
				|| ((year % 400) == 0)) {
			result = 29;
		} else {
			result = dayNumbers[month - 1];
		}

		return result;
	}

	/**
	 * ��������Ƿ�Ϸ�.
	 * 
	 * @param ayear
	 *            ��
	 * @param amonth
	 *            ��
	 * @param aday
	 *            ��
	 * @return �Ϸ�����0,�Ƿ�����-1,�շ���1
	 */
	public static int validDate(int ayear, int amonth, int aday) {
		int isGood = 0;
		if ((ayear == 0) || (amonth == 0) || (aday == 0)) {
			isGood = 1;
		} else {
			int monthDays = getDayInMonth(ayear, amonth);
			if ((aday < 1) || (aday > monthDays)) {
				isGood = -1;
			}
		}
		return isGood;
	}

	/**
	 * ��������Ƿ�Ϸ�.
	 * 
	 * @param syear
	 *            ��
	 * @param smonth
	 *            ��
	 * @param sday
	 *            ��
	 * @return �Ϸ�����0,�Ƿ�����-1,�շ���1
	 */
	public static int validDate(String syear, String smonth, String sday) {
		int ayear, amonth, aday;
		ayear = StringUtil.myparseInt(syear, 0);
		amonth = StringUtil.myparseInt(smonth, 0);
		aday = StringUtil.myparseInt(sday, 0);
		return validDate(ayear, amonth, aday);
	}

	/**
	 * ���һ�������ַ����Ƿ�Ϸ�: 2003-9-5.
	 * 
	 * @param aDateStr
	 *            �����ַ���
	 * @return �Ϸ�����0,�Ƿ�����-1,�շ���1
	 */
	public static int validDate(String aDateStr) {
		if (StringUtil.isTrimEmpty(aDateStr)) {
			return 1;
		}
		String[] aObj = StringUtil.splitString("/-/", aDateStr);
		if (null == aObj) {
			return 1;
		}
		return validDate(aObj[0], aObj[1], aObj[2]);
	}

	/**
	 * ���ʱ��ĺϷ���.
	 * 
	 * @param nHour
	 *            int
	 * @param nMin
	 *            int
	 * @param nSec
	 *            int
	 * @return int �Ϸ�����0,�Ƿ�����-1,�շ���1
	 */
	public static int validTime(int nHour, int nMin, int nSec) {
		int isGood = 0; // normal
		if ((nHour == 0) || (nMin == 0) || (nSec == 0)) {
			isGood = 1; // empty
		} else {
			if ((nHour > 23 || nHour < 0 || nMin > 59 || nMin < 0 || nSec > 59 || nSec < 0)) {
				isGood = -1; // invalid
			}
		}
		return isGood;
	}

	/**
	 * ���һ�������ַ����Ƿ�Ϸ�: 2003-9-5 13:52:5.
	 * 
	 * @param aDateTimeStr
	 *            �����ַ���
	 * @return �Ϸ�����0,�Ƿ�����-1,�շ���1
	 */
	public static int validDateTime(String aDateTimeStr) {
		if (StringUtil.isTrimEmpty(aDateTimeStr)) {
			return 1;
		}
		String[] aObj = StringUtil.splitString("/ /", aDateTimeStr);
		if (null == aObj) {
			return 1;
		}
		if (aObj.length != 2) {
			return 1;
		}

		if (validDate(aObj[0]) == 0) {
			String[] aTimeObj = StringUtil.splitString("/:/", aObj[1]);
			if (aTimeObj.length == 3) {
				int nHour = StringUtil.myparseInt(aTimeObj[0], 0);
				int nMin = StringUtil.myparseInt(aTimeObj[0], 0);
				int nSec = StringUtil.myparseInt(aTimeObj[0], 0);
				return validTime(nHour, nMin, nSec);
			}
		}
		return -1;
	}

	/**
	 * ��������Ƿ�Ϊ��.
	 * 
	 * @param syear
	 *            ��
	 * @param smonth
	 *            ��
	 * @param sday
	 *            ��
	 * @return Ϊ�շ���true
	 */
	public static boolean isEmptyDate(String syear, String smonth, String sday) {
		boolean isEmpty = false;

		int ayear, amonth, aday;
		ayear = StringUtil.myparseInt(syear, 0);
		amonth = StringUtil.myparseInt(smonth, 0);
		aday = StringUtil.myparseInt(sday, 0);

		if ((ayear == 0) || (amonth == 0) || (aday == 0)) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * ��������Ƿ�Ϊ��.
	 * 
	 * @param ayear
	 *            ��
	 * @param amonth
	 *            ��
	 * @param aday
	 *            ��
	 * @return Ϊ�շ���true
	 */
	public static boolean isEmptyDate(int ayear, int amonth, int aday) {
		boolean isEmpty = false;

		if ((ayear == 0) || (amonth == 0) || (aday == 0)) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * �õ�����ʱ���ǰ/��һ��ʱ��.
	 * 
	 * @param nSecs
	 *            ��������ʱ�������
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getPastTime(int nSecs) {
		java.sql.Timestamp ts1 = getNowTimestamp();
		java.sql.Timestamp ts2;
		ts2 = new java.sql.Timestamp(ts1.getTime() - nSecs * 1000);
		return ts2;
	}

	/**
	 * �õ�����ĳ��ʱ��һ��ʱ���һ��ʱ��.
	 * 
	 * @param aTime
	 *            ��Ե�ʱ��
	 * @param nSecs
	 *            ʱ�����:��
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getPastTime(java.sql.Timestamp aTime,
			int nSecs) {
		java.sql.Timestamp ts2;
		ts2 = new java.sql.Timestamp(aTime.getTime() - nSecs * 1000);
		return ts2;
	}

	/**
	 * ���aDate�ǲ��ǽ���.
	 * 
	 * @param aDate
	 *            ����������
	 * @return �ǽ��췵��true
	 */
	public static boolean isToday(java.sql.Date aDate) {
		Calendar aCal1 = Calendar.getInstance();
		aCal1.setTime(aDate);

		java.sql.Date date1 = getNowSqlDate();

		Calendar aCal2 = Calendar.getInstance();
		aCal2.setTime(date1);

		return ((aCal1.get(Calendar.DATE) == aCal2.get(Calendar.DATE))
				&& (aCal1.get(Calendar.YEAR) == aCal2.get(Calendar.YEAR)) && (aCal1
				.get(Calendar.MONTH) == aCal2.get(Calendar.MONTH)));
	}

	/**
	 * ���ַ������չ���ת��Ϊ����ʱ���longֵ.
	 * 
	 * ������Ϸ�,�򷵻ؽ���.
	 * 
	 * @param str
	 *            Ҫ�������ַ���
	 * @param pattern
	 *            ����
	 * @throws NullPointerException
	 * @return long
	 */
	public static Long str2DateTime(String str, String pattern) {
		DateFormat dateFmt = new SimpleDateFormat(pattern, Locale.US);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFmt.parse(str));
			return new Long(calendar.getTime().getTime());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * ��һ�������ַ����õ�ʱ���longֵ.
	 * 
	 * @param strTemp
	 *            String
	 * @return long
	 */
	public static Long dateStr2Time(String strTemp) {
		if (null == strTemp || strTemp.length() < 8) {
			return null;
		}
		String sSign = strTemp.substring(4, 5);
		String sPattern = new StringBuilder("yyyy").append(sSign).append("MM")
				.append(sSign).append("dd").toString();
		return str2DateTime(strTemp, sPattern);
	}

	/**
	 * ��һ������ʱ���ַ����õ�ʱ���longֵ. ���� 2004-5-6 23:52:22 ,������
	 * 
	 * @param strTemp
	 *            String
	 * @return long
	 */
	public static Long dateTimeStr2Time(String strTemp) {
		if (null == strTemp || strTemp.length() < 8) {
			return null;
		}
		String sSign = strTemp.substring(4, 5);
		String sPattern = new StringBuilder("yyyy").append(sSign).append("MM")
				.append(sSign).append("dd").append(" hh:mm:ss").toString();

		Long aLong = str2DateTime(strTemp, sPattern);
		if (null == aLong) {
			String sShortPattern = new StringBuilder("yyyy").append(sSign)
					.append("MM").append(sSign).append("dd").append(" hh:mm")
					.toString();
			aLong = str2DateTime(strTemp, sShortPattern);
		}
		return aLong;
	}

	/**
	 * ��2003-10-11�����ַ���ת��Ϊʱ���ʽ,������Ϸ�,���ص��ǵ�ǰʱ��.
	 * 
	 * @param aStr
	 *            Ҫ�������ַ���,��ʽΪ2003-11-11
	 * @return Timestamp
	 */
	public static java.sql.Timestamp dateStr2Timestamp(String aStr) {
		Long temp = dateStr2Time(aStr);
		if (null == temp) {
			return null;
		}
		return new java.sql.Timestamp(temp.longValue());
	}

	/**
	 * ��2003-10-11�����ַ���ת��Ϊʱ���ʽ,������Ϸ�,���ص��ǵ�ǰʱ��.
	 * 
	 * @param aStr
	 *            Ҫ�������ַ���,��ʽΪ2003-11-11
	 * @return Date
	 */
	public static java.util.Date dateStr2Date(String aStr) {
		Long result = dateStr2Time(aStr);
		if (null == result) {
			return null;
		}
		return new java.sql.Date(result.longValue());
	}

	/**
	 * ��2003-10-11 23:43:55�����ַ���ת��Ϊʱ���ʽ,������Ϸ�,���ص��ǵ�ǰʱ��.
	 * 
	 * @param aStr
	 *            Ҫ�������ַ���
	 * @return Timestamp
	 */
	public static java.sql.Timestamp dateTimeStr2Timestamp(String aStr) {
		Long tempVar = dateTimeStr2Time(aStr);
		if (null == tempVar) {
			return null;
		}
		return new java.sql.Timestamp(tempVar.longValue());
	}

	/**
	 * �����ַ���ת��Ϊ���ڷ�Χ�Ŀ�ʼʱ��,���Ӹ����0:0:0��ʼ. ��������ݿ����;�����������,��ֱ��getFormDate����.
	 * 
	 * @param aStr
	 *            String
	 * @return Timestamp
	 */
	public static java.sql.Timestamp dateStr2BeginDateTime(String aStr) {
		if (StringUtil.isTrimEmpty(aStr)) {
			return null;
		} else {
			return dateTimeStr2Timestamp(aStr + " 0:0:0");
		}
	}

	/**
	 * �����ַ���ת��Ϊ���ڷ�Χ�Ľ���ʱ��,���������23:59:59. ��������ݿ����;�����������,��ֱ��getFormDate����.
	 * 
	 * @param aStr
	 *            String
	 * @return Timestamp
	 */
	public static java.sql.Timestamp dateStr2EndDateTime(String aStr) {
		if (StringUtil.isTrimEmpty(aStr)) {
			return null;
		} else {
			return dateTimeStr2Timestamp(aStr + " 23:59:59");
		}
	}

	/**
	 * �õ������е���.
	 * 
	 * @param aDate
	 *            Ҫ����������
	 * @return ��
	 */
	public static int getYearFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.YEAR);
	}

	/**
	 * �õ������е���.
	 * 
	 * @param aDate
	 *            Ҫ����������
	 * @return ��
	 */
	public static int getMonthFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.MONTH) + 1;
	}

	/**
	 * �õ������е���.
	 * 
	 * @param aDate
	 *            Ҫ����������
	 * @return ��
	 */
	public static int getDAYFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.DATE);
	}

	/**
	 * �õ������е�Сʱ.
	 * 
	 * @param aDate
	 *            Ҫ����������
	 * @return Сʱ
	 */
	public static int getHourFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * �õ������еķ���.
	 * 
	 * @param aDate
	 *            Ҫ����������
	 * @return ����
	 */
	public static int getMinuteFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.MINUTE);
	}

	/**
	 * �õ����ڵ�����.
	 * 
	 * @param aDate
	 *            Ҫ����������
	 * @return ����
	 */
	public static int getWeekFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * ��õ�ǰʱ�������ʱ��ľ���
	 * 
	 * @param compMillSecond ����ʱ�����Э������ʱ 1970 �� 1 �� 1 ����ҹ֮���ʱ���
	 * @return ����:367Day 59H 56Min 
	 */
	public static String diff(long compMillSecond) {
		long diff = System.currentTimeMillis() - compMillSecond;
		long day = diff/ONE_DAY;
		long hour = (diff%ONE_DAY)/ONE_HOUR;
		long min = ((diff%ONE_DAY)%ONE_HOUR)/ONE_MIN;
		return String.valueOf(day) + " Days " + String.valueOf(hour) + " Hours " +  String.valueOf(min) + " Mins ";
	}
	/**
	 * ��õ�ǰʱ�������ʱ��֮������ķ�����
	 * @param date
	 * @return
	 */
	public static long minitueBeforNow(Date date){
	    if(System.currentTimeMillis() < date.getTime())
	    {
	        return -1;
	    }
	    long diff = System.currentTimeMillis() - date.getTime();
	    long min = diff/ONE_MIN;
	    return min;
	}
}
