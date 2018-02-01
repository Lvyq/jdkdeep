package org.bryadong.jdkdeep.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年8月29日
 */
public class ThreadPoolTest {

	@Test
	public void testFix() throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		final CountDownLatch latch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			final int n = i;
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "   " + n);
					latch.countDown();
				}
			});
		}
		latch.await();
	}
	
	@Test
	public void testFix2() throws InterruptedException {
	    ExecutorService exec1 = Executors.newFixedThreadPool(10, new MyThreadFactory("exec-zmxySync"));
	    final CountDownLatch latch1 = new CountDownLatch(100);
	    for (int i = 0; i < 100; i++) {
	        final int n = i;
	        exec1.execute(new Runnable() {
	            
	            @Override
	            public void run() {
	                System.out.println(Thread.currentThread().getName() + "   " + n);
	                latch1.countDown();
	            }
	        });
	    }
	    latch1.await();
	    
	    
	    ExecutorService exec2 = Executors.newFixedThreadPool(10, new MyThreadFactory("exec-zmxySync"));
	    final CountDownLatch latch2 = new CountDownLatch(100);
	    for (int i = 0; i < 100; i++) {
	        final int n = i;
	        exec2.execute(new Runnable() {
	            
	            @Override
	            public void run() {
	                System.out.println(Thread.currentThread().getName() + "   " + n);
	                latch2.countDown();
	            }
	        });
	    }
	    latch2.await();
	}
	
	@Test
	public void testCache() throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final CountDownLatch latch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					latch.countDown();
				}
			});
		}
		latch.await();
	}
	
	@Test
	public void testScheduler1() throws InterruptedException {
		 ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
		final CountDownLatch latch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			final int n = i;
			System.out.println("start thread " + i 
					+ " @" + new Date().toLocaleString());
			exec.schedule(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "---" + n 
							+ " @" + new Date().toLocaleString());
					latch.countDown();
				}
			}, 10, TimeUnit.SECONDS);
			
			Thread.sleep(10 * 1000);
		}
		latch.await();
	}
	
	@Test
	public void testScheduler2() throws InterruptedException {
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
		final CountDownLatch latch = new CountDownLatch(100);

		System.out.println("start thread @" + new Date().toLocaleString());
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " @" 
			+ new Date().toLocaleString());
				latch.countDown();
			}
		}, 10, 5, TimeUnit.SECONDS);

		Thread.sleep(10 * 1000);

		latch.await();
	}

}
