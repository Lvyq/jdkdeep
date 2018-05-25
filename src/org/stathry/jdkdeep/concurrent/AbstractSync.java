package org.stathry.jdkdeep.concurrent;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 18/05/24
 */
public abstract class AbstractSync {

    protected synchronized void m() {
        m1();
    }

    protected abstract void m1();

}
