package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lijiyang on 2017/11/20.
 */
public class TestFutureTask {
    public static void main(String ... args) {
        ACallAble callAble = new ACallAble();
        FutureTask<String> futureTask = new FutureTask<>(callAble);
        Thread thread = new Thread(futureTask);
        thread.start();

        do {

        }while (!futureTask.isDone());

        try {
            String result = futureTask.get();
            System.out.println("Result:" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class ACallAble implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Thread-Name:" + Thread.currentThread().getName();
    }
}
