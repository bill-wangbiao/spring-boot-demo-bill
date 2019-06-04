package com.bill.test.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.queue
 * @Description: TODO
 * @date Date : 2019年06月04日 15:16
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //声明一个容量为10的缓存队列
        BlockingQueue<String> queue=new LinkedBlockingDeque<String>(10);

        //new 三个生产者和一个消费者
        Producer p1=new Producer(queue);
        Producer p2=new Producer(queue);
        Producer p3=new Producer(queue);

        Consumer c=new Consumer(queue);

        ExecutorService executorService= Executors.newCachedThreadPool();
        //启动线程
        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(p3);
        executorService.execute(c);

        Thread.sleep(10*1000);
        p1.stop();
        p2.stop();
        p3.stop();

        Thread.sleep(2000);
        executorService.shutdown();
    }
}
