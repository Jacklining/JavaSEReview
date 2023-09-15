package com.cuixiaobin;

import com.cuixiaobin.pojo.Users;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//集合章节复习
public class T_03Collection {

    /**
     * 集合的基本划分：
     * 1.单列集合（大都可变）
     * list(有序，有索引，可重复)
     * Arraylist
     * linkedlist
     * vector
     * set（无序，无索引，不可重复）
     * hashSet
     * LinkedSet（有序，无索引，不可重复）
     * TreeSet（排序，无索引，不可重复）
     * <p>
     * 2.双列集合（大都可变）
     * Map（无序，无索引，不可重复）
     * HashMap
     * LinkededMap（有序，无索引，不可重复）
     * TreeMap（排序，无索引，不可重复）
     * <p>
     * <p>
     * 3.集合工具类
     * Collection
     * Iterator
     * Arrays
     */

    /**
     * 遍历的几种方式：
     * 1.迭代器  : 适用于list与set
     * 2.增强for循环 ：适用于list与set
     * 3.Lambda表达式 ：
     */


    //1.集合工具类 Collection对象都实现迭代器接口
    @Test
    public void apis() throws ClassNotFoundException {


        ArrayList arrayList = new ArrayList();
        arrayList.add("123");
        arrayList.add(123);
        arrayList.add(12.12);
        arrayList.forEach(e -> System.out.println(e.getClass().toString()));

        System.out.println("/****************************/");


        {
            Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
            Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
            Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
            Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
            Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
            ArrayList<Users> users = new ArrayList<>();
        }

        //checkedList:返回一个受监测的结合，严格控制集合中元素的类型

        {
            List list = Collections.checkedList(arrayList, Class.forName("java.lang.String"));

            try {
                list.add(Integer.valueOf(10));
            } catch (Exception e) {
                System.out.println("类型传入有问题");
            }
            System.out.println("****************************");
            //直接声明泛型方法的方式：也报错，感觉Collections.checkedList没有什么卵用
            ArrayList<String> list1 = new ArrayList<String>();

            list1.add("4313");
        }

        //Collections.disjoint(listBJ,set) 传入两个任意类型的集合，判断其元素是否相交
        {
            Set<Integer> set = new HashSet<Integer>();
            set.add(Integer.valueOf(10));

            ArrayList<Integer> listBJ = new ArrayList<>();
            listBJ.add(Integer.valueOf(10));

            System.out.println(Collections.disjoint(listBJ, set));

        }


        //Collections.enumeration()集合转换成枚举，以便在不支持迭代器的情况下遍历集合元素。
        //Collections.list(Enumeration e) 与之对应，相互转化
        {
            System.out.println("**************************");
            HashSet<Integer> set = new HashSet<Integer>();
            set.add(1);
            set.add(2);
            Enumeration<Object> enumeration = Collections.enumeration(Collections.singleton(set));
            while (enumeration.hasMoreElements()) {

                System.out.println(enumeration.nextElement());

            }

        }

        // Collections.copy(a,integers),覆盖函数，copy数组还不能size小于src数组
        {
            System.out.println("***************************");
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);

            ArrayList<Integer> a = new ArrayList<>();
            a.add(6);
            a.add(1);
            a.add(5);
            a.add(5);
            a.add(5);
            Collections.copy(a, integers);
            integers.forEach(e -> System.out.println(e));

        }

        // Collections.frequency 统计集合中元素出现的频率
        {
            System.out.println("****************************");
            ArrayList<Integer> a = new ArrayList<>();
            a.add(6);
            a.add(1);
            a.add(5);
            a.add(5);
            a.add(5);

            Set<Integer> set = new HashSet<>();
            set.add(Integer.valueOf(10));
            set.add(Integer.valueOf(11));
            set.add(Integer.valueOf(10));
            set.add(Integer.valueOf(10));
            set.add(Integer.valueOf(10));
            set.add(Integer.valueOf(10));

            int frequency = Collections.frequency(a, 5);
            System.out.println(frequency);
            System.out.println(Collections.frequency(set, 11));

        }

        //max,min，addall
        //Collections.fill(T t) 填充方法，不是添加方法，填充是用一个元素覆盖掉所有的
        //原集合中的元素
        {
            System.out.println("*************************************");
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);

