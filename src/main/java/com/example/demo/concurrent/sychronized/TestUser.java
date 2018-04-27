package com.example.demo.concurrent.sychronized;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lijiyang on 2017/12/26.
 */
public class TestUser {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5000);
        User user = new User();
        user.setId("1");
        user.setName("小米");
        user.setAge(1);

        for(int i=0;i<5000;i++){
            new Thread(() -> {
                UserService service = new UserService();
                service.incAge(user);
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            System.out.println(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
