package com.cuixiaobin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_12Thread {

    //1.线程通信：线程间通过某种方式互相告知自己的状态，以相互协调，避免无效的资源挣抢。




}

//感觉上还是要锁的东西越关键，越少越好。把可以重复执行的放在锁外面，不关键的东西
//"细粒度"通常是指将共享资源的锁定范围尽可能缩小

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

    // 同步代码块：方式
//    public void drawMoney(double money) {
//        // 先搞清楚是谁来取钱？
//        String name = Thread.currentThread().getName();
//        // 1、判断余额是否足够
//        synchronized (this) {
//            if (this.money >= money) {
//                System.out.println(name + "来取钱" + money + "成功！");
//                this.money -= money;
//                System.out.println(name + "来取钱后，余额剩余：" + this.money);
//            } else {
//                System.out.println(name + "来取钱：余额不足~");
//            }
//        }
//    }
    private final Lock lk = new ReentrantLock();


    //lock锁方式：
    public void drawMoney(double money) {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();

        // 1、判断余额是否足够

        try {
            lk.lock();

            if (this.money >= money) {
                System.out.println(name + "来取钱" + money + "成功！");
                this.money -= money;
                System.out.println(name + "来取钱后，余额剩余：" + this.money);
            } else {
                System.out.println(name + "来取钱：余额不足~");
            }
        } finally {
            lk.unlock();
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


/**
 * 条件变量（Condition）是java.util.concurrent.locks包中的一种机制，
 * 与ReentrantLock一起使用，用于更灵活地控制线程的等待和唤醒。
 * 这是针对一个变量说的
 * 主线程受阻但是不会影响子线程的执行情况
 */
class ConditionVariableExample {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean conditionMet = false;

    public void waitForCondition() throws InterruptedException {
        int i = 0;
        lock.lock();
        try {
            while (!conditionMet) {
                condition.await(); // 等待条件变量满足
                //这里已经释放锁了
                System.out.println("after await");
                i++;

            }
            // 条件满足后执行操作
            System.out.println("Condition is met!");
            System.out.println("2秒内循环"+i+"次");
        } finally {
            lock.unlock();
        }
    }

    public void setConditionMet() {
        lock.lock();
        try {
            conditionMet = true;
            condition.signal(); // 通知等待的线程条件已满足
            System.out.println("条件已经满足了");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionVariableExample example = new ConditionVariableExample();

        Thread waitingThread = new Thread(() -> {
            try {
                example.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread notifyingThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // 模拟一段时间后条件满足
                example.setConditionMet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        waitingThread.start();
        notifyingThread.start();

//        两个线程并行执行，waitingThread再循环中一直等待满足条件，
//        notifyingThread一满足条件，就继续执行
        /**
         *
         * 在你的代码中：
         *
         * 1. `waitingThread.join();` 会等待 `waitingThread` 执行完成，
         *     但不会直接影响 `notifyingThread` 的执行。
         *    `notifyingThread` 会继续运行，只有在其自己的任务完成后才会退出。
         *
         * 2. 接着执行 `notifyingThread.join();`，这会等待 `notifyingThread` 执行完成，
         *    但不会直接影响 `waitingThread` 的执行。
         *    `waitingThread` 会继续运行，只有在其自己的任务完成后才会退出。
         *
         * 主线程受阻，但是不影响子线程的正常执行
         */

        waitingThread.join();
        System.out.println("waitingThread");
        notifyingThread.join();
        System.out.println("notifyingThread");

    }
}


