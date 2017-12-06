package org.free.jdk.deep.java8;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月6日
 */
public interface DefaultInteface {

	default void print1() {
		System.out.println("default print.");
	}

	void print2();
	
	static void print3() {
		System.out.println("static print3");
	}

}
