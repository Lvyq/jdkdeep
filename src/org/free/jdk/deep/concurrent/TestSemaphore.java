package org.free.jdk.deep.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年8月9日
 */
public class TestSemaphore {

	@Test
	public void testSemaphore() throws InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		// Semaphore可用于控制并发量
		// 如下面例子虽然提交了20个task，但执行时一次最多只能执行5个
		final Semaphore sem = new Semaphore(5);
		Runnable r;
		for (int i = 0; i < 20; i++) {
			final int n = i;
			final int group = i / 5;
			r = new Runnable() {
				@Override
				public void run() {
					try {
						sem.acquire();
						Thread.currentThread().setName("group" + group + "-" + n);
						System.out.println(new Date().toLocaleString() + ",running thread" 
								+ Thread.currentThread().getName());
						Thread.sleep(3000);
						sem.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exe.execute(r);
		}
		Thread.sleep(60000);
	}

}
