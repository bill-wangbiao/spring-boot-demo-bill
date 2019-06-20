package com.bill.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.bill.test.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.controller
 * @Description: TODO Springboot — 用更优雅的方式发HTTP请求(RestTemplate详解)
 * @date Date : 2019年06月13日 14:11
 */
@RestController
@Slf4j
public class RestTemplateController {

    private RestTemplate restTemplate=new RestTemplate();

    @RequestMapping("/getForObject")
    public User getForObject(){
        String url="http://localhost:8088/index/get?name={name}";
        Map map=new HashMap<String,String>(1);
        map.put("name","http");
        log.info("请求url:"+url+"，请求参数："+ JSONObject.toJSONString(map));
        User result = restTemplate.getForObject(url, User.class, map);
        log.info("使用RestTemplate发送Http请求，获取的结果："+JSONObject.toJSONString(result));
        return result;
    }

    @RequestMapping("/getUser")
    public User getUser(){
        String url="http://localhost:8088/index/get/{1}/{2}";
        log.info("请求url:"+url);
        User result = restTemplate.getForObject(url, User.class, 2,"bill");
        log.info("使用RestTemplate发送Http请求，获取的结果："+JSONObject.toJSONString(result));
        return result;
    }
}
