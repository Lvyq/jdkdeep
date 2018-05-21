package org.stathry.jdkdeep.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/21
 */
public class OverloadTest {

    private String typeName(Collection<?> c) {
        return "UNKNOWN";
    }

    private String typeName(List<?> c) {
        return "LIST";
    }

    private String typeName(Set<?> c) {
        return "SET";
    }

    // 具体调用哪个重载方法是由编译时的类型决定的
    // ca中的元素的编译时类型是Collection ，所以返回的是UNKNOWN
    @Test
    public void testOverloadTypes () {
        Assert.assertEquals("LIST", typeName(new ArrayList<>()));
        Assert.assertEquals("SET", typeName(new HashSet<>()));

        Collection<?>[] ca = new Collection[2];
        ca[0] = new ArrayList<>();
        ca[1] = new HashSet<>();
        System.out.println(typeName(ca[0]));
        System.out.println(typeName(ca[1]));
        Assert.assertEquals("UNKNOWN", typeName(ca[0]));
        Assert.assertEquals("UNKNOWN", typeName(ca[1]));
     }

}
