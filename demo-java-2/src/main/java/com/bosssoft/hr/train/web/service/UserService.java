package com.bosssoft.hr.train.web.service;
;

import com.bosssoft.hr.train.web.pojo.Query;
import com.bosssoft.hr.train.web.pojo.User;

import java.util.List;

public interface UserService {
    boolean save(User user);
    boolean remove(User user);
    boolean update(User user);
    List<User> queryByCondition(Query queryCondition);

    /**
     *  给 LoginController 调用
     * @param user
     * @return
     */
    boolean authentication(User user);

}
