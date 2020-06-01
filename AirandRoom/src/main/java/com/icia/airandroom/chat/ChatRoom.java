package com.icia.airandroom.chat;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private String contents;
    private String imgname;
    private int mkey;
    private String cno;
    private String cdate;
    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = name;
        chatRoom.name = name;
        return chatRoom;
    }
	@Override
	public String toString() {
		return "ChatRoom [roomId=" + roomId + ", name=" + name + ", contents=" + contents + ", mkey=" + mkey + ", cno="
				+ cno + ", cdate=" + cdate + "]";
	}
    
    
    
    
    
}