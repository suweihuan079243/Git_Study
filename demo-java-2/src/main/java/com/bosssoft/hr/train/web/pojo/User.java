package com.bosssoft.hr.train.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;



@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -4858663419007862663L;
    private Integer id;
    private String name;

    private String code;
    private String password;

    public User(String code, String password) {
    }

    public User(Integer id, String password) {
        this.id=id;
        this.password=password;
    }
}
