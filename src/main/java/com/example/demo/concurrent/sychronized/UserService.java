package com.example.demo.concurrent.sychronized;

/**
 * Created by lijiyang on 2017/12/26.
 */
public class UserService {

    public void incAge(User user){
        synchronized (user){
            user.setAge(user.getAge()+1);
        }
    }


    public static void main(String[] args) {
        System.out.println(""+0x28);
    }
}
