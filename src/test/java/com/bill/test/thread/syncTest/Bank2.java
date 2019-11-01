package com.bill.test.thread.syncTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread.syncTest
 * @Description: TODO
 * @date Date : 2019年11月01日 10:34
 */
public class Bank2 {
    private int count =0;//账户余额

    //需要声明这个锁
    private Lock lock = new ReentrantLock();

    //存钱
    public  void addMoney(int money){
        lock.lock();//上锁
        try {
            count +=money;
            System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+"存进："+money);
        }finally {
            lock.unlock();
        }

    }

    //取钱
    public  void subMoney(int money){
        lock.lock();
        try {
            if(count-money < 0){
                System.out.println("余额不足");
                return;
            }
            count -=money;
            System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+"取出："+money);
        }finally {
            lock.unlock();
        }
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
