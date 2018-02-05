package org.stathry.jdkdeep.lang.finals;

/**
 * TODO
 * @date 2018年1月31日
 */
public class F2 {
	
	private int n1 = 10;
	private final int n2;
	public F2(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public static void main(String[] args) {
		F2 o = new F2(11, 22);
		System.out.println(o.n1);
		System.out.println(o.n2);
	}
	

}
