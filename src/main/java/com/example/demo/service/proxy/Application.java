package com.example.demo.service.proxy;

import com.example.demo.service.IUserService;
import com.example.demo.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by lijiyang on 2018/1/17.
 */
public class Application {
    public static void main(String[] args) {
        IUserService service = (IUserService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class<?>[]{IUserService.class},new UserInvokeHandler(new UserServiceImpl()));
        service.addUser(123);
    }
}
