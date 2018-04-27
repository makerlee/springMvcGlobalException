package com.example.demo.service.proxy_cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lijiyang on 2018/1/17.
 */
public class UserInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("invoke method:"+method.getName());
        return methodProxy.invoke(o,args);
    }
}
