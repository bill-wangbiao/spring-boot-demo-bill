package com.bill.test.thread;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: 死锁单元测试类
 * @date Date : 2019年02月18日 15:55
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";
    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        System.out.println("线程1等待2秒");
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1拿到了锁A");
                    synchronized (B) {
                        System.out.println("线程1拿到了锁A后又拿到了锁B");
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    System.out.println("线程2拿到了锁B");
                    synchronized (A) {
                        System.out.println("线程2拿到了锁B后又拿到了线程A");
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
