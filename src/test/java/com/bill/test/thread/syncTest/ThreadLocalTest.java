package com.bill.test.thread.syncTest;

import org.junit.Test;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread.syncTest
 * @Description: TODO
 * @date Date : 2019年11月01日 12:26
 */
public class ThreadLocalTest {
    ThreadLocal<Integer> count=new ThreadLocal<>();

    @Test
    public void test(){
        count.set(5);
        System.out.println(count.get());
    }
}
