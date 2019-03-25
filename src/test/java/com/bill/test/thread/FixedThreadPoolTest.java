package com.bill.test.thread;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: 固定线程池单元测试类
 *  创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * @date Date : 2019年03月16日 13:30
 */
@Slf4j
public class FixedThreadPoolTest {

    public static void main(String [] args){
        try {
            /**固定线程池**/
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<600000;i++){
                list.add(i);
            }
            int size=list.size();
            int pool=size/30+1;
            ExecutorService service = Executors.newFixedThreadPool(pool);
            List<String> listStr=new ArrayList<>();
            for(int i=0;i<pool;i++){
                /**在未来某个时间执行给定的命令**/
                int start=i*30;
                int end=(i+1)*30;
                List<Integer> listSub=list.subList(start,end>size?size:end);
                log.info("截取的数据长度："+listSub.size());
                log.info("截取的数据："+JSONObject.toJSONString(listSub));
                service.execute(new ListSplit(listSub,listStr));
            }
            /**关闭启动线程**/
            service.shutdown();
            /**等待子线程结束，再继续执行下面的代码**/
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            log.info("listStr的长度："+listStr.size());
            log.info("all thread complete,输出listStr:"+ JSONObject.toJSONString(listStr));
        } catch (Exception e) {
            log.error("执行异常：",e);
        }
    }

    static class ListSplit implements Runnable{
        private static final Object lock = new Object();
        List<Integer> listSub;
        List<String> listStr;
        public ListSplit(List<Integer> listSub, List<String> listStr) {
            this.listSub = listSub;
            this.listStr = listStr;
        }
        @Override
        public void run() {
            for(Integer i:listSub){
                synchronized(lock){
                    listStr.add(String.valueOf(i));
                }
            }
        }
    }
}
