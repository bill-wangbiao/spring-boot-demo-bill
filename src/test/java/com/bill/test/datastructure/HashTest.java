package com.bill.test.datastructure;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.datastructure
 * @Description: 散列表测试类
 * @date Date : 2019年04月24日 14:57
 */
@Slf4j
public class HashTest {
    @Test
    public void map(){
        Map<String,Integer> map=new HashMap<String,Integer>();
        for(int i=0;i<50;i++){
            map.put(String.valueOf(i),i);
        }
        int size = map.size();
        log.error("map的长度："+size);
        for(Map.Entry<String,Integer> m:map.entrySet()){

            String key = m.getKey();
            Integer value = m.getValue();
            int hashCode = key.hashCode();
            int mo = hashCode % 16;
            log.info("key:"+
                    key+",value:"+value+",key.hashCode:"+hashCode+",求模："+mo);
        }
    }
}
