package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;

public interface ChatRoomService {
  public void saveChatRoom(ChatRoom chatRoom);
  public ChatRoom getChatRoomByMembers(Long toPartyId,Long fromPartyId);
  public ChatRoom getChatRoom(Long Id);
  
  public List<Person> getAllMembersOfChatRoomByChatRoomId(Long chatRoomId);
  public List<Chat> getSortedChatsOfPerson(Person person);
  public List<ChatRoom> getAllNewSortedChatRoomsOfPerson(Person person);
  
  public void deleteChatRoom(Long chatRoomId);
  
  
}
