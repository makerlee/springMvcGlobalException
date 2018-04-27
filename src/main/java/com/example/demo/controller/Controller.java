package com.example.demo.controller;

import com.example.demo.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lijiyang on 2017/5/25.
 */
@RestController
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class Controller {
    public static final Logger logger = Logger.getLogger(Controller.class);

    @Value("${words}")
    String words;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(@RequestParam("name") String name,
                       @RequestBody Key key){
        logger.error("this just a test error info");
        logger.info("this just a test info for elk");

        KeyChild keyChild = (KeyChild)key;

        System.out.println(keyChild);

        logger.error(key);
        return name;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://".concat("localhost:8012").concat("/test").concat("?name={name}");
        KeyChild key = new KeyChild();
        key.setId(1);
        key.setValue("123");
        key.setExt("123");

        LinkedMultiValueMap uriParams = new LinkedMultiValueMap();
        uriParams.add("name", "123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity req = new HttpEntity<>(key, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, req, String.class,uriParams);
        System.out.println(result.getBody().toString());
        System.out.println("============================");
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

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add(@RequestParam("name") String name){
        System.out.println("---------->"+name);
    }
}
