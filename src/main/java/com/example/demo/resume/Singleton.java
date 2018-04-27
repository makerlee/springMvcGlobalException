package com.example.demo.resume;

/**
 * 1.支持并发
 * 2.避免指令重排造成的多实例问题
 *
 * Created by lijiyang on 2017/11/29.
 */
public class Singleton {

    private Singleton(){}

    private volatile static Singleton instance = null;//单例对象

    public static Singleton getInstane(){
        if(instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
