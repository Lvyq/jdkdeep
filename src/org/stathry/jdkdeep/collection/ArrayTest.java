/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.collection;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author stathry@126.com
 * @date 2017年6月6日
 */
public class ArrayTest {

    // 当使用基本类型的数组作为可变参数时，传入的数组并未匹配可变参数方法的数组
    // 当使用包装类型的数组作为可变参数时，传入的数组匹配可变参数方法的数组
    @Test
    public void testIntArrayAsList() {
        int[] a = {1, 3, 5};
        List<int[]> list = Arrays.asList(a);
        System.out.println(list);
        Assert.assertTrue(list != null && list.size() == 1);
        Assert.assertTrue(list.get(0).equals(a));

        Integer[] a2 = {1, 3, 5};
        List<Integer> list2 = Arrays.asList(a2);
        System.out.println(list2);
        Assert.assertTrue(list2 != null && list2.size() == 3);
    }

    @Test
    public void testArrayAsList() {
        List<String> list = Arrays.asList();
        Assert.assertTrue(list != null && list.size() == 0);
        List<String> list2 = Arrays.asList("a", "x");
        Assert.assertTrue(list2 != null && list2.size() == 2);
    }

	@Test
	public void testArrayCopy() {
		Integer[] a1 = new Integer[]{1,2,3};
		Object[] a2 = Arrays.copyOf(a1, a1.length, Object[].class);
		System.out.println("a1:" + a1);
		System.out.println("a2:" + a2);
		Assert.assertTrue(a2[0].getClass() == Integer.class);
	}

    @Test
    public void testFillInts() {
        int v = 1;
        int size = 10;
        int[] a = new int[size];
        Arrays.fill(a, v);
        System.out.println(a);
        System.out.println(a.length);
        Assert.assertTrue(a[0] == a[size - 1] && a[0] == v);
    }

    @Test
    public void testFillString() {
        String v = "0.01";
        int size = 10;
        String[] a = new String[size];
        Arrays.fill(a, v);
        System.out.println(a);
        System.out.println(a.length);
        Assert.assertTrue(a[0] == a[size - 1] && a[0] == v);
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
