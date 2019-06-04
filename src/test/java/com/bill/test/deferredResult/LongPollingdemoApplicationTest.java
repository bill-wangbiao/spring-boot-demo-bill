//package com.bill.test.deferredResult;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author : wangbiao
// * @version V1.0
// * @Project: spring-boot-demo-bill
// * @Package com.bill.test.deferredResult
// * @Description: TODO
// * @date Date : 2019年06月03日 15:32
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LongPollingdemoApplicationTest {
//
////    @Autowired
////    AsyncFeginService asyncFeginService;
//    /**
//     * 模拟多个浏览器客户端发起长轮询请求，等待testLongPolling测试用例请求通知服务端返回各浏览器的请求结果
//     * @throws Exception
//     */
//    @Test
//    public void contextLoads() throws Exception{
//        ExecutorService executorService= Executors.newFixedThreadPool(4);
//        for (int i=0;i<=3;i++){
//            executorService.execute(()->{
////                String kl=asyncFeginService.longPolling();
//                System.err.println("收到响应：");
//            });
//        }
//        System.in.read();
//    }
//
//    /**
//     * 通知服务端返回上个测试的长轮询结果
//     */
//    @Test
//    public void testLongPolling(){
//        asyncFeginService.returnLongPollingValue();
//    }
//}
