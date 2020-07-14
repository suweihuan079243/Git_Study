package com.bosssoft.hr.train.annotation;

import lombok.Data;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:32
 */
@Table(value = "t_user")
@Data
public class User extends BaseModel{
    @Id(value = "id")
    private Long ID;
    @Column(value = "name")
    private String name;
    @Column(value = "age")
    private Integer age;
}
