package org.stathry.jdkdeep.vm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * RuntimeConstantPoolOOMTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-03-05 09:26
 */
public class RuntimeConstantPoolOOMTest {

    @Test(expected = OutOfMemoryError.class)
    public void testConstantPoolOOM() {
        List<String> list = new ArrayList<>(1000_0000);
        System.out.println(list.size());
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
