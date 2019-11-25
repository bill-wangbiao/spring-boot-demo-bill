package com.bill.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.controller
 * @Description: TODO
 * @date Date : 2019年11月07日 11:20
 */
@RestController
@RequestMapping(value = "/thread")
@Slf4j
public class ThreadController {
    /**
     * 访问时间：http://localhost:8088/thread/process?start=0&end=18
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("/process")
    @ResponseBody
    public String process(int start, int end) {
        long time = System.currentTimeMillis();
        final int pool = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(pool);
        try {
            for (int i = 0; i < pool; i++) {
                executorService.execute(new MyRunnable(i, start, end));
            }
            //禁止新建线程，老线程继续执行
            executorService.shutdown();
            while (true){
                //线程全部实行完毕
                if (executorService.isTerminated()){
                    log.info("-----------------所有子线程全部执行完毕，耗时：" + (System.currentTimeMillis() - time) + "毫秒");
                    return "success:耗时：" + (System.currentTimeMillis() - time) + "毫秒";
                }
            }
        } catch (Exception e) {
            log.error("线程执行异常，", e);
            return "error:耗时：" + (System.currentTimeMillis() - time) + "毫秒";
        } finally {
            log.info("执行到了finally语句");
        }
    }

    class MyRunnable implements Runnable {
        private int id;
        private int start;
        private int end;

        public MyRunnable(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                log.info("------------------线程已经休息了5秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
