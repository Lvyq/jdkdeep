package org.bryadong.jdkdeep.lang;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月28日
 */
public class ComputerTest {

	@Test
	public void test() {
		int a = 10;
		short b = 5;
		a += b;
		System.out.println(a);
	}
	@Test
	public void test1() {
		System.out.println(Short.MAX_VALUE);
		short a = 32767;
		int b = 2;
		a += b;
		System.out.println(a);
	}

}
