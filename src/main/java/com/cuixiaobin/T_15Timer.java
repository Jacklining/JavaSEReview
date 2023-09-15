package com.cuixiaobin;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T_15Timer {

    //1.定时器：Timer

    //顺序执行语句：但是一个Timer出现问题，后面的Timer都会出现问题

//    LocalDateTime,LocalTime,DateTime 都实现了Temporal接口

    @Test
    public void test1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器");
            }
        }, 1000);

        Date date = Date.from(Instant.from(LocalDateTime.of(2020, 1, 1, 1, 1, 1)));


        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, date, 1000);
    }

    public static void main(String[] args) {
        // 1、创建Timer定时器
        Timer timer = new Timer();  // 定时器本身就是一个单线程。
        // 2、调用方法，处理定时任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行AAA~~~" + new Date());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);

        //如果2秒内执行不完程序,这个秒的时间差度会超过2000ms,这个两秒就没有用了

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行BB~~~" + new Date());
//                System.out.println(10 / 0);
                //遇到异常时，Time全部停止
            }
        }, 0, 2000);

        //多个Timer相互影响达不到原来效果，解决方案：多线程

    }


}

class my {
    //2.定时器

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

        pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器");
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }


}


class TimerDemo2 {
    public static void main(String[] args) {
        // 1、创建ScheduledExecutorService线程池，做定时器
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

        // 2、开启定时任务
        pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行输出：AAA  ==》 " + new Date());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);


        pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行输出：BBB  ==》 " + new Date());
                System.out.println(10 / 0);
            }
        }, 0, 2, TimeUnit.SECONDS);


        pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行输出：CCC  ==》 " + new Date());
            }
        }, 0, 2, TimeUnit.SECONDS);



    }
}
