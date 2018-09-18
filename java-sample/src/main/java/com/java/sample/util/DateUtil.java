package com.java.sample.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	// private static String LOCALE_UTC = "UTC";
	private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	// private static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	// private static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// private static String DEFAULT_DETAIL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * Date To String
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String dateToString(Date date, String format) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * String To Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String dateStr, String format) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(dateStr);
	}

	/**
	 * date에 addDate 만큼 추가한 날짜를 리턴
	 * 
	 * @param date
	 * @param addDate
	 * @return
	 */
	public static Date addDate(Date date, int addDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addDate);
		return calendar.getTime();
	}

	/**
	 * date에 addDate 만큼 추가한 날짜를 리턴
	 * 
	 * @param date
	 * @param addDate
	 * @return
	 */
	public static String addDateToStr(Date date, int addDate) {
		Date value = addDate(date, addDate);
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(value);
	}

	/**
	 * 현재 년도 가져오기 실행.
	 * 
	 * @return
	 */
	public static String getYear() {
		return dateInfoMap().get(Calendar.YEAR);
	}

	/**
	 * 현재 월 리턴.
	 * 
	 * @return
	 */
	public static String getMonth() {
		return dateInfoMap().get(Calendar.MONTH);
	}

	/**
	 * 현재 일자 리턴.
	 * 
	 * @return
	 */
	public static String getDay() {
		return dateInfoMap().get(Calendar.DATE);
	}

	/**
	 * 현재 시간 리턴.
	 * 
	 * @return
	 */
	public static String getHour() {
		return dateInfoMap().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 현재 분 리턴.
	 * 
	 * @return
	 */
	public static String getMinute() {
		return dateInfoMap().get(Calendar.MINUTE);
	}

	/**
	 * 현재 초 리턴.
	 * 
	 * @return
	 */
	public static String getSecond() {
		return dateInfoMap().get(Calendar.SECOND);
	}

	/**
	 * 현재 날짜의 년월 리턴.
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map = dateInfoMap();
		sb.append(map.get(Calendar.YEAR));
		sb.append(map.get(Calendar.MONTH));
		return sb.toString();
	}

	/**
	 * 현재 날짜의 년월일 리턴
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map = dateInfoMap();
		sb.append(getCurrentMonth());
		sb.append(map.get(Calendar.DATE));
		return sb.toString();
	}

	/**
	 * 현재 날짜의 년월일시 리턴
	 * 
	 * @return
	 */
	public static String getCurrentHour() {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map = dateInfoMap();
		sb.append(getCurrentDay());
		sb.append(map.get(Calendar.HOUR_OF_DAY));
		return sb.toString();
	}

	/**
	 * 현재 날짜의 년월일시분 리턴
	 * 
	 * @return
	 */
	public static String getCurrentMinute() {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map = dateInfoMap();
		sb.append(getCurrentHour());
		sb.append(map.get(Calendar.MINUTE));
		return sb.toString();
	}

	/**
	 * 현재 날짜의 년월일시분초 리턴
	 * 
	 * @return
	 */
	public static String getCurrentSecond() {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map = dateInfoMap();
		sb.append(getCurrentMinute());
		sb.append(map.get(Calendar.SECOND));
		return sb.toString();
	}

	/**
	 * 현재 날짜에 대한 년도, 월, 일자 정보를 Map으로 리턴
	 * 
	 * @return
	 */
	public static Map<Integer, String> dateInfoMap() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(Calendar.YEAR, Integer.toString(calendar.get(Calendar.YEAR)));
		map.put(Calendar.MONTH, Integer.toString(calendar.get(Calendar.MONTH) + 1));
		map.put(Calendar.DATE, Integer.toString(calendar.get(Calendar.DATE)));
		map.put(Calendar.HOUR_OF_DAY, Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)));
		map.put(Calendar.MINUTE, Integer.toString(calendar.get(Calendar.MINUTE)));
		map.put(Calendar.SECOND, Integer.toString(calendar.get(Calendar.SECOND)));

		for (int key : map.keySet()) {
			String value = map.get(key);
			if (value.length() == 1) {
				map.put(key, "0" + value);
			}
		}
		return map;
	}
}