package org.stathry.jdkdeep.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @date 2018/3/1
 */
public class LogInvocationHandler implements InvocationHandler {

    private Object object;

    public LogInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String argsStr = argsToString(args);
        System.out.println("before log: method:" + method.toString() + ",args:" + argsStr);
        Object result = method.invoke(object,args);
        System.out.println("after log:" + result);
        return result;
    }

    private String argsToString(Object[] args) {
        StringBuilder s = new StringBuilder();
        if(args == null) {
            s.append("null");
            return s.toString();
        }
        for (Object a : args) {
            s.append(a.toString());
        }
        return s.toString();
    }

}
