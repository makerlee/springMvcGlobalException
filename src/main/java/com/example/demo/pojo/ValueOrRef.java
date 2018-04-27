package com.example.demo.pojo;

/**
 * Created by lijiyang on 2018/4/20.
 */
public class ValueOrRef {
    public static void main(String[] args) {
        Person zhangsan = new Person("zs");
        changePerson(zhangsan);
        zhangsan.printName();
    }

    public static void changePerson(Person person){
        person = new Person("Lisi");
    }
}
class Person{
    String name = "de";

    public Person(String name) {
        this.name = name;
    }

    void printName(){
        System.out.println(this.name);
    }
}
