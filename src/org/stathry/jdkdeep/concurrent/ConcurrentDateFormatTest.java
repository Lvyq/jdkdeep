package org.stathry.jdkdeep.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月21日
 */
public class ConcurrentDateFormatTest {

	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(PATTERN);
	private static final AtomicLong counter = new AtomicLong(0);

	@Test
	public void test() throws InterruptedException {
		int limit = 1000000;
//		ExecutorService exec = new ThreadPoolExecutor(100, 1000, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(limit));
		ExecutorService exec = new ThreadPoolExecutor(100, 1000, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(limit));
		for (int i = 0; i < limit; i++) {
			exec.submit(new Runnable() {

				@Override
				public void run() {
					Date now = new Date();
					String custDate = FORMAT.format(now);
					String date = new SimpleDateFormat(PATTERN).format(now);
					if(!custDate.equals(date)) {
						System.out.println("false___" + custDate + "___" + date);
						counter.incrementAndGet();
					} else {
						System.out.println("true___" + custDate + "___" + date);
					}
				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(600, TimeUnit.SECONDS);
		System.out.println(counter.get());
	}

	@Test
	public void testFormat() {
		new SimpleDateFormat("abc").format(new Date());
	}

}
