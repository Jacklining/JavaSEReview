package com.cuixiaobin;

import java.util.ArrayList;
import java.util.List;

public class T_13Thread {

    //线程通信的等待与激活，构成的一个循环
}
class Desk {
    private List<String> list = new ArrayList<>();

    // 放1个包子的方法
    // 厨师1 厨师2 厨师3
    public synchronized void put() {
        try {
            String name = Thread.currentThread().getName();
            // 判断是否有包子。
            if(list.size() == 0){
                list.add(name + "做的肉包子");
                System.out.println(name + "做了一个肉包子~~");
                Thread.sleep(2000);

                // 唤醒别人, 等待自己
                this.notifyAll();
                this.wait();
            }else {
                // 有包子了，不做了。
                // 唤醒别人, 等待自己
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 吃货1 吃货2
    public synchronized void get() {
        try {
            String name = Thread.currentThread().getName();
            if(list.size() == 1){
                // 有包子，吃了
                System.out.println(name  + "吃了：" + list.get(0));
                list.clear();
                Thread.sleep(1000);
                this.notifyAll();
                this.wait();
            }else {
                // 没有包子
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest {
    public static void main(String[] args) {
        //   需求：3个生产者线程，负责生产包子，每个线程每次只能生产1个包子放在桌子上
        //      2个消费者线程负责吃包子，每人每次只能从桌子上拿1个包子吃。
        Desk desk  = new Desk();

        // 创建3个生产者线程（3个厨师）
        new Thread(() -> {
            while (true) {
                desk.put();
            }
        }, "厨师1").start();

        new Thread(() -> {
            while (true) {
                desk.put();
            }
        }, "厨师2").start();

        new Thread(() -> {
            while (true) {
                desk.put();
            }
        }, "厨师3").start();

        // 创建2个消费者线程（2个吃货）
        new Thread(() -> {
            while (true) {
                desk.get();
            }
        }, "吃货1").start();

        new Thread(() -> {
            while (true) {
                desk.get();
            }
        }, "吃货2").start();
    }
}