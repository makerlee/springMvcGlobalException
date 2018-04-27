package com.example.demo.factoryPattern;

import com.example.demo.factoryPattern.Filter.AirFilter;
import com.example.demo.factoryPattern.Filter.FuelFilter;
import com.example.demo.factoryPattern.belt.FanBelt;
import com.example.demo.factoryPattern.belt.GeneratorBelt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lijiyang on 2018/1/9.
 */
public class Part {
    static List<Factory<? extends Part>> partFactories = new ArrayList<>();

    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
    }

    private static Random rand = new Random();
    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(4));
    }
}
