package cn.com.ssm.common.plugin.cache.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by Nathy on 2017/12/15.
 */
public class RedisListener extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.print("channel:"+channel+"==========="+"message:"+message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}
