package com.bosssoft.hr.train.annotation;

import java.lang.annotation.*;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Table {
    String value() ;
}
