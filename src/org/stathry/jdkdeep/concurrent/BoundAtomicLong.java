package org.stathry.jdkdeep.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 边界原子递增
 */
public class BoundAtomicLong extends AtomicLong {

    private long bound;

    public BoundAtomicLong(long i, int bound) {
        super(i);
        this.bound = bound;
    }

    public long incrementWithBound() {
        for (;;) {
            long current = get();
            if(current >= bound) {
                set(1);
                return get();
            }
            long next = current + 1;
            if (compareAndSet(current, next))
                return next;
        }
    }

}
