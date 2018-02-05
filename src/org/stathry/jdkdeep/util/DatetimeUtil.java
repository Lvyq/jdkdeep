package org.stathry.jdkdeep.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年11月23日
 */
public class DatetimeUtil {

	private static final String format0 = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String format1 = "yyyy-MM-dd HH:mm";
	
	public static void main(String[] args) {
//		System.out.println(format(nextTime(TimeUnit.SECONDS, 1)));
//		System.out.println(format(nextTime(TimeUnit.MINUTES, 1)));
//		System.out.println(format(nextTime(TimeUnit.HOURS, 1)));
//		System.out.println(format(nextTime(TimeUnit.DAYS, 1)));
		System.out.println(format(new Date()));
		System.out.println(TimeUnit.MILLISECONDS.toHours(toNextTime(TimeUnit.DAYS, 1)));
		System.out.println(TimeUnit.MILLISECONDS.toMinutes(toNextTime(TimeUnit.HOURS, 1)));
		System.out.println(TimeUnit.MILLISECONDS.toSeconds(toNextTime(TimeUnit.MINUTES, 1)));
		System.out.println(TimeUnit.MILLISECONDS.toMillis(toNextTime(TimeUnit.SECONDS, 1)));
	}
	
	public static String format(Date date) {
		return new SimpleDateFormat(format0).format(date);
	}
	
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static long toNextTime(TimeUnit unit, int n) {
		long start = System.currentTimeMillis();
		Date next = nextTime(unit, n);
		long delay = next.getTime() - start;
		return delay;
	}
	
	public static Date nextTime(TimeUnit unit, int n) {
		Calendar c = Calendar.getInstance();
		switch (unit) {
		case DAYS:
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.add(Calendar.DATE, n);
			break;
		case HOURS:
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MINUTE, 0);
			c.add(Calendar.HOUR_OF_DAY, n);
			break;
		case MINUTES:
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.SECOND, 0);
			c.add(Calendar.MINUTE, n);
			break;
		case SECONDS:
			c.set(Calendar.MILLISECOND, 0);
			c.add(Calendar.SECOND, n);
			break;
		default:
			break;
		}
//		System.out.println(format(c.getTime()));
		return c.getTime();
	}
	
}
