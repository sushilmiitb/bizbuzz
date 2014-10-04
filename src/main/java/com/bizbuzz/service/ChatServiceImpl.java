package com.bizbuzz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.bizbuzz.dto.NoOfNewMessagesWithPersonIdDTO;
import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Item;
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
    return chatRepository.findAllChatsBySenderId(senderId);  
  }

  @Override
  public List<Chat> getAllChatsByChatRoomId(Long chatRoomId) {
    List<Chat> chats = chatRepository.findChatsByChatRoomId(chatRoomId);
    if(chats==null)
        return null;
    else
        return chats;
  }


  @Override
  public List<Chat> getAllChatsByChatRoomIdAndItemId(Long chatRoomId,Long itemId) {
    List<Chat> chats = chatRepository.findAllChatsByChatRoomIdAndItemId(chatRoomId,itemId);
    if(chats==null)
        return null;
    else
        return chats;
  }


  @Override
  public List<Chat> getChatsByChatRoomIdAndItemIdNotNull(Long chatRoomId) {
    return chatRepository.findChatsByChatRoomIdAndItemIdNotNull(chatRoomId);
  }
  
  public Long getCountOfNewIncomingChats(Long chatRoomId, Long senderId) {
    return chatRepository.findCountOfNewIncomingChats(chatRoomId, senderId);
  }

  public List<NoOfNewMessagesWithPersonIdDTO> getCountOfNewIncomingChatsOfPersonForAllChatroom(Long senderId) {
    List<Object[]> objectList = chatRepository.findCountOfNewIncomingChatsOfPersonForAllChatroom(senderId);
    if(objectList==null) return null;
    else{
      List<NoOfNewMessagesWithPersonIdDTO> noOfNewMessageslist = new ArrayList<NoOfNewMessagesWithPersonIdDTO>();
      for(Object[] arr : objectList) {
        NoOfNewMessagesWithPersonIdDTO noOfNewMessagesWithPersonIdDTO = new NoOfNewMessagesWithPersonIdDTO();
        noOfNewMessagesWithPersonIdDTO.setPersonId((Long)arr[0]);
        noOfNewMessagesWithPersonIdDTO.setNoOfNewMessages((Long)arr[1]);
        noOfNewMessageslist.add(noOfNewMessagesWithPersonIdDTO);
      }
      return noOfNewMessageslist;
   }
  }

/*
  @Override
  public void deleteAllChatByChatRoomId(Long chatRoomId) {
    chatRepository.deleteAllChatsByChatRoomId(chatRoomId);
  }
*/

}
