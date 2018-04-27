package com.example.demo.concurrent;

/**
 * Created by lijiyang on 2017/10/12.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        DeadLockBySynchronized factory = new DeadLockBySynchronized("My");
//        MyTask task = new MyTask();
//        Thread thread = factory.newThread(task);
//
//        thread.start();
//        thread.join();
//
//        System.out.printf("Main: Thread information.\n");
//        System.out.printf("%s\n",thread);
//        System.out.printf("Main: End of the example.\n");

        System.out.println(get());
    }

    public static int get(){
        for (int i = 0;i<10;i++){
            return i;
        }
        return 0;
    }
}
