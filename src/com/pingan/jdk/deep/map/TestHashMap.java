/**
 * Copyright 2012-2016 Deppon Co., Ltd.
 */
package com.pingan.jdk.deep.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author dongdaiming@deppon.com 2016年9月29日
 */
public class TestHashMap {

	@SuppressWarnings("all")
	public static void main(String[] args) {
		Map map = new HashMap<>();
		System.out.println(map.put("a", 1));
		System.out.println(map.put("a", 2));
		System.out.println(map.put(1, 'a'));
	}
	
	@Test
	public void testChar() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('a', 1);
	}
}
