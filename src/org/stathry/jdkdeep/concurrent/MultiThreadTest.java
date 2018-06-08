package org.stathry.jdkdeep.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年1月31日
 */
public class MultiThreadTest {

	@Test
	public void testPrintHtreadInfos() {
		ThreadMXBean mxb = ManagementFactory.getThreadMXBean();
		ThreadInfo[] infos = mxb.dumpAllThreads(false, false);
		for (ThreadInfo ti : infos) {
			System.out.println(ti.getThreadId() + "\t" + ti.getThreadName());
		}
	}

	// 13079
	@Test
	public void testThreadMax() throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(Integer.MAX_VALUE);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			final int n = i;
			exec.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(n);
				}
			});
		}

		exec.shutdown();
		exec.awaitTermination(3, TimeUnit.MINUTES);
	}

	@Test
    public void testPrintCurMethod() {
        System.out.println(Thread.currentThread().getStackTrace()[0]);
        System.out.println(Thread.currentThread().getStackTrace()[0].getMethodName());

        System.out.println(Thread.currentThread().getStackTrace()[1]);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
	
}
