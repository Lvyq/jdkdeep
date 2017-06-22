/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.concurrent;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月15日
 */
public class ThreadX implements Runnable {

	private Printer printer;
	
	public ThreadX() {
	}

	public ThreadX(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void run() {
			printer.turnX();
	}

}