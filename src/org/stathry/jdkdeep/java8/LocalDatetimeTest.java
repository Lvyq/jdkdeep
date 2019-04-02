package org.stathry.jdkdeep.java8;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * LocalDatetimeTest
 * @author dongdaiming
 * @date 2017年12月6日
 */
public class LocalDatetimeTest {

    @Test
    public void testMinusMon() {
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 40; i++) {
            System.out.println(now.minusDays(i).minusMonths(1) + "---" + now.minusDays(i));
        }
    }

    @Test
    public void testDatetime() {
        LocalDateTime time1 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime time2 = LocalDate.now().atStartOfDay();
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time1.compareTo(time2));
    }

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
