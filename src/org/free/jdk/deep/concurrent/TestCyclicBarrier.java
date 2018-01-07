package org.free.jdk.deep.concurrent;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import sun.print.resources.serviceui;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年8月10日
 */
public class TestCyclicBarrier {

	@Test
	public void testNoCuntDown() throws InterruptedException {
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
	public void testCyc() throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		final CyclicBarrier barrier = new CyclicBarrier(11);
		for(int i = 0; i < 10; i++) {
			Runnable task = new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 1000));
						System.out.println(new Date().toLocaleString() + "---" + Thread.currentThread().getName() + " ready...");
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(new Date().toLocaleString() + "---" + Thread.currentThread().getName() + " run.");
				}
			};
			executor.submit(task);
		}
		
		executor.shutdown();
		executor.awaitTermination(5, TimeUnit.MINUTES);
		System.out.println("main thread stoped.");
	}

}
