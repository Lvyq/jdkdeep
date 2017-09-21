/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.concurrent;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月1日
 */
public class SyncTest {
	
	@Test
	public void test1() {
		try {
			sync1(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sync1(Object o) {
		synchronized (o) {
			System.out.println(o);
		}
	}

}
