package org.stathry.jdkdeep.string;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author stathry
 * @date 2018/4/17
 */
public class StringFormatTest {

    private static final int N = 10000000;

//     https://docs.oracle.com/javase/7/docs/api/java/net/URLEncoder.html
//    The alphanumeric characters "a" through "z", "A" through "Z" and "0" through "9" remain the same.
//    The special characters ".", "-", "*", and "_" remain the same.
//    The space character " " is converted into a plus sign "+".
    @Test
    public void testURLEncode() throws UnsupportedEncodingException {
        Assert.assertEquals("abc123", URLEncoder.encode("abc123", "utf-8"));
        Assert.assertEquals("%24abc123", URLEncoder.encode("$abc123", "utf-8"));

        String s = "$xyz666中文abc123";
        String es = URLEncoder.encode(s, "utf-8");
        System.out.println(es);
        String des = URLDecoder.decode(es, "utf-8");
        System.out.println(des);
        Assert.assertEquals(s, des);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void testMFE() {
        String m1 = MessageFormat.format("你好，{}, 今天是", "james");
        System.out.println(m1);
    }

    // 入参应以string格式传入，否则可能会出现非预期格式
    @Test
    public void testMessageFormat0() {
        String m1 = MessageFormat.format("你好，{0}, 今天是{1}", "james", 1121);
        System.out.println(m1);
        Assert.assertEquals("你好，james, 今天是1,121", m1);

        String m12 = MessageFormat.format("你好，{0}, 今天是{1}", "james", "1121");
        System.out.println(m12);
        Assert.assertEquals("你好，james, 今天是1121", m12);

        MessageFormat mf = new MessageFormat("你好，{0}, 今天是{1}");
        String m2 = mf.format(new Object[]{"maque", 1121});
        System.out.println(m2);
        Assert.assertEquals("你好，maque, 今天是1,121", m2);

        String m22 = mf.format(new Object[]{"maque", "1121"});
        System.out.println(m22);
        Assert.assertEquals("你好，maque, 今天是1121", m22);

        String m23 = mf.format(new Object[]{"maque", new Date()});
        System.out.println(m23);
//        Assert.assertEquals("你好，maque, 今天是2018/11/21 上午9:32", m23);
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
