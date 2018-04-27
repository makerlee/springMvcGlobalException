package com.example.demo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijiyang on 2017/12/6.
 */
public class TestArrayList {
    @Test
    public void testDefault(){
        List<String> list = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        for(int i=0; i<8000; i++){
            list.add("test"+i);
        }
        System.out.println("-->"+(System.currentTimeMillis()-t1));
    }

    @Test
    public void testSpecify(){
        List<String> list = new ArrayList<>(4000);
        long t1 = System.currentTimeMillis();
        for(int i=0; i<8000; i++){
            list.add("test"+i);
        }
        System.out.println("-->"+(System.currentTimeMillis()-t1));
    }

    @Test
    public void test0(){
        String[] arr = new String[40000];
        long t1 = System.currentTimeMillis();
        for(int i=0;i<40000;i++){
            arr[i] = i+"";
        }
        System.out.println("-->"+(System.currentTimeMillis()-t1));
    }

}
