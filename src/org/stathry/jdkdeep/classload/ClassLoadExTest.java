package org.stathry.jdkdeep.classload;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年2月5日
 */
public class ClassLoadExTest {

	@Test
	public void test1() throws ClassNotFoundException {
		Class<?> c = Class.forName("org.stathry.jdkdeep.classload.TempClass2");
		System.out.println(c);
		assertNotNull(c);
	}

	@Test(expected = ClassNotFoundException.class)
	public void test2() throws ClassNotFoundException {
		Class<?> c = Class.forName("org.stathry.jdkdeep.classload.TempClass1");
		System.out.println(c);
		assertNotNull(c);
	}

	@Test
	public void test3() throws ClassNotFoundException {
		Class<?> c = this.getClass().getClassLoader().loadClass("org.stathry.jdkdeep.classload.TempClass2");
		System.out.println(c);
		assertNotNull(c);
	}

	@Test(expected = ClassNotFoundException.class)
	public void test4() throws ClassNotFoundException {
		Class<?> c = this.getClass().getClassLoader().loadClass("org.stathry.jdkdeep.classload.TempClass1");
		System.out.println(c);
		assertNotNull(c);
	}

	@Test
	public void testNoDef() {
		TempClass2 c = new TempClass2();
		System.out.println(c);
		assertNotNull(c);
	}

	// 删除TempClass2.class后执行会有NoClassDefFoundError
	@Test(expected = NoClassDefFoundError.class)
	public void testNoDefEx() {
		TempClass2 c = new TempClass2();
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.classpath"));
	}

}
