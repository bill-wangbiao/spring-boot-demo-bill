package com.bill.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: 单元测试类
 * @date Date : 2019年03月18日 22:33
 */
@Slf4j
public class BillTest {
    @Test
    public void subStr(){
        String branchCode="XS420000";
        if(branchCode.contains("XS")){
            String s = branchCode.substring(2, branchCode.length());
            log.info("输出s:"+s);
            String substring = branchCode.substring(2);
            log.info("输出substring："+substring);
        }
    }
}
