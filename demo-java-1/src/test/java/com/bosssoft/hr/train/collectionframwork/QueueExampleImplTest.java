package com.bosssoft.hr.train.collectionframwork;

import com.bosssoft.hr.train.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueueExampleImplTest {
    QueueExampleImpl<User> queueExample;
    User user;

    @Before
    public void setUp() throws Exception {
        queueExample = new QueueExampleImpl<>();
        user = new User();
        user.setId(1);
        user.setName("zhangsan");
    }

    @After
    public void tearDown() throws Exception {
        queueExample = null;
        user = null;
    }

    @Test
    public void add() {
        assertTrue(queueExample.add(user));
    }

    @Test
    public void offer() {
        assertTrue(queueExample.offer(user));
    }

    @Test
    public void remove() {
        assertTrue(queueExample.offer(user));
        assertEquals(user,queueExample.remove());
    }

    @Test
    public void poll() {
        assertTrue(queueExample.offer(user));
        assertEquals(user,queueExample.poll());
    }

    @Test
    public void element() {
        assertTrue(queueExample.offer(user));
        assertEquals(user,queueExample.element());
    }

    @Test
    public void peek() {
        assertTrue(queueExample.offer(user));
        assertEquals(user,queueExample.peek());
    }
}