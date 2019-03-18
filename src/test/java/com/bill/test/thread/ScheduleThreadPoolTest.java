package com.bill.test.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: 定时线程池单元测试类
 *  创建一个定长线程池，支持定时及周期性任务执行
 * @date Date : 2019年03月18日 13:45
 */
@Slf4j
public class ScheduleThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
        scheduledThreadPool.shutdown();
        scheduledThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        log.info("所有线程执行完毕");

        ScheduledExecutorService scheduledThreadPool1 = Executors.newScheduledThreadPool(5);
        scheduledThreadPool1.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
