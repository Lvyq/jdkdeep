package org.stathry.jdkdeep.util;

public class Assert {

    private Assert() {}

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "illegal argument.");
    }

    public static void assertTrue(boolean exp) {
        assert exp;
    }

    /**
     * assert需要在vm配置中开启"-ea",断言失败会抛出AssertionError
     * @param exp
     * @param msg
     */
    public static void assertTrue(boolean exp, String msg) {
        assert exp : msg;
    }

    public static void main(String[] args) {
//        assertTrue(true);
//        assertTrue(false);

        assertTrue(true, "err_msg1");
        assertTrue(false, "err_msg2");
    }
}
