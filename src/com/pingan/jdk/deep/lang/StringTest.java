/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.lang;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月8日
 */
public class StringTest {

	@Test
	public void test() {
		String s = new String("abc");
		String s1 = "abc";
		String s2 = new String("abc");
		String si1 = s1.intern();
		String si2 = s2.intern();
		System.out.println(s == s1);
		assertFalse(s == s1);
		System.out.println(s == s2);
		assertFalse(s == s2);
		System.out.println(s1 == s2);
		assertFalse(s1 == s2);
	}
	
	@Test
	public void test2() {
		char[] s1 = {'a', 'b', 'c', 'd'};
		char[] s2 = {'x', 'y', 'z','1','2'};
		System.arraycopy(s1, 1, s2, 2, 2);
		System.out.println(s2);
	}

}
