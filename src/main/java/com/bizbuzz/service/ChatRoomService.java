package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;

public interface ChatRoomService {
  public void saveChatRoom(ChatRoom chatRoom);
  public ChatRoom getChatRoomByMembers(Long fromPartyId,Long topartyId);
  public ChatRoom getChatRoomByChatRoomId(Long Id);
  
  public List<Person> getAllMembersOfChatRoomByChatRoomId(Long chatRoomId);
  public List<Chat> getSortedChatsOfPerson(Person person);
  public List<Chat> getSortedChatsOfPersonWithMembers(Person person);
  public List<Chat> getSortedItemChatsOfPerson(Long personId, Long itemId);
  public List<ChatRoom> getAllNewSortedChatRoomsOfPerson(Person person);
  public List<ChatRoom> getAllNewSortedChatRoomsOfPersonWithMembers(Person person);
 // public List<ChatRoom> getAllItemChatRoomsWithNoCoversation(Long personId, Long itemId);
  
  public void deleteChatRoom(Long chatRoomId);
  
  
}
