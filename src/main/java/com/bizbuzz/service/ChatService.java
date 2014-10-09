package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.dto.NoOfUnreadMessagesWithPersonIdDTO;
import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Item;

public interface ChatService {
  public void saveChat(Chat chat);
  public List<Chat> getChat(Long senderId);
  public List<Chat> getAllChatsByChatRoomId(Long chatRoomId);
  public List<Chat> getChatsByChatRoomIdAndItemIdNotNull(Long chatRoomId);
 // public void deleteAllChatByChatRoomId(Long chatRoomId);
  public List<Chat> getAllChatsByChatRoomIdAndItemId(Long chatRoomId,Long itemId);
  public Long getCountOfNewIncomingChats(Long chatRoomId,Long senderId);
 // public List<Long> getCountOfNewIncomingChatsOfPersonForAllChatroom(Long senderId);
  public List<NoOfUnreadMessagesWithPersonIdDTO> getCountOfUnreadChatsOfPersonForAllChatroom(Long senderId);
}
