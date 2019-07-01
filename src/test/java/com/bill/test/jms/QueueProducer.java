package com.bill.test.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.jms
 * @Description: TODO 消息生产者，JMS规范的体系架构：分为点对点模型，发布订阅模型
 * 启动rocketmq服务：D:\github\activemq\apache-activemq-5.15.9-bin\apache-activemq-5.15.9\bin\win64
 * 控制台地址：http://localhost:8161/admin/queues.jsp
 * 账号密码：admin  admin
 * @date Date : 2019年06月28日 14:54
 */
public class QueueProducer {
    /**默认用户名**/
    public static final String username= ActiveMQConnection.DEFAULT_USER;
    /**默认密码**/
    public static final String password=ActiveMQConnection.DEFAULT_PASSWORD;
    /**默认连接地址**/
    public static final String broker_url=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args){
        /**创建连接工厂**/
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(username,password,broker_url);
        try {
            /**创建连接**/
            Connection connection=connectionFactory.createConnection();
            /**启动连接**/
            connection.start();
            /**创建会话**/
            Session session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            /**创建队列，需要指定队列名称，消息生产者和消息消费者，将根据它们来发送，接收对应的消息**/
            Queue myTestQueue=session.createQueue("activemq-queue-test1");
            /**消息生产者**/
            MessageProducer producer=session.createProducer(myTestQueue);

            /**以下注释代码：topic订阅发布模式**/
//            Topic topic = session.createTopic("activemq-topic-test1");
//            MessageProducer producer1 = session.createProducer(topic);

            /**创建一个消息对象**/
            TextMessage message=session.createTextMessage();
            message.setText("测试点对点的一条消息11");
            /**发送消息**/
            producer.send(message);
            /**提交事务**/
            session.commit();
            /**关闭资源**/
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
