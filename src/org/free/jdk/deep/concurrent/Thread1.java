/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.concurrent;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月15日
 */
public class Thread1 implements Runnable {

	private Printer printer;
	
	public Thread1() {
	}

	public Thread1(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void run() {
			printer.order1();
	}

}