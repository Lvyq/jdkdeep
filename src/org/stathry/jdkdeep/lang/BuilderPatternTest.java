package org.stathry.jdkdeep.lang;

import org.junit.Test;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/16
 */
public class BuilderPatternTest {

    @Test
    public void testBuilderDream() {
        Dream dream = new Dream.DreamBuilder(1, 2).sal(40).year(2020).build();
        System.out.println(dream.toString());
    }

}
