package com.bosssoft.hr.train.annotation;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:56
 */
public class BaseModelTest {
    
    User user=new User();

    @Before
    public void setUp(){
        user.setID(300L);
        user.setName("swh");
        user.setAge(20);
    }
    
    @Test
    public void testQuery() throws SQLException {
        System.out.println(user.queryForList());
    }
    
    @Test
    public void testSave(){
        user.save();
    }
    
    @Test
    public void testUpdate(){
        user.setName("swh2");
        user.update();
    }
    @Test
    public void testRemove(){
        user.remove();
    }
    
    
}
