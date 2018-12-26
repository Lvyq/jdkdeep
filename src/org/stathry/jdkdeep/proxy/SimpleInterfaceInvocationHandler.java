package org.stathry.jdkdeep.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * SimpleInterfaceInvocationHandler
 *
 * @date 2018/3/1
 */
public class SimpleInterfaceInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String argsStr = argsToString(args);
        System.out.println("before invoke: method:" + method.toString() + ",args:" + argsStr);
        System.out.println("mock invoking");
        // 调用代理对象的方法会陷入无限循环
//         return method.invoke(proxy, args);
        System.out.println("after invoke");
        return null;
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
