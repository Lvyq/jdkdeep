package org.free.jdk.deep.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权重随机
 */
public class WeightRandom1<K, V extends Number> implements Weight<K, V> {

    private Map<K, WeightEntry<K,V>> weightMap = new HashMap<>();
    
    private double sum = 0;
    
    private boolean startOpen = true;

    public WeightRandom1(List<K> keys, List<V> weightes, boolean startOpen) {
        this.startOpen = startOpen;
        for (int i = 0; i < keys.size(); i++) {
            K k = keys.get(i);
            V w = weightes.get(i);
            double value = weightes.get(i).doubleValue();
            double startRange = sum;
            sum += value;
            double endRange = sum;
            WeightEntry<K, V> entry = new WeightEntry<>(k, w, startRange, endRange);
             weightMap.put(k, entry);
        }
    }

    @Override
    public K random() {
        double r = sum * Math.random();
        for (java.util.Map.Entry<K, WeightEntry<K, V>> e : weightMap.entrySet()) {
            if(startOpen) {
                if(r >= e.getValue().getMin() && r < e.getValue().getMax()) {
                    return e.getKey();
                }
            } else {
                if(r > e.getValue().getMin() && r <= e.getValue().getMax()) {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    static class WeightEntry<K, V extends Number> implements Weight.WeightEntry<K, V> {
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
            WeightRandom1<String, Integer> random = new WeightRandom1<>
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
