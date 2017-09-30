package org.free.jdk.deep.map;

/**
 * 权重接口
 */
public interface Weight<K, V extends Number> {
    
    /**
     * 按权重随机获取K
     * @return
     */
    K random();

    interface WeightEntry<K, V extends Number> {
        
        K getKey();

        V getWeight();
        
        public double getMin();
        
        public double getMax();

    }

}
