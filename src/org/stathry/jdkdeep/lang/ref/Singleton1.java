package org.stathry.jdkdeep.lang.ref;

/**
 * TODO
 * Created by dongdaiming on 2018-06-04 11:48
 */
public class Singleton1 {

    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
