package org.stathry.jdkdeep.lang.inst;

public class B extends A {

    private static int f3 = initF3();
    private int f4 = initF4();

    static {
        System.out.println("4B.static block");
    }

    {
        System.out.println("9B.constructor block");
    }

    public B() {
        System.out.println("10B.constructor");
    }

    private static int initF3() {
        System.out.println("3B.initF3");
        return 0;
    }

    /**
     * @return
     */
    private int initF4() {
        System.out.println("8B.initF4");
        return 0;
    }

}
