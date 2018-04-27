package com.example.demo.factoryPattern.belt;

/**
 * Created by lijiyang on 2018/1/9.
 */
public class GeneratorBelt extends Belt{
    public static class Factory implements com.example.demo.factoryPattern.Factory<GeneratorBelt>{
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}
