package com.cuixiaobin;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;

import java.io.FileReader;
import java.util.List;

public class T_09XML {

    @Test
    public void test_01() throws Exception {

        SAXReader saxReader = new SAXReader();
//SAXWriter : Simple API for XML
        Document read = saxReader.read("src/main/resources/book.xml");

        Element rootElement = read.getRootElement();

        rootElement.getText();

//        System.out.println(rootElement.getText());//前后空格和换行键都会打印出来
        System.out.println(rootElement.attributeValue("id"));//节点的属性值
        System.out.println(rootElement.getName());//标签名
        System.out.println(rootElement.getNamespacePrefix());//命名空间前缀


        SAXReader reader= new SAXReader();

        Document read1 = reader.read("src/main/resources/book2.xml");

        System.out.println(read1.getRootElement().getName());
        System.out.println("561516166");
        System.out.println(read1.getRootElement().elements().get(0));
        DefaultElement defaultElement= (DefaultElement) read1.getRootElement().elements().get(0);

        System.out.println(defaultElement.getName());
        System.out.println("***************************");
        Element rootElement1 = read1.getRootElement();
        System.out.println(rootElement1.getNamespace().getPrefix());
        System.out.println(rootElement1.getNamespace().getName());
        System.out.println(rootElement1.elements().size());
        rootElement1.elements().forEach(e -> System.out.println(e.toString()));


    }

    @Test
    public void test_02() throws Exception {

        // 1、创建一个Dom4J框架提供的解析器对象
        SAXReader saxReader = new SAXReader();

        // 2、使用saxReader对象把需要解析的XML文件读成一个Document对象。
        Document document =
                saxReader.read("src/main/resources/helloworld.xml");

        // 3、从文档对象中解析XML文件的全部数据了
        Element root = document.getRootElement();
        System.out.println(root.getName());

        // 4、获取根元素下的全部一级子元素。
        // List<Element> elements = root.elements();
        List<Element> elements = root.elements("user");
        for (Element element : elements) {
            System.out.println(element.getName());
        }

        // 5、获取当前元素下的某个子元素。
        Element people = root.element("people");
        System.out.println(people.getText());

        // 如果下面有很多子元素user，默认获取第一个。
        Element user = root.element("user");
        System.out.println(user.elementText("name"));

        // 6、获取元素的属性信息呢？
        System.out.println(user.attributeValue("id"));
        Attribute id = user.attribute("id");
        System.out.println(id.getName());
        System.out.println(id.getValue());

        List<Attribute> attributes = user.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getName() + "=" + attribute.getValue());
        }

        // 7、如何获取全部的文本内容:获取当前元素下的子元素文本值
        System.out.println(user.elementText("name"));
        System.out.println(user.elementText("地址"));
        System.out.println(user.elementTextTrim("地址")); // 取出文本去除前后空格
        System.out.println(user.elementText("password"));

        Element data = user.element("data");
        System.out.println(data.getText());
        System.out.println(data.getTextTrim()); // 取出文本去除前后空格
    }

}
