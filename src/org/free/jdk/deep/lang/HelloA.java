package org.free.jdk.deep.lang;

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
    
    

}
