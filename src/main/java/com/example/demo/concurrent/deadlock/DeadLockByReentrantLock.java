package com.example.demo.concurrent.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lijiyang on 2018/4/25.
 */
public class DeadLockByReentrantLock {
    public static void main(String[] args) {
        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("now i in T1-lock1");
                    lock1.lock();
                    Thread.sleep(1000);
                    //do something
                    try {
                        lock2.lock();
                    }finally {
                        lock2.unlock();
                    }
                }catch (Exception e){
                    //throw ex
                }finally {
                    lock1.unlock();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("now i in T2-lock2");
                    lock2.lock();
                    Thread.sleep(1000);
                    //do something
                    try {
                        lock1.lock();
                    }finally {
                        lock1.unlock();
                    }
                }catch (Exception e){
                    //throw ex
                }finally {
                    lock2.unlock();
                }

            }
        });

        t1.start();
        t2.start();
    }
}
