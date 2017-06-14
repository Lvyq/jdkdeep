/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月6日
 */
public class ArrayListTest {
	
	@Test
	public void testAddByIndex() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(1, "x");
		Assert.assertTrue(list.get(0).equals("a"));
		Assert.assertTrue(list.get(1).equals("x"));
		Assert.assertTrue(list.get(2).equals("b"));
		Assert.assertTrue(list.get(3).equals("c"));
	}
	
}
