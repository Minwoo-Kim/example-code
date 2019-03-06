package com.java.sample.basic.java8.localdate_;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@SuppressWarnings("unused")
public class LocalDateSamp {
	public static void main(String[] args) {
		LocalDateSamp localDateSamp = new LocalDateSamp();
		localDateSamp.samp();
	}

	public void samp() {
		// 컴퓨터의 현재 날짜 정보를 저장한 LocalDate 객체를 리턴한다. 결과 : 2018-01-31
		LocalDate currentDate = LocalDate.now();
		LocalDate targetDate = LocalDate.of(2018, 01, 31);
		LocalDate parseDate = LocalDate.parse("2018-01-31");

		// Minus
		LocalDate parseDate2 = LocalDate.parse("20180131", DateTimeFormatter.BASIC_ISO_DATE);
		String minusOne = parseDate2.minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE);

		// 파라미터로 주어진 시간 정보를 저장한 LocalTime 객체를 리턴한다. 컴퓨터의 현재 시간 정보. 결과 : 16:24:02.408
		LocalTime currentTime = LocalTime.now();
		LocalTime targetTime = LocalTime.of(11, 34, 30, 345);

		// 컴퓨터의 현재 날짜와 시간 정보. 결과 : 2018-01-31T11:37:30.000000345
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime targetDateTime = LocalDateTime.of(2018, 01, 31, 11, 37, 30, 345);
		LocalDateTime parseDateTime = LocalDateTime.parse("2018-12-26T17:18:10.535");

		// 2018-01-31T02:38:31.863Z[UTC]
		ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		// 2018-01-31T11:38:42.656+09:00[Asia/Seoul]
		ZonedDateTime seoulDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

		System.out.println("DD");
	}

	public void calculate() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime targetDateTime = currentDateTime.plusYears(1) // 년도 더하기
				.minusYears(1) // 년도 빼기
				.plusMonths(1) // 월 더하기
				.minusMonths(1) // 월 빼기
				.plusDays(1) // 일 더하기
				.minusDays(1) // 일 빼기
				.plusWeeks(1) // 주 더하기
				.minusWeeks(1) // 주 빼기
				.plusHours(1) // 시간 더하기
				.minusHours(1) // 시간 빼기
				.plusMinutes(1) // 분 더하기
				.minusMinutes(1) // 분 빼기
				.plusSeconds(1) // 초 더하기
				.minusSeconds(1) // 초 빼기
				.plusNanos(1) // 나노초 더하기
				.minusNanos(1); // 나노초 빼기
	}

	public void modify() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime targetDateTime3 = currentDateTime.withYear(1) // 년도를 변경
				.withMonth(1) // 월 변경
				.withDayOfMonth(1) // 월의 일을 변경
				.withDayOfYear(1) // 년도의 일을 변경
				// .with(TemporalAdjuster adjuster) // 현재 날짜를 기준으로 상대적인 날짜로 변경
				.with(TemporalAdjusters.firstDayOfYear()) // 이번 년도의 첫 번째 일(1월 1일)
				.with(TemporalAdjusters.lastDayOfYear()) // 이번 년도의 마지막 일(12월 31일)
				.with(TemporalAdjusters.firstDayOfNextYear()) // 다음 년도의 첫 번째 일(1월 1일)
				.with(TemporalAdjusters.firstDayOfMonth()) // 이번 달의 첫 번째 일(1일)
				.with(TemporalAdjusters.lastDayOfMonth()) // 이번 달의 마지막 일
				.with(TemporalAdjusters.firstDayOfNextMonth()) // 다음 달의 첫 번째 일(1일)
				.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)) // 이번 달의 첫 번째 요일(여기서는 월요일)
				.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)) // 이번 달의 마지막 요일(여기서는 마지막 금요일)
				.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)) // 다음주 금요일
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)) // 다음주 금요일(오늘 포함. 오늘이 금요일이라면 오늘 날짜가 표시 된다.)
				.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)) // 지난주 금요일
				.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))// 지난주 금요일(오늘 포함)
				.withHour(1) // 시간 변경
				.withMinute(1) // 분 변경
				.withSecond(1) // 초 변경
				.withNano(1); // 나노초 변경
	}

	public void compareDate() {
		// 2016-04-01T23:39:57.008
		LocalDateTime startDateTime = LocalDateTime.now();
		LocalDateTime endDateTime = LocalDateTime.of(2016, 4, 1, 23, 59, 59);

		// startDateTime이 endDateTime 보다 이전 날짜 인지 비교
		startDateTime.isBefore(endDateTime); // true

		// 동일 날짜인지 비교
		startDateTime.isEqual(endDateTime); // false

		// startDateTime이 endDateTime 보다 이후 날짜인지 비교
		startDateTime.isAfter(endDateTime); // false
	}

	public void compareTime() {
		LocalTime startTime = LocalTime.now(); // 23:52:35.603
		LocalTime endTime = LocalTime.of(23, 59, 59);

		// startTime이 endTime 보다 이전 시간 인지 비교
		startTime.isBefore(endTime); // true

		// startTime이 endTime 보다 이후 시간 인지 비교
		startTime.isAfter(endTime); // false
	}

	public void period() {
		LocalDate currentDate = LocalDate.now(); // 2016-04-02
		LocalDate targetDate = LocalDate.of(2016, 5, 5);

		{
			Period period = currentDate.until(targetDate);

			period.getYears(); // 0년
			period.getMonths(); // 1개월
			period.getDays(); // 3일 차이
		}

		{
			Period period = Period.between(currentDate, targetDate);

			period.getYears(); // 0년
			period.getMonths(); // 1개월
			period.getDays(); // 3일 차이
		}

		{
			LocalDate startDate = LocalDate.now(); // 2016-04-02
			LocalDate endDate = LocalDate.of(2016, 5, 5);

			ChronoUnit.DAYS.between(startDate, endDate); // 결과 : 33 (1개월 3일)
		}

		{
			// 시간 차이.
			LocalTime startTime = LocalTime.now(); // 00:35:39
			LocalTime endTime = LocalTime.of(12, 59, 00);

			startTime.until(endTime, ChronoUnit.HOURS);

			Duration duration = Duration.between(startTime, endTime);
			duration.getSeconds(); // 초의 차이
			duration.getNano(); // 나노초의 차이
		}
	}

	public void formatting() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");

		// 결과 : 2016년 4월 2일 오전 1시 4분
		String nowString = now.format(dateTimeFormatter);
	}
}
