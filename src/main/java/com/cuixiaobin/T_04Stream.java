package com.cuixiaobin;

import com.cuixiaobin.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T_04Stream {

    //1.获得Stream流的方法
    //（1）集合类直接点：单列双列一样
    //（2）数组类
    public static int allmoney;

    @Test
    public void test() {

        //1.使用Stream.builder()创建Stream流
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Stream.Builder<Object> builder = Stream.builder();
        builder.add(1);
        builder.add(2);
        builder.add(3);
        builder.add(4);
        builder.add(5);
        Stream<Object> build = builder.build();

        build.forEach(System.out::println);

        //2.使用Stream.generate创建一个包含随机整数的无限数据流

        /**
         *  // 使用Stream.generate创建一个包含随机整数的无限数据流
         *         Stream<Integer> randomStream = Stream.generate(() -> new Random().nextInt(100));
         *
         *         // 使用limit方法限制输出元素的数量
         *         randomStream.limit(10).forEach(System.out::println);
         */

        System.out.println("********************************");
        Stream.generate(new Supplier<Object>() {
            @Override
            public Object get() {
                return new Random().nextInt(100) + 1;
            }
        }).limit(10).forEach(System.out::println);


        System.out.println("********************************");


        //3.迭代创建：
        //Stream.iterate(0, i -> i + 1).limit(100).forEach(System.out::println);

        //4.数组创建
        System.out.println("*********************************");
        Stream.of(1, 2, 3, 4, 5).forEach(System.out::println);
        int[] arrs = {1, 2, 3, 4, 5, 6};
//        Stream.of(arrs).forEach(e-> System.out.println(e.toString()));
        System.out.println("**************");

        Integer[] A = {0, 1, 2, 3, 4};
        //Steanm类/Arrays类的静态方法
        Stream<Integer> stream4 = Stream.of(A);
        Stream<Integer> stream5 = Arrays.stream(A);
        System.out.println("*****Stream.of包装类直接转换：成功****");
        stream4.forEach(e -> System.out.println(e));
        System.out.println("*****Arrays.stream包装类直接转换：成功********");
        stream5.forEach(e -> System.out.println(e));
        System.out.println("*****Arrays.stream非包装类直接转换：成功**********");
        Arrays.stream(arrs).forEach(e -> System.out.println(e));
        System.out.println("******Stream.of非包装类直接转换：失败**********");
        Stream.of(arrs).forEach(e -> System.out.println(e));

    }


    //2.练题
   @Test
    public void test2() {
       List<Employee> one = new ArrayList<>();
       one.add(new Employee("猪八戒", '男', 30000, 25000, null));
       one.add(new Employee("孙悟空", '男', 25000, 1000, "顶撞上司"));
       one.add(new Employee("沙僧", '男', 20000, 20000, null));
       one.add(new Employee("小白龙", '男', 20000, 25000, null));

       List<Employee> two = new ArrayList<>();
       two.add(new Employee("武松", '男', 15000, 9000, null));
       two.add(new Employee("李逵", '男', 20000, 10000, null));
       two.add(new Employee("西门庆", '男', 50000, 100000, "被打"));
       two.add(new Employee("潘金莲", '女', 3500, 1000, "被打"));
       two.add(new Employee("武大郎", '女', 20000, 0, "下毒"));


       /**
        * ③：分别筛选出2个部门的最高工资的员工信息，封装成优秀员工对象Topperformer
        *
        * ④：分别统计出2个部门的平均月收入，要求去掉最高和最低工资。
        *
        * ⑤：统计2个开发部门整体的平均工资，去掉最低和最高工资的平均值。
        */


       Employee employee = one.stream().max((o1, o2) -> (o1.getSalary() + o1.getBonus()) - (o2.getSalary() + o2.getBonus())).get();

       System.out.println(employee);

       Employee employee1 = two.stream().max((o1, o2) -> (o1.getSalary() + o1.getBonus()) - (o2.getSalary() + o2.getBonus())).get();
       System.out.println(employee1);

       System.out.println("******************");

       one.stream().sorted((o1, o2) -> (o1.getSalary()+o1.getBonus()) - (o2.getSalary()+o2.getBonus()))
               .limit(one.size()-1).sorted((o1, o2) -> (o2.getSalary()+o2.getBonus()) - (o1.getSalary()+o1.getBonus())).limit(one.size()-2)
               .forEach(e->{
                   allmoney = allmoney + e.getSalary() + e.getBonus();
               });

       System.out.println("平均工资："+allmoney/(one.size()-2));
       allmoney=0;

       two.stream().sorted((o1, o2) -> (o1.getSalary()+o1.getBonus()) - (o2.getSalary()+o2.getBonus())).skip(1).limit(two.size()-2)
               .forEach(e->allmoney=allmoney + e.getSalary() + e.getBonus());
       System.out.println("平均工资："+allmoney/(two.size()-2));
       allmoney=0;


       System.out.println("******************");

       Stream.concat(one.stream(),two.stream()).sorted((o1,o2) ->(o1.getBonus()+o1.getBonus())-(o2.getBonus()+o2.getBonus())).skip(1).limit(one.size()+two.size()-2)
               .forEach(e->allmoney=allmoney+e.getSalary()+e.getBonus());
       System.out.println("平均工资："+allmoney/(two.size()+one.size()-2));


       List<Employee> collect = one.stream().collect(Collectors.toList());

       System.out.println("**********************************");
       collect.forEach(e-> System.out.println(e));

   }

}
