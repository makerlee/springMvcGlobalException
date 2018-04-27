package com.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lijiyang on 2017/10/17.
 */
@RestController
@RequestMapping(value = "/mongo")
public class MongoController {
    @Autowired
    @Qualifier("mongodbVs")
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(@RequestParam("name") String name) throws JsonProcessingException {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("name").is(name);
        query.addCriteria(criteria);
        DBObject dbObject = query.getQueryObject();
        DBCursor cusor = mongoTemplate.getDb().getCollection("student").find(dbObject);

        String result = "没有结果";
        while(cusor.hasNext()){
            DBObject object = cusor.next();
            Map<String,String> map = (Map<String, String>) object;
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(map);
            System.out.println(mapper.writeValueAsString(map));
        }
        return result;
    }


}
