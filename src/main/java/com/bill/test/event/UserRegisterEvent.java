package com.bill.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.event
 * @Description: TODO 用户注册事件
 * @date Date : 2019年06月12日 11:21
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**注意该处的Object source的序列化机制**/
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
