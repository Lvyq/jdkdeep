package org.stathry.jdkdeep.concurrent;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年8月10日
 */
public class TestCountDownLatch {

	@Test
	public void testNoCountDown() throws InterruptedException {
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						System.out.println(Thread.currentThread().getName() + " complete.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			},"t" + i);
			t.start();
		}
//		Thread.sleep(5000);
		System.out.println("main thread stoped.");
	}
	
	@Test
	public void testCountDown() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(10);
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						
						System.out.println(new Date().toLocaleString() + "---" + Thread.currentThread().getName() + " complete...");
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			},"t" + i);
			t.start();
		}
		
		latch.await();
		System.out.println("statistics result data.");
	}

}
