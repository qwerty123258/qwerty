package com.icia.airandroom.chat;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
    
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	@Autowired
	HttpSession session;
	
	
	
	private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);		
    	RedisTemplate<String, Object> redisTemplate1 = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
		  ListOperations<String, Object> list=redisTemplate1.opsForList();
		  ChatRoom chatroom=new ChatRoom();
		    int check=chatRoomRepository.checkMessage(message);
		    if(check==0) {
		    	chatRoomRepository.chatMessageReg(message);
		        	
		    }else {

		        chatroom.setRoomId(message.getRoomId());
		        
		        chatroom.setContents(message.getMessage());
		        
		        chatRoomRepository.changeMessage(chatroom);

		    
		    }
		    

		  list.rightPush(message.getRoomId(),message.getSender()+"@"+message.getMessage());
        messagingTemplate.convertAndSend("/sub/chat/room/admin", message);
    
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    

   
    }
    
   
    
}