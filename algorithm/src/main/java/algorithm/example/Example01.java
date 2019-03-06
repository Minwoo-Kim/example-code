package algorithm.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Example01 {
	public static void main(String[] args) throws Exception {
		Example01 ex01 = new Example01();
		ex01.ex1();
	}

	public void ex1() throws Exception {
		String DATE_FORMAT = "yyyymmdd";

		Date date = new SimpleDateFormat(DATE_FORMAT).parse("20190222");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		String preDate = new SimpleDateFormat(DATE_FORMAT).format(calendar.getTime());

		System.out.println(preDate);

	}
}
