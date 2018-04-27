package com.example.demo;

import java.util.concurrent.CountDownLatch;

public class Test{
    static int i = 0;
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(10000);
        for (int j=0;j<10000;j++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    i++;
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}