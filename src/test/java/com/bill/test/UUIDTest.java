package com.bill.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: TODO
 * @date Date : 2019年11月25日 11:51
 */
@Slf4j
public class UUIDTest {
    @Test
    public void uuid(){
        String s = UUID.randomUUID().toString();
        log.info("输出UUID："+s);
    }
}
