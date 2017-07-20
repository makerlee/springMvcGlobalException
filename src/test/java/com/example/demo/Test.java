package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Created by lijiyang on 2017/6/5.
 */
public class Test {
    @org.junit.Test
    public void test(){
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        long nowLong = now.getTime();
        System.out.println(nowLong);
        String nowStr = sm.format(now);
        System.out.println(nowStr);
        System.out.println(new Date(1500368661043L));
    }

    @org.junit.Test
    public void optional(){
        Optional<?> optional=Optional.ofNullable("123");
        System.out.println(optional.isPresent());
    }
}
