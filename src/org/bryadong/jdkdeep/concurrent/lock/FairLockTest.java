package org.bryadong.jdkdeep.concurrent.lock;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月28日
 */
public class FairLockTest {
	
//	testfairLock:java version 1.8.0_111,thread 8,runn 1000000,cost seconds 109
//	testUnfairLock:java version 1.8.0_111,thread 8,runn 1000000,cost seconds 116
	
//	testfairLock:java version 1.7.0_21,thread 8,runn 1000000,cost seconds 113
//	testUnfairLock:java version 1.7.0_21,thread 8,runn 1000000,cost seconds 114
	
//	testfairLock:java version 1.6.0_45,thread 8,runn 1000000,cost seconds 108
//	testUnfairLock:java version 1.6.0_45,thread 8,runn 1000000,cost seconds 112
	
	
//	testfairLock:java version 1.8.0_111,thread 8,runn 10000,cost seconds 1
//	testUnfairLock:java version 1.8.0_111,thread 8,runn 10000,cost seconds 1
	
//	testfairLock:java version 1.7.0_21,thread 8,runn 10000,cost seconds 1
//	testUnfairLock:java version 1.7.0_21,thread 8,runn 10000,cost seconds 1

	@Test
	public void testfairLock() throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService exec = Executors.newCachedThreadPool();
		int tn = 8;
		final int runn = 1000000;
		final Lock fairLock = new ReentrantLock(true);
		for (int i = 0; i < tn; i++) {
			exec.submit(new Runnable() {

				public void run() {
					StringBuilder names = new StringBuilder();
					for (int j = 0; j < runn; j++) {
						fairLock.lock();
						try {
							names.append(Thread.currentThread().getName());
							names.append('_');
							names.append(j);
							names.append(',');
							if (j % 1000 == 0 && j != 0) {
								System.out.println(names.toString());
								names.setLength(0);
							} else if (j == runn - 1) {
								System.out.println(names);
							}
						} finally {
							fairLock.unlock();
						}
					}

				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(30, TimeUnit.MINUTES);
		long end = System.currentTimeMillis();
		System.out.println("testfairLock:java version " + System.getProperty("java.version") + ",thread " + tn
				+ ",runn " + runn + ",cost seconds " + TimeUnit.MILLISECONDS.toSeconds(end - start));
	}

	@Test
	public void testUnfairLock() throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService exec = Executors.newCachedThreadPool();
		int tn = 8;
		final int runn = 1000000;
		final Lock unfairLock = new ReentrantLock();
		for (int i = 0; i < tn; i++) {
			exec.submit(new Runnable() {

				public void run() {
					StringBuilder names = new StringBuilder();
					for (int j = 0; j < runn; j++) {
						unfairLock.lock();
						try {
							names.append(Thread.currentThread().getName());
							names.append('_');
							names.append(j);
							names.append(',');
							if (j % 1000 == 0 && j != 0) {
								System.out.println(names.toString());
								names.setLength(0);
							} else if (j == runn - 1) {
								System.out.println(names);
							}
						} finally {
							unfairLock.unlock();
						}
					}

				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(30, TimeUnit.MINUTES);
		long end = System.currentTimeMillis();
		System.out.println("testUnfairLock:java version " + System.getProperty("java.version") + ",thread " + tn
				+ ",runn " + runn + ",cost seconds " + TimeUnit.MILLISECONDS.toSeconds(end - start));
	}

}
