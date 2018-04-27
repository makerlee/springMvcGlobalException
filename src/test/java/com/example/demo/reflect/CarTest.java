package com.example.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by lijiyang on 2017/12/12.
 */
public class CarTest {
    public static void main(String[] args) throws Exception {
        //Class clazz = Class.forName("com.example.demo.reflect.Car");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.example.demo.reflect.Car");

        System.out.println("加载"+clazz.getCanonicalName());
        Constructor<Car> constructor = clazz.getConstructor();
        Car car = constructor.newInstance();
//        car.setBrand("bens");
//        car.setColor("骚红");
//        car.setVehNO("黑B38383");

        //通过反射
        Method setBranch = clazz.getMethod("setBrand",String.class);
        setBranch.invoke(car, "bens");
        Method setColor = clazz.getMethod("setColor",String.class);
        setColor.invoke(car, "骚红");
        Method setVehNO = clazz.getMethod("setVehNO",String.class);
        setVehNO.invoke(car, "黑B38383");
        car.getInfo();
    }
}
