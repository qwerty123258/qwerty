package com.icia.airandroom.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

//import 생략...

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

 private final com.icia.airandroom.chat.ChatRoomRepository chatRoomRepository;

 @Autowired
 HttpSession session;
 // 채팅 리스트 화면
 @GetMapping("/room")
 public String rooms(Model model) {
     return "chat/room";
 }
 // 채팅방 생성
 @PostMapping("/room")
 @ResponseBody
 public ChatRoom createRoom(@RequestParam String name) {
     return chatRoomRepository.createChatRoom(name);
 }
 // 채팅방 입장 화면
 @GetMapping("/room/enter/{roomId}")
 public String roomDetail(Model model, @PathVariable String roomId) {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);	
		RedisTemplate<String, Object> redisTemplate1 = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
		List<Object> list=redisTemplate1.opsForList().range(roomId, 0, -1);
		List<String> list2= new ArrayList<String>();
		List<ChatRoom> chatList = new ArrayList<ChatRoom>();
		
		String index[]=null;
		for(int i=0; i<list.size(); i++) {
			Object obj=list.get(i);
			list2.add(obj.toString());
		}
		for(int i=0; i<list2.size(); i++) {
			ChatRoom chat = new ChatRoom();
			index=list2.get(i).split("@");
			chat.setName(index[0]);
			chat.setContents(index[1]);
			chatList.add(chat);
		}
		if(((String)session.getAttribute("id")).equals("admin")) {
			 model.addAttribute("list", chatList);	
			 		
		}
		 model.addAttribute("roomId", roomId);

	
		return "chat/roomdetail";
 }
 // 특정 채팅방 조회
 @GetMapping("/room/{roomId}")
 @ResponseBody
 public ChatRoom roomInfo(@PathVariable String roomId) {
     return chatRoomRepository.findRoomById(roomId);
 }
 
 @ResponseBody
 @RequestMapping(value="/chatRoomSelect",method=RequestMethod.POST)
 public List<ChatRoom> chatRoomSelect(){
 	List<ChatRoom> list=chatRoomRepository.chatRoomList();
 	return list;
 }
 
 @ResponseBody
 @GetMapping("/chatDelete")
 public String chatDelete(@RequestParam("id") String id) {
    ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
 	RedisTemplate<String, Object> redisTemplate1 = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
	redisTemplate1.opsForValue().getOperations().delete(String.valueOf(id));
	chatRoomRepository.chatDelete(id);
	return "Success";
 }

 
 
}
