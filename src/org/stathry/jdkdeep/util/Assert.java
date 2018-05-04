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
}
