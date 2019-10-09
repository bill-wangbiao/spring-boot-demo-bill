package com.bill.test.controller;

import com.bill.test.rocketMQ.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.controller
 * @Description: TODO
 * @date Date : 2019年10月09日 16:21
 */
@RestController
@Slf4j
public class RocketMQController {
    @Autowired
    ProducerService producer;

    @GetMapping("/rocketmqProduce")
    public String produce(String topic,String msg){
        producer.send(topic,msg);
        return "success";
    }
}
