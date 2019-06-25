package com.bill.test.rocketmq;

import java.io.IOException;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.rocketmq
 * @Description: TODO
 * @date Date : 2019年06月25日 16:47
 */
public class ProduceClient {
    public static void main(String[] args) throws IOException {
        MqClient client=new MqClient();
        client.produce("hello bill");
    }
}
