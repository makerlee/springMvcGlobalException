package com.example.demo.concurrent.wait_notify;

/**
 * Created by lijiyang on 2018/4/25.
 */
public class WaitAndNotify {
    public static void main(String[] args) {
        Thread f = new Thread(new Runnable() {
            public void run() {
                Thread s = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<100; i++) {
                            System.out.println(i);
                        }
                    }
                });
                s.start();
                System.out.println("************* son thread started *************");
                try {
                    s.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("************* son thread died *************");
            }
        });
        f.start();
    }
}
