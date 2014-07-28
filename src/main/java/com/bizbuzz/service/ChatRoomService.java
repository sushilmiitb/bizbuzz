package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;

public interface ChatRoomService {
  public void saveChatroomMembers(ChatRoom chatRoom);
  public ChatRoom getChatRoomByMembers(Long toPartyId,Long fromPartyId);
  public ChatRoom getChatRoom(Long Id);
  
  public List<ChatRoom> getSortedChatRoomsOfPerson(Long personId);
  public Person getPersonByChatRoomId(Long chatRoomId,Long personId);
}
