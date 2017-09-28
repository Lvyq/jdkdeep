package org.free.jdk.deep.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 */
public class AtomicLongBound extends AtomicLong{
    
    private static final long serialVersionUID = -8650032295035697897L;

    public AtomicLongBound(int i) {
        super(i);
    }

    public final long incrementAndGet(long bound) {
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
