/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.collection;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月6日
 */
public class ArraysTest {
	
	@Test
	public void testArrayCopy() {
		Integer[] a1 = new Integer[]{1,2,3};
		Object[] a2 = Arrays.copyOf(a1, a1.length, Object[].class);
		System.out.println("a1:" + a1);
		System.out.println("a2:" + a2);
		Assert.assertTrue(a2[0].getClass() == Integer.class);
	}
	
	@Test
	public void testSystemArrayCopy() {
		Integer[] a1 = new Integer[]{1,2,3};
		Object[] a2 = new Object[10];
		System.arraycopy(a1, 1, a2, 1, 1);
		Assert.assertTrue(a2[0] == null);
		Assert.assertTrue(((Integer)a2[1]).intValue() == 2);
		Assert.assertTrue(a2[2] == null);
	}
	
}
