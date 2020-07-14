package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.User;

/**
 * @authur: Suweihuan
 * @Date:2020/7/11
 */
public interface ListExample<T> {
    /**
     * 添加元素到尾部
     *
     * @param user
     * @return
     */
    boolean append(T user);

    /**
     * 返回给定索引的值
     *
     * @param index
     * @return
     */
    T get(Integer index);

    /**
     * 删除给定位置的值
     *
     * @param position
     * @return
     */
    boolean remove(Integer position);

    /**
     * 按下表遍历 注意这种方式遍历过程不可以删除
     */
    void listByIndex();

    /**
     * 按迭代器遍历
     */
    void listByIterator();

    /**
     * 列表数组转换
     *
     * @return
     */
    User[] toArray();

    /**
     * 列表排序演示
     */
    void sort();

}
