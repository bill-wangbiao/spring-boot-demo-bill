package com.bill.test.rocketmq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.rocketmq
 * @Description: TODO
 * @date Date : 2019年06月25日 16:36
 */
public class MqClient {
    /**生产消息**/
    public static void produce(String message) throws IOException {
        Socket socket=new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        try (
           PrintWriter out=new PrintWriter(socket.getOutputStream())
                ){
            out.println(message);
            out.flush();
        }
    }

    /**消费消息**/
    public static String consume() throws IOException {
        Socket socket=new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        try (
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out=new PrintWriter(socket.getOutputStream())
                ){
            /**先向消息队列发送字符串CONSUMER，表示消费**/
            out.println("CONSUME");
            out.flush();
            /**再从消息队列获取一条消息**/
            String message=in.readLine();
            return message;
        }
    }
}
