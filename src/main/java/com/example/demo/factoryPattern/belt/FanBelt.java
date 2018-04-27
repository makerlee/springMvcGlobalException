package com.example.demo.factoryPattern.belt;

/**
 * Created by lijiyang on 2018/1/9.
 */
public class FanBelt extends Belt{
    public static class Factory implements com.example.demo.factoryPattern.Factory<FanBelt>{
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
