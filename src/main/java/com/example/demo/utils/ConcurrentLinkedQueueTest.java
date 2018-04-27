package com.example.demo.utils;

/**
 * Created by lijiyang on 2017/11/13.
 */

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class ConcurrentLinkedQueueTest {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static int count = 1; // 线程个数
    //CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {
        //固定容量的Executor实例
//        long timeStart = System.currentTimeMillis();
//        ExecutorService es = Executors.newFixedThreadPool(4);
//        ConcurrentLinkedQueueTest.offer();
//        for (int i = 0; i < count; i++) {
//            es.submit(new Poll(i+""));
//        }
//        latch.await(); //使得主线程(main)阻塞直到latch.countDown()为零才继续执行
//        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");
//        es.shutdown();

//        String s = "hhhhhhhhh,kkkkn,kkk,";
//        int first = s.indexOf(",");
//        int second = s.indexOf(",",first+1);
//        System.out.println("---->"+first+"--->"+second);
//        String res = s.substring(first+1,second);
//        System.out.println(res);
    }

    /**
     * 生产
     */
    public static void offer() {
        for (int i = 0; i < 1000; i++) {
            queue.offer(i);
        }
    }


    /**
     * 消费
     */
    static class Poll implements Runnable {
        private String name;

        public Poll(String name) {
            this.name = name;
        }

        public void run() {
             //while (queue.size()>0) {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll()+"--->by thread :"+this.name);
            }
            latch.countDown();
        }
    }
}