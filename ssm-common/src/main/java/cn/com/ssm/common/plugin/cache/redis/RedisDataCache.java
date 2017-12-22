package cn.com.ssm.common.plugin.cache.redis;


import cn.com.ssm.common.plugin.cache.Cache;
import com.ivymei.framework.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by Nathy on 2017/12/14.
 */
public class RedisDataCache implements Cache {
    private  ShardedJedisPool shardedJedisPool;
    private  String  cacheKeyPrefix;

    public RedisDataCache(ShardedJedisPool shardedJedisPool,String key){
        this.shardedJedisPool=shardedJedisPool;
        this.cacheKeyPrefix=key;
    }


    public Jedis getJedis(){
        return null;
    }

    public void put(String key, Object object) {
        ShardedJedis jedis=null;
        key=cacheKeyPrefix+"_"+key;
        jedis=shardedJedisPool.getResource();
        jedis.set(key.getBytes(), SerializeUtil.serialize(object));
    }

    public void remove(String key) {
        ShardedJedis jedis=null;
        jedis=shardedJedisPool.getResource();
        jedis.del(key.getBytes());
    }

    public Object get(String key) {
        ShardedJedis jedis=null;
        jedis=shardedJedisPool.getResource();
        byte[] bytes=jedis.get(key.getBytes());
        return SerializeUtil.unserialize(bytes);
    }
}
