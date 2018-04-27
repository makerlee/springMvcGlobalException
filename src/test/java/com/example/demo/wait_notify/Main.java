package com.example.demo.wait_notify;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lijiyang on 2018/1/18.
 */
public class Main {
    public static void main(String[] args) {
        List list = new LinkedList();
        Thread r = new Thread(new ReadList(list));
        Thread w = new Thread(new WriteList(list));
        r.start();
        w.start();
    }
}

class ReadList implements Runnable{
    private List list;

    public ReadList(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("read list begin at:"+System.currentTimeMillis());
        synchronized (list){
            try {
                Thread.sleep(1000);
                System.out.println("list.wait() begin at:"+System.currentTimeMillis());
                list.wait();
                System.out.println("list.wait() ended at:"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("read list end at:"+System.currentTimeMillis());
    }
}

class WriteList implements Runnable{
    private List list;

    public WriteList(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("write list begin at:"+System.currentTimeMillis());
        synchronized (list){
            System.out.println("get lock at:"+System.currentTimeMillis());
            list.notify();
            System.out.println("list.notify() at:"+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get out of block at:"+System.currentTimeMillis());
        }
        System.out.println("write list end at:"+System.currentTimeMillis());
    }
}

