package com.bill.test.netty;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.netty
 * @Description: TODO
 * @date Date : 2019年10月12日 12:04
 */
public class RpcRequest {
    private String id;
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "RpcRequest{" + "id='" + id + '\'' + ", data=" + data + '}';
    }
}
