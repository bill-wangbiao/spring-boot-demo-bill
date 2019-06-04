package com.bill.test.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.queue
 * @Description: TODO 生产者线程
 * @date Date : 2019年06月04日 14:50
 */
public class Producer implements Runnable{
    private volatile boolean  isRunning = true;//是否在运行标志
    private BlockingQueue queue;//阻塞队列
    private static AtomicInteger count = new AtomicInteger();//自动更新的值
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue queue){
        this.queue=queue;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        String data=null;
        Random r=new Random();
        System.out.println("启动生产者线程！");
        try {
            while (isRunning){
                System.out.println("正在生产数据。。。");
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                //以原子方式将count当前值加1
                data="data:"+count.incrementAndGet();
                System.out.println("将数据："+data+"放入队列。。。");
                //设定的等待时间为2s，如果超过2s还没加进去返回true
                if(!queue.offer(data, 2,TimeUnit.SECONDS)){
                    System.out.println("放入数据失败：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop(){
        isRunning=false;
    }
}
