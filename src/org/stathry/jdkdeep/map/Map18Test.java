/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.stathry.jdkdeep.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiConsumer;

import org.junit.Test;

/**
 * @date 2017年4月6日
 */
public class Map18Test {

	@Test
	public void test0() {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.put("k3", "v3");
		map.forEach((k, v) -> System.out.println(k + "---" + v));
	}
	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.put("k3", "v3");
		map.forEach(new BiConsumer<String, String>() {
			
			@Override
			public void accept(String k, String v) {
				System.out.println(k + "---" + v);
			}
			
		});
	}

	@Test
	public void test1() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 11);
		map.put(null, 22);
		System.out.println(map);
	}

	@Test(expected = NullPointerException.class)
	public void test11() {
		Map<Integer, Integer> map = new ConcurrentHashMap<>();
		map.put(1, 11);
		map.put(null, 22);
		System.out.println(map);
	}
	@Test(expected = NullPointerException.class)
	public void test111() {
		Map<Integer, Integer> map = new ConcurrentHashMap<>();
		map.put(1, 11);
		map.put(2, null);
		System.out.println(map);
	}

	@Test(expected = NullPointerException.class)
	public void test2() {
		Map<Integer, Integer> map = new TreeMap<>();
		map.put(1, 11);
		map.put(null, 22);
		System.out.println(map);
	}
	@Test
	public void test211() {
		Map<Integer, Integer> map = new TreeMap<>();
		map.put(1, 11);
		map.put(2, null);
		System.out.println(map);
	}

	@Test(expected = NullPointerException.class)
	public void test21() {
		Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
		map.put(1, 11);
		map.put(null, 22);
		System.out.println(map);
	}
	@Test(expected = NullPointerException.class)
	public void test212() {
		Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
		map.put(1, 11);
		map.put(2, null);
		System.out.println(map);
	}

	@Test
	public void test3() {
		Map<Integer, Integer> map = new LinkedHashMap<>();
		map.put(1, 11);
		map.put(null, 22);
		System.out.println(map);
	}

	@Test(expected = NullPointerException.class)
	public void test4() {
		Map<Integer, Integer> map = new Hashtable<>();
		map.put(1, 11);
		map.put(null, 22);
		System.out.println(map);
	}
	@Test(expected = NullPointerException.class)
	public void test41() {
		Map<Integer, Integer> map = new Hashtable<>();
		map.put(1, 11);
		map.put(2, null);
		System.out.println(map);
	}
	
	@Test
	public void test5() {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(null);
		System.out.println(set);
	}
	@Test(expected = NullPointerException.class)
	public void test51() {
		Set<Integer> set = new TreeSet<>();
		set.add(1);
		set.add(null);
		System.out.println(set);
	}

}
