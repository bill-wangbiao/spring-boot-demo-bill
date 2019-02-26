package com.bill.test.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.bean
 * @Description: spring boot 整合JMX暴露的MBean
 * @date Date : 2019年02月26日 13:57
 */
@Component
@ManagedResource(objectName = "com.bill.test.bean:type=SimpleBean",description = "暴露的MBean，SimpleBean")
public class SimpleBean {
    private Long id;
    private String name;
    private int age;

    public SimpleBean(){

    }

    public SimpleBean(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @ManagedAttribute(description = "暴露的主键id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManagedAttribute(description = "暴露的姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManagedAttribute(description = "暴露的年龄")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManagedOperation(description = "暴露的方法，display")
    public String display(){
        return JSONObject.toJSONString(new SimpleBean(1L,"bill",18));
    }
}
