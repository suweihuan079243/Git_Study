package com.bosssoft.hr.train.web.util;

import com.bosssoft.hr.train.web.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public final class DBUtil {
    private static final Logger log = LoggerFactory.getLogger(DBUtil.class);
    private static final String URL = "jdbc:mysql://localhost:3306/boss_train?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static  final String CODE="10001";
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;

    private DBUtil() {

    }

    /**
     * 封装一个方法用于连接
     *
     * @return
     */
    public static Connection init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
            throw new BusinessException(CODE, "数据库连接异常", e);
        }
    }

    /**
     * 用来增删改
     * 但是仅用于参数都确定
     *
     * @param sql
     * @return
     */
    public static int creatOrUpdateOrDelete(String sql) {
        int result = -1;
        if (conn != null) {
            try {
                ps = conn.prepareStatement(sql);
                //执行
                result = ps.executeUpdate();
            } catch (SQLException e) {
                throw new BusinessException(CODE, "数据库修改异常", e);
            }
        } else {
            log.error("com.bosssoft.hr.train.j2se.basic.example.database.DBUtil.CreatOrUpdateOrDelete：获取数据库连接失败！");
        }
        return result;
    }

    /**
     * 查询
     *
     * @param sql
     * @return
     */
    public static ResultSet select(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
        } catch (SQLException e) {
            throw new BusinessException(CODE, "数据库查询异常", e);
        }
        return rs;
    }

    /**
     * 关闭连接
     */
    public static void closeConn() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new BusinessException(CODE, "数据库关闭异常", e);
        }
    }
}
