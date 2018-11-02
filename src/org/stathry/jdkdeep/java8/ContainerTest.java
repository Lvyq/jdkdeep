package org.stathry.jdkdeep.java8;

import org.junit.Test;
import org.stathry.jdkdeep.util.FlagEnum;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

/**
 * ContainerTest
 * Created by dongdaiming on 2018-09-29 17:00
 */
public class ContainerTest {

    @Test
    public void testKVNull() {
        List<Object> vector = new Vector<>();
        vector.add(null);
        EnumMap<FlagEnum, Object> enumMap = new EnumMap<>(FlagEnum.class);
        enumMap.put(FlagEnum.E0, null);
        Map<Object, Object> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put(1, null);
        syncMap.put(null, 1);
        Map<Object, Object> id = new IdentityHashMap<>();
        id.put(1, null);
        id.put(null, 1);

    }
    @Test(expected = Exception.class)
    public void testKVNullEX() {
//        Map<Object, Object> propMap = new Properties();
//        propMap.put(1, null);
//        propMap.put(null, 1);
//        Map<Object, Object> tab = new Hashtable<>();
//        tab.put(1, null);
//        tab.put(null, 1);

//        EnumMap<FlagEnum, Object> enumMap = new EnumMap<>(FlagEnum.class);
//        enumMap.put(null, null);

    }

}
