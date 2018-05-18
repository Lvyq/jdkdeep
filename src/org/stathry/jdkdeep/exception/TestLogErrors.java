package org.stathry.jdkdeep.exception;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/17
 */
public class TestLogErrors {

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
}
