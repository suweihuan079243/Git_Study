package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import org.junit.Test;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 11:18
 */
public class SAXOperationTest {
    @Test
    public void query() {
        SAXOperation saxOperation=new SAXOperation();
        saxOperation.query(new Student());
    }
}
