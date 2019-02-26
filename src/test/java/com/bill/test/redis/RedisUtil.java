package com.bill.test.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>简介</p>
 *
 * @author liuyang
 * @company
 * @since 2018年10月11日 上午10:10
 */
public class RedisUtil {

    private static String getKey(){
        // 普通商品库存
//        String key = "ec_product_SKU_STOCK_KEY:%s";
//        String key = "ec_product_STOCK_COMMON_HUBEI:%s";
//        String key = "ec_product_SKU_RELATION_CATEGORY:" + 46943;
//        jedis.set(key, "");
        // 秒杀商品库存
//        String key = "ec_product_SKU_SECKILL_STOCK_KEY:169119:750";
//        String key = "ec_product_SKU_SPECIAL_STOCK_KEY:169115:716";
        // 特价商品库存
        String key = "ec_product_SKU_SPECIAL_STOCK_KEY:%s:1728";
//        String key = "ec_product_SKU_ORDER_SELL_KEY:60541";
        return key;
    }

    /**
     * 获取商品库存
     * @param jedisCluster
     */
    private static void getSkuStockFromRedis(JedisCluster jedisCluster) {
        String key = String.format(getKey(), 42279);
        System.out.println(key + ",库存值-->"+ jedisCluster.get(key));
        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        JedisCluster jedisCluster = getRedisEnvironment("prod");
        // 商品库存
        getSkuStockFromRedis(jedisCluster);
        // 关闭jedis。
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static JedisCluster getRedisEnvironment(String environment){
        // 压测环境
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        String password;
        switch (environment){
            case "test":
                jedisClusterNode.add(new HostAndPort("db2-test.redis.ybm100.top", 50002));
                password = "T9gN1Vn9c500ip2q";
                break;
            case "bench":
                jedisClusterNode.add(new HostAndPort("db2-ec-bench.redis.ybm100.top", 50001));
                jedisClusterNode.add(new HostAndPort("db6-ec-bench.redis.ybm100.top", 50002));
                jedisClusterNode.add(new HostAndPort("db2-ec-bench.redis.ybm100.top", 50003));
                jedisClusterNode.add(new HostAndPort("db6-ec-bench.redis.ybm100.top", 50004));
                jedisClusterNode.add(new HostAndPort("db2-ec-bench.redis.ybm100.top", 50005));
                jedisClusterNode.add(new HostAndPort("db6-ec-bench.redis.ybm100.top", 50006));
                password = "upT21mYeEAgFuhit";
                break;
            case "stage":
                jedisClusterNode.add(new HostAndPort("db4-stage.redis.ybm100.top", 50005));
                jedisClusterNode.add(new HostAndPort("db4-stage.redis.ybm100.top", 50006));
                jedisClusterNode.add(new HostAndPort("db4-stage.redis.ybm100.top", 50007));
                password = "aWzxTbvAaTEXACcb";
                break;
            case "prod":
                jedisClusterNode.add(new HostAndPort("redis06-ec-prod-hd1.ybm100.top", 50001));
                jedisClusterNode.add(new HostAndPort("redis07-ec-prod-hd1.ybm100.top", 50002));
                jedisClusterNode.add(new HostAndPort("redis08-ec-prod-hd1.ybm100.top", 50003));
                jedisClusterNode.add(new HostAndPort("redis09-ec-prod-hd1.ybm100.top", 50004));
                jedisClusterNode.add(new HostAndPort("redis10-ec-prod-hd1.ybm100.top", 50005));
                jedisClusterNode.add(new HostAndPort("redis06-ec-prod-hd1.ybm100.top", 50006));
                jedisClusterNode.add(new HostAndPort("redis07-ec-prod-hd1.ybm100.top", 50007));
                jedisClusterNode.add(new HostAndPort("redis08-ec-prod-hd1.ybm100.top", 50008));
                jedisClusterNode.add(new HostAndPort("redis09-ec-prod-hd1.ybm100.top", 50009));
                jedisClusterNode.add(new HostAndPort("redis10-ec-prod-hd1.ybm100.top", 50010));
                jedisClusterNode.add(new HostAndPort("redis06-ec-prod-hd1.ybm100.top", 50011));
                jedisClusterNode.add(new HostAndPort("redis07-ec-prod-hd1.ybm100.top", 50012));
                jedisClusterNode.add(new HostAndPort("redis08-ec-prod-hd1.ybm100.top", 50013));
                jedisClusterNode.add(new HostAndPort("redis09-ec-prod-hd1.ybm100.top", 50014));
                jedisClusterNode.add(new HostAndPort("redis10-ec-prod-hd1.ybm100.top", 50015));
                password = "5wUqGhYkCa3hPoOw";
                break;
            default:
                jedisClusterNode.add(new HostAndPort("db2-ec-bench.redis.ybm100.top", 50001));
                jedisClusterNode.add(new HostAndPort("db6-ec-bench.redis.ybm100.top", 50002));
                jedisClusterNode.add(new HostAndPort("db2-ec-bench.redis.ybm100.top", 50003));
                jedisClusterNode.add(new HostAndPort("db6-ec-bench.redis.ybm100.top", 50004));
                jedisClusterNode.add(new HostAndPort("db2-ec-bench.redis.ybm100.top", 50005));
                jedisClusterNode.add(new HostAndPort("db6-ec-bench.redis.ybm100.top", 50006));
                password = "upT21mYeEAgFuhit";
                break;
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        return new JedisCluster(jedisClusterNode, 100000, 10000,1, password, config);
    }

}

