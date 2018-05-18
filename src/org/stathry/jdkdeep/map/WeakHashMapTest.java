package org.stathry.jdkdeep.map;

import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/17
 */
public class WeakHashMapTest {

    // WeakHashMap中未使用的引用自动被回收
    @Test
    public void testRefIsAlive() throws InterruptedException {
        long size = 1000000;
        Map<Long, Long> map = new WeakHashMap<>(1000000 * 2);
        for (long i = 0; i < size; i++) {
            map.put(i, i);
        }
        long start = System.currentTimeMillis();
        long time = 0;
        long curSize = 0;
        while (time < TimeUnit.SECONDS.toMillis(10)) {
            curSize = map.size();
            System.out.println(size);
            // exp ex
            if(curSize != size) {
                throw new IllegalStateException("init size:" + size + ",cur size:" + curSize + ",sub:" + (size - curSize));
            }
            time = System.currentTimeMillis() - start;
            Thread.sleep(1000);
        }
        curSize = map.size();
        System.out.println("init size:" + size + ",cur size:" + curSize + ",sub:" + (size - curSize));
    }

    @Test
    public void testRefIsCleared() {
        long lastSize = 0;
        Map<Long, Long> map = new WeakHashMap<>();
        long size;
        for (long i = 0, max = Long.MAX_VALUE; i < max; i++) {
            map.put(i, i);
            size =  map.size();
            if(size < lastSize) {
                throw new IllegalStateException("curSize:" + size + ", lastSize:" + lastSize);
            }
            lastSize = size;
            System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory() + ", map.Size:" + size);
        }
    }

}


