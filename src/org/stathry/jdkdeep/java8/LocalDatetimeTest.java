package org.stathry.jdkdeep.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月6日
 */
public class LocalDatetimeTest {

	@Test
	public void testLocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		System.out.println(now.toLocalDate());
		System.out.println(now.toLocalTime());
		System.out.println(now.format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSS")));
	}
	
	@Test
	public void testLocalDate() {
		LocalDate now = LocalDate.parse("2017-12-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	}
	@Test
	public void testLocalTime() {
		LocalTime now = LocalTime.parse("16:27:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
		System.out.println(now.format(DateTimeFormatter.ofPattern("HHmmssSSS")));
	}

}
