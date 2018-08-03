package org.stathry.jdkdeep.lang;

/**
 * TODO
 * 
 * @author dongdaiming
 */
public class HelloB extends HelloA {

    static {
        System.out.println("static B");
    }

    {
        System.out.println("I'm B class");
    }

    public HelloB() {
        System.out.println("HelloB");
    }
    
    public static void main(String[] args) {
        System.out.println("main start.");
        new HelloB();
        new HelloB();
        System.out.println("main end.");
    }


}
