package com.example.demo.factoryPattern;

/**
 * Created by lijiyang on 2018/1/9.
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}
