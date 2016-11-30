/**
 * Copyright 2012-2016 Deppon Co., Ltd.
 */
package com.deppon.jdk.math;

import org.junit.Test;

/**
 * @author dongdaiming@deppon.com
 * 2016年10月13日
 */
public class MathTest {

	@Test
	public void testround() {
		System.out.println(Math.round(11.49));
		System.out.println(Math.round(11.5));
		System.out.println(Math.round(-11.51));
		System.out.println(Math.round(-11.5));
	}
	
	@Test
	public void testfloor() {
		System.out.println(Math.floor(11.5));
		System.out.println(Math.floor(-11.51));
	}
	
	@Test
	public void testceil() {
		System.out.println(Math.ceil(11.5));
		System.out.println(Math.ceil(-11.51));
	}
}
