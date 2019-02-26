package com.bill.test.redis;

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
 * @Description: 预上线查询redis单元测试类
 * @date Date : 2019年02月22日 17:09
 */
public class RedisStageClientTest {
    private static final Logger logger= LoggerFactory.getLogger(RedisStageClientTest.class);
    private static final int DEFAULT_REDIRECTIONS = 5;
    private static final String password_stage="6i9ZL1MkBfoGp4Tq";

    /**
     * db4-stage.redis.ybm100.top:50008,
     * db4-stage.redis.ybm100.top:50009,
     * db4-stage.redis.ybm100.top:50010
     */

    @Test
    public void getStock() throws IOException {
//        Set<HostAndPort> jedisClusterNode = new LinkedHashSet<HostAndPort>();
//        jedisClusterNode.add(new HostAndPort("db4-stage.redis.ybm100.top", 50008));
//        jedisClusterNode.add(new HostAndPort("db4-stage.redis.ybm100.top", 50009));
//        jedisClusterNode.add(new HostAndPort("db4-stage.redis.ybm100.top", 50010));
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(1);
//        JedisCluster jedis = new JedisCluster(jedisClusterNode, 0, 2, DEFAULT_REDIRECTIONS, password_stage, config);

        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        JedisShardInfo shard1 = new JedisShardInfo("db4-stage.redis.ybm100.top", 50008);
        shard1.setPassword(password_stage);
        list.add(shard1);
        JedisShardInfo shard2 = new JedisShardInfo("db4-stage.redis.ybm100.top", 50009);
        shard2.setPassword(password_stage);
        list.add(shard2);
        JedisShardInfo shard3 = new JedisShardInfo("db4-stage.redis.ybm100.top", 50010);
        shard3.setPassword(password_stage);
        list.add(shard3);
        ShardedJedis jedis=new ShardedJedis(list);


        /**普通商品库存**/
        String stockKey = "ec_product_SKU_STOCK_KEY:"+4202;
        String commonKey = "ec_product_STOCK_COMMON_HUBEI:"+4202;
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

