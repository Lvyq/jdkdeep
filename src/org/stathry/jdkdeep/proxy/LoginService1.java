package org.stathry.jdkdeep.proxy;

/**
 * TODO
 *
 * @date 2018/3/1
 */
public class LoginService1 implements LoginService {

    public void login() {
        System.out.println("login1 running");
    }

    @Override
    public String hello(String name) {
        String s = "hello1 " + name + "!";
        System.out.println(s);
        return s;
    }
}
