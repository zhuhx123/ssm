package cn.com.ssm.common.plugin.cache.redis;

import com.ivymei.framework.util.ConfigReaderUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * redis订阅发布模式
 * Created by Nathy on 2017/12/13.
 */
public class RedisUtil {
    private static String cacheKeyPrefix = "table";//初始化为table，如有需要，则改为各库名称。

    private static RedisDataCache redisDataCache;
    private static Integer maxActive;  //最大分配的对象数
    private static Integer maxIdle;   //最大能够保持idel(空闲)状态的对象数
    private static Integer minIdle;
    private static Integer maxWait; //当池内没有返回对象时，最大等待时间
    private static Boolean testOnBorrow;  //当调用borrow Object方法时，是否进行有效性检查
    private static Boolean testOnReturn;
    private static String host;
    private static String port;
    private static String password;
    public static JedisPool pool=null;

    private static void init() {
        if (pool != null) {
            return;
        }
        ConfigReaderUtil.init("configs/redis1");
        maxActive = Integer.parseInt(ConfigReaderUtil.getValue("spring.redis.pool.maxActive"));
        maxIdle = Integer.parseInt(ConfigReaderUtil.getValue("spring.redis.pool.maxIdle"));
        minIdle = Integer.parseInt(ConfigReaderUtil.getValue("spring.redis.pool.minIdle"));
        maxWait = Integer.parseInt(ConfigReaderUtil.getValue("spring.redis.pool.maxWait"));
        testOnBorrow = Boolean.parseBoolean(ConfigReaderUtil.getValue("spring.redis.pool.testOnBorrow"));
        testOnReturn = Boolean.parseBoolean(ConfigReaderUtil.getValue("spring.redis.pool.testOnReturn"));
        host = ConfigReaderUtil.getValue("spring.redis.host");
        port = ConfigReaderUtil.getValue("spring.redis.port");
        password = ConfigReaderUtil.getValue("spring.redis.password");
        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();
        pool = getJedisPool(jedisPoolConfig);
    }

    private static JedisPool getJedisPool(JedisPoolConfig jedisPoolConfig) {
        JedisPool shardInfo = new JedisPool(jedisPoolConfig, host, Integer.parseInt(port), 3000,password);
        return shardInfo;
    }

    public static Jedis getJedis(){
        init();
        return pool.getResource();
    }

    public void subscribe(JedisPubSub jedisPubSub, String... channels){
        Jedis jedis=getJedis();
        try {
            jedis.subscribe(jedisPubSub,channels);
        }catch (Exception e){

        }finally {
            close(jedis);
        }
    }

    public void publish(String channel,String message){
        Jedis jedis=getJedis();
        try {
            jedis.publish(channel,message);
        }catch (Exception e){

        }finally {
            close(jedis);
        }
    }


    public void close(Jedis jedis){
        if(jedis!=null){
            pool.returnResource(jedis);
        }
    }


    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestOnReturn(testOnReturn);
        return poolConfig;
    }
}
