package org.stathry.jdkdeep.lang;

/**
 * TODO
 * @author dongdaiming
 */
public class HelloA {
    
    static {
        System.out.println("static A");
    }

    {
        System.out.println("I'm A class");
    }
    
    public HelloA() {
        System.out.println("HelloA");
    }
    
    /**
	 * 
	 */
	public final void m1() {

	}

	// 静态方法无法被覆盖
    public static void m2() {

    }

}
