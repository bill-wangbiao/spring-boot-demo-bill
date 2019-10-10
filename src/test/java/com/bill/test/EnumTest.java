package com.bill.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.MQVersion;
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

    /**
     * 根据某一个序号查询枚举类中对应的枚举对象的name属性
     * @param value
     * @return
     */
    public static String getVersionDesc(int value) {
        int length = MQVersion.Version.values().length;
        if (value >= length) {
            return MQVersion.Version.values()[length - 1].name();
        }
        return MQVersion.Version.values()[value].name();
    }

    /**
     * 根据某一个序号查询枚举类中对应的枚举对象
     * @param value
     * @return
     */
    public static MQVersion.Version value2Version(int value) {
        int length = MQVersion.Version.values().length;
        if (value >= length) {
            return MQVersion.Version.values()[length - 1];
        }
        return MQVersion.Version.values()[value];
    }

    @Test
    public void hashMap(){
        HashMap hashMap=new HashMap();
        hashMap.put("key","value");
        hashMap.put("key1","value1");
        log.info("echo:"+hashMap);
    }

    @Test
    public void outputOrdinalValue(){
        int version=Color.GREEN.ordinal();
        log.info("version value:"+version);
        log.info("Color.RED.ordinal() value:"+Color.RED.ordinal());
        log.info("Color.YELLOW.ordinal() value:"+Color.YELLOW.ordinal());
        log.info("Color.BLACK.ordinal() value:"+Color.BLACK.ordinal());

        log.info("输出枚举类Color中序号为0的枚举对象："+Color.values()[0]);
        log.info("输出枚举类Color中序号为0的枚举对象name属性："+Color.values()[0].name());

        log.info("输出rocketmq的version desc:"+getVersionDesc(5));
        log.info("输出rocketmq的version desc:"+getVersionDesc(7));
    }
}
