package org.stathry.jdkdeep.lang.ext1;

/**
 *
 * @author dongdaiming
 * @date 2018/5/17
 */
public class B1 extends A1 {

    // 子类不可调用父类的私有构造方法
//    public B1() {
////        super();
//    }

    public B1(int n) {
        super(n);
    }

}
