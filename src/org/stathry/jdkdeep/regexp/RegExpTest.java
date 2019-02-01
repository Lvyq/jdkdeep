package org.stathry.jdkdeep.regexp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则性能测试
 * 对比下面测试结果，将正则缓存下来的代码要比每次都重新编译正则快1倍多
 * @author stathry
 * @date 2018/3/28
 */
public class RegExpTest {

    private static final String REGEXP = "\\d*[02468]";
    private static final int TN = 8;
    private static final int LIMIT = 1000000;
    private static final String ARR = "ABC,DEF,XYZ";
    private static final char SEP = ',';

    @Test
    public void testMatchRegKeyChars() {
        Assert.assertTrue(Pattern.compile("urealsoon\\.").matcher("urealsoon.").matches());
        Assert.assertTrue(Pattern.compile("sms\\-center").matcher("sms-center").matches());
    }

    // group分组是按括号内正则分组，如果匹配成功，则group(0)返回整个匹配的字符串，group(1)返回第一个括号匹配的字符串，group(2)以此类推
    @Test
    public void testGroupStrs() {
        Pattern pattern = Pattern.compile("www\\.(\\w+)\\.com");
        String in = "www.google.com www.taobao.com";
        Matcher m = pattern.matcher(in);
        List<String> hosts = new ArrayList<>();
        while (m.find()) {
            hosts.add(m.group(1));
        }
        Assert.assertEquals(2, hosts.size());
        Assert.assertEquals("google", hosts.get(0));
        Assert.assertEquals("taobao", hosts.get(1));
    }

    @Test
    public void testGroupStr() {
        Pattern pattern = Pattern.compile("urealsoon\\.sms\\-center\\.smsStatusSync\\.(\\w+)\\.key");
        String in = "urealsoon.sms-center.smsStatusSync.wotao.key";
        Matcher m = pattern.matcher(in);
        if(m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            Assert.assertEquals(in, m.group(0));
            Assert.assertEquals("wotao", m.group(1));
        }
    }

    @Test
    public void testGroup() {

        String reg = "(\\d+)天内有(\\d+)天无通话记录";
        String in = "180天内有116天无通话记录";
        Matcher m = Pattern.compile(reg).matcher(in);
        if(m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            Assert.assertEquals(in, m.group(0));
            Assert.assertEquals("180", m.group(1));
            Assert.assertEquals("116", m.group(2));
        }
    }

    // 2026,2101,2031
    @Test
    public void testStrToArr() throws InterruptedException {
        long start = System.currentTimeMillis();
        LongAdder counter = new LongAdder();
        ExecutorService exec = Executors.newFixedThreadPool(TN);
        for (int i = 0; i < TN; i++) {
            exec.submit(() -> {
                for (int j = 0; j < LIMIT; j++) {
                String s = ARR + j;
                counter.add(strToArr(s).length);
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(counter.longValue());
    }

    // 1370,1438,1477
    @Test
    public void testSplit() throws InterruptedException {
        long start = System.currentTimeMillis();
        LongAdder counter = new LongAdder();
        ExecutorService exec = Executors.newFixedThreadPool(TN);
        for (int i = 0; i < TN; i++) {
            exec.submit(() -> {
                for (int j = 0; j < LIMIT; j++) {
                    String s = ARR + j;
                    counter.add(s.split(",").length);
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(counter.longValue());
    }

    @Test
    public void testCalc2() throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicLong counter = new AtomicLong();
        ExecutorService exec = Executors.newFixedThreadPool(TN);
        for (int i = 0; i < TN; i++) {
            exec.submit(() -> {
                boolean match = false;
                for (int j = 0; j < LIMIT; j++) {
                    match = j % 2 == 0;
                    if (match) {
                        counter.getAndIncrement();
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(counter.get());
    }

    @Test
    public void testStringMatch2() throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicLong counter = new AtomicLong();
        ExecutorService exec = Executors.newFixedThreadPool(TN);
        for (int i = 0; i < TN; i++) {
            exec.submit(() -> {
                boolean match = false;
                for (int j = 0; j < LIMIT; j++) {
                    match = String.valueOf(j).matches(REGEXP);
                    if (match) {
                        counter.getAndIncrement();
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(counter.get());
    }

    // 1509,1046,974,1235
    @Test
    public void testPatternMatch2() throws InterruptedException {
        long start = System.currentTimeMillis();
        final Pattern PATTERN = Pattern.compile(REGEXP);
        AtomicLong counter = new AtomicLong();
        ExecutorService exec = Executors.newFixedThreadPool(TN);
        for (int i = 0; i < TN; i++) {
            exec.submit(() -> {
                boolean match = false;
                for (int j = 0; j < LIMIT; j++) {
                    match = PATTERN.matcher(String.valueOf(j)).matches();
                    if (match) {
                        counter.getAndIncrement();
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(counter.get());
    }

    private static String[] strToArr(String arr) {
        char[] cs = arr.toCharArray();
        List<String> l = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < arr.length(); i++) {
            if(cs[i] == SEP) {
                l.add(arr.substring(start, i));
                start = i+1;
            } else if(i == arr.length() - 1) {
                l.add(arr.substring(start));
            }
        }
        String[] a = new String[l.size()];
        l.toArray(a);
        return a;
    }
}
