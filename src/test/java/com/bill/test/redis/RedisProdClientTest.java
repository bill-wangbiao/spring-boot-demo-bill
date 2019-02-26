package com.bill.test.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.redis
 * @Description: 线上redis单元测试类
 * @date Date : 2019年02月22日 15:58
 */
public class RedisProdClientTest {
    private static final Logger logger= LoggerFactory.getLogger(RedisProdClientTest.class);
    private static final int DEFAULT_REDIRECTIONS = 1;
    private static final String password_prod="5wUqGhYkCa3hPoOw";

    @Test
    public void getStock(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(30);
        config.setMaxWaitMillis(2000);
        List<JedisShardInfo> jedisClusterNode = new ArrayList<JedisShardInfo>();
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("redis01-ec-prod-hd1.ybm100.top", 50001);
        jedisShardInfo1.setPassword(password_prod);
        jedisClusterNode.add(jedisShardInfo1);

//        ShardedJedisPool pool = new ShardedJedisPool(config, jedisClusterNode);
//        ShardedJedis jedis = pool.getResource();
        ShardedJedis jedis=new ShardedJedis(jedisClusterNode);
        /**普通商品库存**/
        String stockKey = "ec_product_SKU_STOCK_KEY:"+36941;
        String commonKey = "ec_product_STOCK_COMMON_HUBEI:"+36941;
        /**秒杀商品库存**/
        String seckillKey = "ec_product_SKU_SECKILL_STOCK_KEY:"+758+":"+175113;
        /**特价商品库存**/
        String specialKey = "ec_product_SKU_SPECIAL_STOCK_KEY:"+1728+":"+36951;
        logger.info("普通商品库存："+jedis.get(stockKey));
        logger.info("湖北普通商品库存："+jedis.get(commonKey));
        logger.info("秒杀商品库存："+jedis.get(seckillKey));
        logger.info("特价商品库存："+jedis.get(specialKey));
        jedis.close();
        jedis.disconnect();
//        pool.destroy();
    }

    @Test
    public void testGet() throws IOException {
        Set<HostAndPort> jedisClusterNode = new LinkedHashSet<HostAndPort>();
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

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        JedisCluster jedis = new JedisCluster(jedisClusterNode, 100000, 10000, DEFAULT_REDIRECTIONS, password_prod, config);
        /**普通商品库存**/
        String stockKey = "ec_product_SKU_STOCK_KEY:"+65876;
        String commonKey = "ec_product_STOCK_COMMON_HUBEI:"+65876;
        /**秒杀商品库存**/
        String seckillKey = "ec_product_SKU_SECKILL_STOCK_KEY:"+758+":"+175113;
        /**特价商品库存**/
        String specialKey = "ec_product_SKU_SPECIAL_STOCK_KEY:"+1728+":"+36951;
        logger.info("普通商品库存："+jedis.get(stockKey));
        logger.info("湖北普通商品库存："+jedis.get(commonKey));
        logger.info("秒杀商品库存："+jedis.get(seckillKey));
        logger.info("特价商品库存："+jedis.get(specialKey));
        jedis.close();
    }

}
