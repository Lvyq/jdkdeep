package org.stathry.jdkdeep.concurrent.lock;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月29日
 */
public class ReentrentLockTest {

	@Test
	public void testSync() throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Runnable t1 = new TaskSync1();
		exec.submit(t1);
		exec.submit(t1);
		exec.submit(t1);
		exec.shutdown();
		exec.awaitTermination(5, TimeUnit.MINUTES);
	}

	private static class TaskSync1 implements Runnable {

		@Override
		public void run() {
			m1();
		}

		/**
		 * 
		 */
		private synchronized void m1() {
			System.out.println(Thread.currentThread().getName() + " m1");
			m2();
		}

		/**
		 * 
		 */
		private synchronized void m2() {
			System.out.println(Thread.currentThread().getName() + " m2");
		}

	}

}
