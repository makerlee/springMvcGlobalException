package com.example.demo.service.proxy;

import com.example.demo.service.IUserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lijiyang on 2018/1/17.
 */
public class UserInvokeHandler implements InvocationHandler{
    private IUserService service;

    public UserInvokeHandler(IUserService service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----->add User");
        return method.invoke(service,args);
    }
}
