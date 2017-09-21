/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.thread;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月14日
 */
public class Thread2 implements Runnable {

	private Thread preTh;

	public Thread2(Thread joinThread) {
		this.preTh = joinThread;
	}

	@Override
	public void run() {
		try {
			preTh.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 2; i < 30; i += 2) {
			System.out.println(Thread.currentThread().getName() +"---" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
