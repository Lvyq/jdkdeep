package org.stathry.jdkdeep.lang.ref;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * Created by dongdaiming on 2018-07-03 10:23
 */
public class GenericTest {

    public void array(String[] a) {}
    public void array(Integer[] a) {}

    // 存在相同的类型擦除的方法
//    public void list(List<String> l) {}
    public void list(List<Integer> l) {}

    public void initParentParam() {
        // 泛型非协变, 但可以用通配符模拟协变
//        List<Number> list = new ArrayList<Integer>();
        List<? extends Number> list = new ArrayList<Integer>();
    }
}
