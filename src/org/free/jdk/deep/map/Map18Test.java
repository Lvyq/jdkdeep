/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年4月6日
 */
public class Map18Test {

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

}
