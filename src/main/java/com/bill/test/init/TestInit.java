package com.bill.test.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.init
 * @Description: TODO
 * @date Date : 2019年11月28日 14:13
 */
@Component
@Slf4j
public class TestInit implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("测试启动时，是否打印日志到：/var/log/messages中");
    }
}
