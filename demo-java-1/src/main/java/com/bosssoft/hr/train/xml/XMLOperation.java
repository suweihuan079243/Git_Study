package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;

/**
 * @author: Suweihuan
 * @date: 2020/7/11 15:50
 */
public interface XMLOperation <T extends Student>{
    boolean create(T object);
    boolean remove(T object);
    boolean update(T object);
    T query(T object);

}
