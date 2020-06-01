package com.icia.airandroom.chat;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Repository;


@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;
    
    @Autowired
    private HttpSession session;

	@Autowired
	private SqlSessionTemplate sql;
	
    
	
    
    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }
    public ChatRoom createChatRoom(String name) {
    	ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        ChatRoom chatRoomSave=new ChatRoom();
        chatRoomSave.setRoomId(chatRoom.getRoomId());
        chatRoomSave.setName((String)session.getAttribute("id"));
       int check=chatRoomCheck(chatRoom.getRoomId());
       if(check==0) {

        	   chatRoomReg(chatRoomSave);
         	   

       }
    	ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
    	ChannelTopic topic = (ChannelTopic)ctx.getBean("topic");//구독 값			
        return chatRoom;
    }
	private int chatRoomCheck(String roomId) {
		return sql.selectOne("Chat.chatRoomCheck",roomId);
	}
	public List<ChatRoom> chatRoomList() {
		return sql.selectList("Chat.chatRoomList");
	}

	public int chatRoomReg(ChatRoom chat) {
		return sql.insert("Chat.chatRoomReg",chat);
	}
	public int changeMessage(ChatRoom chat) {
		return sql.update("Chat.changeMessage", chat);
	}
	public void chatMessageReg(ChatMessage chatmessage) {
		sql.insert("Chat.chatMessageReg",chatmessage);
	}
	public int checkMessage(ChatMessage message) {
		return sql.selectOne("Chat.checkMessage",message);
	}
	

}