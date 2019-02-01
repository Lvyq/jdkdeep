package org.stathry.jdkdeep.java8;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月6日
 */
public class LocalDatetimeTest {

    @Test
    public void testMills2LocalDate() {
        long ms = System.currentTimeMillis();
        LocalDateTime date = Instant.ofEpochMilli(ms).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(date);

        LocalDateTime date2 = new Timestamp(ms).toLocalDateTime();
        System.out.println(date2);

        LocalDateTime date3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault());
        System.out.println(date3);

        System.out.println(ms);
    }

    @Test
    public void testLocalDate2Mills() {
        System.out.println("ldt " + LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println("ctm " + System.currentTimeMillis());
    }

	@Test
	public void testLocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		System.out.println(now.toLocalDate());
		System.out.println(now.toLocalTime());
		System.out.println(now.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ss.SSS")));
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
