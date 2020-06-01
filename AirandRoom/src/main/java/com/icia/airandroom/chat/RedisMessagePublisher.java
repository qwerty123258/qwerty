package com.icia.airandroom.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MessagePublisher{
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ChannelTopic topic;
 
    public RedisMessagePublisher() { }
 
    public RedisMessagePublisher(StringRedisTemplate redisTemplate, ChannelTopic topic) {
      this.redisTemplate = redisTemplate;
      this.topic = topic;
    }
 
    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
