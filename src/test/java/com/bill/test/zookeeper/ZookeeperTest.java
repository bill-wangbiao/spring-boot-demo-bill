package com.bill.test.zookeeper;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.zookeeper
 * @Description: TODO
 * @date Date : 2019年06月05日 19:19
 */
@Slf4j
public class ZookeeperTest {
    private static final int CLIENT_PORT=2181;

    @Test
    public void getChildren() throws IOException, KeeperException, InterruptedException {
        String path="/dubbo";
        this.output(0,path);
    }

    private  void output(int deep, String path) throws IOException, KeeperException, InterruptedException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT,
                600000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.err.println("已经触发了" + event.getType() + "事件！");
            }
        });
        //查询子节点
        List<String> list = zk.getChildren(path,false);
        if(list == null || list.isEmpty()) {
            if(path.contains("providers")){
                //输出当前路径和数据
                System.err.println(getPrefix(deep)+path+"--"+JSONObject.toJSONString(zk.getData(path,false,null)));
            }

            return;
        }
        //输出子节点
        for(String value : list) {
            String path1=path+"/"+value;
            output(deep+1, path1);
        }
    }

    private static String getPrefix(int i) {
        String str = "----------------------------------------";
        return str.substring(0,i*2); //层级展示处理
    }


    @Test
    public void get() throws IOException, KeeperException, InterruptedException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT,
                600000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.err.println("已经触发了" + event.getType() + "事件！");
            }
        });

        String path="/dubbo/com.xyy.ec.system.business.api.MessageCenterBusinessApi/providers";
        System.err.println(JSONObject.toJSONString(zk.getChildren(path,true,null)));

        zk.close();
    }

}
