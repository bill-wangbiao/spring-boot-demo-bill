package com.bill.test.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.lock
 * @Description: TODO
 * @date Date : 2019年06月19日 20:23
 */
@Slf4j
public class Counter {
    /**可重入锁**/
//    private ReentrantLock lock=new ReentrantLock();

    /**不可重入锁**/
//    private SelfUnReentrantLock lock=new SelfUnReentrantLock();

    /**可重入锁**/
    private SelfReentrantLock lock=new SelfReentrantLock();

    private int count=0;

    @Test
    public void incTest() throws InterruptedException {
        lock.lock();
        doAdd();
        lock.unlock();
        log.info("print："+count);
    }

    public int doAdd() throws InterruptedException {
        lock.lock();
        int countAdd=this.count++;
        lock.unlock();
        return countAdd;
    }
}
