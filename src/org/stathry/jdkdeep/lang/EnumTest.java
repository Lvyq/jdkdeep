package org.stathry.jdkdeep.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class EnumTest {

    @Test
    public void testEnumUsages() {
        Assert.assertEquals(3, Opt.ADD.apply(1 , 2));
        Assert.assertEquals(1, Opt.SUB.apply(3 , 2));
        Assert.assertEquals(6, Opt.MUL.apply(3 , 2));
        Assert.assertEquals(1, Opt.DIV.apply(3 , 2));
        Assert.assertEquals(Opt.ADD, Enum.valueOf(Opt.class, "ADD"));
        Assert.assertEquals(Opt.ADD, Opt.valueOf("ADD"));
        Assert.assertEquals(4, Opt.values().length);
        Set<Opt> opts1 = new HashSet<>(Arrays.asList(Opt.ADD, Opt.SUB));
        Set<Opt> opts2 = EnumSet.of(Opt.ADD, Opt.SUB);
        Assert.assertEquals(opts1, opts2);
        opts2.add(Opt.DIV);
        Assert.assertEquals(3, opts2.size());
//        Set<Opt> opts3 = Collections.unmodifiableSet(opts2);
//        opts3.add(Opt.MUL);
    }
}
