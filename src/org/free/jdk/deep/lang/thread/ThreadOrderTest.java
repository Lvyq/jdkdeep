/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.thread;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月14日
 */
public class ThreadOrderTest {

	@Test
	public void test() throws InterruptedException {
		Thread t0 = new Thread(new Thread0());
		t0.setName("t0");
		
		Thread t1 = new Thread(new Thread1());
		t0.setName("t1");
		
		t0.start();
		t1.start();
		Thread.sleep(60 * 1000);
	}
	
	@Test
	public void testInOrder() throws InterruptedException {
		Thread t1 = new Thread(new Thread1());
		t1.setName("t1");
		
		Thread t2 = new Thread(new Thread2(t1));
		t2.setName("t2");
		
		t1.start();
		t2.start();
		Thread.sleep(60 * 1000);
	}
	
	@Test
	public void testInTurn() throws InterruptedException {
		Num n = new Num();
		Thread t3 = new Thread(new Thread3(n));
		t3.setName("t3");
		
		Thread t4 = new Thread(new Thread4(n));
		t4.setName("t4");
		
		t3.start();
		t4.start();
		Thread.sleep(1000);
	}

}
