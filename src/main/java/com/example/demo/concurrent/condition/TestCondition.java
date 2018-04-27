package com.example.demo.concurrent.condition;

import java.util.Date;

/**
 * Created by lijiyang on 2018/4/24.
 */
public class TestCondition {
    public static void main(String[] args) throws InterruptedException {
        BoundedBufferWithCondition bu = new BoundedBufferWithCondition();

        Thread t1 = new Thread(() -> {
            try {
                bu.put("hello1");
                System.out.println("t1 put....");
            } catch (InterruptedException execption) {
                System.out.println("intercetp1:"+Thread.currentThread().getName());
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                bu.put("hello2");
                System.out.println("t2 put....");
            } catch (InterruptedException execption) {
                System.out.println("intercetp2:"+Thread.currentThread().getName());
            }
        });
        Thread t3 =  new Thread(() -> {
            try {
                bu.put("hello3");
                System.out.println("t3 put....1");
                Thread.sleep(7000);
                bu.put("last one...");
            } catch (InterruptedException execption) {
                System.out.println("intercetp3:"+Thread.currentThread().getName());
            }
        });
        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(5000);
            System.out.println(bu.take());
            System.out.println(bu.take());
            System.out.println(bu.take());
            System.out.println(bu.take());
        } catch (InterruptedException execption) {
            execption.printStackTrace();
        }

        System.out.println(new Date()+" main over...");
    }
}
