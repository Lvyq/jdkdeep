package org.stathry.jdkdeep.string;

import org.junit.Test;

import java.text.FieldPosition;
import java.text.MessageFormat;

/**
 * TODO
 *
 * @author stathry
 * @date 2018/4/17
 */
public class StringFormatTest {

    private static final int N = 10000000;

//    @Test
    public void testStringFormat() {
        StringBuilder b = new StringBuilder();

        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            b.append(String.format("\r\n\"%s\" : \"%s\",", "k" + i, "v" + i));
        }
        long end = System.currentTimeMillis();
        System.out.println("testStringFormat:" + (end - start));
//        System.out.println(b.toString());
    }

    @Test
    public void testMessageFormat() {
        StringBuffer b = new StringBuffer();
        long start = System.currentTimeMillis();
        Object[] args = new Object[]{null, null};
        MessageFormat format = new MessageFormat("\r\n\"{0}\" : \"{1}\",");
        for (int i = 0; i < N; i++) {
            args[0] = "k" + i;
            args[1] = "v" + i;
            format.format(args, b, new FieldPosition(0));
        }
        long end = System.currentTimeMillis();
        System.out.println("testMessageFormat:" + (end - start));
//        System.out.println(b.toString());
    }

    @Test
    public void testMessageFormat1() {
        StringBuffer b = new StringBuffer();
        long start = System.currentTimeMillis();
        MessageFormat format = new MessageFormat("\r\n\"{0}\" : \"{1}\",");
        for (int i = 0; i < N; i++) {
            format.format(new Object[] {"k" + i, "v" + i}, b, new FieldPosition(0));
        }
        long end = System.currentTimeMillis();
        System.out.println("testMessageFormat1:" + (end - start));
//        System.out.println(b.toString());
    }
}
