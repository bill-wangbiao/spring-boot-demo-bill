package com.bill.test.lock;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.lock
 * @Description: TODO 不可重入锁,也成为自旋锁，不可对锁重复获取，必须先把锁释放再次获取
 * @date Date : 2019年06月19日 20:35
 */
public class SelfUnReentrantLock {
    private boolean isLocked=false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            wait();
        }
        isLocked=true;
    }

    public synchronized void unlock(){
        isLocked=false;
        notify();
    }
}
