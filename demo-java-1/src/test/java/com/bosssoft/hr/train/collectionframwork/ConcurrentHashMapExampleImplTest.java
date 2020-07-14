package com.bosssoft.hr.train.collectionframwork;


import com.bosssoft.hr.train.pojo.Resource;
import com.bosssoft.hr.train.pojo.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConcurrentHashMapExampleImplTest {
    ConcurrentHashMapExampleImpl concurrentHashMapExample;
    Role role;
    Resource resource;

    @Before
    public void setUp() throws Exception {
        concurrentHashMapExample = new ConcurrentHashMapExampleImpl();
        role = new Role(1, "boss");
        resource = new Resource(1, "all");
    }

    @After
    public void tearDown() throws Exception {
        concurrentHashMapExample = null;
        role = null;
        resource = null;
    }

    // concurrentHashMap 当key为null时 报错空指针
    @Test(expected = NullPointerException.class)
    public void put() {
        // 需要了解 put返回值的内容
        concurrentHashMapExample.put(null, null);

        //第一次应为null
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));

        Resource resource = concurrentHashMapExample.put(this.role, this.resource);
        assertEquals(this.resource, resource);
    }

    @Test
    public void remove() {
        //第一次应为null
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));
        //删除
        assertEquals(this.resource, concurrentHashMapExample.remove(role));

        //删除不存在返回null
        assertEquals(null, concurrentHashMapExample.remove(new Role()));

    }

    @Test
    public void containsKey() {
        //第一次应为null
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));

        assertTrue(concurrentHashMapExample.containsKey(role));

        assertFalse(concurrentHashMapExample.containsKey(new Role()));

    }

    @Test
    public void visitByEntryset() {
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));

        concurrentHashMapExample.visitByEntryset();

    }

    @Test
    public void visitByEntrySetLambda() {
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));
        concurrentHashMapExample.visitByEntrySetLambda();
    }

    @Test
    public void visitByKeyset() {
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));
        concurrentHashMapExample.visitByKeyset();
    }

    @Test
    public void visitByValues() {
        assertEquals(null, concurrentHashMapExample.put(this.role, this.resource));
        concurrentHashMapExample.visitByValues();
    }
}