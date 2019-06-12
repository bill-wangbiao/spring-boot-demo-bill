package com.bill.test.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.event
 * @Description: TODO 发布事件
 * @date Date : 2019年06月12日 11:28
 */
@Service
@Slf4j
public class UserRegisterPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }

    public String regist(String name){
        log.info("regist get param name:"+name);
        log.info("事件发布者发布注册事件");
        /**发布注册事件**/
        applicationEventPublisher.publishEvent(new UserRegisterEvent(name));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("测试事件发布是否是异步 ,测试结果：默认情况下，整个过程包含事件发布，监听是同步的。");
        return "regist success!";
    }
}
