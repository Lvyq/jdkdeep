package org.stathry.jdkdeep.lang;

import org.junit.Test;

/**
 * TODO
 * Created by dongdaiming on 2018-07-31 14:53
 */
public class LoopTest {

    // 在java中，要想跳出多重循环，可以在外面的循环语句前定义一个标号，然后在里层循环体的代码中使用带有标号的的break语句，即可跳出
    @Test
    public void testBreakMultiLoop() {
        ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i + "," + j);
                if(i == 5 && j == 5) {
                    break ok;
                }
            }
        }
    }

    @Test
    public void testBreakMultiLoop2() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i + "," + j);
                if(i == 5 && j == 5) {
                    return;
                }
            }
        }
    }
}
