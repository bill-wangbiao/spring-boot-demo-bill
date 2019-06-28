package com.bill.test.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.jms
 * @Description: TODO 消息消费者
 * @date Date : 2019年06月28日 15:11
 */
public class QueueConsumer {
    public static final String username= ActiveMQConnection.DEFAULT_USER;
    public static final String password=ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String broker_url=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args){
        //创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(username,password,broker_url);

        try {
            //创建连接
            Connection connection=connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建会话
            final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建队列，作为消费者消费消息的目的地
            Queue myTestQueue=session.createQueue("activemq-queue-test1");
            //消息消费者
            MessageConsumer consumer=session.createConsumer(myTestQueue);
            //消费者实现监听接口消费消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        TextMessage textMessage=(TextMessage) message;
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    try {
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            //让主线程休眠100秒，使消息消费者对象能够继续存活一段时间，从而能监听到消息
            try {
                Thread.sleep(100*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //关闭资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
