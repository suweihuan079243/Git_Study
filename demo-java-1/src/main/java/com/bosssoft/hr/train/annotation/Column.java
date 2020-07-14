package com.bosssoft.hr.train.annotation;

import java.lang.annotation.*;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:29
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Column {
    String value() ;

}
