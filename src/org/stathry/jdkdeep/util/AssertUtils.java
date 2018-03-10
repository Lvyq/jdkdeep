package org.stathry.jdkdeep.util;

public class AssertUtils {

    private AssertUtils() {}

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
