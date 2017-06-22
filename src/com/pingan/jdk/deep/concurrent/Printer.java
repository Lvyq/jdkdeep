/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月22日
 */
public class Printer {

	private final Lock lock = new ReentrantLock();
	private final Condition cx = lock.newCondition();
	private final Condition cy = lock.newCondition();
	private final Condition c1 = lock.newCondition();
	private final Condition c2 = lock.newCondition();

	private char th = 'x';
	private int cur = 1;

	public void turnX() {
		try {
			lock.lock();
			while (true) {
				if (th == 'x') {
					System.out.println(Thread.currentThread().getName() + "--->" + 'x');
					th = 'y';
					cy.signal();
				} else {
					cx.await();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void turnY() {
		try {
			lock.lock();
			while (true) {
				if (th == 'y') {
					System.out.println(Thread.currentThread().getName() + "--->" + 'y');
					th = 'x';
					cx.signal();
				} else {
					cy.await();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void order1() {
		try {
			lock.lock();
				for (int i = 1; i < 20; i+=2) {
					System.out.println(Thread.currentThread().getName() + "--->" + i);
					Thread.sleep(1000);
				}
				cur = 2;
				c2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void order2() {
		try {
			lock.lock();
			while(true) {
				if(cur == 2) {
					for (int i = 0; i < 20; i+=2) {
						System.out.println(Thread.currentThread().getName() + "--->" + i);
						Thread.sleep(1000);
					}
					
					break;
				} else {
					c2.await();
					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
