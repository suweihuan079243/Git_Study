package com.bosssoft.hr.train.annotation;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:30
 */
@Slf4j
public class BaseModel {
    /**
     * 数据库列名对属性字段名转换
     */
    private Map<String, String> colToField = new HashMap<>();
    /**
     * 表名
     */
    private String tableName = null;
    /**
     * 主键名
     */
    private String primaryKey = null;
    /**
     * 所有列名
     */
    private StringBuilder allColumns = new StringBuilder();


    public Map<String, String> analysisAnnotation() {
        //初始化大小
        Map<String, String> map = new LinkedHashMap<>(20);
        try {
            //获取Annotation的Class对象
            Class<?> cls = this.getClass();
            //首先获取tableName 考虑没标注解情况的话
            boolean isTable = cls.isAnnotationPresent(Table.class);
            if (isTable) {
                tableName = cls.getAnnotation(Table.class).value();
            } else {
                log.error("缺少@Table注解");
                return null;
            }
            //获取类中的字段
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                //设置访问性
                field.setAccessible(true);
                //获取注解 判断注解类型默认每个字段标注一个注解
                boolean isId = field.isAnnotationPresent(Id.class);
                boolean isColumn = field.isAnnotationPresent(Column.class);
                if (isId) {
                    primaryKey = field.getAnnotation(Id.class).value();
                    //主键的值
                    map.put(primaryKey, field.get(this).toString());
                    allColumns.append(primaryKey + ",");
                    //列名到字段映射
                    colToField.put(primaryKey, field.getName());
                } else if (isColumn) {
                    String column = field.getAnnotation(Column.class).value();
                    //添加普通的列和他们对应的value 相同覆盖
                    map.put(column, field.get(this).toString());

                    allColumns.append(column + ",");
                    //列名到字段映射
                    colToField.put(column, field.getName());
                } else {
                    //没有标注注解不做处理
                }
            }
            //去掉多余","
            allColumns = new StringBuilder(allColumns.substring(0, allColumns.lastIndexOf(",")));
            return map;
        } catch (Exception e) {
            log.error("反射解析异常:{}", e);
        }
        return null;
    }


    public int save() {
        Map<String, String> map = analysisAnnotation();
        // 下面是为了获取列名
        int numColumns = allColumns.toString().split(",").length;
        StringBuilder preSql = new StringBuilder();
        for (int i = 0; i < numColumns; i++) {
            preSql.append("?");
            if (i < numColumns - 1) {
                preSql.append(",");
            }
        }
        String sql = "insert into " + tableName + "(" + allColumns + ")" + " values(" + preSql + ")";
        Connection conn = JDBCUtils.init();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String value = entry.getValue();
                ps.setString(++index, value);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            log.error("数据库语句执行异常:{}", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.error("数据库关闭异常:{}", e);
                }
            }
            JDBCUtils.closeConn();
        }
        return 0;
    }

    public int update() {
        Map<String, String> map = analysisAnnotation();
        String[] columns = allColumns.toString().split(",");
        StringBuilder sql = new StringBuilder("update " + tableName + " set ");
        //过滤掉id
        for (int i = 0; i < columns.length; i++) {
            if (!primaryKey.equals(columns[i])) {
                sql.append(columns[i] + "=? ,");
            }
        }
        //最后会多一个逗号
        sql = new StringBuilder(sql.substring(0, sql.lastIndexOf(",")));
        sql.append("where " + primaryKey + " = ? ");
        Connection conn = JDBCUtils.init();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql.toString());
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                //如果是主键放在最后
                if (primaryKey.equals(key)) {
                    System.out.println(key + "::::" + value);
                    ps.setString(columns.length, value);
                } else {
                    ps.setString(++index, value);
                }
            }
            //执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            log.error("数据库更新异常:{}", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.error("ps关闭异常:{}", e);
                }
            }
            JDBCUtils.closeConn();
        }
        return 0;
    }


    public int remove() {
        Map<String, String> map = analysisAnnotation();
        String id = map.get(primaryKey);
        String sql = "delete from " + tableName + " where " + primaryKey + " =  ? ";
        //初始化数据库
        JDBCUtils.init();
        try {
            //执行sql
            return JDBCUtils.creatOrUpdateOrDelete(sql, id);
        } finally {
            //关闭连接
            JDBCUtils.closeConn();
        }
    }


    public List<Object> queryForList() throws SQLException {
        analysisAnnotation();
        String sql = "select *  from " + tableName + ";";

        ResultSet resultSet = JDBCUtils.select(sql);
        List<Object> result = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            User user = new User();
            user.setID(id);
            user.setName(name);
            user.setAge(age);
            result.add(user);
        }
        return result;
    }
}
