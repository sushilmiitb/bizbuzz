package com.bizbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.repository.ChatRepository;
import com.bizbuzz.repository.ChatRoomRepository;


@Service
public class ChatServiceImpl implements ChatService{

  @Autowired
  ChatRepository chatRepository;
  
 
  @Override
  public void saveChat(Chat chat) {
    chatRepository.save(chat);
  }


  @Override
  public List<Chat> getChat(Long senderId) {
    return chatRepository.getChatBySenderId(senderId);  
  }

  @Override
  public List<Chat> getAllChatsByChatRoomId(Long chatRoomId) {
    List<Chat> chats = chatRepository.findChatsByChatRoomId(chatRoomId);
    if(chats==null)
        return null;
    else
        return chats;
  }


/*
  @Override
  public void deleteAllChatByChatRoomId(Long chatRoomId) {
    chatRepository.deleteAllChatsByChatRoomId(chatRoomId);
  }
*/

}
