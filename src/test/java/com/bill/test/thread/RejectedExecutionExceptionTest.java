package com.bill.test.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: 拒绝被执行异常单元测试
 * 该异常的原因是：线程池配置不合理，导致提交的任务来不及处理。
 * 异常场景1：
 *  调用shutdown()方法关闭了ThreadPoolExecutor线程池，又提交新任务给ThreadPoolExecutor线程池执行，一般调用shutdown()方法之后
 *  ，jvm会得到一个关闭线程池的信号，并不会立即关闭线程池，原来线程池里未执行完的任务仍然在执行，等到任务全部执行完之后才关闭
 *  线程池，但是jvm不允许再提交新的任务给线程池。
 * @date Date : 2019年09月20日 16:23
 */
@Slf4j
public class RejectedExecutionExceptionTest {
    /**
     * 内部类Worker
     */
    static class Worker implements Runnable {
        private int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                log.info(Thread.currentThread().getName()+" 执行任务 "+id);
                Thread.sleep(1000);
                log.info(Thread.currentThread().getName()+" 完成任务 "+id);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("执行异常",e);
            }
        }
    }

    private static void execut() {
        /**
         * 创建一个数量为3的线程池，这三个线程在占用期间，如果有新的任务提交到线程池，那么新的任务会保存在BlockingQueue
         * 阻塞队列里，以等待被空闲线程取出并执行。在这里我们使用一个大小为15的ArrayBlockingQueue队列来保存待执行的任务，
         * 然后我们创建10个任务提交给ThreadPoolExecutor线程池。
         */
        ExecutorService executor=new ThreadPoolExecutor(
                3,
                3,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15));
        Worker[] tasks=new Worker[10];
        for(int i=0;i<10;i++){
            tasks[i]=new Worker(i);
            log.info("提交任务："+tasks[i]+","+i);
            executor.execute(tasks[i]);
        }
        log.info("主线程结束。。。");
        /**关闭线程池**/
        executor.shutdown();

        /**异常场景1，线程池关闭之后，提交新的任务到线程池
         * 运行之后抛异常**/
        try {
            executor.execute(tasks[0]);
        } catch (Exception e) {
            log.error("执行异常：",e);
        }
    }

    /**
     * 不适用该方式做测试
     */
//    @Test
//    public void testWorker(){
//        execut();
//    }
    /**两者区别很大，@Test测试逻辑中如果包含子线程，子线程会随着主线程的执行完毕儿结束
     * 对于main线程则相反，如果有子线程，两者是相互独立的，互不影响**/
    public static void main(String[] args){
        /**场景1**/
//        execut();
        try {
            /**场景2：
             * 要提交给阻塞队列的任务超出了该队列的最大容量。当线程池里的线程都繁忙的时候，新任务会被提交给阻塞队列保存，这个阻塞队列一旦包和，线程池
             * 就会拒绝接收新的任务，抛出异常**/
            ExecutorService executor = new ThreadPoolExecutor(3, 3, 0L,
                    TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(15));

            /**提交20个任务给线程池**/
            Worker tasks[] = new Worker[20];
            for (int i = 0; i < 20; i++) {
                tasks[i] = new Worker(i);
                System.out.println("提交任务: " + tasks[i] + ", " + i);
                executor.execute(tasks[i]);
            }
            System.out.println("主线程结束");
            executor.shutdown();
        } catch (Exception e) {
            log.error("场景2执行异常：",e);
        }
        /**解决办法：
         * 1、当调用了线程池的shutdown()方法以后，不要提交新任务给线程池
         *2、不要提交大量超过线程池处理能力的任务，这时可能会导致队列饱和，抛出异常。
         *
         * 对于第二种情况，我们很容易解决。我们可以选择一种不需要设置大小限制的数据结构，比如 LinkedBlockingQueue 阻塞队列。
         * 在使用 LinkedBlockingQueue 队列以后，如果还出现 RejectedExecutionException 异常，就要将问题的重点放在第一种情况上。
         * 如果第一种情况不是产生问题的原因，那么我们还需要寻找更复杂的原因。比如，由于线程死锁和 LinkedBlockingQueue 饱和，导致内存占用过大，
         * 这个时候我们就需要考虑JVM可用内存的问题了。
         *
         * 对于第二种情况，通常有一些隐藏的信息被我们忽略。其实我们可以给使用 ArrayBlockingQueue 作为阻塞队列的
         * ThreadPoolExecutor 线程池提交超过15个的任务，只要我们在提交新任务前设置一个完成原来任务的等待时间，
         * 这时3个线程就会逐渐消费 ArrayBlockingQueue 阻塞队列里的任务，而不会使它堵塞。**/

        /**
         *     1、java线程池的实现？
         *         原理：
         *         其实java线程池的实现原理很简单，说白了就是一个线程集合workerSet和一个阻塞队列workQueue。当用户向线程池提交一个任务(也就是线程)时，
         *         线程池会先将任务放入workQueue中。
         *         workerSet中的线程会不断的从workQueue中获取线程然后执行。当workQueue中没有任务的时候，worker就会阻塞，直到队列中有任务了就取出来继续执行。
         *          线程池的几个主要参数的作用：
         *         corePoolSize：规定线程池有几个线程（worker）在运行
         *         maximumPoolSize：当workQueue满了，不能添加任务的时候，这个参数才会生效。规定线程池最多只能有多少个线程（worker）在执行。
         *         keepAliveTime：超过corePoolSize大小的那些线程的生存时间，这些线程如果长时间没有执行任务并且超过了keepAliveTime设定的时间，就会消亡。
         *         unit：生存时间对应的单位。
         *         workQueue：存放任务的队列
         *         threadFactory：创建你线程的工厂
         *         handler：当workQueue已经满了，并且线程池线程数已经达到maximumPoolSize将执行拒绝策略。
         *         流程分析：
         *         1、判断当前运行的worker数量是否超过corePoolSize，如果不超过corePoolSize，就创建一个worker直接执行该任务。
         *         2、如果正在运行的worker数量超过或者等于corePoolSize，那么就将该任务加入到workQueue队列中。
         *         3、如果workQueue队列满了，offer返回false的话，就检查当前运行的worker的数量是否小于maximumPoolSize，如果小于就创建一个worker直接执行该任务。
         *         4、如果当前运行的worker数量大于等于maximumPoolSize，那么就执行RejectedExecutionHandler来拒绝这个任务的提交。
         */
    }
}
