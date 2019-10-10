package com.bill.test.system;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.system
 * @Description: TODO
 * vm options配置：-Dmyname="hello world"
 * Program arguments配置：这个配置对应的是args数组，多个配置以空格隔开，
 * 另外：可以通过Options来获取参数配置，详见OptionsTest.java
 * @date Date : 2019年10月10日 17:11
 */
public class SystemTest {
    public static void main(String[] args){
        System.out.println("自定义变量："+ Arrays.toString(args));
        Properties properties = System.getProperties();
        System.out.println("jvm变量："+ JSONObject.toJSONString(properties));
        Map<String, String> getenv = System.getenv();
        System.out.println("系统变量："+ JSONObject.toJSONString(getenv));
        String key="NO_PROXY";
        if(null !=properties){
            key=properties.stringPropertyNames().iterator().next();
        }
        System.out.println("输出key:"+key);
        System.out.println("user.dir:"+System.getProperty("user.dir"));
        System.out.println("System.getProperty:"+System.getProperty(key));
        if(getenv.size()>0){
            key=getenv.keySet().iterator().next();
        }
        System.out.println("System.getenv:"+System.getenv(key));
    }
}
