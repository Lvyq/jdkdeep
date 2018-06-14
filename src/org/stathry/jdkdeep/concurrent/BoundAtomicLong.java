package org.stathry.jdkdeep.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 边界原子递增
 */
public class BoundAtomicLong extends AtomicLong {
    
    private static final long serialVersionUID = -8650032295035697897L;
    
    private long bound;

    public BoundAtomicLong(long i, long bound) {
        super(i);
        this.bound = bound;
    }

    public long incrementWithBound() {
        for (;;) {
            long current = get();
            if(current >= bound) {
                return getAndSet(1);
            }
            long next = current + 1;
            if (compareAndSet(current, next))
                return next;
        }
    }

}
