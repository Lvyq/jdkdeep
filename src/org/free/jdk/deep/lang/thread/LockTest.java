/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月15日
 */
public class LockTest {
	public static void main(String[] args) throws InterruptedException {
		final Outputter1 output = new Outputter1();
		new Thread() {
			public void run() {
				output.output("zhangsan");
			};
		}.start();
		new Thread() {
			public void run() {
				output.output("lisi");
			};
		}.start();

		Thread.sleep(1000);
	}
}

class Outputter1 {
	private Lock lock = new ReentrantLock();// 锁对象

	public void output(String name) {
		// TODO 线程输出方法
		lock.lock();// 得到锁
		int n = 100;
		try {
			for (int i = 0; i < n; i++) {
				System.out.println(name + i);
			}
		} finally {
			lock.unlock();// 释放锁
		}
	}
}
