package com.example.demo.service.impl;

import com.example.demo.exception.RedisExceptionEnum;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijiyang on 2017/7/17.
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private RedisTemplate<String,String> template;

    @Autowired
    private TestAAA aaa;


    public TestAAA getAaa(){
        return aaa;
    }

    @Override
    public void addUser(int id) {
        // TODO: 2017/7/17  模拟添加用户
        System.out.println("add user success!!!---->id= "+id);
    }

    @Override
    public boolean addKV(String key, String val) {
        HashOperations<String, Object, Object> hash = template.opsForHash();
        Map<String,String> map =new HashMap<>();
        map.put("name","john");
        map.put("age","100");
        map.put("sex","男");
        hash.putAll("user001",map);
        boolean b = template.hasKey(key);

        if(!b){
            throw new ServiceException(RedisExceptionEnum.KEY_NOT_EXIST);
        }

        return b;
    }
}
