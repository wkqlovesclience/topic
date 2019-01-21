/*
 * 文件名： DateUtil.java
 * 
 * 创建日期： 2010-3-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
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
 * 日期的工具类，包含了字符串和日期之间转换的方法
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
	 * ORA标准时间格式
	 */
	private static  final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * 带时分秒的ORA标准时间格式
	 */
	private static final  SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static final long ONE_DAY = 24 *60 * 60 * 1000L;
	
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	
	private static final long ONE_MIN = 60 * 1000L;
	
	private DateUtil(){}
	
	/**
	 * 将两个格式为HH:MM:SS的时间字符串相加，例如：00:59:06 + 01:00:59 返回 02:00:05。
	 * 
	 * @param time1
	 *            要累计的时间字符串
	 * @param time2
	 *            要累计的时间字符串
	 * 
	 * @return 累计后的时间字符串
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
				// 超过1分钟的时间累计到min1中
				min1 = min1 + totalSec / 60;
			}
			if (totalSec % 60 > 9) {
				lastSec = Integer.toString(new Integer(totalSec % 60));
			} else {
				lastSec = new String("0" + Integer.toString(new Integer(totalSec % 60)));
			}
	
			int totalMin = min1 + min2;
			if (totalMin / 60 > 0) {
				// 超过1分钟的时间累计到hour1中
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
	 * 创建一个标准ORA时间格式的克隆
	 * 
	 * @return 标准ORA时间格式的克隆
	 */
	private static synchronized DateFormat getOraDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * 创建一个带分秒的ORA时间格式的克隆
	 * 
	 * @return 标准ORA时间格式的克隆
	 */
	private static synchronized DateFormat getOraExtendDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_EXTENDED_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * 得到系统当前的日期 格式为YYYY-MM-DD
	 * 
	 * @return 系统当前的日期 格式为YYYY-MM-DD
	 */
	public static String getSystemCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return doTransform(calendar.get(Calendar.YEAR), calendar
				.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 返回格式为YYYY-MM-DD
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
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
	 * 得到系统当前的日期和时间 格式为YYYY-MM-DD hh:mm:ss
	 * 
	 * @return 格式为YYYY-MM-DD hh:mm:ss的系统当前的日期和时间
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
	 * 得到系统当前的时间 格式为hh:mm:ss
	 * 
	 * @return 格式为hh:mm:ss的系统当前时间
	 */
	public static final String getSystemCurrentTime() {
		return getSystemCurrentDateTime().substring(11, 19);
	}

	/**
	 * 返回格式为YYYY-MM-DD hh:mm:ss
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
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
	 * 获得昨天的日期
	 * 
	 * @return 指定日期的上一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayBeforeToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}
	
	/**
	 * 获得指定日期的上一天的日期
	 * 
	 * @param dateStr 指定的日期 格式:YYYY-MM-DD
	 * 
	 * @return 指定日期的上一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayBeforeToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}

	/**
	 * 获得明天的日期
	 * 
	 * @return 指定日期的下一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayAfterToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}
	
	/**
	 * 获得指定日期的下一天的日期
	 * 
	 * @param dateStr 指定的日期 格式:YYYY-MM-DD
	 * 
	 * @return 指定日期的下一天 格式:YYYY-MM-DD
	 */
	public static synchronized String getDayAfterToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return doTransformDate(toString(gc.getTime(), getOraDateTimeFormat()));
	}

	/**
	 * 获得指定日期的前后几个月的日期
	 * 
	 * @return 指定日期的后面几个月,正数为后,负数为前.
	 */
	public static synchronized Date getDayBeforeAfterMonth(int months) {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
	}
	
	/**
	 * 返回格式为YYYY-MM-DD hh:mm:ss
	 * 
	 * @param date
	 *            输入格式为ORA带小时分秒的标准时间格式
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
	 * 返回格式为YYYY-MM-DD
	 * 
	 * @param date
	 *            输入格式为ORA不带小时分秒的标准时间格式
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
	 * 将一个日期对象转换成为指定日期、时间格式的字符串。 如果日期对象为空，返回一个空字符串对象.
	 * 
	 * @param theDate
	 *            要转换的日期对象
	 * @param theDateFormat
	 *            返回的日期字符串的格式
	 * @return 转换结果
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
	 * 将Date类型转换后返回本系统默认的日期格式 YYYY-MM-DD hh:mm:ss
	 * 
	 * @param theDate
	 *            要转换的Date对象
	 * @return 转换后的字符串
	 */
	public static synchronized String toDefaultString(Date theDate) {
		if (theDate == null) {
			return "";
		}
		return doTransformDateTime(toString(theDate, getOraExtendDateTimeFormat()));
	}

	/**
	 * 将输入格式为2004-8-13 12:31:22类型的字符串转换为标准的Date类型
	 * 
	 * @param dateStr
	 *            要转换的字符串
	 * 
	 * @return 转化后的标准的Date类型
	 * @throws ParseException 
	 */
	public static synchronized Date toDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dateStr);
	}

	/**
	 * 将输入格式为2004-8-13,2004-10-8类型的字符串转换为标准的Date类型,这种Date类型 对应的日期格式为YYYY-MM-DD
	 * 00:00:00,代表一天的开始时刻
	 * 
	 * @param dateStr
	 *            要转换的字符串
	 * @return 转换后的Date对象
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
	 * 将输入格式为2004-8-13,2004-10-8类型的字符串转换为标准的Date类型,这种Date类型 对应的日期格式为YYYY-MM-DD
	 * 23:59:59,代表一天的结束时刻
	 * 
	 * @param dateStr
	 *            输入格式:2004-8-13,2004-10-8
	 * @return 转换后的Date对象
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
	 * 将两个scorm时间相加
	 * 
	 * @param scormTime1
	 *            scorm时间,格式为00:00:00(1..2).0(1..3)
	 * @param scormTime2
	 *            scorm时间,格式为00:00:00(1..2).0(1..3)
	 * @return 两个scorm时间相加的结果
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
	 * 根据timeType返回当前日期与传入日期的差值（当前日期减传入日期） 当要求返回月份的时候，date的日期必须和当前的日期相等，
	 * 否则返回0（例如：2003-2-23 和 2004-6-12由于23号和12号不是同一天，固返回0， 2003-2-23 和 2005-6-23
	 * 则需计算相差的月份，包括年，此例应返回28（个月）。 2003-2-23 和 2001-6-23
	 * 也需计算相差的月份，包括年，此例应返回-20（个月））
	 * 
	 * @param date
	 *            要与当前日期比较的日期
	 * @param timeType
	 *            0代表返回两个日期相差月数，1代表返回两个日期相差天数
	 * 
	 * @return 根据timeType返回当前日期与传入日期的差值
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
	 * 判断当前月份是否是最后12月
	 * 
	 * @return 否是最后12月
	 */
	public static boolean isLastMonth() {
		if ("12".equals(month().toString()))
			return true;
		return false;
	}

	/**
	 * 获得当前日期的年份
	 * 
	 * @return 当前日期的年份
	 */
	public static Integer year() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.YEAR));
	}

	/**
	 * 获得当前日期的月份
	 * 
	 * @return 当前日期的月份
	 */
	public static Integer month() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得当前日期的前一个月份,如果当前是1月，返回12
	 * 
	 * @return 当前日期的前一个月份
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
	 * 获得当前日期的后一个月份,如果当前是12月，返回1
	 * 
	 * @return 当前日期的后一个月份
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
	 * 获得当前日期的日
	 * 
	 * @return 当前日期的日
	 */
	public static Integer day() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得当前时间的小时
	 * 
	 * @return 当前时间的小时
	 */
	public static Integer hour() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * 获得当前时间的分钟
	 * 
	 * @return 当前时间的分钟
	 */
	public static Integer minute() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.MINUTE));
	}

	/**
	 * 获得当前时间的秒
	 * 
	 * @return 当前时间的秒
	 */
	public static Integer second() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.SECOND));
	}

	/**
	 * 获得当前的星期是本月的第几周
	 * 
	 * @return 当前的星期是本月的第几周
	 */
	public static int WeekofMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获得当前的日期是本星期的第几天
	 * 
	 * @return 当前的日期是本星期的第几天
	 */
	public static int DayofWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获得这个月有几周
	 * 
	 * @return 这个月有几周
	 */
	public static int getMaxWeekofMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 得到当前日期的字符串，格式是YYYY-MM-DD
	 * 
	 * @return 当前日期的字符串，格式是YYYY-MM-DD
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
	 * 得到当前日期时间的字符串，格式是YYYY-MM-DD HH:MM:SS
	 * 
	 * @return 当前日期时间的字符串，格式是YYYY-MM-DD HH:MM:SS
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
	 * 比较2个日期大小
	 * 
	 * @param startTime
	 *            起始日期
	 * @param endTime
	 *            结束日期
	 * @return 比较2个日期大小。>0：startTime>endTime 0:startTime=endTime <0:startTime<endTime
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
	 * 比较一个日期是否在指定的日期段中
	 * 
	 * @param nowSysDateTime
	 *            要判断的日期
	 * @param startTime
	 *            起始日期
	 * @param endTime
	 *            结束日期
	 * @return nowSysDateTime在startTime和endTime中返回true
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
	 * 得到某一年的某月的前/后一个月是那一年 例如得到2002年1月的前一个月是哪年 (2002,1,-1) =2001.
	 * 
	 * @param year
	 *            时间点的年份
	 * @param month
	 *            时间点的月份
	 * @param pastMonth
	 *            于时间点的月份距离,负数表示以前的时间,正数表示以后的时间
	 * @return 返回年
	 */
	public static int getYearPastMonth(int year, int month, int pastMonth) {
		return year
				+ (int) Math.floor((double) (month - 1 + pastMonth)
						/ (double) 12);
	}

	/**
	 * 得到某个月的下几个个月是那个月.
	 * 
	 * @param month
	 *            当前月
	 * @param pastMonth
	 *            和当前月的月数差距
	 * @return 目标月数
	 */
	public static int getMonthPastMonth(int month, int pastMonth) {
		return ((12 + month - 1 + pastMonth) % 12) + 1;
	}

	/**
	 * 返回月份的季度数.
	 * 
	 * 0表示非法月份.正常返回1,2,3,4.
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
	 * 得到当前日期.
	 * 
	 * @return 当前日期的java.sql.Date格式
	 */
	public static java.sql.Date getNowSqlDate() {
		java.util.Date aDate = new java.util.Date();
		return new java.sql.Date(aDate.getTime());
	}

	/**
	 * 得到当前日期.
	 * 
	 * @return 当前日期的java.util.Date格式
	 */
	public static java.util.Date getNowDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 得到当前时间.
	 * 
	 * @return java.sql.Time
	 */
	public static java.sql.Timestamp getNowTimestamp() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	/**
	 * 得到某个时间的字符串显示,格式为yyyy-MM-dd HH:mm.
	 * 
	 * @param aTime
	 *            要分析的时间
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
	 * 按照自己的格式显示时间.
	 * 
	 * @param aTime
	 *            要分析的时间
	 * @param aFormat
	 *            按照SimpleDateFormat的规则的格式
	 * @return 字符串
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
	 * 按照自己的格式显示时间.
	 * 
	 * @param aTime
	 *            要分析的时间
	 * @param aFormat
	 *            按照SimpleDateFormat的规则的格式
	 * @return 字符串
	 */
	public static String getSelfTimeShow(java.sql.Date aTime, String aFormat) {
		if (null == aTime) {
			return "";
		}
		SimpleDateFormat sdfTemp = new SimpleDateFormat(aFormat, Locale.US);
		return sdfTemp.format(aTime);
	}

	/**
	 * 查询某个月的天数.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 月的天数
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
	 * 检查日期是否合法.
	 * 
	 * @param ayear
	 *            年
	 * @param amonth
	 *            月
	 * @param aday
	 *            日
	 * @return 合法返回0,非法返回-1,空返回1
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
	 * 检查日期是否合法.
	 * 
	 * @param syear
	 *            年
	 * @param smonth
	 *            月
	 * @param sday
	 *            日
	 * @return 合法返回0,非法返回-1,空返回1
	 */
	public static int validDate(String syear, String smonth, String sday) {
		int ayear, amonth, aday;
		ayear = StringUtil.myparseInt(syear, 0);
		amonth = StringUtil.myparseInt(smonth, 0);
		aday = StringUtil.myparseInt(sday, 0);
		return validDate(ayear, amonth, aday);
	}

	/**
	 * 检查一个日期字符串是否合法: 2003-9-5.
	 * 
	 * @param aDateStr
	 *            日期字符串
	 * @return 合法返回0,非法返回-1,空返回1
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
	 * 检测时间的合法性.
	 * 
	 * @param nHour
	 *            int
	 * @param nMin
	 *            int
	 * @param nSec
	 *            int
	 * @return int 合法返回0,非法返回-1,空返回1
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
	 * 检查一个日期字符串是否合法: 2003-9-5 13:52:5.
	 * 
	 * @param aDateTimeStr
	 *            日期字符串
	 * @return 合法返回0,非法返回-1,空返回1
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
	 * 检查日期是否为空.
	 * 
	 * @param syear
	 *            年
	 * @param smonth
	 *            月
	 * @param sday
	 *            日
	 * @return 为空返回true
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
	 * 检查日期是否为空.
	 * 
	 * @param ayear
	 *            年
	 * @param amonth
	 *            月
	 * @param aday
	 *            日
	 * @return 为空返回true
	 */
	public static boolean isEmptyDate(int ayear, int amonth, int aday) {
		boolean isEmpty = false;

		if ((ayear == 0) || (amonth == 0) || (aday == 0)) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * 得到现在时间的前/后一段时间.
	 * 
	 * @param nSecs
	 *            距离现在时间的秒数
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getPastTime(int nSecs) {
		java.sql.Timestamp ts1 = getNowTimestamp();
		java.sql.Timestamp ts2;
		ts2 = new java.sql.Timestamp(ts1.getTime() - nSecs * 1000);
		return ts2;
	}

	/**
	 * 得到距离某个时间一段时间的一个时间.
	 * 
	 * @param aTime
	 *            相对的时间
	 * @param nSecs
	 *            时间距离:秒
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getPastTime(java.sql.Timestamp aTime,
			int nSecs) {
		java.sql.Timestamp ts2;
		ts2 = new java.sql.Timestamp(aTime.getTime() - nSecs * 1000);
		return ts2;
	}

	/**
	 * 检查aDate是不是今天.
	 * 
	 * @param aDate
	 *            分析的日期
	 * @return 是今天返回true
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
	 * 把字符串按照规则转换为日期时间的long值.
	 * 
	 * 如果不合法,则返回今天.
	 * 
	 * @param str
	 *            要分析的字符串
	 * @param pattern
	 *            规则
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
	 * 从一个日期字符串得到时间的long值.
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
	 * 从一个日期时间字符串得到时间的long值. 例如 2004-5-6 23:52:22 ,包含秒
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
	 * 把2003-10-11这种字符串转换为时间格式,如果不合法,返回的是当前时间.
	 * 
	 * @param aStr
	 *            要分析的字符串,格式为2003-11-11
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
	 * 把2003-10-11这种字符串转换为时间格式,如果不合法,返回的是当前时间.
	 * 
	 * @param aStr
	 *            要分析的字符串,格式为2003-11-11
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
	 * 把2003-10-11 23:43:55这种字符串转换为时间格式,如果不合法,返回的是当前时间.
	 * 
	 * @param aStr
	 *            要分析的字符串
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
	 * 日期字符串转换为日期范围的开始时间,即从该天的0:0:0开始. 如果该数据库类型就是日期类型,则直接getFormDate即可.
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
	 * 日期字符串转换为日期范围的结束时间,即到该天的23:59:59. 如果该数据库类型就是日期类型,则直接getFormDate即可.
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
	 * 得到日期中的年.
	 * 
	 * @param aDate
	 *            要分析的日期
	 * @return 年
	 */
	public static int getYearFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.YEAR);
	}

	/**
	 * 得到日期中的月.
	 * 
	 * @param aDate
	 *            要分析的日期
	 * @return 月
	 */
	public static int getMonthFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到日期中的天.
	 * 
	 * @param aDate
	 *            要分析的日期
	 * @return 天
	 */
	public static int getDAYFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.DATE);
	}

	/**
	 * 得到日期中的小时.
	 * 
	 * @param aDate
	 *            要分析的日期
	 * @return 小时
	 */
	public static int getHourFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到日期中的分钟.
	 * 
	 * @param aDate
	 *            要分析的日期
	 * @return 分钟
	 */
	public static int getMinuteFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.MINUTE);
	}

	/**
	 * 得到日期的星期.
	 * 
	 * @param aDate
	 *            要分析的日期
	 * @return 星期
	 */
	public static int getWeekFromDate(java.util.Date aDate) {
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(aDate);
		return cFF.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得当前时间与给定时间的距离
	 * 
	 * @param compMillSecond 给定时间的与协调世界时 1970 年 1 月 1 日午夜之间的时间差
	 * @return 例如:367Day 59H 56Min 
	 */
	public static String diff(long compMillSecond) {
		long diff = System.currentTimeMillis() - compMillSecond;
		long day = diff/ONE_DAY;
		long hour = (diff%ONE_DAY)/ONE_HOUR;
		long min = ((diff%ONE_DAY)%ONE_HOUR)/ONE_MIN;
		return String.valueOf(day) + " Days " + String.valueOf(hour) + " Hours " +  String.valueOf(min) + " Mins ";
	}
	/**
	 * 获得当前时间与给定时间之间相隔的分钟数
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
