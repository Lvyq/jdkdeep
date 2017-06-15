/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.lang.thread;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月14日
 */
public class Thread1 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i < 30; i += 2) {
			System.out.println(Thread.currentThread().getName() +"---" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
