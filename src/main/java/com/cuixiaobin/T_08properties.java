package com.cuixiaobin;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class T_08properties {

        @Test
    public void test() throws IOException {

            //可以理解成properties就是一个map集合，该怎么操作就怎么操作
            //  操作系统的路径用反斜杠\,网页路径与项目路径用正斜杠/
            //  相对路径是针对项目名称的路径，从src开始
            Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream("src/main/resources/users.properties"), "UTF-8"));
            properties.stringPropertyNames().forEach(e -> System.out.println(properties.getProperty(e)));

//            properties.compute() 是计算方法，他会用k与v施加计算，返回一个新v与k对应
//            properties.merge() 合并方法，引入一个新的value值，新v与旧v进行运算得出另一个v与k对应

            System.out.println("*************************");
            properties.forEach((k, v) -> System.out.println(k + "=" + v));

            //存文件store方法，就是map的put方法

            Properties properties1 = new Properties();

            //可以用put直接向缓存中添加数据
            properties1.put("id", "001");
            properties1.put("name", "zhangsan");
            properties1.put("age", "18");
            properties1.put("birthday", "2021-10-21 12:12:12");
            //store方法会把map集合中的数据写入到文件中，类似IO流的write/flush方法
            properties1.store(new OutputStreamWriter(new FileOutputStream("src/main/resources/text.properties"), "UTF-8"), "一个学生的简介");

            Properties properties2 = new Properties();
            properties2.load(new InputStreamReader(new FileInputStream("src/main/resources/text.properties"), "UTF-8"));
            properties2.forEach((k, v) -> System.out.println(k + "=" + v));
            //注意内存中输出的时间是不带斜杠的，但是properties1的输出时间是有反斜杆的，不过问题不大
            System.out.println("*************************");





        }


}
