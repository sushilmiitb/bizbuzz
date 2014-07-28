package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;

public interface ChatService {
  public void saveMessage(Chat chat);
  public List<Chat> getChat(Long senderId);
  public List<Chat> getAllChats(Long chatRoomId);
 public List<ChatRoom> getAllSortedChatRooms(); 
  
}
