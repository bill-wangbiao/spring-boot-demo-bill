package com.bill.test.exception;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.exception
 * @Description: TODO
 * @date Date : 2019年09月25日 10:25
 */
public class StackOverflowErrorTest {
    public static void main(String[] args){
        System.out.println("output callback:"+callBack(5));
    }

    public static int callBack(int n){
        return callBack(n)*n;
    }
}
