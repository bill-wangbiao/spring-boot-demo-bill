package com.bill.test.netty;

import io.netty.channel.Channel;

import java.util.UUID;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.netty
 * @Description: TODO
 * @date Date : 2019年10月12日 13:55
 */
public class NettyClientTest {
    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient("127.0.0.1", 8088);
        //启动client服务
        client.start();

        Channel channel = client.getChannel();
        //消息体
        RpcRequest request = new RpcRequest();
        request.setId(UUID.randomUUID().toString());
        request.setData("client.message");
        //channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(request);
    }
}
