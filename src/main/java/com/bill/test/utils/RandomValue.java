package com.bill.test.utils;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.utils
 * @Description: TODO
 * @date Date : 2019年11月07日 12:58
 */
public class RandomValue {
    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
