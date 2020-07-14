package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author: Suweihuan
 * @date: 2020/7/11 15:52
 */
@Slf4j
public class DOMOperation implements XMLOperation<Student> {
    @Override
    public boolean create(Student object) {
        OutputFormat format = OutputFormat.createPrettyPrint();//缩减型格式
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        try
        {
            in = new FileInputStream("src/main/resources/students.tld");
            doc = reader.read(in);
            Element root = doc.getRootElement();   //获取xml根节点，即students节点
            Element element = root.addElement("Student");
            element.addAttribute("id",object.getId());
            element.addElement("name").addText(object.getName());
            element.addElement("age").addText(String.valueOf(object.getAge()));
            element.addElement("gender").addText(object.getGender());
            element.addElement("grade").addText(object.getGrade());

            FileOutputStream fos = new FileOutputStream("src/main/resources/students.tld");
            XMLWriter writer = new XMLWriter(fos, format);
            writer.write(doc);
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {

                if(in != null) {
                    in.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean remove(Student object) {
        
       return false;
    }

    @Override
    public boolean update(Student object) {
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        try
        {
            in = new FileInputStream("src/main/resources/students.tld");
            doc = reader.read(in);
            Element root = doc.getRootElement();
            for (Iterator it = root.elementIterator(); it.hasNext();)
            {
                Element element = (Element) it.next();
                String id = element.attributeValue("id");
                if (object.getId().equals(id))
                {
                    element.element("name").setText(String.valueOf(object.getName()));
                    element.element("age").setText(String.valueOf(object.getAge()));
                    element.element("gender").setText(object.getGender());
                    element.element("grade").setText(object.getGrade());
                    break;
                }
            }
            FileWriter writer = new FileWriter("src/main/resources/students.tld");
            doc.write(writer);
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Student query(Student object) {

        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        try
        {
            in = new FileInputStream("src/main/resources/students.tld");
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements)
            {
                if (element.attributeValue("id").equals(object.getId())){
                    Student student = new Student();
                    student.setId(object.getId());
                    student.setName(element.elementText("name"));
                    student.setAge(Integer.valueOf(element.elementText("age")));
                    student.setGender(element.elementText("gender"));
                    student.setGrade(element.elementText("grade"));
                    return student;
                }
               
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
            e.printStackTrace();
            }
        }
        return null;
    }
    
}
