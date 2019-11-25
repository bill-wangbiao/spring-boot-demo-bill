package com.bill.test;

import com.bill.test.utils.RandomValue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: TODO
 * @date Date : 2019年11月10日 2:14
 */
@Slf4j
public class RandomValueTest {
    @Test
    public void getNum(){
        for(int i=0;i<10;i++){
            int v= RandomValue.getNum(1,3);
            log.info("输出："+v);
        }
    }

    @Test
    public void nextInt(){
        for(int i=0;i<10;i++){
            ThreadLocalRandom current = ThreadLocalRandom.current();
            int m = current.nextInt(0, 3);
            log.info("输出："+m);
        }
    }
}
