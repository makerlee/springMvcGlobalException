package com.example.demo.factoryPattern.Filter;

/**
 * Created by lijiyang on 2018/1/9.
 */
public class FuelFilter extends Filter{
    public static class Factory implements com.example.demo.factoryPattern.Factory<Filter>{
        @Override
        public Filter create() {
            return new FuelFilter();
        }
    }
}
