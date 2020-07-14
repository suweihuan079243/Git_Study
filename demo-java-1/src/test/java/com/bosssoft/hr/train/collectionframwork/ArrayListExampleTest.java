package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 14:08
 */
public class ArrayListExampleTest {
    ArrayListExampleImpl arrayListExample;
    User user;
    @Before
    public void setUp() throws Exception {
        arrayListExample = new ArrayListExampleImpl();
        user=new User();
        user.setId(1);
        user.setName("zhangsan");
    }

    @After
    public void tearDown() throws Exception {
        arrayListExample = null;
        user=null;
    }

    @Test
    public void append() {
        assertEquals(true, arrayListExample.append(user));
    }

    @Test
    public void get() {
        //首先插入
        assertEquals(true, arrayListExample.append(user));
        assertEquals(user, arrayListExample.get(0));
    }

    @Test
    public void insert() {
        assertEquals(true, arrayListExample.insert(0, user));
    }

    @Test
    public void remove() {
        assertEquals(true, arrayListExample.insert(0, user));
        assertEquals(true, arrayListExample.insert(1, user));

        
        assertEquals(true, arrayListExample.remove(0));
    }

    @Test
    public void listByIndex() {
        assertEquals(true, arrayListExample.insert(0, user));
        assertEquals(true, arrayListExample.insert(1, user));
        try {
            arrayListExample.listByIndex();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void listByIterator() {
        assertEquals(true, arrayListExample.insert(0, user));
        try {
            arrayListExample.listByIterator();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void toArray() {
        assertEquals(true, arrayListExample.insert(0, user));
        User[] users = new User[]{user};
        assertArrayEquals(users, arrayListExample.toArray());
    }

    @Test
    public void sort() {
        User user2=new User();
        user2.setId(2);
        user2.setName("lisi");
        assertEquals(true, arrayListExample.append(user2));
        assertEquals(true, arrayListExample.append(user));

        //顺序是否正确
        arrayListExample.sort();
        assertEquals(user, arrayListExample.get(0));
    }
}
