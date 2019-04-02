package org.stathry.jdkdeep.concurrent.threadlocal;

import java.util.concurrent.atomic.LongAdder;

/**
 * 线程隔离计数器
 *
 * @author dongdaiming(董代明)
 * @date 2019-03-05 18:37
 */
public class ThreadLocalAdder {

    private static final ThreadLocal<LongAdder> LOCAL_ADDER = new ThreadLocal<>();

    private ThreadLocalAdder() {
    }

    public static LongAdder getLocalAdder() {
        LongAdder adder = LOCAL_ADDER.get();
        if (adder == null) {
            adder = new LongAdder();
            LOCAL_ADDER.set(adder);
        }
        return adder;
    }

    public static void initLongAdder() {
        LOCAL_ADDER.remove();
        LOCAL_ADDER.set(new LongAdder());
    }

    public static void remove() {
        LOCAL_ADDER.remove();
    }
}
