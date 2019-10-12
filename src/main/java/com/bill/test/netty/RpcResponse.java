package com.bill.test.netty;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.netty
 * @Description: TODO
 * @date Date : 2019年10月12日 12:05
 */
public class RpcResponse {
    private String id;
    private Object data;
    // 0=success -1=fail
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RpcResponse{" + "id='" + id + '\'' + ", data=" + data + ", status=" + status + '}';
    }

}
