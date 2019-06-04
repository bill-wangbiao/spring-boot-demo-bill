package com.bill.test.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.controller
 * @Description: TODO 多个请求的结果，使用另一个请求控制响应返回
 * @date Date : 2019年06月03日 10:25
 */
@RestController
@RequestMapping("/async")
@Log4j
public class AsyncController {
    final Map<Integer,DeferredResult> deferredResultMap=new ConcurrentHashMap<>();

    @GetMapping("/longPolling")
    public DeferredResult longPolling(){
        DeferredResult deferredResult=new DeferredResult(0L);
        deferredResultMap.put(deferredResult.hashCode(),deferredResult);
        deferredResult.onCompletion(()->{
            deferredResultMap.remove(deferredResult.hashCode());
            System.err.println("还剩"+deferredResultMap.size()+"个deferredResult未响应");
        });
        return deferredResult;
    }

    @GetMapping("/returnLongPollingValue")
    public void returnLongPollingValue(){
        for (Map.Entry<Integer,DeferredResult> entry:deferredResultMap.entrySet()){
            entry.getValue().setResult("kl");
        }
    }

}
