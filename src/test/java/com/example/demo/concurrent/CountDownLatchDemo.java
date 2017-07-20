package com.example.demo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lijiyang on 2017/6/14.
 */
public class CountDownLatchDemo {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate=new CountDownLatch(1);
        final CountDownLatch endGate=new CountDownLatch(nThreads);
        for(int i=0;i<nThreads;i++){
            Thread t=new Thread(){
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException e){}
                }
            };
            t.start();
        }

        long start =System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end=System.nanoTime();
        return end-start;


    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=============main start===============");
        new CountDownLatchDemo().timeTasks(3, () -> System.out.println("检查...."));

        System.out.println("=============main end===============");
    }
}
