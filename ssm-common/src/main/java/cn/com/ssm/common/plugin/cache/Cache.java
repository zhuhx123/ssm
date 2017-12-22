package cn.com.ssm.common.plugin.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by Nathy on 2017/12/14.
 */
public interface Cache {
    void put(String key, Object object);

    void remove(String key);

    Object get(String key);

    Jedis getJedis();
}
