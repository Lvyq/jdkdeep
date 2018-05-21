package org.stathry.jdkdeep.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/18
 */
public class AnClassTest {

    @Test
    public void testImplComp() {
        List<Date> dates = new ArrayList<>();
        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    public void testImplAI() {
        AI ai = new AI() {
            @Override
            public void m1() {
                System.out.println("rm1");
            }

            @Override
            public void m2() {
                System.out.println("rm2");
            }
        };
        ai.m1();
        ai.m2();
        System.out.println(ai.getClass());
        System.out.println(ai instanceof AI);


        AI ai2 = new AI() {
            @Override
            public void m1() {
                System.out.println("rm3");
            }

            @Override
            public void m2() {
                System.out.println("rm4");
            }
        };
        ai2.m1();
        ai2.m2();
        System.out.println(ai2.getClass());
        System.out.println(ai2 instanceof AI);
    }

//    @FunctionalInterface 仅能标记只有一个方法的接口
    interface AI {
        void m1();
        void m2();
    }
}
