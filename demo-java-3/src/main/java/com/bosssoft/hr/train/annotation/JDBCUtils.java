package com.bosssoft.hr.train.annotation;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:45
 */
@Slf4j
public class JDBCUtils {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/boss_train?serverTimeZone=UTC";
    private static String username = "root";
    private static String password = "root";

    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;

    private JDBCUtils() {

    }

    /**
     * 封装一个方法用于连接
     *
     * @return
     */
    public static Connection init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            log.info("获取连接出错");
        }
        return null;
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
                log.error("数据库修改异常");
            }
        } else {
            log.info("数据库连接为空");
        }
        return result;
    }

    /**
     * 重写方法传入要处理的值
     *
     * @param sql
     * @param columns
     * @return
     */
    public static int creatOrUpdateOrDelete(String sql, String... columns) {
        int result = -1;
        if (conn != null) {
            try {
                ps = conn.prepareStatement(sql);
                for (int i = 1; i <= columns.length; i++) {
                    ps.setString(i, columns[i - 1]);
                }
                //执行
                result = ps.executeUpdate();
            } catch (SQLException e) {
                log.error("数据库修改异常");
            }
        } else {
            log.info("数据库连接为空");
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
            log.error("数据库查询异常");
        }
        return rs;
    }

    /**
     * 关闭连接
     */
    public static void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            log.error("数据库关闭异常");

        }
    }
}
