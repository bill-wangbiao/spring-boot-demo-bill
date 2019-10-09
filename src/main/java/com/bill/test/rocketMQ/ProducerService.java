package com.bill.test.rocketMQ;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.rocketMQ
 * @Description: TODO
 * @date Date : 2019年10月09日 16:06
 */
@Service
@Slf4j
public class ProducerService {
    private DefaultMQProducer producer=null;

    @PostConstruct
    public void initMQProducer(){
        producer=new DefaultMQProducer("producerGroupName");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setRetryTimesWhenSendAsyncFailed(3);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            log.error("启动报错：",e);
        }
    }

    public void send(String topic,String msgStr){
        Message msg=new Message(topic,"","",msgStr.getBytes());
        try {
            SendResult send = producer.send(msg);
            log.info("发送结果："+ JSONObject.toJSONString(send));
        } catch (MQClientException e) {
            e.printStackTrace();
            log.error("MQClientException：",e);
        } catch (RemotingException e) {
            e.printStackTrace();
            log.error("RemotingException：",e);
        } catch (MQBrokerException e) {
            e.printStackTrace();
            log.error("MQBrokerException：",e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("InterruptedException：",e);
        }
        return;
    }

    @PreDestroy
    public void shutdownProducer(){
        if(null !=producer){
            producer.shutdown();
        }
    }
}
