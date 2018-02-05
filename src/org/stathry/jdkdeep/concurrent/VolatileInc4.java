package org.stathry.jdkdeep.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年9月4日
 */
public class VolatileInc4 {

    public AtomicInteger i = new AtomicInteger(0);
    
    public void increase() {
    	i.incrementAndGet();
    }
 
}