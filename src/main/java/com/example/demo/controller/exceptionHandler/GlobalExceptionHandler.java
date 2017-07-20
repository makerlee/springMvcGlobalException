package com.example.demo.controller.exceptionHandler;

import com.example.demo.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lijiyang on 2017/7/20.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, ServiceException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();

        r.setMessage(e.getiError().getMessage());
        r.setCode(e.getiError().getCode()+"");
        r.setData("Some Data");
        return r;
    }
}