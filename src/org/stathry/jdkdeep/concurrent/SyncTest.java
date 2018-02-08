/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.concurrent;

import org.junit.Test;

/**
 * @author stathry@126.com
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
