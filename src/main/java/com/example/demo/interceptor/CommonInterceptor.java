package com.example.demo.interceptor;

import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * Created by lijiyang on 2017/7/17.
 */
public class CommonInterceptor implements HandlerInterceptor{
    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("----------------->>>>拦截器preHandle");
        System.out.println("1---->"+(userService==null));

        //httpServletResponse.getWriter().write("你被拦截了");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("2---->"+(userService==null));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

        System.out.println("3---->"+(userService==null));
        System.out.println("-------------------->>>>拦截器afterCompletion");
    }
}
