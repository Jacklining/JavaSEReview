package com.cuixiaobin;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.ZoneId.SHORT_IDS;

public class T_02API {

    /**
     * 日期api
     */
    @Test
    public void test() {
        //Date:
        Date date = new Date();
        System.out.println(date);

        Instant instant = Instant.now();
        System.out.println(instant);

        Date Dinstant = Date.from(instant);
        System.out.println(Dinstant);

        Instant instant1 = date.toInstant();

        //jdk8

        //模板不合适报错：
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDateTime before = LocalDateTime.parse("2022-12-21",dtf);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime before = LocalDateTime.parse("2022-12-21 12:12:12", dtf);

        System.out.println(before);

        LocalDateTime localDateTime3 = LocalDateTime.now(ZoneId.of("CTT", SHORT_IDS));

        //纳秒数
        localDateTime3.getNano();

        ZoneId zoneId2 = ZoneId.of("PST", SHORT_IDS);
//        localDateTime.atZone();

        System.out.println(LocalDateTime.now().atZone(zoneId2));

        System.out.println("***************************************");

        ZoneId zoneId1 = ZoneId.of("America/Los_Angeles"); // 洛杉矶时区
        LocalDateTime localDateTime1 = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(localDateTime1, zoneId1);

        // 格式化输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = zonedDateTime1.format(formatter);

        System.out.println(formattedDateTime);

        System.out.println("***************");
        ZoneId zoneId = ZoneId.of("America/Los_Angeles"); // 洛杉矶时区
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        // 转换为 LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        // 格式化输出
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime1 = localDateTime.format(formatter1);

        System.out.println(formattedDateTime1);

        LocalDate localDate = LocalDate.now();
//        localDate.isSupported(Temporal)

        LocalTime now = LocalTime.now();

        //好像不能互转

        System.out.println(localDate.withYear(2000));


        //时间间隔：
//        Duration:工具类？？？
//        Period：
//        Period period = Period.of(2000,10,21);

        Period period1 = Period.between(LocalDate.from(before), localDate);
        System.out.println(period1.getMonths());
        System.out.println(period1.getDays());
        System.out.println(period1.getYears());
//        System.out.println(period1.isNegative());


        System.out.printf("A%s", "sncnc");
        System.out.println();
        System.out.printf("A%d", 11);
        System.out.println();

        System.out.printf("A%c", 'f');
        System.out.println();

        System.out.printf("A%n");
        System.out.println();

        System.out.printf("A%.2f", 16.12531313);
        System.out.println();

        Duration duration = Duration.between(before, LocalDateTime.now());

        System.out.println("***********************************");
//        duration.getUnits().stream().forEach(temporalUnit -> System.out.println(temporalUnit.));
//        Timer timer = new Timer();
//
////        System.out.println(duration.toDays());
////        System.out.println(duration.toDaysPart());
////        System.out.println(duration.toDaysPart());
////        System.out.println(duration.toDaysPart());
////        System.out.println(duration.toDaysPart());
//
//
//        TimerTask timerTask =new TimerTask() {
//            @Override
//            public void run() {
//                Duration duration = Duration.between(LocalDateTime.now(),before);
//                System.out.println(duration.toDaysPart()+"天"+duration.toHoursPart()+"时间"+duration.toMinutesPart()+"分"+duration.toSecondsPart()+"秒");
//            }
//        };
//        System.out.println("************************************");
//        timer.schedule(timerTask,0,1000);

        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 12, 21, 12, 22);
        Duration duration1 = Duration.between(localDateTime2, LocalDateTime.now());
        System.out.println(duration1.toDaysPart() + "天" + duration1.toHoursPart() + "时间" + duration1.toMinutesPart() + "分" + duration1.toSecondsPart() + "秒");

        //包装类：
        //转化：
        Integer integer = 10;
        Double d = Double.parseDouble(String.valueOf(10));


    }
    //    public static void main(String[] args) {
