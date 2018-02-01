/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.bryadong.jdkdeep.lang.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bryadong.jdkdeep.compare.User;
import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月21日
 */
public class ClassLoaderTest {

	@Test
	public void testMyClassLoader() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		WujuClassLoader loader = new WujuClassLoader("d:/temp");
		Class c = loader.loadClass("com.pingan.jdk.deep.compare.User");
		Object u = c.newInstance();
		Method m = c.getDeclaredMethod("hello", null);
		m.invoke(u, null);
	}
	
	@Test
	public void testJavaClass() {
		printLoader(Object.class);
		printLoader(String.class);
	}
	
	@Test
	public void testMyClass() {
		printLoader(this.getClass());
		printLoader(User.class);
	}
	
	public void printLoader(Class<?> c) {
		Class<?> l = null;
		if(c.getClassLoader() != null) {
			if(c.getClassLoader().getParent() != null) {
				l = c.getClassLoader().getParent().getClass();
			}
			else {
				l = c.getClassLoader().getClass();
			}
		}
				
		System.out.println(c.getSimpleName() + "---" + l);
	}

}
