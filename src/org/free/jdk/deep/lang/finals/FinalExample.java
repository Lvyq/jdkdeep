package org.free.jdk.deep.lang.finals;

/**
 * TODO
 * @date 2018年1月31日
 */
public class FinalExample {
	final int j;                      //final变量
    int i;                            //普通变量
    static FinalExample obj;

    public FinalExample () {     //构造函数
    	j = 2;                        //写final域
        i = 1;                        //写普通域
    }

    public static void writer () {    //写线程A执行
        obj = new FinalExample ();
    }

    public static void reader () {       //读线程B执行
        FinalExample object = obj;       //读对象引用
        int a = object.i;                //读普通域
        int b = object.j;                //读final域
        System.out.println(Thread.currentThread().getName() + "---a:" + a);
        System.out.println(Thread.currentThread().getName() + "---b:" + b);
    }
}
