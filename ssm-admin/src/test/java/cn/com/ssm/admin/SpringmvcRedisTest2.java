package cn.com.ssm.admin;

import com.ivymei.framework.util.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置测试
 * 测试 ShardedJedisPool连接池
 * Created by Nathy on 2017/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:configs/spring/applicationContext.xml"})
public class SpringmvcRedisTest2 {


    @Autowired
    ShardedJedisPool shardedJedisPool;

    @Test
    public void redisGetPool() {
        ShardedJedis jedis= shardedJedisPool.getResource();
        String key="table-1000013admin";
        byte[] bytes=jedis.get(key.getBytes());
        System.out.print(SerializeUtil.unserialize(bytes));
    }







}
