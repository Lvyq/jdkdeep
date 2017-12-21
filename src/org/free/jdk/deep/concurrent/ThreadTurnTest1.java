package org.free.jdk.deep.concurrent;

/**
 * 线程间通信-基于Object的wait/notify
 * 
 * @author dongdaiming
 * @date 2017年12月21日
 */
public class ThreadTurnTest1 {

	private Object lock = new Object();
	private boolean RUN0 = true;
	private static final int LIMIT = 1000;

	public static void main(String[] args) throws InterruptedException {
		final ThreadTurnTest1 o = new ThreadTurnTest1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					o.m0();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t0").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					o.m1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();

		Thread.sleep(10 * 1000);
	}

	private void m1() throws InterruptedException {
		for (int i = 1; i < LIMIT; i += 2) {
			synchronized (lock) {
				if (RUN0) {
					lock.wait();
				}
				System.out.println(Thread.currentThread().getName() + "___" + i);
				RUN0 = true;
				lock.notify();
			}
		}
	}

	private void m0() throws InterruptedException {
		for (int i = 0; i < LIMIT; i += 2) {
			synchronized (lock) {
				if (!RUN0) {
					lock.wait();
				}
				System.out.println(Thread.currentThread().getName() + "___" + i);
				RUN0 = false;
				lock.notify();
			}
		}
	}

}
