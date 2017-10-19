package org.free.jdk.deep.concurrent.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * TODO
 * 
 * @author dongdaiming
 */
public class ListSplitAction<E> extends RecursiveAction {

    private static final long serialVersionUID = -8903627052794658254L;

    private ComputableList<E> list;

    private long threshodDataSize;

    public ListSplitAction(ComputableList<E> list, long threshodDataSize) {
        this.list = list;
        this.threshodDataSize = threshodDataSize;
    }

    public void doAction() {
//        for (E e : list) {
//            System.out.println(e);
//        }
        System.out.println(list.dataSize());
    }

    @Override
    protected void compute() {
        if (list.dataSize() <= threshodDataSize) {
            doAction();
        } else {
            int size = list.size();
            int middle = size >> 1;
            ComputableList<E> lDatas = list.subDataList(0, middle);
            ComputableList<E> rDatas = list.subDataList(middle, size);
            ListSplitAction<E> lact = new ListSplitAction<>(lDatas, threshodDataSize);
            ListSplitAction<E> ract = new ListSplitAction<>(rDatas, threshodDataSize);
            lact.fork();
            ract.fork();
        }
    }

    public ComputableList<E> getList() {
        return list;
    }

    public long getThreshodDataSize() {
        return threshodDataSize;
    }

}
