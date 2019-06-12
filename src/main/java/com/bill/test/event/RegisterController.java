package com.bill.test.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.event
 * @Description: TODO 注册服务，事件发布者
 * @date Date : 2019年06月12日 11:24
 */
@RestController
@RequestMapping("/regist")
@Slf4j
public class RegisterController {

    @Autowired
    UserRegisterPublisher userService;

    @RequestMapping
    public String regist(String name){
        log.info("controller get param name:"+name);

        String regist = userService.regist(name);

        return regist;
    }
}
