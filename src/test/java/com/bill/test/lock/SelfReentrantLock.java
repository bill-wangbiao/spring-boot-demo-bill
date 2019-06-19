package com.bill.test.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.lock
 * @Description: TODO 可重入锁 线程可以进入任何一个它已经拥有的锁 所同步着的代码块
 * java中常用的可重入锁
 *
 * synchronized
 *
 * java.util.concurrent.locks.ReentrantLock
 *
 * ps:顺便记录下java中实现原子操作的类（记录至http://blog.csdn.net/huzhigenlaohu/article/details/51646455）
 *
 * AtomicIntegerFieldUpdater:原子更新整型的字段的更新器
 * AtomicLongFieldUpdater：原子更新长整型字段的更新器
 * AtomicStampedReference:原子更新带有版本号的引用类型。该类将整型数值与引用关联起来，可用于原子的更新数据和数据的版本号，可以解决使用CAS进行原子更新时可能出现的ABA问题。
 * AtomicReference ：原子更新引用类型
 * AtomicReferenceFieldUpdater ：原子更新引用类型里的字段
 * AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子更新一个布尔类型的标记位和应用类型
 * AtomicIntegerArray ：原子更新整型数组里的元素
 * AtomicLongArray :原子更新长整型数组里的元素
 * AtomicReferenceArray : 原子更新引用类型数组的元素
 * AtomicBooleanArray ：原子更新布尔类型数组的元素
 * AtomicBoolean ：原子更新布尔类型
 * AtomicInteger： 原子更新整型
 * AtomicLong: 原子更新长整型
 * @date Date : 2019年06月19日 20:47
 */
@Slf4j
public class SelfReentrantLock {
    boolean isLocked=false;
    Thread lockedBy=null;
    int lockCount=0;

    public synchronized void lock() throws InterruptedException {
        Thread thread=Thread.currentThread();
        while (isLocked && lockedBy!=thread){
            wait();
        }
        isLocked=true;
        lockCount++;
        lockedBy=thread;
    }

    public synchronized void unlock(){
        if(Thread.currentThread()==lockedBy){
            lockCount--;
            if(lockCount==0){
                isLocked=false;
                notify();
            }
        }
    }

    /**原子操作**/
    private static final AtomicBoolean destroyed = new AtomicBoolean(false);
    private AtomicInteger inc=new AtomicInteger(0);

    @Test
    public void destroyAllTest(){
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run(){
                    destroyAll();
                }
            }.start();
        }
    }
    public void destroyAll(){
        if (destroyed.compareAndSet(false, true)) {
            inc.getAndIncrement();
            System.out.println("首次进入。。。。:"+inc);
            return;
        }
        System.out.println("非首次,第"+inc.incrementAndGet()+"次进入。。。。:");
    }
}
