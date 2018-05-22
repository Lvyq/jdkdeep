package org.stathry.jdkdeep.exception;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/17
 */
public class TestErrors {

    private int stackDeep = 1;

    @Test(expected = ParseException.class)
    public void testExTypeCheck1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.parse("2018-05-22"));
    }

    @Test
    public void testExTypeCheck2() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.parse("2018-05-22 17:01"));
    }

    public void testExTypeRuntime1() {
        // RuntimeException 无需捕获或声明抛出
        throwRuntimeEx1();

        // CheckedException 需要捕获或声明抛出
        try {
            throwCheckedEx1();
        } catch (CustCheckedException e) {
            e.printStackTrace();
        }
    }

    private void throwRuntimeEx1() throws CustRuntimeException {

    }

    private void throwCheckedEx1() throws CustCheckedException {

    }

    private void recSum() {
        stackDeep++;
        recSum();
    }

    // 10379, 10375, 10369
    @Test
    public void testStackOverflowError() {
        try {
            recSum();
        } catch (StackOverflowError e) {
            System.out.println("stackDeep: " + stackDeep);
            e.printStackTrace();
        }
    }

    // 9574, 9568, 9568
    @Test
    public void testStackOverflowDeep() throws InterruptedException {
        AtomicInteger i = new AtomicInteger();
            try {
                incr(i);
            } catch (StackOverflowError e) {
                System.out.println(i.get());
                Thread.sleep(100);
                e.printStackTrace();
            }
    }

    private void incr(AtomicInteger i) {
        i.incrementAndGet();
        incr(i);
    }

    @Test
    public void testErrorStruct() throws InterruptedException {
        try {
            int n = 1 / 0;
        } catch (Exception e) {
            System.out.println("e.toString:" + e.toString());
            System.out.println( "stackTrace.size:" + e.getStackTrace().length);
            System.out.println( "stackTrace0:" + e.getStackTrace()[0]);
            System.out.println();

            System.out.println("printStackTrace:");
            Thread.sleep(1000);
            e.printStackTrace();
        }

    }

    // 关机时可以通过钩子来做现场记录、挽救等
    @Test
    public void testJvmShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new LogShutdownHook());
    }

    // 当有大量相同的异常抛出时jvm会做fastThrow优化,抛出一个事先准备好的与之类型相同的stackTrace为空的异常
    // 可设置jvm参数来禁用fastThrow     -XX:-OmitStackTraceInFastThrow
    @Test
    public void testPrintLargeErrors() {
        Runtime.getRuntime().addShutdownHook(new LogShutdownHook());
        int r = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {

            try {
                r = 1 / 0;
            } catch (Exception e) {
                System.out.println("i:" + i + ", trace:" + e.getStackTrace().length);
                if (e.getStackTrace().length == 0) {
                    System.out.println("stackTrace omit after " + i + " times");
                    e.printStackTrace();
//                    break;
                }
            }

        }
    }

    private static class LogShutdownHook extends Thread {
        @Override
        public void run() {
            System.out.println("system abnormal shutdown at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + ".help!");
        }
    }

    private static class CustRuntimeException extends RuntimeException {

    }

    private static class CustCheckedException extends Exception {

    }
}
