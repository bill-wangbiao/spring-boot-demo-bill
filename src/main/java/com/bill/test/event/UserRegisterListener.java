package com.bill.test.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.event
 * @Description: TODO 监听事件发布，做异步通知等
 * @date Date : 2019年06月12日 11:38
 */
@Service
@Slf4j
public class UserRegisterListener implements ApplicationListener<UserRegisterEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    /**使用@Async注解，代表异步，些方法将在执行的时候，将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作。**/
    @Async
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("通知服务监听到了消息，获取到的消息是："+event.getSource().toString()+"，接下来可以做一些异步操作，调用RPC服务等等");
    }
}
