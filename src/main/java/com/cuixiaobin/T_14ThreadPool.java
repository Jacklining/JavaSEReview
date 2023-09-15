package com.cuixiaobin;

import org.junit.Test;

import java.util.concurrent.*;

public class T_14ThreadPool {


    //1.线程池的创建：
    @Test
    public void test1() {
        //ExecutorService线程接口

        ExecutorService executorService = new ThreadPoolExecutor(
                4,
                10,
                8,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    //2.线程池执行Runnable与Callable任务：
    @Test
    public void test2() throws ExecutionException, InterruptedException {

        ExecutorService executor = new ThreadPoolExecutor(
                4,
                10,
                8,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });



        System.out.println(
                executor.submit(()-> {return  "我执行啦！！！！";}).get()
        );

    }
}
