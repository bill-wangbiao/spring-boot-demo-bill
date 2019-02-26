package com.bill.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.bill.test.bean.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.controller
 * @Description: JMX监控controller
 * @date Date : 2019年02月26日 13:55
 */
@RestController
public class JmxController {
    private static final Logger logger= LoggerFactory.getLogger(JmxController.class);

    @Autowired
    SimpleBean simpleBean;

    @GetMapping("/jmx")
    public SimpleBean simpleBean(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false)String name,
                                 @RequestParam(required = false) Integer age){
        SimpleBean simpleBean=new SimpleBean();
        if(null !=id){
            simpleBean.setId(id);
        }
        if(!StringUtils.isEmpty(name)){
            simpleBean.setName(name);
        }
        if(null !=age){
            simpleBean.setAge(age);
        }
        logger.info("SimpleBean-->"+ JSONObject.toJSONString(simpleBean));
        return simpleBean;
    }
}
