package com.cuixiaobin;

import com.cuixiaobin.pojo.Users;
import com.cuixiaobin.pojo.ads;
import org.junit.Test;

import java.lang.reflect.*;
import java.time.LocalDate;

public class T_19Reflect {

    //获取类对象的三种方式
    @Test
    public void test() throws Exception {

        //1.类名.class
        Class user = Users.class;

        //2.对象名.getclass()
        Class user1 = new Users().getClass();

        //3.Class.forName(name)  理解为工具类方法(类的全限名)

        Class user2 = Class.forName("com.cuixiaobin.pojo.Users");

        if (user == user1 && user == user2) {
            System.out.println("同一个类对象");
        }
    }

    //构造方法使用
    @Test
    public void test1() throws Exception {
        Class user = Users.class;

        Constructor[] constructors = user.getConstructors();
        System.out.println(constructors.length);
        for (Constructor c : constructors) {

            System.out.println("********getName********");
            String name = c.getName();
            System.out.println(name);

            System.out.println("***********typeParameters**********");

            TypeVariable[] typeParameters = c.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                System.out.println(name + ":" + typeParameters[i].getName() + ":" + typeParameters[i].getTypeName());
            }

            System.out.println("************getParameterCount*************");

            int parameterCount = c.getParameterCount();
            System.out.println("parameterCount:" + parameterCount);

            System.out.println("***************parameters**************");

            Parameter[] parameters = c.getParameters();
            for (Parameter p : parameters) {
                System.out.println(p.getName());

            }

            System.out.println("***************结束**************");


        }

    }

    @Test
    public void test2() throws Exception {

        Class user = Users.class;

        Constructor constructor = user.getConstructor(Integer.class, String.class, Integer.class, LocalDate.class);
        //构造方法使用是为了产生对象，而不是作用于对象
        Users zs = (Users) constructor.newInstance(1, "张三", 18, LocalDate.now());

        LocalDate birthday = zs.getBirthday();
        System.out.println(birthday);
        System.out.println(zs.toString());
    }

    //成员变量：-属性
    @Test
    public void test3() throws Exception {
        Class user = Users.class;

        Field[] fields = user.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());

        }

        Field field = fields[1];
        Constructor constructor = user.getConstructor(Integer.class, String.class, Integer.class, LocalDate.class);
        Object o = constructor.newInstance(1, "张三", 18, LocalDate.now());

        System.out.println(o);


        field.setAccessible(true);
        field.set(o, "王玉");
        System.out.println(o);

        System.out.println(field.get(o));
    }

    //方法：
    @Test
    public void test4() throws Exception {
        Class user = Users.class;

        Method[] methods = user.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        String[] s = {"神里绫华参上"};
        Method eat = user.getMethod("eat", String.class);
        eat.invoke("sa", "神里绫华参上");
        //这里好像传不传空都无所谓呀,好像是个对象就行

        Method Ieat = user.getMethod("Ieat", String.class);
        Ieat.invoke(new Users(), "神里绫华参上");

        eat.invoke("sa", s);
        //这里好像传不传空都无所谓呀,好像是个对象就行

        Ieat.invoke(new Users(), s);

    }
}
