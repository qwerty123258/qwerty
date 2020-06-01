package com.icia.airandroom.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageSubscriber implements MessageListener {
	   private final RedisTemplate redisTemplate = new RedisTemplate();
	   
    public static List<String> messageList = new ArrayList<String>();
 
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
               
    }
}
