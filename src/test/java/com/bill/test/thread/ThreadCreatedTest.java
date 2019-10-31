package com.bill.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: TODO
 * 1、继承Thread类创建线程
 * 2、实现Runnable接口创建线程
 * 3、使用Callable和Future创建线程
 * 4、使用线程池例如Executor框架
 * @date Date : 2019年10月31日 10:11
 */
public class ThreadCreatedTest {
    public static void main(String[] args){
        /**创建并启动线程**/
        new MyThread().start();

        /**实现runnable接口**/
        MyThread2 myThread2=new MyThread2();
        Thread thread2=new Thread(myThread2);
        thread2.start();

        /**使用Callable和Future创建线程**/
        Callable<Integer> myCallable=new MyCallable();
        FutureTask<Integer> future=new FutureTask<>(myCallable);
        for(int i=0;i<3;i++){
            new Thread(future).start();
        }
        System.out.println("主线程for循环执行完毕..");
        try {
            /**取得新创建的新线程中的call()方法返回的结果**/
            int sum = future.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        /**使用线程池例如Executor框架创建线程
         * 1、Executor执行Runnable任务
         * 2、Executor执行Callable任务
         * 3、ThreadPoolExecutor创建自定义线程池**/
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            executorService.execute(new MyThread2());
            System.out.println("************* a" + i + " *************");
        }
        executorService.shutdown();


        ExecutorService executorService1 = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();
        //创建10个任务并执行
        for (int i = 0; i < 10; i++){
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future1 = executorService1.submit(new TaskWithResult(i));
            //将任务执行结果存储到List中
            resultList.add(future1);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList){
            try{
                while(!fs.isDone());//Future返回如果没有完成，则一直循环等待，直到Future返回完成
                System.out.println("executorService1:"+fs.get());     //打印各个线程（任务）执行的结果
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }finally{
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                executorService.shutdown();
            }
        }




        //创建等待队列
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
        //创建线程池，池中保存的线程数为3，允许的最大线程数为5
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50,TimeUnit.MILLISECONDS,bqueue);
        //创建七个任务
        Runnable t1 = new MyThread();
        Runnable t2 = new MyThread();
        Runnable t3 = new MyThread();
        Runnable t4 = new MyThread();
        Runnable t5 = new MyThread();
        Runnable t6 = new MyThread();
        Runnable t7 = new MyThread();
        //每个任务会在一个线程上执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        //关闭线程池
        pool.shutdown();


        /**
         * 总结：
         * 实现Runnable和实现Callable接口的方式基本相同，不过是后者执行call()方法有返回值，前者线程执行体run()方法无返回值，
         * 因此可以把这两种方式归为一种，这种方式与继承Thread类的方法之间的差别如下：
         *
         * 1、线程只是实现Runnable或实现Callable接口，还可以继承其他类。
         *
         * 2、这种方式下，多个线程可以共享一个target对象，非常适合多线程处理同一份资源的情形。
         *
         * 3、但是编程稍微复杂，如果需要访问当前线程，必须调用Thread.currentThread()方法。
         *
         * 4、继承Thread类的线程类不能再继承其他父类（Java单继承决定）。
         *
         * 5、前三种的线程如果创建关闭频繁会消耗系统资源影响性能，而使用线程池可以不用线程的时候放回线程池，用的时候再从线程池取，项目开发中主要使用线程池
         *
         * 注：在前三种中一般推荐采用实现接口的方式来创建多线程
         *
         * 项目开发中：使用线程池实现Runnable和Callable接口创建线程。
         */


    }
    /**********************************************************************/
    /**
     * 继承Thread类
     */
    /**********************************************************************/
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("#############线程MyThread已经启动"+Thread.currentThread().getName()+"################");
        }
    }

    /**********************************************************************/
    /**
     * 实现runnable接口
     */
    /**********************************************************************/
    static class MyThread2 implements Runnable{
        @Override
        public void run(){
            System.out.println("#############线程MyThread2已经启动"+Thread.currentThread().getName()+"################");
        }
    }

    /**********************************************************************/
    /**
     * 使用Callable和Future创建线程
     */
    /**********************************************************************/
    static class MyCallable implements Callable<Integer> {
        private int i = 0;
        @Override
        public Integer call(){
            int sum = 0;
            for (; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "----" + i);
                sum += i;
            }
            return sum;
        }
    }

    static class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        /**
         * 任务的具体过程，一旦任务传给ExecutorService的submit方法，
         * 则该方法自动在一个线程上执行
         */
        @Override
        public String call() throws Exception {
            System.out.println("call()方法被自动调用！！！    " + Thread.currentThread().getName());
            //该返回结果将被Future的get方法得到
            return "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();
        }
    }

}
