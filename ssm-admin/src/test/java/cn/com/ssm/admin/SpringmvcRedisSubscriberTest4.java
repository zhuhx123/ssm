package cn.com.ssm.admin;

import cn.com.ssm.common.plugin.cache.redis.RedisListener;
import cn.com.ssm.common.plugin.cache.redis.RedisUtil;
import org.junit.*;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 代码配置
 * 测试 订阅
 * Created by Nathy on 2017/12/13.
 */

public class SpringmvcRedisSubscriberTest4 {
    public static final String CHANNEL = "mychannel";
    public static final String CHANNEL2 = "mychannel2";

    public static void main(String[] args){
        test3();
    }


    public static void test1(){
       final Jedis subscriberJedis= RedisUtil.getJedis();
        final  Jedis publisherJedis= RedisUtil.getJedis();
        final JedisPubSub subscriber = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Channel1:" + channel + ",Message:" + message);
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println(pattern + "," + channel + "," + message);
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe: channel[" + channel + "]," + "subscribedChannels[" + subscribedChannels + "]");
            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println(
                        "onUnsubscribe: channel[" + channel + "], " + "subscribedChannels[" + subscribedChannels + "]");
            }

            @Override
            public void onPUnsubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPUnsubscribe: pattern[" + pattern + "]," +

                        "subscribedChannels[" + subscribedChannels + "]");
            }

            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPSubscribe: pattern[" + pattern + "], " +

                        "subscribedChannels[" + subscribedChannels + "]");
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("Subscribing to mychannel,this thread will be block");
                    subscriberJedis.subscribe(subscriber, CHANNEL);
                    System.out.println("subscription ended");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    //    new SpringmvcRedisTest5(publisherJedis,CHANNEL).startPublish();
    //    publisherJedis.close();

   //     subscriber.unsubscribe();
    //    subscriberJedis.close();
    }

    public  static void test2(){
        final Jedis subscriberJedis= RedisUtil.getJedis();
        final RedisListener listener=new RedisListener();
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("Subscribing to mychannel,this thread will be block");
                    subscriberJedis.subscribe(listener, CHANNEL2);
                    System.out.println("subscription ended");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public  static void test3(){
        final Jedis subscriberJedis= RedisUtil.getJedis();
        final RedisListener listener=new RedisListener();
        final RedisListener listener2=new RedisListener();
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("Subscribing to mychannel,this thread will be block2");
                    subscriberJedis.subscribe(listener, CHANNEL,CHANNEL2);
//                    subscriberJedis.subscribe(listener2, CHANNEL);
                    System.out.println("subscription ended2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }







}
