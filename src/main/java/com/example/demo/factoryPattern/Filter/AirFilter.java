package com.example.demo.factoryPattern.Filter;

/**
 * Created by lijiyang on 2018/1/9.
 */
public class AirFilter extends Filter{

    public static class Factory implements com.example.demo.factoryPattern.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
