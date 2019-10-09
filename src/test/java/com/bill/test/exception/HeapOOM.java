package com.bill.test.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.exception
 * @Description: TODO
 * @date Date : 2019年09月30日 11:05
 */
public class HeapOOM {
    public static void main(String[] args){
        List<OOMObject> list=new ArrayList<>();
        while (true){
//            list.add(new OOMObject());
            System.out.println("死循环输出测试内容");
        }
    }

    static class OOMObject{
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
