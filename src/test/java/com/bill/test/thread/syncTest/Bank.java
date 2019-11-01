package com.bill.test.thread.syncTest;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread.syncTest
 * @Description: TODO
 * @date Date : 2019年11月01日 10:34
 */
public class Bank {
    private int count =0;//账户余额

    //存钱
    public  void addMoney(int money){
        count +=money;
        System.out.println(System.currentTimeMillis()+"存进："+money);
    }

    //取钱
    public  void subMoney(int money){
        if(count-money < 0){
            System.out.println("余额不足");
            return;
        }
        count -=money;
        System.out.println(+System.currentTimeMillis()+"取出："+money);
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
