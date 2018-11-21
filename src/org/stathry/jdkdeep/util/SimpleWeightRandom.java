package org.stathry.jdkdeep.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权重随机
 */
public class SimpleWeightRandom<K, W extends Number> {

    private Map<K, WeightEntry<K, W>> weightMap;
    
    private double sum = 0.0d;
    
    private final boolean startOpen;

    public SimpleWeightRandom(List<K> keyList, List<W> weightList, boolean startOpen) {
        if (keyList == null || keyList.isEmpty() || weightList == null || keyList.size() != weightList.size()) {
            throw new IllegalArgumentException("required keyList or weightList.");
        }
        this.startOpen = startOpen;
        int size = keyList.size();
        weightMap = new HashMap<>(size * 2);

        K k;
        W w;
        double value, min, max;
        WeightEntry<K, W> entry;
        for (int i = 0; i < size; i++) {
            k = keyList.get(i);
            w = weightList.get(i);
            value = weightList.get(i).doubleValue();
            min = sum;
            sum += value;
            max = sum;
            entry = new WeightEntry<>(k, w, min, max);
            weightMap.put(k, entry);
        }
    }

    public K random() {
        double r = sum * Math.random();
        for (Map.Entry<K, WeightEntry<K, W>> e : weightMap.entrySet()) {
            if (startOpen && r >= e.getValue().getMin() && r < e.getValue().getMax()) {
                return e.getKey();
            } else if (r > e.getValue().getMin() && r <= e.getValue().getMax()) {
                return e.getKey();
            }
        }
        return null;
    }

    static class WeightEntry<K, V extends Number> {
        private K key;
        private V weight;
        private double min;
        private double max;

        public WeightEntry(K key, V weight, double min, double max) {
            super();
            this.key = key;
            this.weight = weight;
            this.min = min;
            this.max = max;
        }

        public K getKey() {
            return key;
        }

        public V getWeight() {
            return weight;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }
        
        public static void main(String[] args) {
            int limit = 1000000;
            long start = System.currentTimeMillis();
            Map<String, Integer> counter = new HashMap<>(4);
//            WeightRandom1<String, Double> random = new WeightRandom1<>
//            (Arrays.asList("A", "B", "C", "D"), Arrays.asList(0.1, 0.2, 0.3, 0.4), true);
            SimpleWeightRandom<String, Integer> random = new SimpleWeightRandom<>
            (Arrays.asList("A", "B", "C", "D"), Arrays.asList(1, 2, 3, 4), true);
            for (int i = 0; i < limit; i++) {
                String r = random.random();
                if (counter.containsKey(r)) {
                    counter.put(r, (counter.get(r) + 1));
                } else {
                    counter.put(r, 1);
                }
            }
            
            System.out.println(counter);
            System.out.println("cost time in ms:" + (System.currentTimeMillis() - start));
            
            for(java.util.Map.Entry<String, Integer> e : counter.entrySet()) {
                System.out.println(e.getKey() + ":" + e.getValue() * 1.0 / limit);
            }
        }

    }

}
