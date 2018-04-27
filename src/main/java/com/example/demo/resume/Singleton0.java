package com.example.demo.resume;

/**
 * Created by lijiyang on 2017/11/29.
 */
public class Singleton0 {
    private Singleton0(){}

    private static Singleton0 instance = null;

    public static Singleton0 getInstance(){
        if(instance == null){
            instance = new Singleton0();
        }
        return instance;
    }
}
