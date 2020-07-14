package com.bosssoft.hr.train.web.dao.impl;



import com.bosssoft.hr.train.web.dao.UserDao;
import com.bosssoft.hr.train.web.exception.BusinessException;
import com.bosssoft.hr.train.web.pojo.Query;
import com.bosssoft.hr.train.web.pojo.User;
import com.bosssoft.hr.train.web.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public int insert(User user) {
        if (user != null) {
            DBUtil.init();
            String sql;
            if (user.getId() != null) {
                sql = "insert into user " +
                        "values(" + "'" + user.getId() + "'" + "," + "'" + user.getName() + "'" + "," + "'" + user.getCode() + "'" + "," + "'" + user.getPassword() + "'" + ")";
            } else {
                sql = "insert into user(name,code,password) " +
                        "values(" + "'" + user.getName() + "'" + "," + "'" + user.getCode() + "'" + "," + "'" + user.getPassword() + "'" + ")";
            }
            int result = DBUtil.creatOrUpdateOrDelete(sql);
            DBUtil.closeConn();
            return result;
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        if (id != null) {
            //建立连接 其他类似不再注解
            DBUtil.init();
            String sql = "delete from user where id = " + id;
            //执行sql
            int result = DBUtil.creatOrUpdateOrDelete(sql);
            //关闭连接
            DBUtil.closeConn();
            //返回结果
            return result;
        }
        return 0;
    }

    @Override
    public int update(User user) {
        Integer userId = user.getId();
        // 有选择的更新太难了呀 默认全部字段有效吧
        if (userId != null) {
            DBUtil.init();
            String sql = "update user " +
                    "set name=" + "'" + user.getName() + "'" + " , code=" + "'" + user.getCode() + "'" + " , password=" + "'" + user.getPassword() + "'" +
                    "where id=" + "'" + user.getId() + "'" + "";
            int result = DBUtil.creatOrUpdateOrDelete(sql);
            DBUtil.closeConn();
            return result;
        }
        return 0;
    }

    @Override
    public List<User> queryByCondition(Query query) {
        //简单判定一下是名字模糊查询还是验证账号密码
        String sql = query.getUserByCodeAndPassword("user");
        if (sql == null) {
            sql = query.listNameLikes("user");
        }
        DBUtil.init();
        ResultSet resultSet = DBUtil.select(sql);
        List<User> users = new ArrayList<>();
        User user;
        try {
            while (resultSet.next()) {
                //新建user
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new BusinessException("10001", "数据库查询数据解析异常", e);
        } finally {
            DBUtil.closeConn();
        }
        return users;
    }
}
