package org.stathry.jdkdeep.lang;

import org.junit.Test;

/**
 * TODO
 * Created by dongdaiming on 2018-07-31 11:32
 */
public class InterfaceTest {

    @Test
    public void testInterfaceSubClasses() {

    }

    interface F {
        void hello();
    }

    private static class S1 implements F {

        @Override
        public void hello() {
            System.out.println("hello1");
        }
    }

    private static class S2 implements F {

        @Override
        public void hello() {
            System.out.println("hello2");
        }
    }
}
