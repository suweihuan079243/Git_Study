package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 15:26
 */
public class LinkedListExampleTest {
    LinkedListExampleImpl linkedListExample;
    User user;
    User user2;

    @Before
    public void setUp() throws Exception {
        linkedListExample = new LinkedListExampleImpl();
        user=new User();
        user2=new User();
        user.setId(1);
        user.setName("zhangsan");
        user2.setId(2);
        user2.setName("lisi");
    }

    @After
    public void tearDown() throws Exception {
        linkedListExample = null;
        user = null;
        user2 = null;
    }

    @Test
    public void append() {
        //正常添加
        assertEquals(true, linkedListExample.append(user));
        // 添加为null
        assertEquals(true, linkedListExample.append(null));
    }

    @Test
    public void get() {
        //首先插入
        assertEquals(true, linkedListExample.append(user));
        //正常查询
        assertEquals(user, linkedListExample.get(0));

        //超出范围为null
        assertNull(linkedListExample.get(-1));
    }

    @Test
    public void insert() {
        //测试正常插入
        assertEquals(true, linkedListExample.insert(0, user));
        // 测试超出范围
        assertEquals(false, linkedListExample.insert(3, user));
        assertEquals(false, linkedListExample.insert(-1, user));
    }

    @Test
    public void remove() {
        //测试正常插入
        assertEquals(true, linkedListExample.insert(0, user));
        assertEquals(true, linkedListExample.insert(1, user));

        //正常删除
        assertEquals(true, linkedListExample.remove(0));

        //超出范围
        assertEquals(false, linkedListExample.remove(-1));
        assertEquals(false, linkedListExample.remove(5));
    }

    @Test
    public void listByIndex() {
        //测试正常插入
        assertEquals(true, linkedListExample.insert(0, user));
        assertEquals(true, linkedListExample.insert(1, user));
        try {
            linkedListExample.listByIndex();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void listByIterator() {
        //测试正常插入
        assertEquals(true, linkedListExample.insert(0, user));
        assertEquals(true, linkedListExample.insert(1, user2));
        try {
            linkedListExample.listByIterator();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void toArray() {
        //测试正常插入
        assertEquals(true, linkedListExample.insert(0, user));
        assertEquals(true, linkedListExample.insert(1, user2));
        User[] users = new User[]{user, user2};
        assertArrayEquals(users, linkedListExample.toArray());
    }

    @Test
    public void sort() {
        //测试正常插入
        assertEquals(true, linkedListExample.append(user2));
        assertEquals(true, linkedListExample.append(user));

        //顺序是否正确
        linkedListExample.sort();
        assertEquals(user, linkedListExample.get(0));
        assertEquals(user2, linkedListExample.get(1));
    }
    
    @Test
    public void addFirst() {
        linkedListExample.addFirst(user);
        assertEquals(user, linkedListExample.get(0));
    }

    @Test
    public void offer() {
        linkedListExample.offer(user);
        linkedListExample.offer(user2);
        assertEquals(user2, linkedListExample.get(1));
    }

    @Test
    public void synchronizedVisit() {
        // TODO
        linkedListExample.sychronizedVisit(user);
        assertEquals(user, linkedListExample.get(0));
    }

    @Test
    public void push() {
        linkedListExample.push(user);
        assertEquals(user, linkedListExample.get(0));
    }

    @Test
    public void pop() {
        linkedListExample.push(user);
        assertEquals(user, linkedListExample.pop());
    }
}
