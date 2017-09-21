/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.thread;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月14日
 */
public class Thread0 implements Runnable {
	
	@Override
	public void run() {
		while(true) {
			System.out.println(0);
		}
}

}
