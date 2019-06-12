package com.bill.test.zookeeper;

import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.zookeeper
 * @Description: TODO zookeeper znode递归删除
 * @date Date : 2019年06月05日 21:15
 */
public class RmrDemo {
    private static final String connectString = "localhost:2181";

    private static final int sessionTimeout = 600000;

    private static ZooKeeper zookeeper = null;

    /**
     * main函数
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String path="/dubbo";
        ZooKeeper zk = getZookeeper();
        List<String> children = zk.getChildren(path, false);
        if(!CollectionUtils.isEmpty(children)){
            System.out.println("getChildren-->"+ JSONObject.toJSONString(children));
        }
        zk.close();


        //调用rmr,删除所有目录
        rmr(path);

        ZooKeeper zk1 = getZookeeper();
        List<String> children1 = zk.getChildren(path, false);
        if(!CollectionUtils.isEmpty(children1)){
            System.out.println("getChildren-->"+ JSONObject.toJSONString(children1));
        }
        zk1.close();
    }

    /**
     * 递归删除 因为zookeeper只允许删除叶子节点，如果要删除非叶子节点，只能使用递归
     * @param path
     * @throws IOException
     */
    public static void rmr(String path) throws Exception {
        ZooKeeper zk = getZookeeper();
        //获取路径下的节点
        List<String> children = zk.getChildren(path, false);
        for (String pathCd : children) {
            //获取父节点下面的子节点路径
            String newPath = "";
            //递归调用,判断是否是根节点
            if (path.equals("/")) {
                newPath = "/" + pathCd;
            } else {
                newPath = path + "/" + pathCd;
            }
            rmr(newPath);
        }
        //删除/dubbo节点
        if (path != null && path.trim().startsWith("/dubbo")) {
            zk.delete(path, -1);
            //打印删除的节点路径
            System.out.println("被删除的节点为：" + path);
        }

        zk.close();
    }

    /**
     * 获取Zookeeper实例
     * @return
     * @throws IOException
     */
    public static ZooKeeper getZookeeper() throws IOException {
        zookeeper = new ZooKeeper(connectString, sessionTimeout, new MyWatch());
        return zookeeper;
    }

}
