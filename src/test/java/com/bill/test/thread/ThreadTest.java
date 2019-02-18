package com.bill.test.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: 线程单元测试
 * @date Date : 2019年02月18日 14:56
 */
public class ThreadTest {
    private static final Logger logger= LoggerFactory.getLogger(ThreadTest.class);
    private static String A = "A";
    private static String B = "B";

    @Test
    public void threadLockTest(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    logger.info("线程1拿到锁A");
                    System.out.println("线程1拿到锁A");
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        logger.info("线程1拿到了锁A之后，又拿到了锁B");
                        System.out.println("thread1输出1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    logger.info("线程2拿到锁B");
                    synchronized (A) {
                        logger.info("线程2拿到锁B之后，又拿到了锁A");
                        System.out.println("thread2输出2");
                    }
                }
            }
        });
        t1.start();
//        t2.start();
    }

    @Test
    public void testSystemOut(){
        logger.info("测试日志输出");
        System.out.println("测试out控制台日志打印");
    }
}
