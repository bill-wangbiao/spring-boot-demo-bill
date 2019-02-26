package com.bill.test.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.redis
 * @Description: redis单元测试类
 * @date Date : 2019年02月22日 14:10
 */
public class RedisClientTest {
    private static final Logger logger= LoggerFactory.getLogger(RedisClientTest.class);

    private static  final String url_dev="redis://db1-dev.redis.ybm100.top:50001";
    private static final String password_dev="mBgoJeKhbuRrsftT";

    private static  final String url_test="redis://db2-test.redis.ybm100.top:50002";
    private static final String password_test="T9gN1Vn9c500ip2q";

    /**db4-stage.redis.ybm100.top:50008,db4-stage.redis.ybm100.top:50009,db4-stage.redis.ybm100.top:50010**/
    private static  final String url_stage="redis://db4-stage.redis.ybm100.top:50008";
    private static final String password_stage="6i9ZL1MkBfoGp4Tq";

    /**redis01-ec-prod-hd1.ybm100.top:50001,redis02-ec-prod-hd1.ybm100.top:50002,redis03-ec-prod-hd1.ybm100.top:50003,
     * redis04-ec-prod-hd1.ybm100.top:50004,redis05-ec-prod-hd1.ybm100.top:50005,redis01-ec-prod-hd1.ybm100.top:50006,
     * redis02-ec-prod-hd1.ybm100.top:50007,redis03-ec-prod-hd1.ybm100.top:50008,redis04-ec-prod-hd1.ybm100.top:50009,
     * redis05-ec-prod-hd1.ybm100.top:50010,redis01-ec-prod-hd1.ybm100.top:50011,redis02-ec-prod-hd1.ybm100.top:50012,
     * redis03-ec-prod-hd1.ybm100.top:50013,redis04-ec-prod-hd1.ybm100.top:50014,redis05-ec-prod-hd1.ybm100.top:50015,
     * redis11-ec-prod-hd1.ybm100.top:50001,redis12-ec-prod-hd1.ybm100.top:50002,redis13-ec-prod-hd1.ybm100.top:50003,
     * redis11-ec-prod-hd1.ybm100.top:50004,redis12-ec-prod-hd1.ybm100.top:50005,redis13-ec-prod-hd1.ybm100.top:50006,
     * redis11-ec-prod-hd1.ybm100.top:50007,redis12-ec-prod-hd1.ybm100.top:50008,redis13-ec-prod-hd1.ybm100.top:50009,
     * redis11-ec-prod-hd1.ybm100.top:50010,redis12-ec-prod-hd1.ybm100.top:50011,redis13-ec-prod-hd1.ybm100.top:50012,
     * redis11-ec-prod-hd1.ybm100.top:50013,redis12-ec-prod-hd1.ybm100.top:50014,redis13-ec-prod-hd1.ybm100.top:50015**/
    private static  final String url_prod="redis://redis05-ec-prod-hd1.ybm100.top:50005";
    private static final String password_prod="oAgdpxS6200fg1AV";

    @Test
    public void get(){
        JedisShardInfo shardInfo = new JedisShardInfo(url_dev);
        shardInfo.setPassword(password_dev);
        Jedis jedis = new Jedis(shardInfo);
        String set = jedis.set("hello", "world");
        logger.info("jedis.set执行输出："+set);
        String hello = jedis.get("hello");
        logger.info("jedis.get执行输出:"+hello);
        jedis.close();
    }

    @Test
    public void getStock(){
        /**普通商品库存**/
        String stockKey = "ec_product_SKU_STOCK_KEY:"+36941;
        String commonKey = "ec_product_STOCK_COMMON_HUBEI:"+36941;
        /**秒杀商品库存**/
        String seckillKey = "ec_product_SKU_SECKILL_STOCK_KEY:"+758+":"+175113;
        /**特价商品库存**/
        String specialKey = "ec_product_SKU_SPECIAL_STOCK_KEY:"+1728+":"+36951;

        JedisShardInfo shardInfo = new JedisShardInfo(url_test);
        shardInfo.setPassword(password_test);
        Jedis jedis = new Jedis(shardInfo);
        logger.info("普通商品库存："+jedis.get(stockKey));
        logger.info("湖北普通商品库存："+jedis.get(commonKey));
        logger.info("秒杀商品库存："+jedis.get(seckillKey));
        logger.info("特价商品库存："+jedis.get(specialKey));
    }


}
