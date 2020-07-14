package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.collectionframwork.StackExampleImpl;
import com.bosssoft.hr.train.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class StackExampleImplTest {
    StackExampleImpl stackExample;
    Stack<User> users;
    User user;
    User firstUser;
    @Before
    public void setUp() throws Exception {
        stackExample = new StackExampleImpl();
        firstUser=new User();
        firstUser.setId(1);
        firstUser.setName("zhangsan");
        user=new User();
        user.setId(2);
        user.setName("lisi");
        // 默认添加一个
        users=new Stack<>();
        users.push(firstUser);
        stackExample.setUsers(users);
    }

    @After
    public void tearDown() throws Exception {
        stackExample = null;
    }

    @Test
    public void push() {
        assertEquals(user,stackExample.push(user));
    }

    @Test
    public void pop() {
        assertEquals(firstUser,stackExample.pop());
    }

    @Test
    public void peek() {
        assertEquals(firstUser,stackExample.peek());
    }

    @Test
    public void empty() {
        //开始不为空
        assertFalse(stackExample.empty());
        // 删掉一个
        assertEquals(firstUser,stackExample.pop());
        //应该为空
        assertTrue(stackExample.empty());
    }

    @Test
    public void search() {
        // 存在  从1开始
        assertEquals(1,stackExample.search(firstUser));
        // 不存在
        assertEquals(-1,stackExample.search(user));
    }
}