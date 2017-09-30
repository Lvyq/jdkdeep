package org.free.jdk.deep.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 根据权重获取随机key
 * <p>
 * 思路：累加每个元素的权重A(1)-B(3)-C(6)-D(10)，则4个元素的的权重管辖区间分别为[0,1)、[1,3)、[3,6)、[6,10)。
 * 然后随机出一个[0,10)之间的随机数。落在哪个区间，则该区间之后的元素即为按权重命中的元素。
 * </p>
 */
public class WeightRandom<K, V extends Number> {
    
    private TreeMap<Double, K> weightMap = new TreeMap<Double, K>();

    public WeightRandom(LinkedHashMap<K, V> weight) {
        if(weight == null || weight.isEmpty()) {
            throw new IllegalArgumentException("random weight is null");
        }
        
        for (Entry<K, V> w : weight.entrySet()) {
            // 统一转为double
            double lastWeight = weightMap.isEmpty() ? 0 : weightMap.lastKey().doubleValue();

            // 权重累加
            weightMap.put(w.getValue().doubleValue() + lastWeight, w.getKey());
        }
        
    }
    
    public WeightRandom(List<K> keys, List<V> values) {
        if(keys == null || keys.isEmpty() || values == null || values.isEmpty() || keys.size() != values.size()) {
            throw new IllegalArgumentException("illegal random weight");
        }
        
        for (int i = 0; i < keys.size(); i++) {
            // 统一转为double
            double lastWeight = weightMap.isEmpty() ? 0 : weightMap.lastKey().doubleValue();
            
            // 权重累加
            weightMap.put(values.get(i).doubleValue() + lastWeight, keys.get(i));
        }
        
    }
    
    /**
     * 根据权重获取随机key
     * @return
     */
    public K random() {
        double randomWeight = weightMap.lastKey() * Math.random();
        SortedMap<Double, K> tailMap = weightMap.tailMap(randomWeight, false);
        return weightMap.get(tailMap.firstKey());
    }
    
//    public static void main(String[] args) {
//        test1();
//        test2();
//    }
//
//    @SuppressWarnings("all")
//    private static void test1() {
//        int times = 1000000;
//        long start = System.currentTimeMillis();
//        LinkedHashMap<Integer, Double> weight = new LinkedHashMap<>(4);
//        weight.put(1, 0.1);
//        weight.put(2, 0.2);
//        weight.put(3, 0.3);
//        weight.put(4, 0.4);
//        WeightRandom<Integer, Double> random = new WeightRandom<Integer, Double>(weight);
//        
//        Map<Integer,Integer> counter = new HashMap<>(4);
//        Integer r;
//        for (int i = 0; i < times; i++) {
//            r = random.random();
//            if(counter.containsKey(r)) {
//                counter.put(r, (counter.get(r) + 1));
//            } else {
//                counter.put(r, 1);
//            }
//        }
//        System.out.println("1:" + counter.get(1) * 1.0 / times);
//        System.out.println("2:" + counter.get(2) * 1.0 / times);
//        System.out.println("3:" + counter.get(3) * 1.0 / times);
//        System.out.println("4:" + counter.get(4) * 1.0 / times);
//        System.out.println("cost time in ms:" + (System.currentTimeMillis() - start));
//    }
//    
//    @SuppressWarnings("all")
//    private static void test2() {
//        int times = 1000000;
//        long start = System.currentTimeMillis();
//        List<Integer> keys = Arrays.asList(1, 2, 3, 4);
//        List<Double> values = Arrays.asList(0.1, 0.2, 0.3, 0.4);
//        WeightRandom<Integer, Double> random = new WeightRandom<Integer, Double>(keys, values);
//        
//        Map<Integer,Integer> counter = new HashMap<>(4);
//        Integer r;
//        for (int i = 0; i < times; i++) {
//            r = random.random();
//            if(counter.containsKey(r)) {
//                counter.put(r, (counter.get(r) + 1));
//            } else {
//                counter.put(r, 1);
//            }
//        }
//        System.out.println("1:" + counter.get(1) * 1.0 / times);
//        System.out.println("2:" + counter.get(2) * 1.0 / times);
//        System.out.println("3:" + counter.get(3) * 1.0 / times);
//        System.out.println("4:" + counter.get(4) * 1.0 / times);
//        System.out.println("cost time in ms:" + (System.currentTimeMillis() - start));
//    }

}
