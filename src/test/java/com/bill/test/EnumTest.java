package com.bill.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: TODO
 * @date Date : 2019年04月18日 20:31
 */
@Slf4j
public class EnumTest {
    @Test
    public void valueOf(){
        String value="YELLOW";
        Color color = Enum.valueOf(Color.class, value);
        log.info(""+color);
    }

    private enum Color {
        RED, GREEN, BLACK, YELLOW;
    }

    @Test
    public void hashMap(){
        HashMap hashMap=new HashMap();
        hashMap.put("key","value");
        hashMap.put("key1","value1");
        log.info("echo:"+hashMap);
    }
}
