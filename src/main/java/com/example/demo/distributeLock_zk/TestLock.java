package com.example.demo.distributeLock_zk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * zookeeper分布式锁实现测试
 * 利用线程池工具，启动20个线程，同时提交任务
 * Created by lijiyang on 2018/4/27.
 */
public class TestLock {
    public static volatile int i = 0;
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(4);

        for (int i=0;i<4;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increase();
                    latch.countDown();
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }


    public static void increase(){
        DistributedLock lock = new DistributedLock("172.20.66.159:2181","test-multi");
        lock.lock();
        i++;
        lock.unlock();
    }

    //模拟多线程竞争锁
    public static void testMultiThead(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
        for (int i=0;i<20;i++){
            DistributedLock lock = new DistributedLock("172.20.66.159:2181","test-multi");
            fixedThreadPool.submit(new Task(lock));
        }
        fixedThreadPool.shutdown();
    }

    public static void testBasic(){
        DistributedLock lock = new DistributedLock("172.20.66.159:2181","test");
        lock.lock();
        try {
            // do something business
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class Task extends Thread{
    private DistributedLock lock;

    public Task(DistributedLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(this.getName()+"正在执行.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}