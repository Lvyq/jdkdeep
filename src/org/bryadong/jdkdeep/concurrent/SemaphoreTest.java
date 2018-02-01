package org.bryadong.jdkdeep.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年8月9日
 */
public class SemaphoreTest {

	@Test
	public void testSemaphore() throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		int maxAvailable = 5;
		int limit = 100;
		// Semaphore可用于控制并发量
		// 如下面例子虽然提交了limit个task，但执行时一次最多只能执行maxAvailable个
		final Semaphore sem = new Semaphore(maxAvailable);
		Runnable r;
		for (int i = 0; i < limit; i++) {
			final int n = i;
			final int group = i / maxAvailable;
			r = new Runnable() {
				@Override
				public void run() {
					try {
						sem.acquire();
						Thread.currentThread().setName("group" + group + "-" + n);
						System.out.println(new Date().toLocaleString() + ",running thread" 
								+ Thread.currentThread().getName());
						Thread.sleep(10000);
						sem.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(r);
		}
		exec.shutdown();
		exec.awaitTermination(30, TimeUnit.MINUTES);
	}

}
