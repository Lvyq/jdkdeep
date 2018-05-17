/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.lang;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author stathry@126.com
 * @date 2017年6月8日
 */
public class IntegerTest {

	@Test
	public void test1() {
		Integer i1 = 1;
		Integer i2 = 1;
		assertTrue(i1 == i2);
	}

	@Test
	public void test2() {
		Integer i1 = 127;
		Integer i2 = 127;
		assertTrue(i1 == i2);
	}

	@Test
	public void test3() {
		Integer i1 = 128;
		Integer i2 = 128;
		assertTrue(i1 != i2);
	}

	@Test
	public void test4() {
		Integer i1 = -128;
		Integer i2 = -128;
		assertTrue(i1 == i2);
	}

	@Test
	public void test5() {
		Integer i1 = -129;
		Integer i2 = -129;
		assertTrue(i1 != i2);
	}

	@Test
	public void test() {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		assertTrue(i1 != i2);
	}

	@Test
	public void test01() {
		int i = -127 + 128;
		System.out.println(i);
		int i2 = 127 + 128;
		System.out.println(i2);
	}

	@Test
	public void test02() {
		int i = 3;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toHexString(i));
		int i2 = 127;
		System.out.println(Integer.toBinaryString(127));
		System.out.println(Integer.toHexString(127));
	}

	@Test
	public void test03() {
		Integer i1 = new Integer(1);
		Integer i2 = i1;
		Integer i3 = new Integer(3);
		reset(i2, i3);
		System.out.println(i1.intValue());
		System.out.println(i2.intValue());
		System.out.println(i1 == i2);
	}

    @Test
    public void testParseInt1() {
        Assert.assertEquals(1, Integer.parseInt("1"));
        Assert.assertEquals(1, Integer.parseInt("01"));
        Assert.assertEquals(20, Integer.parseInt("020"));

        System.out.println(Integer.parseInt("11", 16));
        Assert.assertEquals(17, Integer.parseInt("11", 16));
    }

    @Test
    public void testParseInt2() {
        Integer.parseInt(" 1 ");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseInt3() {
        Integer.parseInt(" 1 ");
    }
	
	public void reset(Integer i2, Integer i3) {
//		i2 = new Integer(100);
		i2 = i3;
	}

}
