package org.stathry.jdkdeep.proxy;

/**
 * TODO
 *
 * @date 2018/3/1
 */
public class LoginService2 implements LoginService {

    public void login() {
        System.out.println("login2 running");
    }

    @Override
    public String hello(String name) {
        String s = "hello2 " + name + "!";
        System.out.println(s);
        return s;
    }
}
