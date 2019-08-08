package com.bill.test.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: TODO 验证可锁的可重入特性
 * 一个线程获取多少次锁，就必须释放多少次锁。这对于内置锁也是适用的，每一次进入和离开synchornized方法(代码块)，
 * 就是一次完整的锁获取和释放
 * Synchronized读与读互斥
 * ReetrantReadWriteLock读与读不互斥、读与写互斥、写与写互斥，效率更高
 * demo参考地址：https://www.jianshu.com/p/9cd5212c8841
 * 总结：
 * 1.Java并发库中ReetrantReadWriteLock实现了ReadWriteLock接口并添加了可重入的特性
 * 2.ReetrantReadWriteLock读写锁的效率明显高于synchronized关键字
 * 3.ReetrantReadWriteLock读写锁的实现中，读锁使用共享模式；写锁使用独占模式，换句话说，读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的
 * 4.ReetrantReadWriteLock读写锁的实现中，需要注意的，当有读锁时，写锁就不能获得；而当有写锁时，除了获得写锁的这个线程可以获得读锁外，其他线程不能获得读锁
 * @date Date : 2019年08月02日 14:38
 */
public class ReetrantReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
        /**1、测试可重入**/
        final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
        Thread t=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        lock.writeLock().lock();
                        System.out.println("Thread real execute");
                        lock.writeLock().unlock();
                    }
                }
        );
        lock.writeLock().lock();
        lock.writeLock().lock();
        t.start();
        Thread.sleep(200);

        System.out.println("realse one once");
        lock.writeLock().unlock();
        /**测试2次lock，单次unlock**/
        lock.writeLock().unlock();

        /**2、测试synchronized实现，多线程同时读取文件**/
        new Thread(new Runnable() {
            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();

        /**3、ReetrantReadWhriteLock实现，多线程同时读取文件**/
        new Thread(new Runnable() {
            @Override
            public void run() {
                getByReadAndWriteLock(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                getByReadAndWriteLock(Thread.currentThread());
            }
        }).start();
    }

    public synchronized  static void get(Thread thread) {
        long l = System.currentTimeMillis();
        System.out.println("start time:" + l);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        System.out.println("耗时:" + (System.currentTimeMillis()-l));
    }

    /**
     * 效率更高
     * @param thread
     */
    public static void getByReadAndWriteLock(Thread thread) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        System.out.println("一共耗时："+(System.currentTimeMillis()-currentTimeMillis));
        lock.readLock().unlock();
    }







}
