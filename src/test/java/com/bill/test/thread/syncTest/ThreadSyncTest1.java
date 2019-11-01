package com.bill.test.thread.syncTest;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: 线程同步的几种同步方案：
 * 1、同步方法
 * 2、同步块
 * 3、特殊变量volatile
 * 4、使用重入锁实现线程同步
 * 5、局部变量实现线程同步
 * @date Date : 2019年11月01日 10:30
 */
public class ThreadSyncTest1 {
    public static void main(String args[]) {
        final Bank1 bank = new Bank1();
        Thread tadd = new Thread(new Runnable() {
            @Override
            public  void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                }
            }
        });

        Thread tsub = new Thread(new Runnable() {
            @Override
            public  void run() {
                while(true){
                    bank.subMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tsub.start();
        tadd.start();
    }

}
