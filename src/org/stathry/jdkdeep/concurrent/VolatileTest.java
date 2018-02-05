package org.stathry.jdkdeep.concurrent;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年9月4日
 */
public class VolatileTest {

	@Test
	public void test1() throws InterruptedException {
		final VolatileInc test = new VolatileInc();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}

		Thread.sleep(3000);
		System.out.println(test.inc);
	}
	
	@Test
	public void test2() throws InterruptedException {
		final VolatileInc2 test = new VolatileInc2();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}
		
		Thread.sleep(3000);
		System.out.println(test.inc);
	}
	
	@Test
	public void test3() throws InterruptedException {
		final VolatileInc3 test = new VolatileInc3();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}
		
		Thread.sleep(3000);
		System.out.println(test.inc);
	}
	
	@Test
	public void test4() throws InterruptedException {
		final VolatileInc4 test = new VolatileInc4();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}
		
		Thread.sleep(3000);
		System.out.println(test.i.get());
	}
}