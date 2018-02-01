package org.bryadong.jdkdeep.concurrent;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年9月4日
 */
public class VolatileInc {

    public volatile int inc = 0;
 
    public void increase() {
        inc++;
    }
 
}