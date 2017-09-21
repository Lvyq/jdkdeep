/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.concurrent;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月22日
 */
public class LockTest {

	@Test
	public void testInTurn() throws InterruptedException {
		Printer printer = new Printer();
		
		Thread tx = new Thread(new ThreadX(printer));
		Thread ty = new Thread(new ThreadY(printer));
		tx.setName("tx");
		ty.setName("ty");
		
		tx.start();
		ty.start();
		
		Thread.sleep(1000 * 5);
	}
	
	@Test
	public void testInOrder() throws InterruptedException {
		Printer printer = new Printer();
		
		Thread t1 = new Thread(new Thread1(printer));
		Thread t2 = new Thread(new Thread2(printer));
		t1.setName("t1");
		t2.setName("t2");
		
		t1.start();
		t2.start();
		
		Thread.sleep(1000 * 40);
	}

}
