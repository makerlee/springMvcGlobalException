package com.example.demo.reflect;

/**
 * Created by lijiyang on 2017/12/12.
 */
public class Car {
    private String brand;
    private String color;
    private String vehNO;

    public Car() {
    }

    public Car(String brand, String color, String vehNO) {
        this.brand = brand;
        this.color = color;
        this.vehNO = vehNO;
    }

    public void getInfo(){
        System.out.println("brand:" + brand + ", color:" + color + ", vehNO:" + vehNO);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehNO() {
        return vehNO;
    }

    public void setVehNO(String vehNO) {
        this.vehNO = vehNO;
    }
}
