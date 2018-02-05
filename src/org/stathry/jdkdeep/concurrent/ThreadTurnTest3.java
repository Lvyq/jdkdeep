package org.stathry.jdkdeep.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信-基于信号量
 * 
 * @author dongdaiming
 * @date 2017年12月21日
 */
public class ThreadTurnTest3 {

	private Semaphore s0 = new Semaphore(1);
	private Semaphore s1 = new Semaphore(1);
	private Semaphore s2 = new Semaphore(1);
	private static final int LIMIT = 10000;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = new ThreadPoolExecutor(3, 30, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(300), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
		final ThreadTurnTest3 o = new ThreadTurnTest3();
//		s0先执行
		o.getS1().acquire();
		o.getS2().acquire();
		exec.submit(o.createTask(0));
		exec.submit(o.createTask(1));
		exec.submit(o.createTask(2));
		exec.shutdown();
		exec.awaitTermination(5, TimeUnit.MINUTES);
	}

	/**
	 * @param name
	 * @param i
	 */
	private Runnable createTask(final int i) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					m(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

	private void m(final int n) throws InterruptedException {
		for (int i = n; i < LIMIT; i += 3) {
			switch (n) {
			case 0:
				s0.acquire();
				System.out.println(Thread.currentThread().getName() + "___" + i);
				s1.release();
				break;
			case 1:
				s1.acquire();
				System.out.println(Thread.currentThread().getName() + "___" + i);
				s2.release();
				break;
			case 2:
				s2.acquire();
				System.out.println(Thread.currentThread().getName() + "___" + i);
				s0.release();
				break;
			default:
				break;
			}
		}
	}

	public Semaphore getS0() {
		return s0;
	}

	public Semaphore getS1() {
		return s1;
	}

	public Semaphore getS2() {
		return s2;
	}
	
}
