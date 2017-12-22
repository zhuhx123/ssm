package cn.com.ssm.common.plugin.cache.redis;

import cn.com.ssm.common.plugin.cache.Cache;
import com.ivymei.framework.util.ConfigReaderUtil;
import com.ivymei.framework.util.StringUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathy on 2017/12/14.
 */
public class RedisFactory {

    private static Logger logger=Logger.getLogger(RedisFactory.class);

    private static String cacheKeyPrefix="table";//初始化为table，如有需要，则改为各库名称。

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

    public static Cache getRedisDataCache(){
        init();
        return redisDataCache;
    }

    public static void  init(){
        if(redisDataCache!=null){
            return ;
        }
        try {
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
            List<JedisShardInfo> jedisShardInfos = getJedisShardInfo();
            JedisPoolConfig jedisPoolConfig = getShardedJedisPool();
            ShardedJedisPool jedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);
            redisDataCache = new RedisDataCache(jedisPool, cacheKeyPrefix);
        }catch (Exception e){
            logger.info(e);
        }
    }

    private static List<JedisShardInfo> getJedisShardInfo(){
        List<JedisShardInfo> list=new ArrayList<JedisShardInfo>();
        JedisShardInfo shardInfo=new JedisShardInfo(host,port);
        if(!StringUtil.isNullOrBlank(password)){
            shardInfo.setPassword(password);
        }
        list.add(shardInfo);
        return list;
    }

    private static JedisPoolConfig getShardedJedisPool(){
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestOnReturn(testOnReturn);
        return poolConfig;
    }
}
