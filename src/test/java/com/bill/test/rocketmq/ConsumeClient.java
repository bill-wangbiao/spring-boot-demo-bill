package com.bill.test.rocketmq;

import java.io.IOException;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.rocketmq
 * @Description: TODO
 * @date Date : 2019年06月25日 16:54
 */
public class ConsumeClient {
    public static void main(String[] args) throws IOException {
        MqClient client=new MqClient();
        String message=client.consume();
        System.out.println("获取的消息是："+message);
    }
}
