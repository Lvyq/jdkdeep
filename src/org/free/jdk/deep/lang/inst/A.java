package org.free.jdk.deep.lang.inst;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月29日
 */
public class A {

	private static int f1 = initF1();
	private int f2 = initF2();

	static {
		System.out.println("2A.static block");
	}

	{
		System.out.println("6A.constructor block");
	}

	public A() {
		System.out.println("7A.constructor");
	}

	private static int initF1() {
		System.out.println("1A.initF1");
		return 0;
	}

	/**
	 * @return
	 */
	private int initF2() {
		System.out.println("5A.initF2");
		return 0;
	}

}