            ArrayList<Integer> a = new ArrayList<>();
            a.add(6);
            a.add(1);
            a.add(5);
            a.add(5);
            a.add(5);

            Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
            Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
            Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
            Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
            Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
            ArrayList<Users> users = new ArrayList<>();

            Collections.addAll(users, users1, users2, users3, users4, users5);

            Users maxage = Collections.max(users, ((o1, o2) -> o1.getAge() - o2.getAge()));

            System.out.println(maxage);

            System.out.println(Collections.min(users, ((o1, o2) -> o1.getId() - o2.getId())));
            System.out.println("******************************************");
            Collections.fill(users, new Users(6, "f", 26, LocalDate.of(2000, 12, 30)));
            users.forEach(e -> {
                System.out.println(e);
                System.out.println(e.hashCode());
            });


        }

        //Collections.indexOfSubList(list src,list target)
        //返回src中第一次出现target的下标索引(要有顺序),只能添加list，set与map不可以
        //Collections.lastIndexOfSubList(a,arrayList1)同理，都是返回出现的最左侧的索引
        {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);

            ArrayList<Integer> a = new ArrayList<>();
            a.add(6);
            a.add(1);
            a.add(5);
            a.add(5);
            a.add(5);

            Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
            Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
            Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
            Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
            Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
            ArrayList<Users> users = new ArrayList<>();

            System.out.println("*************************************");
            System.out.println(a);
            ArrayList<Integer> arrayList1 = new ArrayList<>();
            arrayList1.add(1);
            arrayList1.add(5);

            System.out.println("first：" + Collections.indexOfSubList(a, arrayList1));
            System.out.println("last：" + Collections.lastIndexOfSubList(a, arrayList1));

            System.out.println("*************************************");
            Set<Integer> set = new HashSet<>();
            set.add(1);
            set.add(5);
            set.add(2);
            set.add(6);
            set.add(7);

            Set set2 = new HashSet();
            set.add(1);
            set.add(5);
            set.add(2);
            set.add(6);
            set.add(7);

//            System.out.println(Collections.indexOfSubList(set,arrayList1));
//            System.out.println(Collections.indexOfSubList(set,arrayList1));


        }

        //Collections.nCopies(int n, T o) 创建一个包含 n 个相同元素 o 的不可变列表（Immutable List）

//      //简单回答：Collections.newSetFromMap() 用于创建一个基于指定 Map 的 Set，其中 Map 的键作为元素，用 Boolean 值表示元素的存在。
        //这个方法在某些情况下非常有用，特别是当你需要将一个 Map 视为一个 Set 时，或者需要操作一个具有特殊要求的 Set 时。
        //连体婴儿
        {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);

            ArrayList<Integer> a = new ArrayList<>();
            a.add(6);
            a.add(1);
            a.add(5);
            a.add(5);
            a.add(5);

            Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
            Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
            Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
            Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
            Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
            ArrayList<Users> users = new ArrayList<>();

            Map<String, Boolean> map = new HashMap<>();
//            map.put("1",true);
//            map.put("2",false);
//            map.put("3",true);


            System.out.println("***************");
            List<Users> users6 = Collections.nCopies(5, users1);
            users6.forEach(e -> System.out.println(e));

            System.out.println("***************");
            Set<String> es = Collections.newSetFromMap(map);
            es.add("a");
            es.add("b");
            es.add("c");
            System.out.println(es);
            System.out.println(map);

        }

        //Collections.replaceAll(list src,T o1,T o2)  只能用于list
        //返回一个新的 list，其中每个等于o1元素都被替换为 o2

        //Collections.rotate(integers, 2); 向右旋转两位
        //Collections.rotate(integers, -2); 向左旋转两位
        {

            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);

            ArrayList<Integer> a = new ArrayList<>();
            a.add(6);
            a.add(1);
            a.add(5);
            a.add(5);
            a.add(5);

            Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
            Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
            Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
            Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
            Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
            ArrayList<Users> users = new ArrayList<>();
            Collections.addAll(users, users1, users2, users3, users4, users5);
            System.out.println("************************");
            Set<Integer> usersSet = new HashSet<>();
            usersSet.addAll(integers);
