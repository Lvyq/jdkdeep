/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.free.jdk.deep.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * @author dongdaiming@free.com 2016年10月13日
 */
public class CompareTest {

	@Test
	public void test1() {
		User user1 = new User();
		user1.setId(2);
		User user2 = new User();
		user2.setId(4);
		System.out.println(user1.compareTo(user2));

	}

	@Test
	public void test2() {
		User user1 = new User();
		user1.setId(6);
		User user2 = new User();
		user2.setId(4);

		List<User> users = new ArrayList<>(10);
		users.add(user1);
		users.add(user2);
		Collections.sort(users);
		System.out.println();

	}

	@Test
	public void test3() throws InterruptedException {
		Date d1 = new Date();
//		Thread.sleep(10);
		Date d2 = new Date();
		System.out.println(d1.before(d2));
	}
}
