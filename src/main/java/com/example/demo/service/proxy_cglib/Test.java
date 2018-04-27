package com.example.demo.service.proxy_cglib;

import com.example.demo.service.IUserService;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by lijiyang on 2018/1/17.
 */
public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{IUserService.class});
        enhancer.setCallback(new UserInterceptor());

        IUserService service = (IUserService) enhancer.create();
        service.addUser(123);
    }
}
