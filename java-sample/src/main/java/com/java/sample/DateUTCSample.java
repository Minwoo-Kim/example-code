package com.java.sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUTCSample {
	public static void main(String[] args) {
		DateUTCSample.getUTCTime();
	}
	
	
	public static Date getUTCTime() {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z Z)");
		dateFormat.setTimeZone(timeZone);
		
		String value = dateFormat.format(date);
		
		System.out.format("%s%n%s%n%n", timeZone.getDisplayName(), dateFormat.format(date));
		
		try {
			Date dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
			System.out.println(dd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return new Date();
	}
	
	public static void zoneDateTime() {
		String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss (z Z)";
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
		ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		String time = utcDateTime.format(format);
		System.out.println(time);
	}

	public static void getLocaleTime() {
		TimeZone tz;
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z Z)");

		tz = TimeZone.getTimeZone("Asia/Seoul");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));

		tz = TimeZone.getTimeZone("GMT");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));
		
		tz = TimeZone.getTimeZone("UTC");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));

		tz = TimeZone.getTimeZone("America/Los_Angeles");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));

		tz = TimeZone.getTimeZone("America/New_York");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));

		tz = TimeZone.getTimeZone("Pacific/Honolulu");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));

		tz = TimeZone.getTimeZone("Asia/Shanghai");
		df.setTimeZone(tz);
		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));

	}
}