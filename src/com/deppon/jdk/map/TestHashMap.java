/**
 * Copyright 2012-2016 Deppon Co., Ltd.
 */
package com.deppon.jdk.map;

import java.util.HashMap;
import java.util.Map;

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
}
