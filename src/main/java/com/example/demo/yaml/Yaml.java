package com.example.demo.yaml;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijiyang on 2017/9/20.
 */
@RestController
@RequestMapping(value = "/yaml")
public class Yaml {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap();
        map.put("status","200");

    }
}
