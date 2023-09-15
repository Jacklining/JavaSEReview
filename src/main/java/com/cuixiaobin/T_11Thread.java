package com.cuixiaobin;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class T_11Thread {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.有关Thread对象
        {



        try {
            //线程的三种创建方式
            //1.集成线程类并重新类方法
//        MyThread myThread = new MyThread();
//        myThread.start();


            System.out.println("***********************");
            //2.实现Runnable接口的run方法
//        Thread thread01 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(Thread.currentThread().getName() + " " + i);
//                }
//            }
//        });
//        thread01.start();

            //3.有返回值的
            System.out.println("*****************************");
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("执行过");
                    return "callable";
                }
            };

            FutureTask futureTask = new FutureTask(callable);

            Thread thread02 = new Thread(futureTask);
            thread02.start();
            System.out.println(futureTask.get());

            System.out.println("*****************************");
            Mycallable mycallable = new Mycallable(10);
            FutureTask<String> futureTask1 = new FutureTask<String>(mycallable);
            Thread thread03 = new Thread(futureTask1);
            thread03.start();
            String s = futureTask1.get();
            System.out.println(s);
            System.out.println("*****************************");

            System.out.println("************thread02.getThreadGroup();线程的分组*****************");
            //thread02.getThreadGroup();线程的分组 （不再推荐使用，了解）
            //创建线程的时候就可以传入线程组对象
            Thread thread04 = new Thread(new ThreadGroup("线程组-1"),()-> System.out.println("打印线程组04"));
            Thread thread07 = new Thread(new ThreadGroup("线程组-1"),()-> System.out.println("打印线程组07"));
            thread04.start();
//        thread07.sleep(5000);//令线程组不消亡
            Thread thread05 = Thread.currentThread();
            thread04.join();//插队，主线程要等待thread04执行完毕之后再执行
            System.out.println(thread05.getThreadGroup().getName());
            System.out.println(thread05.getName());
            System.out.println(thread07.getThreadGroup().getName());
            //由于线程执行完毕之后线程会被GC,而这个线程组只有一个活跃线程，当这个线程消失，线程组也会跟着消失
        } catch (Exception e) {
            e.printStackTrace();
        }



        System.out.println("****************************");
        Thread thread06 = new Thread(()-> System.out.println("thread06执行"));
        System.out.println(thread06.getId());
        System.out.println(thread06.isAlive());//不start就不会活
        System.out.println(thread06.getState());//获取线程的六大生命周期状态

        /**
         * new 新建
         * RUNNABLE 可运行状态(被调用start)
         * BLOCKED 阻塞状态（没有获得锁）
         * WAITING 无限等待状态(被调用wait)
         * TIMED_WAITING 计时等待状态(被调用wait/sleep)
         * TERMINATED 终止状态(被调用stop)
         **/


        System.out.println("################################################################");
        System.out.println("类加载器："+thread06.getContextClassLoader());
        //获取上下文的类加载器
        System.out.println("thread06优先级："+thread06.getPriority());
        System.out.println(thread06.getName());
        System.out.println("main优先级："+Thread.currentThread().getPriority());
        System.out.println(Thread.currentThread().getName());
        //获取线程优先级,默认是5



        System.out.println("栈追踪"+thread06.getStackTrace());
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println(stackTrace.length);

        System.out.println("*****************************************************");
        for (StackTraceElement element : stackTrace) {
            System.out.println("Class: " + element.getClassName());
            System.out.println("Method: " + element.getMethodName());
            System.out.println("File: " + element.getFileName());
            System.out.println("Line: " + element.getLineNumber());
            System.out.println("--------------------------------------");
        }

        //类似方法栈结构的方法调用，了解方法的调用关系，有助于调试和分析问题
        System.out.println("*****************************************************");


        System.out.println("是否是守护线程"+thread06.isDaemon());
        //守护线程是用于在后台执行任务的线程，它们的存在不会阻止程序的正常终止
        System.out.println("判断是否中断"+thread06.isInterrupted());


        /**
         *
         * 中断是一种通信机制，用于请求线程终止或执行其他操作；
         * sleep 是一种使线程休眠的方式，用于时间间隔等待；
         * wait 是一种线程之间的协作方式，用于等待条件满足。
         *
         */





    }

       //2.线程安全问题
        /**
         * 线程同步：同步：协调和控制
         * 方法：加锁
         * 1.同步代码块synchronized(被锁对象)
         * 2.同步方法synchronized
         * 3.Lock
         */

        {
            //账户类
            class Account {
                private String cardId; // 卡号
                private double money; // 余额。

                public Account() {
                }

                public Account(String cardId, double money) {
                    this.cardId = cardId;
                    this.money = money;
                }

                // 小明 小红同时过来的
                public void drawMoney(double money) {
                    // 先搞清楚是谁来取钱？
                    String name = Thread.currentThread().getName();
                    // 1、判断余额是否足够
                    if (this.money >= money) {
                        System.out.println(name + "来取钱" + money + "成功！");
                        this.money -= money;
                        System.out.println(name + "来取钱后，余额剩余：" + this.money);
                    } else {
                        System.out.println(name + "来取钱：余额不足~");
                    }
                }

                public String getCardId() {
                    return cardId;
                }

                public void setCardId(String cardId) {
                    this.cardId = cardId;
                }

                public double getMoney() {
                    return money;
                }

                public void setMoney(double money) {
                    this.money = money;
                }
            }
            //取钱线程：
            class DrawThread extends Thread {
                private Account acc;

                public DrawThread(Account acc, String name) {
                    super(name);
                    this.acc = acc;
                }

                @Override
                public void run() {
                    // 取钱(小明，小红)
                    acc.drawMoney(100000);
                }
            }

            class test {

                public static void main(String[] args) throws InterruptedException {
                    // 1、创建一个账户对象，代表两个人的共享账户。
                    Account acc = new Account("ICBC-110", 100000);
                    // 2、创建两个线程，分别代表小明 小红，再去同一个账户对象中取钱10万。
                    new DrawThread(acc, "小明").start(); // 小明
                    new DrawThread(acc, "小红").start(); // 小红
                }

            }



        }


    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

 class Mycallable implements Callable {

    private int n;

    Mycallable(int n) {
        this.n = n;
    }

    @Override
    public String call() throws Exception {
        return this.n * this.n + "";
    }
}
