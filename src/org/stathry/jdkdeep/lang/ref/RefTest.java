package org.stathry.jdkdeep.lang.ref;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * TODO
 * Created by dongdaiming on 2018-06-04 11:47
 */
public class RefTest {

    @Test
    public void testCreateMoreInstanceByRef() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Singleton1> c1 = Singleton1.class.getDeclaredConstructor();
        c1.setAccessible(true);
        Singleton1 i1 = c1.newInstance();
        Singleton1 i2 = c1.newInstance();
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i1.equals(i2));
        Assert.assertNotEquals(i1, i2);
    }

    @Test(expected = IllegalAccessException.class)
    public void testCreateMoreInstanceByRefError() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Singleton1> c1 = Singleton1.class.getDeclaredConstructor();
//        c1.setAccessible(true);
        Singleton1 i1 = c1.newInstance();
        Singleton1 i2 = c1.newInstance();
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i1.equals(i2));
        Assert.assertNotEquals(i1, i2);
    }

}
