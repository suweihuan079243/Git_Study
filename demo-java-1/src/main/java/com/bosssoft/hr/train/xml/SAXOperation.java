package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 11:13
 */
public class SAXOperation implements XMLOperation<Student> {
    @Override
    public boolean create(Student object) {
        return false;
    }

    @Override
    public boolean remove(Student object) {
        return false;
    }

    @Override
    public boolean update(Student object) {
        return false;
    }

    @Override
    public Student query(Student object) { 
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new File("src/main/resources/students.tld"), new MyDefaultHandler());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class MyDefaultHandler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        System.out.println("----文档开始解析-------");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("<" + qName);

        for(int i = 0; i < attributes.getLength(); i++) {
            System.out.print(" " + attributes.getQName(i) + "=" + attributes.getValue(i));
        }
        System.out.print(">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</" + qName + ">");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\n----文档开始解析结束-----");
    }
}
