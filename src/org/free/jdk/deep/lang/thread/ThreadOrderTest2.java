/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月14日
 */
public class ThreadOrderTest2 {

	@Test
	public void testInTurn() throws InterruptedException {
		Lock lock = new ReentrantLock();
		Thread tx = new Thread(new ThreadX(lock));
		tx.setName("tx");
		
		Thread ty = new Thread(new ThreadY(lock));
		ty.setName("ty");
		
		tx.start();
		ty.start();
		Thread.sleep(1000);
	}

}
