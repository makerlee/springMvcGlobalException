package com.example.demo.resume;

import java.util.concurrent.*;

/**
 * Created by lijiyang on 2017/11/29.
 */
public class SingleTest {
    public static void main(String[] args) {
        //test0();
        test1();
    }
    //测试懒汉模式
    public static void test0(){
        System.out.println("==========main thread start=========");
        CountDownLatch count = new CountDownLatch(1000);
        //FutureTask future = new FutureTask(new Task(count));
        for (int i = 0;i<1000;i++){
            new Thread(new Task(count)).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========main thread start=========");
    }

    //测试完美模式
    public static void test1(){
        System.out.println("==========main thread start=========");
        CountDownLatch count = new CountDownLatch(1000);
        //FutureTask future = new FutureTask(new Task(count));
        for (int i = 0;i<1000;i++){
            new Thread(new Task1(count)).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========main thread start=========");
    }
}

class Task implements Runnable{
    private CountDownLatch count;

    public Task(CountDownLatch count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(Singleton0.getInstance());
        count.countDown();
        //return Singleton0.getInstance();
    }
}

class Task1 implements Runnable{
    private CountDownLatch count;

    public Task1(CountDownLatch count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(Singleton.getInstane());
        count.countDown();
        //return Singleton0.getInstance();
    }
}
