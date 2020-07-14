package com.bosssoft.hr.train.collectionframwork;

/**
 * @author: Suweihuan
 * @date: 2020/7/11
 */
public interface ArrayListExample<T> extends ListExample<T> {
    /**
     *
     * @param position
     * @param node
     * @return
     */
    boolean insert(Integer position, T node);

    /**
     * 中间删除
     * @param position
     * @return
     */
    @Override
    boolean remove(Integer position);


}
