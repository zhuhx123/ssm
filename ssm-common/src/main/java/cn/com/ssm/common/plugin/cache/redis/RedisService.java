package cn.com.ssm.common.plugin.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Nathy on 2017/12/14.
 */
@Service
public class RedisService implements RedisDao {
//    @Autowired
//    private RedisTemplate redisTemplate;

    public Object get(String key){
//        return redisTemplate.opsForValue().get(key);
        return null;
    }

    public void put(Object key,Map<String,Object> map) {
//        redisTemplate.opsForHash().putAll(key,map);
    }

    public void remote(Object key) {
//        redisTemplate.opsForHash().delete(key);
    }

}
