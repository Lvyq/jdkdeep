/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

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
	public void test002() {
		String s1 = "abc";
		final String s2 = "a";
		String s3 = s2 + "bc";
		assertTrue(s3 == s1);
	}

	@Test
	public void test003() {
		String s1 = "abc";
		String s2 = "a";
		String s3 = s2 + "bc";
		assertTrue(s3 != s1);
	}

	@Test
	public void test004() {
		String s1 = "abc1";
		String s2 = "a" + "bc" + 1;
		assertTrue(s1 == s2);
	}

	@Test
	public void test005() {
		String s1 = "1abc";
		String s2 = 1 + "a" + "bc";
		assertTrue(s1 == s2);
	}

	@Test
	public void test006() {
		String s1 = "1abc";
		String s2 = 1 + "a" + "bc";
		String s3 = new String("1abc");
		String s4 = new String("1abcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		System.out.println(System.identityHashCode(s1) + "---" + s1.hashCode());
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4) + "---" + s4.hashCode());

		Object o = new Object();
		System.out.println(System.identityHashCode(o) + "--oo--" + o.hashCode());
	}

	@Test
	public void test2() {
		char[] s1 = { 'a', 'b', 'c', 'd' };
		char[] s2 = { 'x', 'y', 'z', '1', '2' };
		System.arraycopy(s1, 1, s2, 2, 2);
		System.out.println(s2);
	}

	@Test
	public void test007() {
		StringUtils.m();
	}

	@Test
	public void test008() throws UnsupportedEncodingException {
	String a = "名";
	 System.out.println("UTF-8编码长度:"+a.getBytes("UTF-8").length);
	 System.out.println("GBK编码长度:"+a.getBytes("GBK").length);
	 System.out.println("GB2312编码长度:"+a.getBytes("GB2312").length);
	 System.out.println("==========================================");
	 }

}
