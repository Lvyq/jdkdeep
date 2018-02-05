package org.stathry.jdkdeep.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年9月4日
 */
public class VolatileInc3 {

    public int inc = 0;
    
    private final Lock lock = new ReentrantLock();
 
    public void increase() {
    	lock.lock();
    	try {
    		inc++;
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
        
    }
 
}