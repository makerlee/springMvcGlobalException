package com.example.demo.interupt;

/**
 * Created by lijiyang on 2018/1/18.
 */
public class InteruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("thread interrupted");
                    break;
                }
                try {
                    System.out.println("---->业务执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println("interrupt when sleep");
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
