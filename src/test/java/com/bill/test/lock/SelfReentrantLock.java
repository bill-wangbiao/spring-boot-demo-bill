package com.bill.test.lock;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.lock
 * @Description: TODO 可重入锁 线程可以进入任何一个它已经拥有的锁 所同步着的代码块
 * @date Date : 2019年06月19日 20:47
 */
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
}
