package com.bill.test.constans;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.constans
 * @Description: TODO
 * @date Date : 2019年10月22日 18:20
 */
public class DataSourceConstans {
    public static String DATA_SOURCE_SESSION_NAME = "DATA_SOURCE_NUMBER";

    public static volatile AtomicLong productId = new AtomicLong(10144042);
}
