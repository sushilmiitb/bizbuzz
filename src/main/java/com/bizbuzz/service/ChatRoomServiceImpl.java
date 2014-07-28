package com.bizbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.repository.ChatRepository;
import com.bizbuzz.repository.ChatRoomRepository;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{

  @Autowired
  ChatRoomRepository chatRoomRepository;
  
  
  public void saveChatroomMembers(ChatRoom chatRoom) { 
   chatRoomRepository.save(chatRoom); 
  }


  @Override
  public ChatRoom getChatRoomByMembers(Long topartyId,Long fromPartyId) {
    return  chatRoomRepository.findChatRoomByMembers(topartyId,fromPartyId);
    
  }


  @Override
  public ChatRoom getChatRoom(Long id) {
    return chatRoomRepository.findOne(id);
  }


  

  @Override
  public Person getPersonByChatRoomId(Long chatRoomId,Long personId) {
 Person person = chatRoomRepository.findPersonByChatRoomId(chatRoomId,personId);
 if(person==null) return null;
 else return person;
  }


  @Override
  public List<ChatRoom> getSortedChatRoomsOfPerson(Long personId) {
   return chatRoomRepository.findSortedChatRoomsOfPerson(personId);
    
  }
}
