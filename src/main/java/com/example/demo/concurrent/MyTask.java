package com.example.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by lijiyang on 2017/10/12.
 */
public class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
