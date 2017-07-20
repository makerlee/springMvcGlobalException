package com.example.demo.controller;

import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lijiyang on 2017/5/25.
 */
@RestController
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class Controller {
    @Value("${words}")
    String words;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(@RequestParam("name") String name,
                       @RequestBody Key[] keys){
        System.out.println("------------->name="+name);

        for (Key key : keys){
            System.out.println("id="+key.getId()+",value="+key.getValue());
            int useFlag=key.getId();
            System.out.println(useFlag==0?1:0);
        }
        return name;
    }

    @RequestMapping("/")
    public String getWord(){
        String[] arr=words.split(",");
        int i = (int)Math.round(Math.random() * (arr.length - 1));
        return arr[i];
    }

    @RequestMapping("/user/{id}")
    public String addUser(@PathVariable("id") int id){
        userService.addUser(id);
        return id+"";
    }

    @RequestMapping(value = "/redis/{key}",method = RequestMethod.GET)
    public String addKey(@PathVariable("key") String key){
        boolean b= userService.addKV(key,"null");
        String result = "SUCCESS";
        if(!b){
            result = "FAILED";
        }
        return result;
    }
}
