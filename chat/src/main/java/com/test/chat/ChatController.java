package com.test.chat;

import com.test.chat.ChatMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import com.test.chat.ChatRoomController;

@RequiredArgsConstructor
@Controller
public class ChatController {
	
	@Autowired
	private ChatRoomController crc;

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
		if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
		    message.setMessage(message.getSender() + "님이 입장하셨습니다.");
		}
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
