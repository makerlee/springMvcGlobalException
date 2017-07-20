package com.example.demo.controller;

import java.io.Serializable;

/**
 * Created by lijiyang on 2017/7/3.
 */
public class Key implements Serializable{
    private int id;
    private String value;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
