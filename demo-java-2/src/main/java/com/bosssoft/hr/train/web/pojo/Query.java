package com.bosssoft.hr.train.web.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Query {
    /**
     * 用来模糊搜索
     */
    private String name;
    /**
     * 用来查询账号密码
     */
    private String code;
    private String password;

    public Query(String name) {
        this.name=name;
    }

    public Query(User user) {
        this.code = user.getCode();
        this.password = user.getPassword();
    }
    /**
     * 根据name模糊搜索
     *
     * @param tableName
     * @return
     */
    public String listNameLikes(String tableName) {
        String userName = "%" + this.getName() + "%";
        return "select * from " + tableName + " where name like " + "'" + userName + "'";
    }

    /**
     * 验证账号密码
     *
     * @param tableName
     * @return
     */
    public String getUserByCodeAndPassword(String tableName) {
        String userCode = this.getCode();
        String userPassword = this.getPassword();
        if (userCode != null && userPassword != null) {
            return "select * from " + tableName + " where code=" + "'" + userCode + "'" + " and password=" + "'" + userPassword + "'" + "";
        }
        return null;
    }
}
