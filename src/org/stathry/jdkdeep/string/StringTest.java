/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.string;

import org.junit.Assert;
import org.junit.Test;
import org.stathry.jdkdeep.lang.StringTest1;
import org.stathry.jdkdeep.lang.inst.A;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		StringTest1.m();
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
	public void testStringListCompare() {
        List<String> list = Arrays.asList("antlr", "aopalliance", "c3p0", "cglib", "com", "COM", "18");
		Collections.shuffle(list);
		System.out.println("乱序:" + list);
		Collections.sort(list);
		System.out.println("升序排序后：" + list);
		Assert.assertEquals("18",list.get(0));
		Assert.assertEquals("COM",list.get(1));
		Assert.assertEquals("com",list.get(list.size() - 1));
	}

	@Test
	public void testAscii() {
		int n1 = (int)'0';
		System.out.println(n1);
		Assert.assertEquals(48, n1);
		int n2 = (int)'A';
		System.out.println(n2);
		Assert.assertEquals(65, n2);
		int n3 = (int)'a';
		System.out.println(n3);
		Assert.assertEquals(97, n3);
	}
	@Test
	public void test013() {
        Charset charset = Charset.forName("utf-8");
        String a = "董";
        byte[] data = a.getBytes(charset);
        System.out.println(new String(data, charset));
	}

//    delimiters:A, StringSplit:783
//    delimiters:A, StringSplitCompile:1235
//    delimiters:A3, StringSplit:1003
//    delimiters:A3, StringSplitCompile:398
	@Test
	public void testStringSplitVSCompileSplit() {
        showSplits(1000000, "A");
        showSplits(1000000, "A3");
	}

//    charLen:100, delimiters:A, longStringSplit:103
//    charLen:100, delimiters:A, longStringSplitCompile:118
//    charLen:100, delimiters:A, longStringSplitTokenizer:135
//
//    charLen:100, delimiters:A3, longStringSplit:225
//    charLen:100, delimiters:A3, longStringSplitCompile:212
//    charLen:100, delimiters:A3, longStringSplitTokenizer:70
//
//    charLen:100000, delimiters:A, longStringSplit:7613
//    charLen:100000, delimiters:A, longStringSplitCompile:13948
//    charLen:100000, delimiters:A, longStringSplitTokenizer:15248
//
//    charLen:100000, delimiters:A3, longStringSplit:10085
//    charLen:100000, delimiters:A3, longStringSplitCompile:10241
//    charLen:100000, delimiters:A3, longStringSplitTokenizer:19471
	@Test
	public void testLongStringSplitVSCompileSplit() {
        showLongStringSplits(100, 10000, "A");
        showLongStringSplits(100, 10000, "A3");

        showLongStringSplits(100000, 10000, "A");
        showLongStringSplits(100000, 10000, "A3");
	}

    private void showSplits(int n, String delimiters) {
        List<String> l = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            l.add("A101,A201,A301" + ",A40" +i);
        }

        long start = System.currentTimeMillis();
        String[] a ;
        for (int i = 0; i < n; i++) {
            a = l.get(i).split(delimiters);
        }
        long end = System.currentTimeMillis();
        System.out.println("delimiters:" + delimiters + ", StringSplit:" + (end - start));

        final Pattern PS = Pattern.compile(delimiters);
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            a = PS.split(l.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("delimiters:" + delimiters + ", StringSplitCompile:" + (end - start));
    }

    private void showLongStringSplits(int charLen, int times, String delimiters) {
        System.out.println();
        StringBuilder b = new StringBuilder();
        char[] chars = "ABCDE12345".toCharArray();
        Random random = new Random();
        for (int i = 0; i < charLen; i++) {
            b.append(chars[random.nextInt(chars.length)]);
        }
        String s = b.toString();
        String[] a = new String[charLen];

        long start = System.currentTimeMillis();
        for (int j = 0; j < times; j++) {
            String[] a1 = s.split(delimiters);
            for (int i = 0, len = a1.length; i < len; i++) {
                a[i] = a1[i];
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("charLen:" + charLen + ", delimiters:" + delimiters + ", longStringSplit:" + (end - start));

        final Pattern PS = Pattern.compile(delimiters);
        start = System.currentTimeMillis();
        for (int k = 0; k < times; k++) {
            String[] a2 = PS.split(s);
            for (int i = 0, len = a2.length; i < len; i++) {
                a[i] = a2[i];
            }
        }
        end = System.currentTimeMillis();
        System.out.println("charLen:" + charLen + ", delimiters:" + delimiters + ", longStringSplitCompile:" + (end - start));


        start = System.currentTimeMillis();
        for (int n = 0; n < times; n++) {
            final StringTokenizer tokenizer = new StringTokenizer(s, delimiters);
            for (int i = 0; tokenizer.hasMoreTokens(); i++) {
                a[i] = tokenizer.nextToken();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("charLen:" + charLen + ", delimiters:" + delimiters + ", longStringSplitTokenizer:" + (end - start));
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
