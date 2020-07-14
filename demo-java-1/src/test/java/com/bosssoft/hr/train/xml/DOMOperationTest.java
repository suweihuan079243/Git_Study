package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: Suweihuan
 * @date: 2020/7/11 16:03
 */
public class DOMOperationTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        DOMOperation domOperation=new DOMOperation();
        Student student = new Student();
        student.setId("003");
        student.setName("wangwu");
        student.setAge(20);
        student.setGender("男");
        student.setGrade("计算机四班");
        domOperation.create(student);
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
        DOMOperation domOperation=new DOMOperation();
        Student student = new Student();
        student.setId("003");
        student.setName("wangwu2");
        student.setAge(20);
        student.setGender("男");
        student.setGrade("计算机四班");
        domOperation.update(student);
    }

    @Test
    public void query() {
        DOMOperation domOperation=new DOMOperation();
        Student student=new Student();
        student.setId("001");
        Student query = domOperation.query(student);
        System.out.println(query);
    }
}
