/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.lang;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author stathry@126.com
 * @date 2017年6月8日
 */
public class StringTest {

	@Test
	public void trimJson() {
		String s = "{\r\n" + 
				"    \"routers\": {\r\n" + 
				"        \"PHONE_CHECK1\": {\r\n" + 
				"            \"routingType\": \"W\",\r\n" + 
				"            \"retry\": \"true\",\r\n" + 
				"            \"retryTimes\": \"3\",\r\n" + 
				"            \"routingItems\": [\r\n" + 
				"                {\r\n" + 
				"                    \"flag\": \"ORG1\",\r\n" + 
				"                    \"beanName\": \"service1\",\r\n" + 
				"                    \"weight\": \"2\"\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"flag\": \"ORG2\",\r\n" + 
				"                    \"beanName\": \"service2\",\r\n" + 
				"                    \"weight\": \"1\"\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        },\r\n" + 
				"        \"PHONE_CHECK2\": {\r\n" + 
				"            \"routingType\": \"P\",\r\n" + 
				"            \"retry\": \"true\",\r\n" + 
				"            \"retryTimes\": \"2\",\r\n" + 
				"            \"routingItems\": [\r\n" + 
				"                {\r\n" + 
				"                    \"flag\": \"ORG1\",\r\n" + 
				"                    \"beanName\": \"service1\",\r\n" + 
				"                    \"priority\": \"9\"\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                    \"flag\": \"ORG2\",\r\n" + 
				"                    \"beanName\": \"service2\",\r\n" + 
				"                    \"priority\": \"10\"\r\n" + 
				"                }\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
		s = s.replaceAll("\r\n", "");
		s = s.replaceAll(" ", "");
		System.out.println(s);
	}

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
		// String a = "名";
		// String a = "中";
		String a = "董";
		System.out.println("默认编码长度:" + a.getBytes().length);
		System.out.println("UTF-8编码长度:" + a.getBytes("UTF-8").length);
		System.out.println("GBK编码长度:" + a.getBytes("GBK").length);
		System.out.println("GB2312编码长度:" + a.getBytes("GB2312").length);
		System.out.println("==========================================");
	}

	@Test
	public void test009() throws UnsupportedEncodingException {
		String RANGE_SQL = " SELECT %s(ID) FROM %s ";
		System.out.println(String.format(RANGE_SQL, "MIN", "TABLE1"));
	}

	// test010
	@Test
	public void test010() {
		String s = "despacito";
		for (int i = 0, max = 10000; i < max; i++) {
			s += s;
		}
	}

	@Test
	public void test011() {
		Bean1 o = new Bean1();
		String s0 = "";
		String s = Character.toString(o.getC());
		assertEquals("", s);
		assertEquals('\u0000', o.getC());
	}
	@Test
	public void test012() {
        System.out.println(UUID.randomUUID().toString());
	}

	@Test
	public void testCompare() {
        List<String> list = Arrays.asList("antlr", "aopalliance", "c3p0", "cglib", "com", "COM", "18");
		Collections.shuffle(list);
		System.out.println("乱序:" + list);
		Collections.sort(list);
		System.out.println("升序排序后：" + list);
		Assert.assertEquals("18",list.get(0));
		Assert.assertEquals("COM",list.get(1));
		Assert.assertEquals("com",list.get(list.size() - 1));
	}

	static class Bean1 {
		char c;

		public char getC() {
			return c;
		}

		public void setC(char c) {
			this.c = c;
		}
	}

}
