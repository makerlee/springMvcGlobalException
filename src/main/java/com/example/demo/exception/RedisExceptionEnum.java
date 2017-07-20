package com.example.demo.exception;

/**
 * Created by lijiyang on 2017/7/19.
 */
public enum RedisExceptionEnum implements IError{

    UNIQUE_KEY(10001, "主键约束错误"),
    KEY_NOT_EXIST(10002,"key不存在");

    public int code;
    public String message;

    private RedisExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}