package com.example.demo.extendThreadPoolExcutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by lijiyang on 2017/12/21.
 */
public class TestExecutor {
    private static final int CORE_SIZE = 5;

    private static final int MAX_SIZE = 10;

    private static final long KEEP_ALIVE_TIME = 30;

    private static final int QUEUE_SIZE = 5;

    static EnhancedThreadPoolExecutor executor = new EnhancedThreadPoolExecutor(CORE_SIZE,MAX_SIZE,KEEP_ALIVE_TIME, TimeUnit.SECONDS , new TaskQueue(QUEUE_SIZE));

    private static CountDownLatch latch = new CountDownLatch(15);

    public static void main(String[] args){
        for (int i = 0; i < 15; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("------------执行"+Thread.currentThread().getName());
                    Thread.currentThread().sleep(1000);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("线程池中现在的线程数目是："+executor.getPoolSize()+",  队列中正在等待执行的任务数量为："+ executor.getQueue().size());
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---->执行完");
        executor.shutdown();
    }
}