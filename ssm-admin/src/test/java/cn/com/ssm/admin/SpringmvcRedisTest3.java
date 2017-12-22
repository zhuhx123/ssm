package cn.com.ssm.admin;

import cn.com.ssm.common.plugin.cache.redis.RedisFactory;
import cn.com.ssm.common.plugin.cache.redis.RedisUtil;
import com.ivymei.framework.util.SerializeUtil;
import org.junit.Test;

/**
 * 代码配置
 * 测试 ShardedJedisPool连接池
 * Created by Nathy on 2017/12/13.
 */

public class SpringmvcRedisTest3 {

    @Test
    public void get() {
        System.out.print(RedisFactory.getRedisDataCache().get("table-1000013admin"));
    }

    @Test
    public void put() {
        RedisFactory.getRedisDataCache().put("22", 22);
    }

    @Test
    public void remove() {
        RedisFactory.getRedisDataCache().remove("table-111");
    }


    @Test
    public void getByJedis() {
        System.out.print(SerializeUtil.unserialize(RedisUtil.getJedis().get("table-1000013admin".getBytes())));
    }


}
