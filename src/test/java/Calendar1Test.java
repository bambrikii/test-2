import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Arakelyan on 03.09.16 22,30.
 */
public class Calendar1Test {

	private static final Logger logger = LoggerFactory.getLogger(Calendar1Test.class);

	@Test
	public void test1() {
		m1(2008, 1, 1, 18, 0, 0);
		logger.info("");
		m1(2008, 8, 1, 18, 0, 0);

		m1(2016, 2, 12, 18, 0, 0);
		m1(2016, 2, 13, 18, 0, 0);
		m1(2016, 2, 14, 18, 0, 0);
		m1(2016, 9, 29, 18, 0, 0);
		m1(2016, 9, 30, 18, 0, 0);
		m1(2016, 9, 31, 18, 0, 0);
	}

	private void m1(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Europe/Budapest"));
		cal.set(year, month, date, hourOfDay, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		long millis = cal.getTimeInMillis();
		logger.info("{}", millis);
		print(cal);

		Calendar cal2 = new GregorianCalendar();
		cal2.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		cal2.setTimeInMillis(millis);
		print(cal2);

		cal2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		cal2.setTimeInMillis(millis);
		print(cal2);

		Calendar cal4 = new GregorianCalendar();
		cal4.setTimeInMillis(millis);
		print(cal4);

		Calendar cal5 = new GregorianCalendar();
		cal5.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal5.setTimeInMillis(millis);
		print(cal5);
	}

	public void print(Calendar cal) {
		long year = cal.get(YEAR);
		long mon = cal.get(MONTH) + 1;
		long day = cal.get(DAY_OF_MONTH);
		long hour = cal.get(HOUR_OF_DAY);
		long min = cal.get(MINUTE);
		long sec = cal.get(SECOND);
		String tz = cal.getTimeZone().getID();
		logger.info(String.format("%04d-%02d-%02d %02d:%02d:%02d %-15s", year, mon, day, hour, min, sec, tz));
	}
}
