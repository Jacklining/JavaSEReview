package com.cuixiaobin;

import com.cuixiaobin.pojo.Employee;
import com.cuixiaobin.pojo.Users;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class T_07IO {

    /**
     * 1.File类：
     */
    public static int counts = 0;

    //1.对象操作
    @Test
    public void test() throws IOException {
//        String path = "H:\\";
//        File tempFile = File.createTempFile(path, "\\书");
//        System.out.println(tempFile);
        /**
         * File.createTempFile
         */

        File file = new File("H:\\书\\1.txt");//内存中创建
        File file2 = new File("H:\\书");
        File file3 = new File("H:\\书\\1\\axas\\axas\\222");
        System.out.println(file.getName());
        //文件夹遍历：
//        filesFind(file2);
//        System.out.println(counts);
//        traverseDirectory(file2);
        //实际创建,来源于路径是怎么写的
        file.delete();
        file.createNewFile();

        file2.mkdir();

        file3.mkdirs();


    }

    //文件夹递归1
    public static void filesFind(File file) throws IOException {

        //要用迭代栈的思维来想递归的写法，这就是从前的编程思维，好想
        //想先输出的就写在递归方法调用前面，用树的思维想问题

        if (file.exists() && file.isDirectory()) {
            int count = file.listFiles().length;
            for (int i = 0; i < count; i++) {
                filesFind(file.listFiles()[i]);
                System.out.println(file.listFiles()[i].getName());
                counts++;
            }
        }
    }

    //文件夹递归2
    public static void traverseDirectory(File directory) {
        // 检查目录是否存在
        if (directory.exists() && directory.isDirectory()) {
            // 获取目录下的所有文件和子目录
            File[] filesAndDirs = directory.listFiles();

            if (filesAndDirs != null) {
                for (File fileOrDir : filesAndDirs) {
                    if (fileOrDir.isDirectory()) {
                        // 如果是子目录，递归遍历子目录
                        System.out.println("子目录：" + fileOrDir.getAbsolutePath());
                        traverseDirectory(fileOrDir);
                    } else {
                        // 如果是文件，输出文件路径
                        System.out.println("文件：" + fileOrDir.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("目录不存在或不是一个有效的目录：" + directory.getAbsolutePath());
        }
    }


    /**
     * 2.案例在H盘里找到，原神.exe并启动他，并保存路径
     */

    @Test
    public void test2() throws IOException {

        String a = "123.txt";
        String b = "123.txt";
        System.out.println(a.equals(b));


    }


    /**
     * 3.IO流
     */

    //FileInputStream
    @Test
    public void test3() throws IOException {
//        FileInputStream fis = new FileInputStream("E:\\Program Files (x86)\\Genshin Impact Cloud Game\\Genshin Impact Cloud Game.exe");
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = fis.read(buffer))!= -1) {
//            System.out.println(new String(buffer, 0, len));
//        }
//        fis.close();

//        System.out.println("********************************");
//        FileReader reader = new FileReader("E:\\Program Files (x86)\\Genshin Impact Cloud Game\\Genshin Impact Cloud Game.exe");
//        char[] buffer1 = new char[1024];
//        int len1 = 0;
//        while ((len1 = reader.read(buffer1))!= -1) {
//            System.out.println(new String(buffer1, 0, len1));
//        }
//        System.out.println(new String(buffer1,0,len1));


        FileInputStream fileInputStream = new FileInputStream("H:\\IO\\ioen.txt");
        byte[] buffer = new byte[1024];

        int read = fileInputStream.read(buffer);//返回读取的数据个数（字节数）
        System.out.println(read);
        System.out.println(new String(buffer, 0, read));
        System.out.println(buffer[0]);
        System.out.println((char) buffer[0]);
        fileInputStream.close();

        System.out.println("**********不全读*******");
        FileInputStream fileInputStreamzh = new FileInputStream("H:\\IO\\iozh.txt");
        byte[] bytes = new byte[100];

        while (fileInputStreamzh.read(bytes) != -1) {
            System.out.println(new String(bytes, 0, fileInputStreamzh.read(bytes)));
        }


        System.out.println("**********中文全读*******");
        FileInputStream fileInputStreamzh3 = new FileInputStream("H:\\IO\\iozh.txt");
        byte[] bytes1 = new byte[1024 * 10];
        int len = 0;
        while ((len = fileInputStreamzh3.read(bytes1)) != -1) {
            System.out.println(new String(bytes1, 0, len));
        }

        //注：fileInputStreamzh3.read(bytes1)，每调用一次就会执行一次，尽量循环中只用一次，注意len=fileInputStream.read(bytes1)赋值并且作比较的操作

        System.out.println("**********中文全读*******");
        FileInputStream fileInputStreamzh4 = new FileInputStream("H:\\IO\\iozh.txt");
        byte[] bytes3 = fileInputStreamzh4.readAllBytes();
        System.out.println(new String(bytes3));

        //返回还有多少单位的数据可以输入fileInputStream.available()
        //也可以查询文件一共有多少字节（单位）

        System.out.println("********fileInputStreamzh5.available()*******");
        FileInputStream fileInputStreamzh5 = new FileInputStream("H:\\IO\\iozh.txt");

        System.out.println(fileInputStreamzh5.available());


    }

    //FileOutputStream
    @Test
    public void test4() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("H:\\IO\\outen.txt");
        FileOutputStream fileOutputStream1 = new FileOutputStream("H:\\IO\\iozh.txt");


        byte[] buffer = "cjknnenc".getBytes(StandardCharsets.UTF_8);

        fileOutputStream.write(new byte[0]);
        fileOutputStream.write(97);
        fileOutputStream.write(97);

        fileOutputStream.close();
//        fileOutputStream.flush();//内存中的数据被刷新到文件中
//        fileOutputStream.write(98,true);
//        fileOutputStream.write(99,true);
//        报错：追加不能由于是私有的内部方法，不可调用
//        可能FileOutputStream.write(97)，就是不支持追加属性的吧，不过可以将原来的数据，先读到内存中在写入。效果一样

        //程序未关闭之前都是在执行一个write方法，会在后面接着写

    }

    //bufferedInputStream与bufferedOutputStream
    @Test
    public void test5() throws IOException, ClassNotFoundException {


        //1.基本用法：

        {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));
            byte[] buffer = new byte[1024];

            int read = bufferedInputStream.read(buffer);//返回读取的数据个数（字节数）
            System.out.println(read);
            System.out.println(new String(buffer, 0, read));
            System.out.println(buffer[0]);
            System.out.println((char) buffer[0]);
            bufferedInputStream.close();

            System.out.println("**********不全读*******");
            BufferedInputStream bufferedInputStreamzh = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));
            byte[] bytes = bufferedInputStreamzh.readAllBytes();
            System.out.println(new String(bytes));

        }

        //2.mark 与 reset 与 BufferedInputStream

        {
            System.out.println("*************");
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));

            byte[] buffer1 = new byte[100];
            int read = bufferedInputStream1.read(buffer1);
            bufferedInputStream1.mark(200);
            //记录当前开始读的位置
            System.out.println(new String(buffer1, 0, read));

            read = bufferedInputStream1.read(buffer1);
            System.out.println(new String(buffer1, 0, read));

            bufferedInputStream1.reset();
            //回复上一次记录的位置
            read = bufferedInputStream1.read(buffer1);
            System.out.println(new String(buffer1, 0, read));
            bufferedInputStream1.close();

            /**
             * 输出：
             * 先帝开创的大业未完成一半却中途去世了。现在天下分为三国，蜀汉国力薄�
             * ��，处境艰难。这确实是国家危急存亡的时期啊。不过宫廷里侍从护卫的官�
             * ��，处境艰难。这确实是国家危急存亡的时期啊。不过宫廷里侍从护卫的官�
             */

        }

        //3.BufferOutputStream  write()与flush()的辨析：

        {


            /**
             * `FileOutputStream` 并不具备内置的缓冲区。它是一个基于字节的输出流，每次调用 `write()` 方法都会将数据直接写入文件，而不会经过缓冲。
             *
             * 与此不同，`BufferedOutputStream` 是一个具有缓冲区的输出流。当你使用 `BufferedOutputStream` 时，它会在内部维护一个缓冲区，并在适当的时机将数据写入文件。这可以提高写入性能，因为不是每次调用 `write()` 都立即写入文件。
             *
             * 关于 `write()` 和 `flush()` 方法的区别：
             *
             * 1. `write()` 方法用于将数据写入输出流或缓冲区。在 `FileOutputStream` 中，数据直接写入文件，而在 `BufferedOutputStream` 中，数据写入缓冲区。
             * 2. `flush()` 方法用于强制刷新缓冲区中的数据到文件。在 `FileOutputStream` 中，`flush()` 不会引发任何实际操作，因为该流没有内置缓冲区。在 `BufferedOutputStream` 中，`flush()` 将缓冲区中的数据写入文件。
             *
             * 因此，在使用 `FileOutputStream` 时，`write()` 方法将数据直接写入文件，而在使用 `BufferedOutputStream` 时，`write()` 方法将数据写入缓冲区。当你需要确保数据被立即写入文件时，可以使用 `flush()` 方法，特别是在使用 `BufferedOutputStream` 时。
             */

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("H:\\IO\\outen.txt"));
//            bufferedOutputStream.
        }


    }

    //4.序列化和反序列化
    @Test
    public void test06() throws IOException, ClassNotFoundException {

        System.out.println("*****************************");
        List<Employee> one = new ArrayList<>();
        one.add(new Employee("猪八戒", '男', 30000, 25000, null));
        one.add(new Employee("孙悟空", '男', 25000, 1000, "顶撞上司"));
        one.add(new Employee("沙僧", '男', 20000, 20000, null));
        one.add(new Employee("小白龙", '男', 20000, 25000, null));

        //单对象：

        //要实现对象的序列化一定要，对象类实现Serializable接口
        Employee employee = new Employee("小王", '男', 20000, 25000, null);
        //序列化：
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("H:\\IO\\xlh.txt"));
        objectOutputStream.writeObject(employee);
        //反序列化:
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("H:\\IO\\xlh.txt"));
        Employee o = (Employee) objectInputStream.readObject();

        System.out.println(o);

        System.out.println("**********************************");
        //多对象批量序列化与反序列化(没有判定循环跳出的指标，抛异常跳出循环)
        for (int i = 0; i < one.size(); i++) {
            objectOutputStream.writeObject(one.get(i));
        }

        ArrayList<Employee> users = new ArrayList<>();

        while (true) {

            try {
                Employee employee1 = (Employee) objectInputStream.readObject();
                users.add(employee1);
            } catch (IOException e) {
                break;
            } catch (ClassNotFoundException e) {
                break;
            }
        }

        users.forEach(e -> System.out.println(e));


    }

    //字符流
    //FileReader
    @Test
    public void test07() throws IOException {

        FileReader reader = new FileReader("H:\\IO\\iozh.txt");
        FileReader reader2 = new FileReader("H:\\IO\\ioen.txt");

        char[] chars = new char[100];
        int i = 0;
        while ((i = reader.read(chars)) != -1) {
            System.out.printf(String.valueOf(chars, 0, i));
        }
        System.out.println("");
        System.out.println("*****************************************");

        while ((i = reader2.read(chars)) != -1) {
            System.out.printf(String.valueOf(chars, 0, i));
        }

        //注意：没有追加的情况

        System.out.println("********************************");

        System.out.println(reader.getEncoding());
    }

    //FileReader
    @Test
    public void test08() throws IOException {

        FileReader reader = new FileReader("H:\\IO\\iozh.txt");
        FileReader reader2 = new FileReader("H:\\IO\\ioen.txt");

        FileWriter writer = new FileWriter("H:\\IO\\zfsc.txt");


        char[] chars = new char[100];
        int i = 0;
        String zw = "";
        String yw = "";
        while ((i = reader.read(chars)) != -1) {
            zw += new String(chars, 0, i);
        }
        System.out.println("");
        System.out.println("*****************************************");

        while ((i = reader2.read(chars)) != -1) {
            yw += new String(chars, 0, i);
        }
        System.out.println("********************************");

        writer.write(zw);
        writer.append("\n");
        writer.append(yw);
        writer.flush();


//        System.out.println(zw);
//        System.out.println(yw);


    }

    //BufferedReader 与 BufferedWriter
    //BufferedReader 类的 lines() 方法用于返回一个由输入流中的行构成的流
    //BufferedReader 类的 readline() 读不到返回null，因为返回值是String类型，不在是int类型代表读到多少字节或则字符了
    @Test
    public void test09() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("H:\\IO\\iozh.txt"));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader("H:\\IO\\iozh.txt"));
        String s = "";
        String out1 = "";
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
            out1+=s;
        }

        System.out.println("****************************************");
        bufferedReader2.lines().forEach(line -> System.out.println(line));
        bufferedReader.close();
        bufferedReader2.close();


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("H:\\IO\\BufferedW.txt"));

        bufferedWriter.write(out1);
        bufferedWriter.flush();


    }

    //字符集转换流：用于应对文件字符编码与代码编码不一致的情况,字节输入，字符输出的情况下，加上编码一致，无敌
    // 用字节流输入流提取数据，以对应编码转换为字符输入流
    // 将字节输出流转换为对应编码的字符输出流

    //chars[]转String:String.valueOf(chars, 0, len)
    //byte[]转String:new String(byte[], 0, len)
    @Test
    public void test10() throws IOException {

        InputStreamReader reader = new InputStreamReader(new FileInputStream("H:\\IO\\iozh.txt"), "UTF-8");
        char[] chars = new char[100];
        int len = 0;
        String str = "";
        while ((len = reader.read(chars))!= -1) {
            System.out.printf(String.valueOf(chars, 0, len));
            str = str + String.valueOf(chars, 0, len);
        }

        System.out.println();
        System.out.println("*********************");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));

        byte[] byte1 = new byte[100];
        int len1 = 0;
        while ((len1 = bufferedInputStream.read(byte1))!= -1) {
            System.out.printf(new  String(byte1, 0, len1));
        }

        System.out.println("********************************");

        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("H:\\IO\\123.txt"));

        writer.write(str);
        writer.flush();



    }

    //打印流
    @Test
    public void test11() throws IOException {

        Employee employee = new Employee("小王", '男', 20000, 25000, null);

        //字节：
        PrintStream printStream = new PrintStream(new FileOutputStream("H:\\IO\\print.txt"));
        printStream.printf("%d+%s+%c+%.2f\n", 123,"cascac",'A',123.456);
//        printStream.close();

        printStream.print(true);//没有输出数据，说明关闭之后，写入操作无效
        printStream.print("\n");
        printStream.print(new char[]{'a','b','c'});
        printStream.print(employee);
//        printStream.format()==printStream.printf()
        File file = new File("H:\\IO\\printWriter.txt");
        file.createNewFile();
        PrintWriter printWriter = new PrintWriter(new FileOutputStream("H:\\IO\\printWriter.txt"));
        printWriter.println("ncwenckmxkcnvxmlmwvnxmowv");
        printWriter.flush();

        System.setOut(printStream);
        System.out.println("wncwnenclmclkm");//控制台输出转移



    }


    //网络读写流：仅用于网络TCP传输的数据传递
    @Test
    public void test12() throws IOException {
        DataInputStream dataInputStream =new DataInputStream(new FileInputStream("H:\\IO\\iozh.txt"));

        // 1.readFully:感觉很鸡肋
        {
            //readFully:感觉很鸡肋
//            DataInputStream dataInputStream =new DataInputStream(new FileInputStream("H:\\IO\\iozh.txt"));
            byte[] bytes = new byte[102];
            dataInputStream.readFully(bytes);
            //读全，读不全byte数组就报错
            System.out.println(new String(bytes).trim());
            //读取指定数量的字节，直到读取完指定数量的字节或达到文件末尾。如果读取的字节数少于指定数量，则会抛出 EOFException 异常。
        }

        //2.特定数据：读取

//        3.dataInputStream.readUTF(),字符串前面编码了一个短整数，表示字符串的长度，然后根据长度读取字符串内容。
//        这意味着您无需提前知道字符串的长度，可以准确地读取它。
//        readUTF() 读取的字符串必须是以 writeUTF() 方法写入
        {

//            BufferedInputStream in = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));
//            DataOutputStream out = new DataOutputStream(new FileOutputStream("H:\\IO\\writeUTF.txt"));
//            byte[] bytes = in.readAllBytes();
//            String str = new String(bytes,0,bytes.length,Charset.forName("UTF-8")).trim();
//            System.out.println(str);
//
//            out.writeUTF(str);
//           out.flush();
//
//            String s = dataInputStream.readUTF();
//            System.out.println(s);

            System.out.println("****************************************");

            InputStreamReader in = new InputStreamReader(new FileInputStream("H:\\IO\\iozh.txt"),"UTF-8");
            char[] bytes = new char[1024];
            int len = 0;
            while (-1 != (len = in.read(bytes))){
                System.out.println(String.valueOf(bytes,0,len));
            }

            DataInputStream dis = new DataInputStream(new FileInputStream("H:\\IO\\iozh.txt"));
            System.out.println(dis.readUnsignedShort());
//            String s = dis.readUTF();
//            System.out.println(s);

        }

    }




}

class Genshen {

    public static void main(String[] args) throws IOException {

        String fileDir = "E:\\Program Files (x86)\\";
        String fileName = "Genshin Impact Cloud Game.exe";
//        "Genshin Impact Cloud Game.exe";

        new Genshen().find(new File(fileDir), fileName);

        System.out.println(listDirs);


        Runtime runtime = Runtime.getRuntime();
        runtime.exec(listDirs);


    }

    public static String listDirs = "";

    public void find(File file, String fileName) {


        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String getName = files[i].getName();

                if (getName.equals(fileName)) {
                    listDirs = files[i].getAbsolutePath();
                }
                find(files[i], fileName);

            }

        }
    }


}





