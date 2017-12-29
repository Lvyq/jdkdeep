package org.free.jdk.deep.concurrent.lock;

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
public class LockSyncTest {
	
//	testLock java version 1.8.0_111,thread 8,runn 1000000,cost seconds 109
//	testSync java version 1.8.0_111,thread 8,runn 1000000,cost seconds 116
	
//	testLock java version 1.7.0_21,thread 8,runn 1000000,cost seconds 113
//	testSync java version 1.7.0_21,thread 8,runn 1000000,cost seconds 114
	
//	testLock java version 1.6.0_45,thread 8,runn 1000000,cost seconds 109
//	testSync java version 1.6.0_45,thread 8,runn 1000000,cost seconds 109
	
	@Test
	public void testLock() throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService exec = Executors.newCachedThreadPool();
		int tn = 8;
		final int runn = 10000000;
		final Lock lock = new ReentrantLock(false);
		for (int i = 0; i < tn; i++) {
			exec.submit(new Runnable() {

				public void run() {
					StringBuilder names = new StringBuilder();
					for (int j = 0; j < runn; j++) {
						lock.lock();
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
							lock.unlock();
						}
					}

				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(30, TimeUnit.MINUTES);
		long end = System.currentTimeMillis();
		System.out.println("testLock java version " + System.getProperty("java.version") + ",thread " + tn
				+ ",runn " + runn + ",cost seconds " + TimeUnit.MILLISECONDS.toSeconds(end - start));
	}

	@Test
	public void testSync() throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService exec = Executors.newCachedThreadPool();
		int tn = 8;
		final int runn = 10000000;
		final Object lock = new Object();
		for (int i = 0; i < tn; i++) {
			exec.submit(new Runnable() {

				public void run() {
					StringBuilder names = new StringBuilder();
					for (int j = 0; j < runn; j++) {
						synchronized (lock) {
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
						}
					}

				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(30, TimeUnit.MINUTES);
		long end = System.currentTimeMillis();
		System.out.println("testSync java version " + System.getProperty("java.version") + ",thread " + tn
				+ ",runn " + runn + ",cost seconds " + TimeUnit.MILLISECONDS.toSeconds(end - start));
	}

}
