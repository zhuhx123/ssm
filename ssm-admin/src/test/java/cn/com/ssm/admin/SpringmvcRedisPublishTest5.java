package cn.com.ssm.admin;

import cn.com.ssm.common.plugin.cache.redis.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * 代码配置
 *测试 发布
 * Created by Nathy on 2017/12/13.
 */

public class SpringmvcRedisPublishTest5 {


    public static void main(String[] args) {
        Jedis publisherJedis = RedisUtil.getJedis();
        String CHANNEL = "mychannel";
        publisherJedis.publish(CHANNEL, "222233");
    }


    public static void startPublish() {
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            while(true){
//                System.out.println("请输入message:");
//                String line = reader.readLine();
//                if(!"quit".equals(line)){
//                    publisherJedis.publish(channel, line);
//                }else{
//                    break;
//                }
//            }
//            publisherJedis.publish(channel, "2222222222");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
