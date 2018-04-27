package com.example.demo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lijiyang on 2018/4/13.
 */
public class Counter {
    private AtomicInteger count = new AtomicInteger();
    public Counter(){}
    public int getCount(){
        return count.get();
    }
    public void increase(){
        count.getAndIncrement();
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.increase();
        System.out.println(counter.getCount());
    }
}