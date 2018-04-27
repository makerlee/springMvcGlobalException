package com.example.demo.controller;

import com.example.demo.pojo.Article;
import com.example.demo.solr.IVehPackageSolrDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lijiyang on 2017/7/27.
 */
@RestController
@RequestMapping("/solr")
public class SolrController {
    @Autowired
    private IVehPackageSolrDao vehPackageSolrDao;

    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public String save(@RequestParam("title") String title,
                       @RequestParam("url") String url,
                       @RequestParam("id") long id){

        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setUrl(url);
        vehPackageSolrDao.save("webmagic",article);
        return "success";
    }

    @RequestMapping(value = "/article",method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") long id){
        vehPackageSolrDao.delete("webmagic",id+"");
        return "success";
    }


    @CrossOrigin
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Student test(@RequestParam(value = "token",required = false) String token,
                        @RequestBody StudentVo param){

        Student student = new Student();
        //把param里的值复制到真正的Student对象
        BeanUtils.copyProperties(param,student);
        return student;
    }
}

/**
 * Student实体
 */
class Student{
    private String name;
    private String sex;
    private String a;
    private String b;
    private String c;
    private String d;

    //getter and setter...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}

/**
 * 用来接受参数
 */
class StudentVo{
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
