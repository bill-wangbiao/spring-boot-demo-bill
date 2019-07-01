package com.bill.test.jms2point0;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.jms2point0
 * @Description: TODO 实现了JMS2.0 API（Simplified API）activemq消息生产者
 * JMS1.1 API被称作classic API   Spring JmsTemplate
 * @date Date : 2019年06月28日 16:12
 */
public class JmsProducer {
    /**默认用户名**/
    public static final String username= ActiveMQConnection.DEFAULT_USER;
    /**默认密码**/
    public static final String password=ActiveMQConnection.DEFAULT_PASSWORD;
    /**默认连接地址**/
    public static final String broker_url=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args){
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(username,password,broker_url);

    }
}
