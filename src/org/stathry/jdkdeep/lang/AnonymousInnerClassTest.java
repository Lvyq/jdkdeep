package org.stathry.jdkdeep.lang;

import org.junit.Test;

/**
 * 匿名内部类也就是没有名字的内部类
 *
 * 正因为没有名字，所以匿名内部类只能使用一次，它通常用来简化代码编写
 *
 * 但使用匿名内部类还有个前提条件：必须继承一个父类或实现一个接口
 *
 * @author dongdaiming
 * @date 2018/5/9
 */
public class AnonymousInnerClassTest {
    protected interface I1 {
        void m1();
    }

    protected abstract static class A1 {
        abstract void m2();
    }

    @Test
    public void testI() {
        I1 i1 = new I1() {
            @Override
            public void m1() {
                System.out.println("m1");
            }
        };
        i1.m1();
    }

    @Test
    public void testA() {
        A1 a1 = new A1() {
            @Override
            void m2() {
                System.out.println("m2");
            }
        };
        a1.m2();
    }

 }
