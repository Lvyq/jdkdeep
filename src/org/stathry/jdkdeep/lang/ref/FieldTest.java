package org.stathry.jdkdeep.lang.ref;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * FieldTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-03-01 14:26
 */
public class FieldTest {

//    getFields() 获取所有public字段,包括父类字段
//    getDeclaredFields() 获取所有字段,public和protected和private,但是不包括父类字段
    // 获取父类私有字段:getClass().getSuperclass().getDeclaredField(paramString)

    @Test
    public void testGetPrivateFieldError() throws Exception {
        Class<FieldClass1> c = FieldClass1.class;

        try {
            c.getField("n");
        } catch (Exception e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(e.getClass(), NoSuchFieldException.class);
        }

        try {
            Field f1 = c.getField("n");
        } catch (Exception e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(e.getClass(), NoSuchFieldException.class);
        }

        Field f2 = c.getField("n2");
        Assert.assertNotNull(f2);
    }

    @Test
    public void testGetPrivateFieldSuccess() throws Exception {
        Class<FieldClass1> c = FieldClass1.class;

//        Field f0 = c.getDeclaredField("n0");
        Field f1 = c.getDeclaredField("n1");
        Field f2 = c.getDeclaredField("n2");
//        Assert.assertNotNull(f0);
        Assert.assertNotNull(f1);
        Assert.assertNotNull(f2);
    }

    @Test
    public void testSetPrivateField() throws Exception {
        PrivateFieldClass o1 = new PrivateFieldClass();
        Assert.assertEquals(0, o1.getN());

        Field f = o1.getClass().getDeclaredField("n");
        f.setAccessible(true);
        f.setInt(o1, 666);
        Assert.assertEquals(666, o1.getN());
    }

    private static class PrivateFieldClass {

        private int n;

        public int getN() {
            return n;
        }
    }

    private static class FieldClass0 {

        private int n0;

        public int getN0() {
            return n0;
        }
    }

    private static class FieldClass1 extends FieldClass0 {

        private int n1;
        public int n2;

        public int getN1() {
            return n1;
        }

        public int getN2() {
            return n2;
        }
    }
}
