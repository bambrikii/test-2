package org.bambrikii.examples.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Alexander Arakelyan on 14/07/17 19:47.
 */
public class DateTimeExample {

	public static final String MMMM_DD_YYYY_HH_MM_SS_NNNNNNNNNNNNNN = "MMMM dd, yyyy, hh:mm:ss.NNNNNNNNNNNNNN";

	public static void main(String[] args) {
		date();
		time();
		dateTime();
		calendar();
		formatDateAndTime();
		parseDateAndTime();
	}

	private static void parseDateAndTime() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date = LocalDate.parse("01 02 2015", f);
		LocalTime time = LocalTime.parse("11:22");
		System.out.println(date);
// 2015-01-02
		System.out.println(time);
// 11:22
	}

	private static void formatDateAndTime() {
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println("DateTimeFormatter.ISO_LOCAL_DATE: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println("DateTimeFormatter.ISO_LOCAL_TIME: " + time.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println("DateTimeFormatter.ISO_LOCAL_DATE_TIME: " + dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		DateTimeFormatter f = DateTimeFormatter.ofPattern(MMMM_DD_YYYY_HH_MM_SS_NNNNNNNNNNNNNN);
		System.out.println(MMMM_DD_YYYY_HH_MM_SS_NNNNNNNNNNNNNN + ": " + dateTime.format(f)); // January 20, 2020, 11:12

		System.out.println("FormatStyle.SHORT:  " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(dateTime)); // 1/20/20 11:12 AM
		System.out.println("FormatStyle.MEDIUM: " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(dateTime)); // Jan 20, 2020 11:12:34 AM
		System.out.println("FormatStyle.LONG:   " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(dateTime)); // java.time.DateTimeException: Unable to extract value: class java.time.LocalDateTime // ZoneID absent
		System.out.println("FormatStyle.FULL:   " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(dateTime)); // java.time.DateTimeException: Unable to extract value: class java.time.LocalDateTime // ZoneID absent
	}

	private static void calendar() {
		System.out.println("--- calendar ---");
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.JANUARY, 1);
		System.out.println(calendar1.getTime());

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2015, Calendar.JANUARY, 1, 0, 0, 0);
		calendar2.set(Calendar.MILLISECOND, 0);
		System.out.println(calendar2.getTime());
	}

	private static void dateTime() {
		System.out.println("--- date time ---");
		LocalDateTime dateTime1 = LocalDateTime.of(2017, Month.JULY, 14, 20, 5, 1, 1);
		System.out.println(dateTime1);

		LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.of(1, 1, 1), LocalTime.of(1, 1, 1, 1));
		System.out.println(dateTime2);
	}

	private static void time() {
		System.out.println("--- time ---");
		LocalTime time1 = LocalTime.of(1, 2, 3, 1);
		System.out.println(time1);

		LocalTime time2 = LocalTime.of(1, 1);
		System.out.println(time2);
		System.out.println(time2.getSecond());
		System.out.println(time2.getNano());
	}

	private static void date() {
		System.out.println("--- date ---");
		LocalDate date1 = LocalDate.of(2017, Month.JULY, 14);
		System.out.println(date1.format(DateTimeFormatter.ISO_DATE));
		Locale.setDefault(Locale.Category.DISPLAY, Locale.ROOT);
		System.out.println(date1);

		LocalDate date3 = LocalDate.of(1, 1, 1);
		System.out.println(date3);
		System.out.println(date3.plusDays(100));
		System.out.println(date3.plusWeeks(100));
		System.out.println(date3.plusMonths(100));
		System.out.println(date3.plusYears(100));

		LocalDate date4 = LocalDate.of(2016, 1, 31);
		System.out.println(date4.plusDays(31));
		System.out.println(date4.plusMonths(1));

//		System.out.println(Month.of(122)); // java.time.DateTimeException: Invalid value for MonthOfYear: 122
		System.out.println(Month.of(1));

//		System.out.println(LocalDate.of(1, 1, 32)); // java.time.DateTimeException: Invalid value for DayOfMonth (valid values 1 - 28/31): 32
	}
}
