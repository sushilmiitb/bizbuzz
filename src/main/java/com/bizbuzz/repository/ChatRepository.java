package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.dto.NoOfUnreadMessagesWithPersonIdDTO;
import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat,Long>{

  @Query("select c from "
      + "Chat c inner join c.sender p "
      + "where p.id=?1" )
  List<Chat> findAllChatsBySenderId(Long senderId);
  
  @Query("select c from "
      + "Chat c inner join c.chatRoom cr "
      + "where cr.id=?1" )
  List<Chat> findChatsByChatRoomId(Long chatRoomId);
 
  /*
  @Query("delete from Chat c inner join c.chatRoom cr "
      + "where cr.id=?1")
  void deleteAllChatsByChatRoomId(Long chatRoomid);
  */
  @Query("select c from "
      + "Chat c inner join c.chatRoom cr inner join c.item i "
      + "where cr.id=?1 and i.id=?2") 
  List<Chat> findAllChatsByChatRoomIdAndItemId(Long chatRoomId,Long itemId);
  
  
  @Query("select c from "
      +"Chat c inner join c.chatRoom cr inner join c.item i " 
      +"where cr.id=?1 and i.id is not null order by c.createdAt desc")
  List<Chat> findChatsByChatRoomIdAndItemIdNotNull(Long chatRoomId);
  
  //This is for patricular chatroom 
  @Query("select count(c) from Chat c inner join c.sender s inner join c.chatRoom cr inner join cr.members cms " +
  		"where cr.id=?1 and s.id<>?2 and c.createdAt>cms.lastAccess")
  Long findCountOfNewIncomingChats(Long chatRoomId,Long senderId);
/*  
  @Query("select count(c) from Person p inner join p.chatrooms crms inner join crms.chatroom cr inner join cr.chats c " +
  		"inner join cr.members ms inner join ms.member m " +
  		"where p.id=?1 and m.id<>?1 and c.createdAt>crms.lastAccess group by cr.id ")
  List<Long> findCountOfNewIncomingChatsOfPersonForAllChatroom(Long senderId);
  */
 
  @Query("select m.id,count(c) from Person p inner join p.chatrooms crms inner join crms.chatroom cr inner join cr.chats c " +
      "inner join cr.members ms inner join ms.member m " +
      "where p.id=?1 and m.id<>?1 and c.createdAt>crms.lastAccess group by cr.id ")
  List<Object[]> findCountOfUnreadChatsOfPersonForAllChatroom(Long senderId);
 
}  
