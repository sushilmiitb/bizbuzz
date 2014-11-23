package com.bizbuzz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.ChatroomMember;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.repository.ChatRepository;
import com.bizbuzz.repository.ChatRoomRepository;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{

  @Autowired
  ChatRoomRepository chatRoomRepository;
  
  
  public void saveChatRoom(ChatRoom chatRoom) { 
   chatRoomRepository.save(chatRoom); 
  }


  @Override
  public ChatRoom getChatRoomByMembers(Long fromPartyId,Long topartyId) {
    List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomByMembers(fromPartyId,topartyId);
    if(chatRooms==null || chatRooms.size()==0)
      return null;
    return chatRooms.get(0);
  }


  @Override
  public ChatRoom getChatRoomByChatRoomId(Long id) {
    return chatRoomRepository.findOne(id);
  }


  @Override
  public List<Person> getAllMembersOfChatRoomByChatRoomId(Long chatRoomId) {
    List<Person> members = chatRoomRepository.findAllMembersOfChatRoomByChatRoomId(chatRoomId);
    if(members==null) return null;
    else return members;
    
  }


  @Override
  public List<Chat> getSortedChatsOfPerson(Person person) {
    return  chatRoomRepository.findSortedChatsOfPerson(person.getId());
  }
  
  @Override
  @Transactional
  public List<Chat> getSortedChatsOfPersonWithMembers(Person person) {
    List<Chat> chats = chatRoomRepository.findSortedChatsOfPerson(person.getId());
    for (Chat chat : chats) {
      List<ChatroomMember> members = chat.getChatRoom().getMembers();
      for (ChatroomMember chatroomMember : members) {
        Person person1 = (Person)chatroomMember.getMember();
      }
    }
    return chats;
  }
  
  @Override
  public List<Chat> getSortedItemChatsOfPerson(Long personId, Long itemId) {
    return  chatRoomRepository.findSortedItemChatsOfPerson(personId, itemId);
  }
  
  @Override
  public List<ChatRoom> getAllNewSortedChatRoomsOfPerson(Person person) {  
    return chatRoomRepository.findAllNewSortedChatRoomsOfPerson(person.getId());
  }
  
  @Override
  @Transactional
  public List<ChatRoom> getAllNewSortedChatRoomsOfPersonWithMembers(Person person) {  
    List<ChatRoom> chatRooms = chatRoomRepository.findAllNewSortedChatRoomsOfPerson(person.getId());
    for (ChatRoom chatRoom : chatRooms) {
      List<ChatroomMember> chatroomMembers = chatRoom.getMembers();
      for (ChatroomMember chatroomMember : chatroomMembers) {
        Person persontemp = (Person)chatroomMember.getMember();
      }
    }
    return chatRooms;
  }
 /* 
  @Override
  public List<ChatRoom> getAllItemChatRoomsWithNoCoversation(Long personId, Long itemId) {  
    return chatRoomRepository.findAllChatRoomsByNotItemIdAndPersonIdOrderedByPersonName(personId, itemId);
  }
  */
  public void deleteChatRoom(Long chatRoomId) {
    
    chatRoomRepository.delete(chatRoomId);
  }
}
