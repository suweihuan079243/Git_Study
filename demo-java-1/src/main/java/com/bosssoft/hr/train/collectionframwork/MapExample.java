package com.bosssoft.hr.train.collectionframwork;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 14:00
 */
public interface MapExample<T,V> {
    /**
     *
     * @param key  map的键
     * @param value 值
     * @return 返回值
     */
    V put(T key,V value);
    /**
     *
     * @param key  map的键
     * @return 返回值
     */
    V remove(T key);

    /**
     *
     * @param key  map的键
     * @return 返回值 是否存在的判断
     */
    boolean containsKey(T key);

    /**
     * 迭代方式1
     */
    void visitByEntryset();
    /**
     * 迭代方式2
     */
    void visitByKeyset();
    /**
     * 迭代方式3
     */
    void visitByValues();
}
