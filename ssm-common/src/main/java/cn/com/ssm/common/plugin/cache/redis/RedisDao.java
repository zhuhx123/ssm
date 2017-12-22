package cn.com.ssm.common.plugin.cache.redis;

import java.util.Map;

/**
 * Created by Nathy on 2017/12/14.
 */
public interface RedisDao {
     Object get(String key);

     void put(Object key,Map<String,Object> map);

    void remote(Object key);
}
