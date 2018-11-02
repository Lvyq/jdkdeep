/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author stathry@126.com
 * @date 2017年6月6日
 */
public class ArrayTest {

    @Test
    public void testArrayType() {
        int[] a1 = {2, 4, 6};
        Object[] a2 = {new Object(), new Object()};
        long[] a3 = {5L, 4L, 6L};
        Assert.assertEquals("[I", a1.getClass().getName());
        Assert.assertEquals("[Ljava.lang.Object;", a2.getClass().getName());
        Assert.assertEquals("[J", a3.getClass().getName());
    }

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
	public void testSystemArrayCopy1() {
		Integer[] a1 = new Integer[]{1,2,3};
		Object[] a2 = new Object[10];
		System.arraycopy(a1, 1, a2, 1, 1);
        System.out.println(Arrays.toString(a2));
		Assert.assertTrue(a2[0] == null);
		Assert.assertTrue(((Integer)a2[1]).intValue() == 2);
		Assert.assertTrue(a2[2] == null);
	}

    @Test
    public void testSystemArrayCopy2() {
        Integer[] a1 = new Integer[]{1,2,3,4};
        Integer[] a2 = new Integer[]{5,6,7,8};
        System.arraycopy(a1, 2, a2, 1, 2);
        System.out.println(Arrays.toString(a2));
        Assert.assertEquals(5, (int)a2[0]);
        Assert.assertEquals(3, (int)a2[1]);
        Assert.assertEquals(4, (int)a2[2]);
        Assert.assertEquals(8, (int)a2[3]);
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
	public void testForEachVSForI() {
        int size = 10_0000;
        List<Long> sizeList = new ArrayList<>(), foreachTimeList = new ArrayList<>(), forTimeList = new ArrayList();
        for (int i = 0; i <1; i++) {

            for (int j = 0; j < 5; j++) {
                foreachVSfor(size  * (int)Math.pow(10, i), sizeList, foreachTimeList, forTimeList);
            }
        }

        System.out.println("sizeList:" + sizeList);
        System.out.println("foreachTimeList:" + foreachTimeList);
        System.out.println("forTimeList:" + forTimeList);
	}

    private void foreachVSfor(int size, List<Long> sizeList, List<Long> foreachTimeList, List<Long> forTimeList) {
        sizeList.add((long)size);
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        StringBuilder b1 = new StringBuilder(size * 10);
        StringBuilder b2 = new StringBuilder(size * 10);
        long start = System.currentTimeMillis();
        for (Integer e : list) {
            b1.append(e).append(',');
        }
        long time = System.currentTimeMillis() - start;
        foreachTimeList.add(time);

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            b2.append(list.get(i)).append(',');
        }
        time = System.currentTimeMillis() - start;
        forTimeList.add(time);
        System.out.println(b1.length());
        System.out.println(b2.length());
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
