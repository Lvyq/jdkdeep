package org.free.jdk.deep.concurrent.forkjoin;

import java.util.List;

/**
 * TODO
 * 
 * @author dongdaiming
 */
public interface ComputableList<E> extends List<E> {
    
    boolean addData(E e);
    
    ComputableList<E> subDataList(int fromIndex, int toIndex);
    
    long dataSize();

}
