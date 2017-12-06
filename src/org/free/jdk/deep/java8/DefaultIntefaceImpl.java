package org.free.jdk.deep.java8;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月6日
 */
public class DefaultIntefaceImpl implements DefaultInteface {
	
	@Override
	public void print2() {
		System.out.println("impl print2");
	}
	
	public static void main(String[] args) {
		DefaultInteface i = new DefaultIntefaceImpl();
		i.print1();
		i.print2();
		DefaultInteface.print3();
	}

}
