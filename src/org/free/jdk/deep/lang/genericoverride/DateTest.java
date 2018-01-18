/*
 * Copyright © ORG.FREE ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.genericoverride;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongdaiming
 * @date 2018年1月17日
 */
public class DateTest {

//	Date类重写了生成hash码的方法，以系统时间为参照生成当前对象的hash码
	@SuppressWarnings("all")
	@Test
	public void testHash1() {
		// 运行代码你会怀疑date和date1是同一个对象吗？
		List list = new ArrayList<>();
		Date date = new Date();
		list.add(date);
		int h1 = date.hashCode();
		int s1 = list.size();
		// 如果你怀疑是同一个对象，请加上如下demo再试试
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Date date1 = new Date();
		int h2 = date1.hashCode();
		boolean b = list.remove(new Date());
		Assert.assertFalse(b);
		Assert.assertTrue(h1 != h2);
	}
	@SuppressWarnings("all")
	@Test
	public void testHash2() {
		// 运行代码你会怀疑date和date1是同一个对象吗？
		List list = new ArrayList<>();
		Date date = new Date();
		list.add(date);
		int h1 = date.hashCode();
		int s1 = list.size();
		
		Date date1 = new Date();
		int h2 = date1.hashCode();
		boolean b = list.remove(new Date());
		Assert.assertTrue(b);
		Assert.assertTrue(h1 == h2);
	}
	
	@Test
	public void test3() {
		System.out.println(null == null);
	}
	@Test
	public void test4() {
		Date now = new Date();
//		Date now2 = new Date(System.currentTimeMillis() + 1);
		Date now2 = new Date(System.currentTimeMillis());
		System.out.println(now.equals(now2));
	}

}