////        Timer timer = new Timer();
////        TimerTask timerTask =new TimerTask() {
////            @Override
////            public void run() {
////                LocalDateTime localDateTime2 = LocalDateTime.of(2022,12,21,12,22);
////                Duration duration1 = Duration.between(localDateTime2,LocalDateTime.now());
////                System.out.println(duration1.toDaysPart()+"天"+duration1.toHoursPart()+"时间"+duration1.toMinutesPart()+"分"+duration1.toSecondsPart()+"秒");
////
////            }
////        };
////        timer.schedule(timerTask,0,1000);
//
//
//        LocalDateTime localDateTime2 = LocalDateTime.of(2022,12,21,12,22);
//        Duration duration1 = Duration.between(localDateTime2,LocalDateTime.now());
//        System.out.println(duration1.toDaysPart()+"天"+duration1.toHoursPart()+"时"+duration1.toMinutesPart()+"分"+duration1.toSecondsPart()+"秒");
//
//    }
    //正则api:

    /**
     * 正则表达式
     */
    @Test
    public void test2() {
        //正则表达式匹配：
        //匹配模式：Pattern
        //匹配器：matcher：
        String s = "alice@example.com\n" + "bob@gmail.com\n" + "charlie@yahoo.com";
//        Pattern pattern = Pattern.compile("\\w+@\\w+.com");
        Pattern pattern = Pattern.compile("(\\w+@\\w+.com)");
        Matcher m = pattern.matcher(s);

        m.results().forEach(matchResult -> System.out.println(matchResult.group()));


    }

    @Test
    public void test3() {
        String s = "Hello, my email is example@email.com";

        Pattern pattern = Pattern.compile("(\\w+)@(\\w+).com");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }


    }

    @Test
    public void test4() {
        String text = "Hello, my email is example@email.com";
        String pattern = "(\\w+)@(\\w+\\.\\w+)"; // 正则表达式模式匹配邮箱地址，并使用捕获组

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);
        if (matcher.find()) {
            String username = matcher.group(1); // 获取第一个捕获组（用户名）
            String domain = matcher.group(2);   // 获取第二个捕获组（域名）

            System.out.println("用户名: " + username);
            System.out.println("域名: " + domain);
        }

        System.out.println(matcher.groupCount());

    }

    //正则信息爬取：
    // 需求1：从以下内容中爬取出，手机，邮箱，座机、400电话等信息。
    // 分组就不要想分组了，一次最好只取一种类型的值，如果不限类型，可以一下取完
    @Test
    public void test5() {

        String data = " 来黑马程序员学习Java，\n" +
                "        电话：1866668888，18699997777\n" +
                "        或者联系邮箱：boniu@itcast.cn，\n" +
                "        座机电话：01036517895，010-98951256\n" +
                "        邮箱：bozai@itcast.cn，\n" +
                "        邮箱：dlei0009@163.com，\n" +
                "        热线电话：400-618-9090 ，400-618-4000，4006184000，4006189090";


        String regex = "((?<!0)1\\d{9,10})|(\\w+@\\w+.\\w+)|(010-?\\d+)|(400-?\\d+-?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        if (matcher.find()) {
            System.out.println(matcher.groupCount());
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//            System.out.println(matcher.group(4));
            System.out.println(matcher.group(1));

            System.out.println(matcher.group(2));
        }

        System.out.println("****************************");
        String rs = "";
        while (matcher.find()) {
            rs = rs + matcher.group();
            System.out.println(matcher.group());
        }
        System.out.println("************************");
        System.out.println(rs);

    }

    /**
     * 目标：校验用户输入的电话、邮箱、时间是否合法。
     */
    @Test
    public void test6() {

        String p1 = "18676769999";
        String p2 = "010-3424242424";
        String p3 = "0104644535";

        String regex = "1\\d{10}|010-?\\d{10}|010\\d{7}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher1 = pattern.matcher(p1);
        Matcher matcher2 = pattern.matcher(p2);
        Matcher matcher3 = pattern.matcher(p3);

        if (matcher1.find())
            System.out.println(matcher1.group());
        if (matcher2.find())
            System.out.println(matcher2.group());
        if (matcher3.find())
            System.out.println(matcher3.group());

    }

    //    校验邮箱是否正确
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "\\w{2,}@\\w{2,20}(\\.\\w{2,10}){1,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        while (true) {
            String input = scanner.next();
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                System.out.println("success:" + matcher.group());
            } else {
                System.out.println("error:重新输入");
            }
        }
    }

    //    正则表达式搜索与替换
    @Test
    public void test7() {


        // 1、public String replaceAll(String regex , String newStr)：按照正则表达式匹配的内容进行替换
        // 需求1：请把下面字符串中的不是汉字的部分替换为 “-”
        String s1 = "古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴";
        System.out.println(s1.replaceAll("\\w+", "-"));

        // 需求2(拓展)：某语音系统，收到一个口吃的人说的“我我我喜欢编编编编编编编编编编编编程程程！”，需要优化成“我喜欢编程！”。
        String s2 = "我我我喜欢编编编编编编编编编编编编程程程";
        System.out.println(s2.replaceAll("(.)\\1+", "$1"));

        String regex = "(.)\\1+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s2);
        while (matcher.find()) {

            System.out.println(matcher.group());
        }


        // 2、public String[] split(String regex)：按照正则表达式匹配的内容进行分割字符串，反回一个字符串数组。
        // 需求1：请把下面字符串中的人名取出来，使用切割来做

        System.out.println("********************************");
        String s3 = "古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴";
        String[] splits = s3.split("\\w+");
        List<String> strings = Arrays.stream(splits).toList();
        strings.stream().forEach(e -> System.out.println(e));


    }

    /**
     * Arrays类
     */


    @Test
    public void arraysText() {
        Integer[] array = {1, 2, 3, 4, 5};

        // 比较方法：
        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        Arrays.sort(array, comparator);
        Arrays.sort(array, (o1, o2) -> o2.compareTo(o1));
        for (Integer i : array) {
//            System.out.println(i);
        }

        Arrays.sort(array);
        System.out.println(Arrays.binarySearch(array, 5));
        System.out.println(Arrays.binarySearch(array, 4654));


        Integer[] integers = Arrays.copyOf(array, 3);
        for (int i = 0; i < integers.length; i++) {
            System.out.println(integers[i]);
        }
        Integer[] integers1 = new Integer[10];
        System.arraycopy(array, 1, integers1, 0, 4);

        System.out.println("***********************************");
        for (int i = 0; i < integers1.length; i++) {
            System.out.println(integers1[i]);
        }
    }


    /**
     * Runtime : java运行时类
     */

    @Test
    public void RuntimeText() throws Exception {

        // 1、public static Runtime getRuntime() 返回与当前Java应用程序关联的运行时对象。
        Runtime runtime = Runtime.getRuntime();

        // 2、public void exit(int status) 终止当前运行的虚拟机,该参数用作状态代码; 按照惯例，非零状态代码表示异常终止。
        // r.exit(0);

        // 3、public int availableProcessors(): 获取虚拟机能够使用的处理器数。
        System.out.println(runtime.freeMemory() / (1024 * 1024));
        System.out.println(runtime.totalMemory() / (1024 * 1024));//单位：M
        Process exec = runtime.exec("E:\\Program Files (x86)\\Genshin Impact Cloud Game\\Genshin Impact Cloud Game.exe");

        Thread thread = new Thread();

        thread.sleep(5000);
        System.out.println(exec.isAlive());


        thread.sleep(5000);
        System.out.println("info"+exec.info());
        System.out.println("pid:"+exec.pid());
//        exec.destroy();
        // 6、public Process exec(String command) 启动某个程序，并返回代表该程序的对象。
        // r.exec("D:\\soft\\XMind\\XMind.exe");
//        Process p = r.exec("QQ");
//        Thread.sleep(5000); // 让程序在这里暂停5s后继续往下走！！
//        p.destroy(); // 销毁！关闭程序！
    }

    //不可执行那个快捷方式，只能执行可执行文件
    @Test
    public void runtimeTest() throws IOException {
        Runtime runtime = Runtime.getRuntime();

//        Thread thread01 =

        runtime.exec("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\云·原神\\云·原神");

    }

    /**
     * 精确计算：
     */

    @Test
    public void testD01(){
        double a = 0.1;
        double b = 0.2;

        System.out.println(a+b);

        //浮点进制转换问题

        //创建bigDecimal最好的解决方案
        BigDecimal bigDecimal = BigDecimal.valueOf(215.026);
        System.out.println("plus:"+bigDecimal.plus());
        System.out.println(bigDecimal.add(BigDecimal.valueOf(10)));
        System.out.println(bigDecimal.subtract(BigDecimal.valueOf(10)));
        System.out.println(bigDecimal.divide(BigDecimal.valueOf(10)));
        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(10)));


    }

}



