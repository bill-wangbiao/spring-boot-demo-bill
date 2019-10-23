package com.bill.test.constans;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.constans
 * @Description: TODO
 * @date Date : 2019年10月22日 18:22
 */
@Slf4j
public class ConstansTest {
    @Test
    public void autoIncr(){
        DataSourceConstans.productId.set(1);
        log.info("productId:"+DataSourceConstans.productId);
        long andIncrement = DataSourceConstans.productId.getAndIncrement();
        log.info("andIncrement:"+andIncrement);
        log.info("increment:"+DataSourceConstans.productId);
    }
}
