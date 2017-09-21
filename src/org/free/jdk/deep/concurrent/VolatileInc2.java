package org.free.jdk.deep.concurrent;


/**
 * TODO
 * @author dongdaiming
 * @date 2017年9月4日
 */
public class VolatileInc2 {

    public int inc = 0;
 
    public synchronized void increase() {
        inc++;
    }
 
}