package com.bill.test.netty;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.netty
 * @Description: TODO
 * @date Date : 2019年10月12日 11:24
 */
@Slf4j
public class NettyServerTest {
    public static void main(String[] args) throws Exception {
        //启动server服务
        new NettyServer().bind(8088);
    }

}
