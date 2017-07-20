package com.example.demo.exception;

/**
 * Created by lijiyang on 2017/7/19.
 */
public class ServiceException extends RuntimeException{
    private IError iError;
    public ServiceException(IError exceptionEnums){
        this.iError = exceptionEnums;
    }
    public IError getExceptionEnums(){
        return iError;
    }

    public IError getiError() {
        return iError;
    }

    public void setiError(IError iError) {
        this.iError = iError;
    }
}
