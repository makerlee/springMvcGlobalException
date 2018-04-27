package com.example.demo.concurrent.deadlock;

/**
 * Created by lijiyang on 2017/10/12.
 */
public class DeadLockBySynchronized {
    public static void main(String[] args) {
        final Object a = new Object();
        final Object b = new Object();
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (a) {
                    try {
                        System.out.println("now i in threadA-locka");
                        Thread.sleep(1000l);
                        synchronized (b) {
                            System.out.println("now i in threadA-lockb");
                        }
                    } catch (Exception e) {}
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (b) {
                try {
                    System.out.println("now i in threadB-lockb");
                    Thread.sleep(1000l);
                    synchronized (a) {
                        System.out.println("now i in threadB-locka");
                    }
                } catch (Exception e) {}
            }
        });
        threadA.start();
        threadB.start();
    }

}
