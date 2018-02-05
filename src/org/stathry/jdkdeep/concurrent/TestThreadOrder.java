package org.stathry.jdkdeep.concurrent;

import org.junit.Test;
import org.stathry.jdkdeep.concurrent.Thread12.Thread1;
import org.stathry.jdkdeep.concurrent.Thread12.Thread2;

/**
 * TODO
 */
public class TestThreadOrder {

	@Test
	public void testInturnByLockCondition() throws InterruptedException {
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

	@Test
	public void testInturnBySync() throws InterruptedException {
		final PrinterXY p = new PrinterXY();
		new Thread(new Runnable() {

			@Override
			public void run() {
				p.printX();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				p.printY();
			}
		}).start();

		Thread.sleep(30 * 1000);
	}

	@Test
	public void testInOrderByJoin() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread1 t1 = new Thread12.Thread1();
			Thread2 t2 = new Thread12.Thread2(t1);
			t1.setName("t1i" + i);
			t2.setName("t2i" + i);
			t2.start();
			t1.start();
			Thread.sleep(1 * 1000);
			System.out.println();
			System.out.println();
		}
		
		Thread.sleep(30 * 1000);
	}

}
