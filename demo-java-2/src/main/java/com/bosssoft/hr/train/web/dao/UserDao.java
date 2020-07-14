package com.bosssoft.hr.train.web.dao;


import com.bosssoft.hr.train.web.pojo.Query;
import com.bosssoft.hr.train.web.pojo.User;

import java.util.List;


public interface UserDao {
    /**
     *  插入记录
     * @param user
     * @return 返回影响的行数
     */
    int insert(User user);
    int deleteById(Integer id);
    int update(User user);
    List<User> queryByCondition(Query queryCondition);
}
