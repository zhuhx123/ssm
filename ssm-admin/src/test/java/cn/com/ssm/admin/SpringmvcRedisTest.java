package cn.com.ssm.admin;

import cn.com.ssm.common.plugin.cache.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置测试
 * 测试redis RedisTemplate模板
 * Created by Nathy on 2017/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:configs/spring/applicationContext.xml"})
public class SpringmvcRedisTest {
//    private static ApplicationContext applicationContext;
//
//    static {
//        applicationContext = new ClassPathXmlApplicationContext("classpath:configs/spring/applicationContext.xml");
//    }
//    @Autowired
//    RedisTemplate redisTemplate;

    @Autowired
    RedisService redisService;


    @Test
    public void redisGet() {
       // System.out.println(applicationContext);
      //  RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        //redisTemplate.renameIfAbsent("abc", "bbb");//如果key=abc存在，则将key修改为bbb
//        System.out.println(redisTemplate.opsForValue().get("table-1000013admin"));
//        Map<String,Object> map=(Map)redisTemplate.opsForValue().get("table-1000013admin");
//        System.out.println(map.get("name"));

    }



    @Test
    public void redisGet2() {
        // System.out.println(applicationContext);
        //  RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        //redisTemplate.renameIfAbsent("abc", "bbb");//如果key=abc存在，则将key修改为bbb
        System.out.println(redisService.get("table-1000013admin"));
        Map<String,Object> map=(Map)redisService.get("table-1000013admin");
        System.out.println(map.get("name"));
    }

    @Test
    public void redisGet3() {
        // System.out.println(applicationContext);
        //  RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        //redisTemplate.renameIfAbsent("abc", "bbb");//如果key=abc存在，则将key修改为bbb
        System.out.println(redisService.get("table-111"));
    //    Map<String,Object> map=(Map)redisService.get("11");
      //  System.out.println(map.get("11"));
    }


    @Test
    public void redisPut() {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("11",11);
        map.put("22",12);
        redisService.put("table-11",map);
        Map<String,Object> map1=new HashMap<String, Object>();
        map1=(Map<String, Object>) redisService.get("11");
        System.out.print(map1);
    }





}
