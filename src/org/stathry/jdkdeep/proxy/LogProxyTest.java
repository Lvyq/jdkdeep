package org.stathry.jdkdeep.proxy;

import org.junit.Assert;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * TODO
 *
 * @date 2018/3/1
 */
public class LogProxyTest {

    static {
    }

    public LogProxyTest() {
        super();
    }

    @Test
    public void testLogProxy1() throws NoSuchFieldException, IllegalAccessException {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        LoginService s1 = new LoginService1();
        InvocationHandler handle1 = new LogInvocationHandler(s1);
        LoginService ps1 = (LoginService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{LoginService.class}, handle1);
//        System.out.println(ps1);
//        System.out.println(s1.equals(ps1));
//        ps1.login();
        ps1.login();
//        ps1.hello("ddm");
//        ps1.hello("ddm");
    }

    @Test
    public void testLogProxyOneClass() {
        LoginService s1 = new LoginService1();
        InvocationHandler handle1 = new LogInvocationHandler(s1);
        LoginService ps1 = (LoginService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{LoginService.class}, handle1);
        LoginService ps2 = (LoginService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{LoginService.class}, handle1);
        System.out.println(ps1.getClass().getName());
        System.out.println(ps2.getClass().getName());
        // proxyClass会缓存，相同的interfaces只会创建一个proxyClass
        Assert.assertTrue(ps2.getClass().equals(ps1.getClass()));
        Assert.assertTrue(ps1.getClass().getName().startsWith("com.sun.proxy.$Proxy"));
        Assert.assertFalse(ps1.equals(ps2));
    }

    @Test
    public void testLogProxy2() {
        LoginService s2 = new LoginService2();
        InvocationHandler handle2 = new LogInvocationHandler(s2);
        LoginService ps2 = (LoginService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{LoginService.class}, handle2);
        System.out.println(ps2.getClass().getName());
       Method[] ms = ps2.getClass().getMethods();
        System.out.println("interface list:");
        for(Class<?> c : ps2.getClass().getInterfaces()) {
            System.out.println(c.getName());
        }
        System.out.println("methods length:" + ms.length);
        System.out.println("method list:");
        for(Method m : ms) {
            System.out.println(m);
        }
        System.out.println("method list end.");
        ps2.login();
    }
}
