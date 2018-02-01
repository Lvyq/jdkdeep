package org.bryadong.jdkdeep.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterAB {

	private Lock lock = new ReentrantLock();
	private Condition ca = lock.newCondition();
	private Condition cb = lock.newCondition();
	private boolean flagA = true;

	public void printA() {
		for (int i = 0; i < 10; i++) {
			lock.lock();
			try {
				if (!flagA) {
					ca.await();
				}
				Thread.sleep(1000);
				System.out.println("runA-" + i);
				flagA = false;
				cb.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	public void printB() {
		for (int i = 0; i < 10; i++) {
			lock.lock();
			try {
				if (flagA) {
					cb.await();
				}
				Thread.sleep(1000);
				System.out.println("runB-" + i);
				flagA = true;
				ca.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final PrinterAB p = new PrinterAB();
		new Thread(new Runnable() {

			@Override
			public void run() {
				p.printA();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				p.printB();
			}
		}).start();

		Thread.sleep(30 * 1000);
	}

}