//            Collections.replaceAll(usersSet,5,6);

            System.out.println("*******************");
            Collections.replaceAll(users, users1, new Users(6, "f", 25, LocalDate.of(2025, 10, 12)));
            System.out.println(users);

            System.out.println("*******************");
            System.out.println(integers);
            Collections.rotate(integers, 2);
            System.out.println(integers);
            Collections.rotate(integers, -2);
            System.out.println(integers);
            Collections.rotate(integers, -2);
            System.out.println(integers);

            /**
             * [1, 2, 3, 4, 5]
             * [4, 5, 1, 2, 3]
             * [1, 2, 3, 4, 5]
             * [3, 4, 5, 1, 2]
             */


        }

        //Collections.shuffle(list) 随机打乱list
        {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(1);
            a.add(2);
            a.add(3);
            a.add(4);
            a.add(5);


            Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
            Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
            Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
            Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
            Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
            ArrayList<Users> users = new ArrayList<>();
            Collections.addAll(users, users1, users2, users3, users4, users5);

            Collections.shuffle(users);
            Collections.shuffle(a);

            users.forEach(e -> System.out.println(e));
            System.out.println(a);
        }


        //  Collections.swap(integers,0,4); 参数是索引值
        {
            System.out.println("*******************");
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            integers.add(5);
            Collections.swap(integers, 0, 4);
            integers.forEach(e -> System.out.println(e));
        }


    }

    //2.集合工具类 Iterator,Collection。
    @Test
    public void Iteratortest() {
        //常用方法：hasnext（）与next()

        {
            ArrayList arrayList = new ArrayList();
            Iterator iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();

            Set set = new HashSet();
            set.add(1);
            set.add(2);
            set.add(3);
            set.add(4);
            set.add(5);

            System.out.println("*****************");
            Iterator it = set.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }


            Map map = new HashMap();
//            map.
        }

    }

    //3.list类方法
    @Test
    public void lsittest() {

        Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
        Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
        Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
        Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
        Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
        ArrayList<Users> users = new ArrayList<>();
        Collections.addAll(users, users1, users2, users3, users4, users5);

        //1.clone方法：浅拷贝，只复制集合的引用和集合内部的引用，
        // 不复制元素的值，改值两者都变（）针对对象是可变的类型
        {

            ArrayList<Users> clone = (ArrayList) users.clone();

            clone.get(0).setId(Integer.valueOf(6));

            System.out.println(clone);
            System.out.println(users);

        }

        //2.users.retainAll()保留该集合中在另一个集合中出现的元素
        {
            System.out.println("--------------------------------");
            ArrayList<Integer> number = new ArrayList<Integer>();
            Collections.addAll(number, 1, 2, 2, 3, 4, 3, 4, 5);

            ArrayList<Integer> number2 = new ArrayList<Integer>();
            Collections.addAll(number2, 2, 3, 5);


            number.retainAll(number2);

            number.forEach(e -> System.out.println(e));

            //2
            //2
            //3
            //3
            //5

        }

        //3.users.removeAll()移除该集合中在另一个集合中出现的元素
        //users.indexOf() 找索引
        //users.lastIndex()
        //4.users.clear() 清空所有元素
        {
            System.out.println("--------------------------------");
            ArrayList<Integer> number = new ArrayList<Integer>();
            Collections.addAll(number, 1, 2, 2, 3, 4, 3, 4, 5);

            number.clear();
            System.out.println(number);

        }

        //4.List.of(*****)得到不可变的集合
        {
            System.out.println("--------------------------------");
            ArrayList<Integer> number = new ArrayList<Integer>();
            Collections.addAll(number, 1, 2, 2, 3, 4, 3, 4, 5);

            List integers = List.of(1, 2, 3, 4, 5);
            integers.forEach(e -> System.out.println(e));
            System.out.println(integers.getClass().getName());

            try {
                integers.add(6);
                System.out.println(integers);
            } catch (Exception e) {
                System.out.println("操作错误，不可变集合");
            }
        }

        //5.List.copyOf(Collection collection)
        //创建一个不可变的（immutable）列表，
        // 该列表包含指定集合的元素。不可变列表是一旦创建就不能被修改的，
        // 任何尝试修改不可变列表的操作都会抛出 UnsupportedOperationException 异常。
        {
            System.out.println("--------------------------------");
            ArrayList<Integer> number = new ArrayList<Integer>();
            Collections.addAll(number, 1, 2, 2, 3, 4, 3, 4, 5);

            List<Users> copyOf = List.copyOf(users);

            copyOf.get(0).setId(Integer.valueOf(6));

            try {
                copyOf.add(new Users(6, "f", 25, LocalDate.of(2025, 12, 21)));
                System.out.println("没有执行");
            } catch (Exception e) {
                System.out.println("列表不可变");
            }

            System.out.println(copyOf);
            System.out.println(users);
        }
    }

    //4.ArrayList 方法
    @Test
    public void ArrayListtest() {
        Users users1 = new Users(1, "a", 21, LocalDate.of(2000, 12, 21));
        Users users2 = new Users(2, "b", 22, LocalDate.of(2000, 12, 22));
        Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
        Users users4 = new Users(4, "d", 24, LocalDate.of(2000, 12, 24));
        Users users5 = new Users(5, "e", 25, LocalDate.of(2000, 12, 25));
        ArrayList<Users> users = new ArrayList<>();
        Collections.addAll(users, users1, users2, users3, users4, users5);

        //1.将此实例的容量修剪为列表的 ArrayList 当前大小
        {
            users.trimToSize();

        }


    }

    //5.LinkedList  方法
    @Test
    public void LinkedListtest() {
        Users users1 = new Users(1, "a", 25, LocalDate.of(2000, 12, 21));
        Users users2 = new Users(2, "b", 24, LocalDate.of(2000, 12, 22));
        Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
        Users users4 = new Users(4, "d", 22, LocalDate.of(2000, 12, 24));
        Users users5 = new Users(5, "e", 21, LocalDate.of(2000, 12, 25));
        LinkedList<Users> users = new LinkedList<>();
        Collections.addAll(users, users1, users2, users3, users4, users5);

        String s = "2012-12-12-12-12-12";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.parse(s, dateTimeFormatter);
        System.out.println(localDateTime);

        System.out.println("********************************");


    }

    //6.TreeSet
    @Test
    public void testTreeSet(){
        System.out.println("********************************");


        Users users1 = new Users(1, "a", 25, LocalDate.of(2000, 12, 21));
        Users users2 = new Users(2, "b", 24, LocalDate.of(2000, 12, 22));
        Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
        Users users4 = new Users(4, "d", 22, LocalDate.of(2000, 12, 24));
        Users users5 = new Users(5, "e", 21, LocalDate.of(2000, 12, 25));

        //TreeSet<Users> Treeeusers = new TreeSet<Users>();
        TreeSet<Users> treeSet = new TreeSet<Users>((u1, u2) -> u1.getAge().compareTo(u2.getAge()));
        treeSet.add(users1);
        treeSet.add(users2);
        treeSet.add(users3);
        treeSet.add(users4);
        treeSet.add(users5);

        System.out.println(treeSet);

        System.out.println("--------------------------------");

        Iterator<Users> users = treeSet.iterator();
        while(users.hasNext()){
            System.out.println(users.next());
        }

        System.out.println("-----倒序输出-----------");

        Iterator<Users> usersIterator = treeSet.descendingIterator();
        while(usersIterator.hasNext()){
            System.out.println(usersIterator.next());
        }

        System.out.println("-------取第一个--------");
        Users users6 = treeSet.pollFirst();
        System.out.println(users6);

    }


    //7.Map
    @Test
    public void testMap(){

        Users users1 = new Users(1, "a", 25, LocalDate.of(2000, 12, 21));
        Users users2 = new Users(2, "b", 24, LocalDate.of(2000, 12, 22));
        Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
        Users users4 = new Users(4, "d", 22, LocalDate.of(2000, 12, 24));
        Users users5 = new Users(5, "e", 21, LocalDate.of(2000, 12, 25));

        Map<Integer, Users> map = new HashMap<>();
       map.put(1, users1);
       map.put(2, users2);
       map.put(3, users3);
       map.put(4, users4);
       map.put(5, users5);

        System.out.println("**********遍历1:lambda表达式**************");
        map.forEach((k, v) -> System.out.println(k + " " +v));

        System.out.println("**********遍历2 :取整体**************");
        Set<Map.Entry<Integer, Users>> entries = map.entrySet();
        entries.stream().forEach(e-> System.out.println(e.getKey()+":"+e.getValue()));


        System.out.println("**********遍历3：k找value**************");
        Set<Integer> integers = map.keySet();
        integers.stream().forEach(e -> System.out.println(e+":"+map.get(e)));

        System.out.println("**********merge**************");

        /**
         * 好的，让我们通过一个比方来说明 `map.merge()` 方法的意思。
         *
         * 假设你有一个成绩单，其中记录了学生的成绩。你想要将学生的成绩添加到成绩单中，但是如果同一个学生已经有了一个成绩，你想要将新的成绩与旧的成绩相加，而不是覆盖旧的成绩。
         *
         * 现在，让我们使用 `map.merge()` 来完成这个任务：
         *
         * ```java
         * import java.util.HashMap;
         * import java.util.Map;
         *
         * public class Main {
         *     public static void main(String[] args) {
         *         Map<String, Integer> scoreMap = new HashMap<>();
         *
         *         // 假设学生 "Alice" 考了数学，得了 90 分
         *         scoreMap.put("Alice", 90);
         *
         *         // 现在学生 "Alice" 又考了一次数学，得了 85 分
         *         // 我们想要将新的成绩与旧的成绩相加，而不是覆盖
         *         scoreMap.merge("Alice", 85, (oldScore, newScore) -> oldScore + newScore);
         *
         *         // 打印学生成绩单
         *         System.out.println("学生成绩单：" + scoreMap);
         *     }
         * }
         * ```
         *
         *
         * */

    }

    //8.HashMap

    @Test
    public void hashMap() throws Exception {
        Users users1 = new Users(1, "a", 25, LocalDate.of(2000, 12, 21));
        Users users2 = new Users(2, "b", 24, LocalDate.of(2000, 12, 22));
        Users users3 = new Users(3, "c", 23, LocalDate.of(2000, 12, 23));
        Users users4 = new Users(4, "d", 22, LocalDate.of(2000, 12, 24));
        Users users5 = new Users(5, "e", 21, LocalDate.of(2000, 12, 25));

        Map<Integer, Users> map = new HashMap<>();


        Map<Integer, Users> hashMap = new HashMap<>();

        hashMap.put(1, users1);
        hashMap.put(2, users2);
        hashMap.put(3, users3);
        hashMap.put(4, users4);
        hashMap.put(5, users5);

        //根据值key计算value
        //hashMap.compute（key，（key，oldvalue）->{}）
        {
            hashMap.compute(1, (key, oldvalue) -> {
                if (oldvalue == null) {

                    return new Users(6, "f", 21, LocalDate.of(2000, 12, 25));

                }
                oldvalue.setName("Na");
                return oldvalue;
            });

            hashMap.compute(2, (key, oldvalue) -> {
                if (oldvalue == null) {

                    return new Users(6, "f", 21, LocalDate.of(2000, 12, 25));

                }
                oldvalue.setName("Nb");
                return oldvalue;
            });
            hashMap.compute(3, (key, oldvalue) -> {
                if (oldvalue == null) {

                    return new Users(6, "f", 21, LocalDate.of(2000, 12, 25));

                }
                oldvalue.setName("Nc");
                return oldvalue;
            });
            hashMap.compute(4, (key, oldvalue) -> {
                if (oldvalue == null) {

                    return new Users(6, "f", 21, LocalDate.of(2000, 12, 25));

                }
                oldvalue.setName("Nd");
                return oldvalue;
            });
            hashMap.compute(5, (key, oldvalue) -> {
                if (oldvalue == null) {

                    return new Users(6, "f", 21, LocalDate.of(2000, 12, 25));

                }
                oldvalue.setName("Ne");
                return oldvalue;
            });
            System.out.println("********************");
            hashMap.forEach((k, v) -> System.out.println(k + " " + v));
        }



    }
}
