package com.example.demo.distributedLock_redis;

import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Created by lijiyang on 2018/1/16.
 */
public class Test {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        String clientId = UUID.randomUUID().toString();
        boolean b = RedisTool.tryGetDistributedLock(jedis,"lijyang_redis",clientId,5000);
        if(b){
            System.out.println("------------>得到锁");
            try {
                Thread.sleep(3000);//模拟业务操作
                System.out.println("----->业务操作");
                boolean release = RedisTool.releaseDistributedLock(jedis,"lijyang_redis",clientId);
                if (release){
                    System.out.println("------------>释放锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("--------获取锁失败");
        }
    }
}
