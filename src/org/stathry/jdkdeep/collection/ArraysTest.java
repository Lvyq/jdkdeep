/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.collection;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author stathry@126.com
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

//	@Test
	public void testToString() {
		Integer[] a1 = new Integer[] { 1, 2, 3 };
		System.out.println(new Object());
		System.out.println(a1.toString());
		Assert.assertTrue(a1.toString().startsWith("[Ljava.lang.Integer"));
		Assert.assertEquals(Arrays.toString(a1), "[1, 2, 3]");
	}

	@Test
	public void testForEachArray() {
		int size = 10000000;
		Integer[] a = new Integer[size];
		for (int i = 0; i < size; i++) {
			a[i] = i;
		}
		long start = System.currentTimeMillis();
		for (Integer e : a) {
			System.out.println(e);
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("testForEachArray:" + time);
		//50560		48631
	}
	@Test
	public void testForArray() {
		int size = 10000000;
		Integer[] a = new Integer[size];
		for (int i = 0; i < size; i++) {
			a[i] = i;
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			System.out.println(a[i]);
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("testForArray:" + time);
		// 50106	50481
	}
	
	@Test
	public void testSort() {
		Object[] a = new Object[10];
		for(int i = 0 ; i < 10; i++) {
			a[i] = new Object();
		}
		Arrays.sort(a);
		for(int i = 0 ; i < 10; i++) {
			System.out.println(a[i]);
		}
	}

}
